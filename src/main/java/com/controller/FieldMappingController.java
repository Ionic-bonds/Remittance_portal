package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Api;
import com.model.ApiField;
import com.model.CommonApi;
import com.model.CorporateField;
import com.model.CorporateUser;
import com.model.EverywhereRemit;
import com.model.FinanceNow;
import com.model.PaymentGo;
import com.model.SelectedField;
import com.model.Transaction;
import com.repository.ApiFieldRepository;
import com.repository.ApiRepository;
import com.repository.CorporateFieldRepository;
import com.repository.CorporateUserRepository;
import com.repository.SelectedFieldRepository;
import com.repository.TransactionRepository;
import com.request.FieldMapRequest;
import com.request.SendTransaction;
import com.request.TransactionAuth;
import com.response.TransactOutcome;
import com.response.TransactResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FieldMappingController {
    private final String transactionToken = "UGdsCTmT3AEWmngHyJg9OoWxwSl8Z4";
    @Autowired
    private ApiRepository apiRepository;
    @Autowired
    private ApiFieldRepository apiFieldRepository;
    @Autowired
    private CorporateFieldRepository corporateFieldRepository;
    @Autowired
    private CorporateUserRepository corporateUserRepository;
    @Autowired
    private SelectedFieldRepository selectedFieldRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public Api determineApi(List<Api> apiList, double amount) {
        Api searchApi = null;
        Iterator<Api> iterApi = apiList.iterator();
        while (iterApi.hasNext()) {
            Api currentApi = iterApi.next();
            if (amount > currentApi.getMinAmount() && amount <= currentApi.getMaxAmount()) {
                searchApi = apiRepository.findById(currentApi.getApiId()).orElseThrow(() 
                    -> new ResourceNotFoundException("No Api found with api_id = " + currentApi.getApiId()));
                return searchApi;
            }
        }
        return searchApi;
    }

    // Returns "" if there is not error
    public String checkDataType(String cell, ApiField apiField, String currentHeader) {
        List<SelectedField> selectedFields = selectedFieldRepository.findAllSelectedByApiFieldId(apiField);
        String errorMessage = "";
        String dataType = apiField.getDatatype();

        // Check if the field is a selected field
        if (selectedFields.isEmpty()) {
            // Validation: Return true if cell is a String (Regex Validation - only alphanumeric and symbols)
            if (dataType.equals("String")) {
                // code goes here: 
                

                return errorMessage;
            } 
            // Validation: Return true if cell is able to parse into an Integer
            else if (dataType.equals("Integer")) {
                // code goes here: 
    
    
                return errorMessage;
            }
            // Validation: Return true if cell is able to parse into a Double
            else if (dataType.equals("Double")) {
                // code goes here: 
    
    
                return errorMessage;
            } 
            // Validation: Return true if cell is able to parse into a Date
            else if (dataType.equals("Date")) {
                // code goes here: 
    
                
                return errorMessage;
            }
        // Field is a selected field
        } else {
            errorMessage = String.format("invalid cell input '%s' for '%s' to '%s' [API: %s]", 
                cell, currentHeader, apiField.getApiFieldName(), apiField.getApi().getApiName());
            Iterator<SelectedField> iterSelectedField = selectedFields.iterator();
            // Validation: Check if cell is inside selectedFields
            while (iterSelectedField.hasNext()) {
                SelectedField currentIterSelected = iterSelectedField.next();
                if (currentIterSelected.getSelectedFieldCode().equals(cell)) {
                    errorMessage = "";
                    break;
                } 
            }
        }
        return errorMessage;
    }

    // Can call this method if new access token is needed for a transaction
    public String authSandbox() {
        String url = "https://prelive.paywho.com/api/smu_authenticate";
        RestTemplate restTemplate = new RestTemplate();
        TransactionAuth credentials = new TransactionAuth("test", "123456");

        HttpEntity<TransactionAuth> requestEntity = new HttpEntity<>(credentials);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        String responseString = responseEntity.getBody();
        String returnMessage = "";

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseString);
            JsonNode innerNode = rootNode.get("access_token");
            returnMessage = innerNode.asText();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return returnMessage;
    }

    @PostMapping("/uploadFieldMapping/{corporateUserId}")
    public ResponseEntity<TransactResponse> addFieldMapping(
        @PathVariable("corporateUserId") long corporateUserId, 
        @RequestParam("file") MultipartFile file) {
        
        List<TransactOutcome> transactOutcomeList = new ArrayList<>();
        List<CommonApi> commonApiList = new ArrayList<>();
        List<String> errorList = new ArrayList<>();
        int amountCol = -1;

        CorporateUser corporateUser = corporateUserRepository.findById(corporateUserId).orElseThrow(() 
            -> new ResourceNotFoundException("No Corporate found with corporate_id = " + corporateUserId));
        List<CorporateField> corporateFields = corporateFieldRepository.findAllCorpFieldByUserId(corporateUser);
        int headerRow = corporateUser.getHeaderRow();

        // Generate CorporateField HashMap
        Iterator<CorporateField> iterCorpField = corporateFields.iterator();
        HashMap<String, Set<ApiField>> fieldMapping = new HashMap<>();
        while (iterCorpField.hasNext()) {
            CorporateField currentCorpField = iterCorpField.next();
            Set<ApiField> currentApiFields = currentCorpField.getApiFields();
            String corpFieldName = currentCorpField.getCorporateFieldName();
            // Retrieve amount column
            if (amountCol == -1) {
                Iterator<ApiField> amountIterator = currentApiFields.iterator();
                while (amountIterator.hasNext()) {
                    ApiField amountIter = amountIterator.next();
                    if (amountIter.getApiFieldName().equals("ReceivingAmount") 
                            || amountIter.getApiFieldName().equals("receiving_amount") 
                            || amountIter.getApiFieldName().equals("receivingAmount")) {
                        amountCol = Integer.parseInt(corpFieldName.substring(
                            corpFieldName.lastIndexOf("_") + 1, corpFieldName.length()));
                    }
                }
            }
            fieldMapping.put(corpFieldName, currentApiFields);
        }
        if (amountCol == -1) {
            errorList.add(String.format("Spreadsheet does not have a mapped amount column"));
            TransactResponse TransactResponse = new TransactResponse(null, errorList);
            return new ResponseEntity<>(TransactResponse, HttpStatus.CREATED);
        }
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sh = workbook.getSheetAt(0);
            Row header = sh.getRow(headerRow);
            List<Api> apiList = apiRepository.findAll();
            Iterator<Row> iterApiRow = sh.iterator();
            // Skipping headers row
            for (int i=0; i<headerRow; i++) {
                iterApiRow.next();
            }
            while (iterApiRow.hasNext()) {
                try {
                    int colCounter = 1;
                    Row currentRow = iterApiRow.next();
                    int rowNum = currentRow.getRowNum();
                    CommonApi commonApi = new CommonApi();
                    String errorMessage = "";

                    // Determine which API to instantiate based on the amount
                    Double amount = Double.parseDouble(currentRow.getCell(amountCol - 1).toString());
                    Api searchApi = determineApi(apiList, amount);
                    // Amount matches an API range 
                    if (searchApi != null) {
                        String searchApiName = searchApi.getApiName();
                        if (searchApiName.equals("FinanceNow")) {
                            commonApi = new FinanceNow();
                        } else if (searchApiName.equals("EverywhereRemit")) {
                            commonApi = new EverywhereRemit();
                        } else if (searchApiName.equals("PaymentGo")) {
                            commonApi = new PaymentGo();
                        }
                        // Retrieving the row's cell value
                        Iterator<Cell> iterApiCol = currentRow.iterator();
                        Iterator<Cell> iterHeadGet = header.iterator();
                        while (iterApiCol.hasNext()) {
                            DataFormatter dataFormatter = new DataFormatter();
                            String cell = dataFormatter.formatCellValue(iterApiCol.next());
                            String currentHeader = dataFormatter.formatCellValue(iterHeadGet.next());
                            // Concatenates Excel's column header with colCounter to retrieve mapped fields
                            String searchApiField = currentHeader + "_" + String.valueOf(colCounter++);
                            Set<ApiField> apiFields = fieldMapping.get(searchApiField);
                            // Current header is matched to an API Field
                            if (apiFields != null) {
                                for (ApiField apiField : apiFields) {
                                    // Calls checkDataType method to perform data validation
                                    if (apiField.getApi().getApiName().equals(searchApi.getApiName())) {
                                        String validationOutput = checkDataType(cell, apiField, currentHeader);
                                        if (validationOutput.equals("")) {
                                            String apiFieldName = apiField.getApiFieldName();
                                            commonApi.apiSetter(cell, apiFieldName);
                                        }
                                        // Column has failed data validation
                                        else {
                                            // Validation: 
                                            errorMessage += validationOutput;
                                        }
                                    }
                                }
                            }
                        }
                        if (errorMessage.equals("")) {
                            commonApiList.add(commonApi);
                        } else {
                            errorList.add(String.format("Error row%s: %s", String.valueOf(rowNum+headerRow), errorMessage));
                        }
                    }
                    // Amount is not within the range
                    else {
                        // Validation: 
                        errorList.add(String.format("Error row%s: %s", String.valueOf(rowNum+headerRow), "Amount is not within API range"));
                    }
                // amount column has a non-number value
                } catch (NumberFormatException e) {
                    // Validation: 
                    
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail upload transactions: " + e.getMessage());
        }
        if (errorList.size() == 0) {
            transactOutcomeList = uploadAllTransactions(commonApiList);
            for (TransactOutcome transactOutcome : transactOutcomeList) {
                Transaction transactionRequest = new Transaction();
                transactionRequest.setReferenceId(transactOutcome.getCommonApi().getReferenceId());
                transactionRequest.setOutcome(transactOutcome.getOutcome());
                addTransaction(corporateUserId, transactionRequest);
            }
        }
        TransactResponse TransactResponse = new TransactResponse(transactOutcomeList, errorList);
        return new ResponseEntity<>(TransactResponse, HttpStatus.CREATED);
    }

    public List<TransactOutcome> uploadAllTransactions(List<CommonApi> commonApiList) {
        String url = "https://prelive.paywho.com/api/smu_send_transaction";
        RestTemplate restTemplate = new RestTemplate();
        // Seconds of delay per API call
        int apiDelay = 0;
        List<TransactOutcome> transactOutcomeList = new ArrayList<>();

        // Determine the "api_name" to be sent to Sandbox
        for (CommonApi commonApi : commonApiList) {
            String apiName = "";
            if (commonApi instanceof FinanceNow) {
                apiName = "financenow";
            }
            else if (commonApi instanceof EverywhereRemit) {
                apiName = "everywhereremit";
            } 
            else if (commonApi instanceof PaymentGo) {
                apiName = "paymentgo";
            }
            SendTransaction credentials = new SendTransaction(transactionToken, apiName, commonApi);
            HttpEntity<SendTransaction> requestEntity = new HttpEntity<>(credentials);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
            try {
                // Retrieving the "message" value from Sandbox response
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseEntity.getBody());
                JsonNode innerNode = rootNode.get("message");
                transactOutcomeList.add(new TransactOutcome(commonApi, innerNode.asText(), apiName));
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(apiDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return transactOutcomeList;
    }

    @PostMapping("/addFieldMapping")
    public ResponseEntity<List<ApiField>> addFieldMapping(@RequestBody List<FieldMapRequest> fieldMapRequests) {
        List<ApiField> apiFieldList = new ArrayList<ApiField>();
        for (FieldMapRequest fieldMapRequest : fieldMapRequests) {
            long corporateFieldId = fieldMapRequest.getCorporateFieldId();
            long apiFieldId = fieldMapRequest.getApiFieldId();
            CorporateField corporateField = corporateFieldRepository.findById(apiFieldId).orElseThrow(() 
                -> new ResourceNotFoundException("No Api found with corporate_field_id = " + corporateFieldId));
            ApiField apiField = apiFieldRepository.findById(apiFieldId).orElseThrow(() 
                -> new ResourceNotFoundException("No Api found with api_field_id = " + apiFieldId));
            corporateFieldRepository.save(corporateField);
            apiFieldList.add(apiFieldRepository.save(apiField));
        }
        return new ResponseEntity<>(apiFieldList, HttpStatus.CREATED);
    }

    // @PostMapping("/addFieldMappingOld/{corporateFieldId}")
    // public ResponseEntity<ApiField> addFieldMappingOld(
    //     @PathVariable(value = "corporateFieldId") Long corporateFieldId, 
    //     @RequestBody ApiField apiFieldRequest) {

    //     ApiField apiField = corporateFieldRepository.findById(corporateFieldId).map(corporateField -> {
    //         long apiFieldId = apiFieldRequest.getApiFieldId();
    //         // ApiField exists
    //         if (apiFieldId != 0L) {
    //             ApiField _apiField = apiFieldRepository.findById(apiFieldId)
    //                 .orElseThrow(() -> new ResourceNotFoundException("No Api Field found with api_field_id = " + apiFieldId));
    //             corporateField.addApiField(_apiField);
    //             corporateFieldRepository.save(corporateField);
    //             return _apiField;
    //         }
    //         // Add ApiField 
    //         corporateField.addApiField(apiFieldRequest);
    //         return apiFieldRepository.save(apiFieldRequest);
    //     }).orElseThrow(() -> new ResourceNotFoundException("No Corporate Field found with corporate_field_id = " + corporateFieldId));
    //     return new ResponseEntity<>(apiField, HttpStatus.CREATED);
    // }

    // Get all Transactions
    @GetMapping("/getAllTransaction")
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // Get Transaction by transaction_id
    @GetMapping("/getTransactionById/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("transactionId") long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(
                () -> new ResourceNotFoundException("No Transaction found with transaction_id = " + transactionId));
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    // Get all Transactions by corporate_user_id
    @GetMapping("/getAllTransactionByCorpId/{corporateId}")
    public ResponseEntity<List<Transaction>> getAllTransactionByApiId(
            @PathVariable(value = "corporateId") Long corporateId) {
        CorporateUser corporateUser = corporateUserRepository.findById(corporateId).orElseThrow(
                () -> new ResourceNotFoundException("No Corporate User found with corporate_user_id = " + corporateId));
        if (corporateUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Transaction> apiFields = transactionRepository.findAllTransactByCorpUserId(corporateUser);
        if (apiFields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apiFields, HttpStatus.OK);
    }

    // Add new Transaction
    public Transaction addTransaction(@PathVariable(value = "corporateId") Long corporateId,
            @RequestBody Transaction transactionRequest) {
        Transaction transaction = corporateUserRepository.findById(corporateId).map(corporateUser -> {
            transactionRequest.setCorporateUser(corporateUser);
            return transactionRepository.save(transactionRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "No Corporate User found with corporate_user_id = " + corporateId));
        return transaction;
    }
}
