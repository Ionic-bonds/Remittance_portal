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
import com.model.CorporateField;
import com.model.CorporateUser;
import com.model.EverywhereRemit;
import com.model.FinanceNow;
import com.model.PaymentGo;
import com.model.SelectedField;
import com.repository.ApiFieldRepository;
import com.repository.ApiRepository;
import com.repository.CorporateFieldRepository;
import com.repository.CorporateUserRepository;
import com.repository.SelectedFieldRepository;
import com.transaction.request.SendTransaction;
import com.transaction.request.TransactionAuth;
import com.transaction.response.FieldMapListResponse;
import com.transaction.response.TransactOutcome;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @PostMapping("/uploadFieldMapping/{corporateUserId}/{amountCol}")
    public ResponseEntity<List<TransactOutcome>> addFieldMapping(
        @PathVariable("corporateUserId") long corporateUserId, 
        @PathVariable("amountCol") int amountCol, 
        @RequestParam("file") MultipartFile file) {

        List<FinanceNow> financeNowList = new ArrayList<>();
        List<EverywhereRemit> everywhereRemitList = new ArrayList<>();
        List<PaymentGo> paymentGoList = new ArrayList<>();

        CorporateUser corporateUser = corporateUserRepository.findById(corporateUserId).orElseThrow(() 
            -> new ResourceNotFoundException("No Corporate found with corporate_id = " + corporateUserId));
        List<CorporateField> corporateFields = corporateFieldRepository.findAllCorpFieldByUserId(corporateUser);

        // Generate CorporateField HashMap
        Iterator<CorporateField> iterCorpField = corporateFields.iterator();
        HashMap<String, Set<ApiField>> fieldMapping = new HashMap<>();
        while (iterCorpField.hasNext()) {
            CorporateField currentCorpField = iterCorpField.next();
            Set<ApiField> currentApiFields = currentCorpField.getApiFields();
            fieldMapping.put(currentCorpField.getCorporateFieldName(), currentApiFields);
        }
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            int headerRow = 1;
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
                    FinanceNow financeNow = new FinanceNow();
                    EverywhereRemit everywhereRemit = new EverywhereRemit();
                    PaymentGo paymentGo = new PaymentGo();

                    // Determine which API to instantiate based on the amount
                    Double amount = Double.parseDouble(currentRow.getCell(amountCol - 1).toString());
                    Api searchApi = determineApi(apiList, amount);
                    // Amount matches an API range 
                    if (searchApi != null) {
                        long searchApiId = searchApi.getApiId();
                        String searchApiName = searchApi.getApiName();
                        
                        Iterator<Cell> iterApiCol = currentRow.iterator();
                        Iterator<Cell> iterHeadGet = header.iterator();
                        while (iterApiCol.hasNext()) {
                            Cell currentCol = iterApiCol.next();
                            Cell currentHeader = iterHeadGet.next();
                            String searchApiField = currentHeader.toString() + "_" + String.valueOf(colCounter++);

                            // Maps current cell to the fieldMapping
                            Set<ApiField> apiFields = fieldMapping.get(searchApiField);
                            if (apiFields != null) {
                                for (ApiField apiField : apiFields) {
                                    // if (checkDataType(currentCol, apiField) == true) {
                                        String apiFieldName = apiField.getApiFieldName();
                                        String apiName = apiField.getApi().getApiName();
                                        long apiFieldApiId = apiField.getApi().getApiId();
                                        if (apiFieldApiId == searchApiId) {
                                            if (apiName.equals("FinanceNow")) {
                                                financeNow.apiSetter(currentCol.toString(), apiFieldName);
                                            } else if (apiName.equals("EverywhereRemit")) {
                                                everywhereRemit.apiSetter(currentCol.toString(), apiFieldName);
                                            } else if (apiName.equals("PaymentGo")) {
                                                paymentGo.apiSetter(currentCol.toString(), apiFieldName);
                                            }
                                        }
                                    // }
                                    // // Column has failed data validation
                                    // else {
                                        

                                    // }
                                }
                            }
                        }
                        if (searchApiName.equals("FinanceNow")) {
                            financeNowList.add(financeNow);
                        } else if (searchApiName.equals("EverywhereRemit")) {
                            everywhereRemitList.add(everywhereRemit);
                        } else if (searchApiName.equals("PaymentGo")) {
                            paymentGoList.add(paymentGo);
                        }
                    } 
                    // Amount is not within the range
                    else {
                        // Validation: 


                    }
                
                // amount column has a non-number value
                } catch (NumberFormatException e) {
                    // Validation: 

                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail upload transactions: " + e.getMessage());
        }
        FieldMapListResponse fieldMapList = new FieldMapListResponse(financeNowList, everywhereRemitList, paymentGoList);
        List<TransactOutcome> outcomeList = uploadAllTransactions(fieldMapList);
        return new ResponseEntity<>(outcomeList, HttpStatus.CREATED);
    }

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

    public boolean checkDataType(Cell column, ApiField apiField) {
        String dataType = apiField.getDatatype();
        List<SelectedField> selectedFields = selectedFieldRepository.findAllSelectedByApiFieldId(apiField);

        // Check if the field is a selected field
        if (selectedFields.isEmpty()) {
            if (dataType.equals("String")) {
                // Validation: Return true if column.toString() is a String
    
                return true;
            // code for checking number
            } 
            else if (dataType.equals("Number")) {
                // Validation: Return true if column.toString() is a Number
    
    
                return true;
            // code for checking date
            } 
            else if (dataType.equals("Date")) {
                // Validation: Return true if column.toString() is a Date
    
                
                return true;
            }
        } else {
            // Iterator<SelectedField> iterSelectedField = selectedFields.iterator();
            // Validation: Check if column.toString() is inside selectedFields

            return true;
            // To be completed
            // while (iterSelectedField.hasNext()) {
            //     SelectedField currentIterSelected = iterSelectedField.next();
            //     if (currentIterSelected.getSelectedFieldCode().equals(column.toString())) {
            //         return true;
            //     } 
            // }
        }
        return false;
    }

    public String authSandbox() {
        String url = "https://prelive.paywho.com/api/smu_authenticate";
        RestTemplate restTemplate = new RestTemplate();
        TransactionAuth credentials = new TransactionAuth("xxx", "yyy");

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

    public List<TransactOutcome> uploadAllTransactions(@RequestBody FieldMapListResponse fieldMapListResponse) {
        String url = "https://prelive.paywho.com/api/smu_send_transaction";
        RestTemplate restTemplate = new RestTemplate();
        String transactionToken = authSandbox();

        List<FinanceNow> financeNowList = fieldMapListResponse.getFinanceNowList();
        List<EverywhereRemit> everywhereRemitList = fieldMapListResponse.getEverywhereRemitList();
        List<PaymentGo> paymentGoList = fieldMapListResponse.getPaymentGoList();
        List<TransactOutcome> transactOutcomeList = new ArrayList<>();
        int apiDelay = 1;

        for (FinanceNow financeNow : financeNowList) {
            SendTransaction credentials = new SendTransaction(transactionToken, "financenow", financeNow);
            HttpEntity<SendTransaction> requestEntity = new HttpEntity<>(credentials);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseEntity.getBody());
                JsonNode innerNode = rootNode.get("message");
                transactOutcomeList.add(new TransactOutcome(financeNow, innerNode.asText()));
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
        for (EverywhereRemit everywhereRemit : everywhereRemitList) {
            SendTransaction credentials = new SendTransaction(transactionToken, "everywhereremit", everywhereRemit);
            HttpEntity<SendTransaction> requestEntity = new HttpEntity<>(credentials);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseEntity.getBody());
                JsonNode innerNode = rootNode.get("message");
                transactOutcomeList.add(new TransactOutcome(everywhereRemit, innerNode.asText()));
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
        for (PaymentGo paymentGo : paymentGoList) {
            SendTransaction credentials = new SendTransaction(transactionToken, "paymentgo", paymentGo);
            HttpEntity<SendTransaction> requestEntity = new HttpEntity<>(credentials);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseEntity.getBody());
                JsonNode innerNode = rootNode.get("message");
                transactOutcomeList.add(new TransactOutcome(paymentGo, innerNode.asText()));
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

    @PostMapping("/uploadTransactions")
    public ResponseEntity<List<String>> uploadTransactions(@RequestBody List<FinanceNow> transactionList) {
        List<String> outcomeList = new ArrayList<>();
        String url = "https://prelive.paywho.com/api/smu_send_transaction";
        RestTemplate restTemplate = new RestTemplate();
        String transactionToken = authSandbox();
        for (FinanceNow transaction : transactionList) {
            SendTransaction credentials = new SendTransaction(transactionToken, "financenow", transaction);
            HttpEntity<SendTransaction> requestEntity = new HttpEntity<>(credentials);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode messageNode = mapper.readTree(responseEntity.getBody()).get("message");
                outcomeList.add(messageNode.asText());
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(outcomeList, HttpStatus.CREATED);
    }


    @PostMapping("/addFieldMapping/{corporateFieldId}")
    public ResponseEntity<ApiField> addFieldMapping(
        @PathVariable(value = "corporateFieldId") Long corporateFieldId, 
        @RequestBody ApiField apiFieldRequest) {

        ApiField apiField = corporateFieldRepository.findById(corporateFieldId).map(corporateField -> {
            long apiFieldId = apiFieldRequest.getApiFieldId();
            // ApiField exists
            if (apiFieldId != 0L) {
                ApiField _apiField = apiFieldRepository.findById(apiFieldId)
                    .orElseThrow(() -> new ResourceNotFoundException("No Api Field found with api_field_id = " + apiFieldId));
                corporateField.addApiField(_apiField);
                corporateFieldRepository.save(corporateField);
                return _apiField;
            }
            // Add ApiField 
            corporateField.addApiField(apiFieldRequest);
            return apiFieldRepository.save(apiFieldRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No Corporate Field found with corporate_field_id = " + corporateFieldId));
        return new ResponseEntity<>(apiField, HttpStatus.CREATED);
    }
}
