package com.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import com.model.UserApiRequest;
import com.repository.ApiFieldRepository;
import com.repository.ApiRepository;
import com.repository.CorporateFieldRepository;
import com.repository.CorporateUserRepository;
import com.repository.SelectedFieldRepository;
import com.repository.TransactionRepository;
import com.repository.UserApiRequestRepository;
import com.request.FieldMapRequest;
import com.request.SendTransaction;
import com.request.TransactOutcome;
import com.request.TransactionAuth;
import com.response.ResourceNotFoundException;
import com.response.TransactResponse;
import com.response.UserMappedFieldResponse;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class FieldMappingController {
    // private final String TRANSACTION_TOKEN = "token goes here";
    private final TransactionAuth CREDENTIALS = new TransactionAuth("g2team2", "ouhenglieh");
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
    @Autowired
    private UserApiRequestRepository userApiRequestRepository;

    // Get Sandbox Authentication Token
    private String authSandbox() {
        String url = "https://prelive.paywho.com/api/smu_authenticate";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<TransactionAuth> requestEntity = new HttpEntity<>(CREDENTIALS);
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

    private List<TransactOutcome> uploadAllTransactions(List<CommonApi> commonApiList) {
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
            } else if (commonApi instanceof EverywhereRemit) {
                apiName = "everywhereremit";
            } else if (commonApi instanceof PaymentGo) {
                apiName = "paymentgo";
            }
            String transactionToken = authSandbox();
            SendTransaction credentials = new SendTransaction(transactionToken, apiName, commonApi);
            HttpEntity<SendTransaction> requestEntity = new HttpEntity<>(credentials);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
            try {
                // Retrieving the "message" value from Sandbox response
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseEntity.getBody());
                JsonNode innerNode = rootNode.get("message");
                if (innerNode == null) {
                    // Connection with sandbox failed
                    return transactOutcomeList;
                }
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

    private boolean isRowEmpty(Row row) {
        boolean isEmpty = true;
        DataFormatter dataFormatter = new DataFormatter();
        if (row != null) {
            for (Cell cell : row) {
                if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
                    isEmpty = false;
                    break;
                }
            }
        }
        return isEmpty;
    }

    // Determine which API to call based on amount
    private Api determineApi(List<Api> apiList, double amount) {
        Api searchApi = null;
        Iterator<Api> iterApi = apiList.iterator();
        while (iterApi.hasNext()) {
            Api currentApi = iterApi.next();
            if (amount > currentApi.getMinAmount() && amount <= currentApi.getMaxAmount()) {
                searchApi = apiRepository.findById(currentApi.getApiId()).orElseThrow(
                        () -> new ResourceNotFoundException("No Api found with api_id = " + currentApi.getApiId()));
                return searchApi;
            }
        }
        return searchApi;
    }

    // Returns "" if there is no error
    private String checkDataType(String cell, ApiField apiField, String currentHeader) {
        List<SelectedField> selectedFields = selectedFieldRepository.findAllSelectedByApiFieldId(apiField);
        String errorMessage = "";
        String dataType = apiField.getDatatype();

        // Check if the field is a selected field
        if (selectedFields.isEmpty()) {
            // Validation: Return true if cell is not empty
            if (!cell.isEmpty() || cell.length() != 0) {
                // Validation: Return true if cell is a String (Regex Validation - only
                // alphanumeric and symbols)
                if (dataType.equals("Name")) {
                    if (!cell.replaceAll("\\s", "").matches("[a-zA-Z]+")) {
                        errorMessage = String.format("STRING ERROR '%s' for '%s' to '%s' [API: %s]: " +
                                "Please ensure cell input only contains non english alphabets.",
                                cell, currentHeader, apiField.getApiFieldName(), apiField.getApi().getApiName());
                        return errorMessage;
                    }
                    return errorMessage;
                }
                // Validation: Return true if cell is able to parse into an Integer
                else if (dataType.equals("Integer")) {
                    try {
                        Integer.parseInt(cell);
                    } catch (NumberFormatException e) {
                        // Not a Double
                        errorMessage = String.format("INTEGER ERROR '%s' for '%s' to '%s' [API: %s]: " +
                                "Please ensure cell input is in numeric (integer) format.",
                                cell, currentHeader, apiField.getApiFieldName(), apiField.getApi().getApiName());
                    }
                    return errorMessage;
                }
                // Validation: Return true if cell is able to parse into a Double
                else if (dataType.equals("Double")) {
                    try {
                        Double.parseDouble(cell);
                    } catch (NumberFormatException e) {
                        // Not a Double
                        errorMessage = String.format("DOUBLE ERROR '%s' for '%s' to '%s' [API: %s]: " +
                                "Please ensure cell input is in numeric (double) format.",
                                cell, currentHeader, apiField.getApiFieldName(), apiField.getApi().getApiName());
                    }
                    return errorMessage;
                }
                // Validation: Return true if cell is able to parse into a Date
                else if (dataType.equals("Date")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    try {
                        LocalDate.parse(cell, formatter);
                    } catch (Exception e) {
                        // Not a Date
                        errorMessage = String.format("DATE ERROR '%s' for '%s' to '%s' [API: %s]: " +
                                "Please ensure cell input is in YYYY-MM-DD format.",
                                cell, currentHeader, apiField.getApiFieldName(), apiField.getApi().getApiName());
                    }
                    return errorMessage;
                }
            } else {
                // Empty cell
                errorMessage = String.format("EMPTY INPUT ERROR Empty data input for '%s' to '%s' [API: %s] " +
                        "Please ensure cell input contains a value.",
                        currentHeader, apiField.getApiFieldName(), apiField.getApi().getApiName());
            }
            // Field is a selected field
        } else {
            errorMessage = String.format("Invalid Cell Input '%s' for '%s' to '%s' [API: %s]: " +
                    "Please ensure cell matches the list of values",
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

    // Get all Api
    @GetMapping("/getAllApi")
    public ResponseEntity<List<Api>> getAllApis() {
        List<Api> apis = apiRepository.findAll();
        if (apis.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apis, HttpStatus.OK);
    }

    // Get Api by api_id
    @GetMapping("/getApiById/{apiId}")
    public ResponseEntity<Api> getApiById(@PathVariable("apiId") long apiId) {
        Api api = apiRepository.findById(apiId)
                .orElseThrow(() -> new ResourceNotFoundException("No Api found with api_id = " + apiId));
        return new ResponseEntity<>(api, HttpStatus.OK);
    }

    // Add new Api
    @PostMapping("/addApi")
    public ResponseEntity<Api> addApi(@RequestBody Api api) {
        Api _api = apiRepository.save(new Api(api.getApiName(), api.getMinAmount(), api.getMaxAmount()));
        return new ResponseEntity<>(_api, HttpStatus.CREATED);
    }

    // Get all Api Fields
    @GetMapping("/getAllApiField")
    public ResponseEntity<List<ApiField>> getAllApiField() {
        List<ApiField> apiFields = apiFieldRepository.findAll();
        if (apiFields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apiFields, HttpStatus.OK);
    }

    // Get Api Field by api_field_id
    @GetMapping("/getApiFieldById/{apiFieldId}")
    public ResponseEntity<ApiField> getApiFieldById(@PathVariable("apiFieldId") long apiFieldId) {
        ApiField apiField = apiFieldRepository.findById(apiFieldId)
                .orElseThrow(() -> new ResourceNotFoundException("No Api found with api_field_id = " + apiFieldId));
        return new ResponseEntity<>(apiField, HttpStatus.OK);
    }

    // Get all Api Fields by api_id
    @GetMapping("/getAllApiFieldByApiId/{apiId}")
    public ResponseEntity<List<ApiField>> getAllApiFieldByApiId(@PathVariable(value = "apiId") long apiId) {
        Api searchApi = apiRepository.findById(apiId)
                .orElseThrow(() -> new ResourceNotFoundException("No Api found with api_id = " + apiId));
        if (searchApi == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<ApiField> apiFields = apiFieldRepository.findAllApiFieldByApiId(searchApi);
        if (apiFields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apiFields, HttpStatus.OK);
    }

    // Add new Api Field
    @PostMapping("/addApiField/{apiId}")
    public ResponseEntity<ApiField> addApiField(@PathVariable(value = "apiId") long apiId,
            @RequestBody ApiField apiFieldRequest) {
        ApiField apiField = apiRepository.findById(apiId).map(api -> {
            apiFieldRequest.setApi(api);
            return apiFieldRepository.save(apiFieldRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No Api found with api_id = " + apiId));
        return new ResponseEntity<>(apiField, HttpStatus.CREATED);
    }

    // Get all Selected Fields
    @GetMapping("/getAllSelectedField")
    public ResponseEntity<List<SelectedField>> getAllSelectedField() {
        List<SelectedField> apis = selectedFieldRepository.findAll();
        if (apis.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apis, HttpStatus.OK);
    }

    // Get Selected Field by selected_field_id
    @GetMapping("/getSelectedFieldById/{selectedFieldId}")
    public ResponseEntity<SelectedField> getSelectedFieldById(@PathVariable("selectedFieldId") long selectedFieldId) {
        SelectedField selectedField = selectedFieldRepository.findById(selectedFieldId).orElseThrow(
                () -> new ResourceNotFoundException("No Api found with api_field_id = " + selectedFieldId));
        return new ResponseEntity<>(selectedField, HttpStatus.OK);
    }

    // Get all Selected Field by api_field_id
    @GetMapping("/getAllSelectedByApiFieldId/{apiFieldId}")
    public ResponseEntity<List<SelectedField>> getAllSelectedByApiFieldId(
            @PathVariable(value = "apiFieldId") long apiFieldId) {
        ApiField searchApiField = apiFieldRepository.findById(apiFieldId)
                .orElseThrow(() -> new ResourceNotFoundException("No Api found with api_field_id = " + apiFieldId));
        if (searchApiField == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<SelectedField> selectedFields = selectedFieldRepository.findAllSelectedByApiFieldId(searchApiField);
        if (selectedFields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(selectedFields, HttpStatus.OK);
    }

    // Add new Selected Field
    @PostMapping("/addSelectedField/{apiFieldId}")
    public ResponseEntity<SelectedField> addSelectedField(@PathVariable(value = "apiFieldId") long apiFieldId,
            @RequestBody SelectedField selectedFieldRequest) {
        SelectedField selectedField = apiFieldRepository.findById(apiFieldId).map(apiField -> {
            selectedFieldRequest.setApiField(apiField);
            return selectedFieldRepository.save(selectedFieldRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No Api Field found with api_field_id = " + apiFieldId));
        return new ResponseEntity<>(selectedField, HttpStatus.CREATED);
    }

    // Get all Corporate Users
    @GetMapping("/getAllCorpUsers")
    public ResponseEntity<List<CorporateUser>> getAllCorpUsers() {
        List<CorporateUser> apis = corporateUserRepository.findAll();
        if (apis.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apis, HttpStatus.OK);
    }

    // Get Corporate User by corporate_user_id
    @GetMapping("/getCorpUserById/{corporateUserId}")
    public ResponseEntity<CorporateUser> getCorpUserById(@PathVariable("corporateUserId") long corporateUserId) {
        CorporateUser corporateUser = corporateUserRepository.findById(corporateUserId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No Corporate User found with corporate_user_id = " + corporateUserId));
        return new ResponseEntity<>(corporateUser, HttpStatus.OK);
    }

    // Update Corporate User
    public void updateCorpUser(long corporateUserId, CorporateUser corporateUser) {
        CorporateUser _api = corporateUserRepository.findById(corporateUserId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No Corporate User found with corporate_user_id = " + corporateUserId));
        _api.setEmail(corporateUser.getEmail());
        _api.setPassword(corporateUser.getPassword());
        _api.setHeaderRow(corporateUser.getHeaderRow());
    }

    // Get all Corporate Fields
    @GetMapping("/getAllCorpField")
    public ResponseEntity<List<CorporateField>> getAllCorpField() {
        List<CorporateField> corporateFields = corporateFieldRepository.findAll();
        if (corporateFields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(corporateFields, HttpStatus.OK);
    }

    // Get Corporate Field by corporate_field_id
    @GetMapping("/getCorpFieldById/{corporateFieldId}")
    public ResponseEntity<CorporateField> getCorpFieldById(@PathVariable("corporateFieldId") long corporateFieldId) {
        CorporateField corporateField = corporateFieldRepository.findById(corporateFieldId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No Corporate Field found with corporate_field_id = " + corporateFieldId));
        return new ResponseEntity<>(corporateField, HttpStatus.OK);
    }

    // Get all Corporate Fields by corporate_user_id
    @GetMapping("/getAllCorpFieldByCorpUserId/{corporateId}")
    public ResponseEntity<UserMappedFieldResponse> getAllCorpFieldByCorpUserId(
            @PathVariable(value = "corporateId") long corporateId) {
        CorporateUser searchCorporate = corporateUserRepository.findById(corporateId).orElseThrow(
                () -> new ResourceNotFoundException("No Corporate found with corporate_id = " + corporateId));
        if (searchCorporate == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CorporateField> corporateFields = corporateFieldRepository.findAllCorpFieldByUserId(searchCorporate);
        List<ApiField> apiFields = apiFieldRepository.findAll();
        if (apiFields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // User has not uploaded any corporate fields
        UserMappedFieldResponse userMappedFieldResponse = new UserMappedFieldResponse(corporateFields, apiFields);
        return new ResponseEntity<>(userMappedFieldResponse, HttpStatus.OK);
    }

    // Add new Corporate Field
    @PostMapping("/addCorpField/{corporateUserId}")
    public ResponseEntity<CorporateField> addCorpField(@PathVariable(value = "corporateUserId") long corporateUserId,
            @RequestBody CorporateField corporateFieldRequest) {
        CorporateField corporateField = corporateUserRepository.findById(corporateUserId).map(corporateUser -> {
            corporateFieldRequest.setCorporateUser(corporateUser);
            return corporateFieldRepository.save(corporateFieldRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "No Corporate User found with corporate_user_id = " + corporateUserId));
        return new ResponseEntity<>(corporateField, HttpStatus.CREATED);
    }

    // Update Corporate Field
    @PutMapping("/updateCorpField/{corporateFieldId}")
    public ResponseEntity<CorporateField> updateCorpField(@PathVariable("corporateFieldId") long corporateFieldId,
            @RequestBody CorporateField corporateField) {
        CorporateField _api = corporateFieldRepository.findById(corporateFieldId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No Corporate Field found with corporate_field_id = " + corporateFieldId));
        _api.setCorporateFieldName(corporateField.getCorporateFieldName());
        return new ResponseEntity<>(corporateFieldRepository.save(_api), HttpStatus.OK);
    }

    // Delete Corporate Field
    @DeleteMapping("/deleteCorpField/{corporateId}")
    public ResponseEntity<HttpStatus> deleteCorpField(@PathVariable("corporateId") long corporateId) {
        CorporateUser searchCorporate = corporateUserRepository.findById(corporateId).orElseThrow(
                () -> new ResourceNotFoundException("No Corporate found with corporate_id = " + corporateId));
        corporateFieldRepository.deleteAllCorpFieldsByUserId(searchCorporate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/uploadExcelHeader/{corporateUserId}/{headerRow}")
    public ResponseEntity<List<CorporateField>> uploadExcelHeader(@PathVariable("corporateUserId") long corporateUserId,
            @PathVariable("headerRow") int headerRow,
            @RequestParam("file") MultipartFile file) {

        try {
            // Retrieve List of Corporate Fields
            CorporateUser searchCorporate = corporateUserRepository.findById(corporateUserId).orElseThrow(
                    () -> new ResourceNotFoundException("No Corporate found with corporate_id = " + corporateUserId));
            if (searchCorporate == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<CorporateField> corporateFields = corporateFieldRepository.findAllCorpFieldByUserId(searchCorporate);
            if (corporateFields.size() != 0) {
                deleteCorpField(corporateUserId);
            }
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            // Reading the first sheet
            Sheet sh = workbook.getSheetAt(0);
            // If headers in row 1 of Excel sheet, parameter is 0; if headers in row 2,
            // parameter is 1
            headerRow -= 1;
            Row header = sh.getRow(headerRow);
            Iterator<Cell> iterHeader = header.iterator();
            // Searching for a user by {corporateUserId}
            CorporateUser corporateUser = corporateUserRepository.findById(corporateUserId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "No Corporate User found with corporate_user_id = " + corporateUserId));
            corporateUser.setHeaderRow(headerRow);
            updateCorpUser(corporateUserId, corporateUser);

            int colCounter = 1;
            // Iterate through the columns within a row
            while (iterHeader.hasNext()) {
                String currentCell = iterHeader.next().toString();
                // Declaring empty CorporateUser
                CorporateField newCorpField = new CorporateField();
                // Setting new CorporateUser
                String newCorporateFieldName = currentCell + "_" + String.valueOf(colCounter++);
                newCorpField.setCorporateFieldName(newCorporateFieldName);
                newCorpField.setCorporateUser(corporateUser);
                corporateFieldRepository.save(newCorpField);
            }

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
        CorporateUser searchCorporate = corporateUserRepository.findById(corporateUserId).orElseThrow(
                () -> new ResourceNotFoundException("No Corporate found with corporate_id = " + corporateUserId));
        List<CorporateField> corpFields = corporateFieldRepository.findAllCorpFieldByUserId(searchCorporate);
        return new ResponseEntity<>(corpFields, HttpStatus.OK);
    }

    // Add new Field Mappings
    @PostMapping("/addFieldMapping")
    public ResponseEntity<List<ApiField>> addFieldMapping(@RequestBody List<FieldMapRequest> fieldMapRequests) {
        List<ApiField> apiFieldList = new ArrayList<ApiField>();
        for (FieldMapRequest fieldMapRequest : fieldMapRequests) {
            long corporateFieldId = fieldMapRequest.getCorporateFieldId();
            long apiFieldRequestId = fieldMapRequest.getApiFieldId();
            ApiField apiFieldRequest = apiFieldRepository.findById(apiFieldRequestId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "No Api Field found with api_field_id = " + apiFieldRequestId));
            ApiField apiField = corporateFieldRepository.findById(corporateFieldId).map(corporateField -> {
                long apiFieldId = fieldMapRequest.getApiFieldId();
                // ApiField exists
                if (apiFieldId != 0L) {
                    ApiField _apiField = apiFieldRepository.findById(apiFieldId)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "No Api Field found with api_field_id = " + apiFieldId));
                    corporateField.addApiField(_apiField);
                    corporateFieldRepository.save(corporateField);
                    return _apiField;
                }
                // Add ApiField
                corporateField.addApiField(apiFieldRequest);
                return apiFieldRepository.save(apiFieldRequest);
            }).orElseThrow(() -> new ResourceNotFoundException(
                    "No Corporate Field found with corporate_field_id = " + corporateFieldId));
            apiFieldList.add(apiFieldRepository.save(apiField));
        }
        return new ResponseEntity<>(apiFieldList, HttpStatus.CREATED);
    }

    // Upload Excel Sheet for Field Mapping & Validation
    @PostMapping("/uploadFieldMapping/{corporateUserId}")
    public ResponseEntity<TransactResponse> uploadFieldMapping(
            @PathVariable("corporateUserId") long corporateUserId,
            @RequestParam("file") MultipartFile file) {

        UserApiRequest userApiRequest = new UserApiRequest();
        List<TransactOutcome> transactOutcomeList = new ArrayList<>();
        List<CommonApi> commonApiList = new ArrayList<>();
        List<String> errorList = new ArrayList<>();
        int amountCol = -1;

        CorporateUser corporateUser = corporateUserRepository.findById(corporateUserId).orElseThrow(
                () -> new ResourceNotFoundException("No Corporate found with corporate_id = " + corporateUserId));
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
            userApiRequest.setStatusCode(200);
            userApiRequest.setMessage("Validation Failed: No Amount column found");
            addUserApiRequest(corporateUserId, userApiRequest);
            return new ResponseEntity<>(TransactResponse, HttpStatus.CREATED);
        }
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sh = workbook.getSheetAt(0);
            Row header = sh.getRow(headerRow);
            int totalRows = sh.getLastRowNum();
            List<Api> apiList = apiRepository.findAll();
            for (int i = headerRow; i < totalRows; i++) {
                Row currentRow = sh.getRow(i);
                if (isRowEmpty(currentRow)) continue;
                int rowNum = currentRow.getRowNum();
                int colCounter = 1;
                String errorMessage = "";
                try {
                    CommonApi commonApi = new CommonApi();
                    Double amount = Double.parseDouble(currentRow.getCell(amountCol - 1).toString());
                    Api searchApi = determineApi(apiList, amount);
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
                        Iterator<Cell> iterHeadGet = header.iterator();
                        for (Cell col : currentRow) {
                            DataFormatter dataFormatter = new DataFormatter();
                            String cell = dataFormatter.formatCellValue(col);
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
                            errorList.add(String.format("Error row%s: %s", String.valueOf(rowNum + 1), errorMessage));
                        }
                    }
                    // Amount is not within the range
                    else {
                        // Validation:
                        errorList.add(String.format("Error row%s: %s", String.valueOf(rowNum + 1),
                                "Amount is not within API range."));
                    }
                } catch (NumberFormatException e) {
                    // Validation:
                    if (rowNum > headerRow && rowNum < totalRows - 1) {
                        errorList.add(
                                String.format("Error row%s: %s", String.valueOf(rowNum + 1), "Amount is invalid."));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Invalid excel file: " + e.getMessage());
        }
        if (commonApiList.size() == 0) errorList.add(String.format("Error: Excel file has no transactions"));
        if (errorList.size() == 0) {
            transactOutcomeList = uploadAllTransactions(commonApiList);
            for (TransactOutcome transactOutcome : transactOutcomeList) {
                Transaction transactionRequest = new Transaction();
                transactionRequest.setReferenceId(transactOutcome.getCommonApi().getReferenceId());
                transactionRequest.setOutcome(transactOutcome.getOutcome());
                addTransaction(corporateUserId, transactionRequest);
            }
            if (transactOutcomeList.size() == commonApiList.size()) {
                userApiRequest.setStatusCode(400);
                userApiRequest.setMessage("Transaction Success");
                addUserApiRequest(corporateUserId, userApiRequest);
            } else {
                userApiRequest.setStatusCode(200);
                userApiRequest.setMessage("Transaction Failed: Invalid Access Token or API");
                addUserApiRequest(corporateUserId, userApiRequest);
            }

        } else {
            String errorMessage = "";
            for (String error : errorList) {
                errorMessage += error + "\n";
            }
            userApiRequest.setStatusCode(200);
            userApiRequest.setMessage("Validation Failed: " + errorMessage);
            addUserApiRequest(corporateUserId, userApiRequest);
        }
        TransactResponse TransactResponse = new TransactResponse(transactOutcomeList, errorList);
        return new ResponseEntity<>(TransactResponse, HttpStatus.CREATED);
    }

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
    public ResponseEntity<List<Transaction>> getAllTransactionByCorpId(
            @PathVariable(value = "corporateId") long corporateId) {
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
    public Transaction addTransaction(@PathVariable(value = "corporateId") long corporateId,
            @RequestBody Transaction transactionRequest) {
        Transaction transaction = corporateUserRepository.findById(corporateId).map(corporateUser -> {
            transactionRequest.setCorporateUser(corporateUser);
            return transactionRepository.save(transactionRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "No Corporate User found with corporate_user_id = " + corporateId));
        return transaction;
    }

    // Get all UserApiRequests
    @GetMapping("/getAllUserApiRequest")
    public ResponseEntity<List<UserApiRequest>> getAllUserApiRequest() {
        List<UserApiRequest> userApiRequests = userApiRequestRepository.findAll();
        if (userApiRequests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userApiRequests, HttpStatus.OK);
    }

    // Get UserApiRequest by userApiRequest_id
    @GetMapping("/getUserApiRequestById/{userApiRequestId}")
    public ResponseEntity<UserApiRequest> getUserApiRequestById(
            @PathVariable("userApiRequestId") long userApiRequestId) {
        UserApiRequest userApiRequest = userApiRequestRepository.findById(userApiRequestId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "No UserApiRequest found with UserApiRequest_id = " + userApiRequestId));
        return new ResponseEntity<>(userApiRequest, HttpStatus.OK);
    }

    // Get all UserApiRequests by corporate_user_id
    @GetMapping("/getAllUserApiRequestByCorpId/{corporateId}")
    public ResponseEntity<List<UserApiRequest>> getAllUserApiRequestByCorpId(
            @PathVariable(value = "corporateId") long corporateId) {
        CorporateUser corporateUser = corporateUserRepository.findById(corporateId).orElseThrow(
                () -> new ResourceNotFoundException("No Corporate User found with corporate_user_id = " + corporateId));
        if (corporateUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<UserApiRequest> apiFields = userApiRequestRepository.findAllApiRequestByCorpUserId(corporateUser);
        if (apiFields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apiFields, HttpStatus.OK);
    }

    // Add new User Api Request
    public void addUserApiRequest(@PathVariable(value = "corporateId") long corporateId,
            @RequestBody UserApiRequest userApiRequest) {
        corporateUserRepository.findById(corporateId).map(corporateUser -> {
            userApiRequest.setCorporateUser(corporateUser);
            return userApiRequestRepository.save(userApiRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "No Corporate User found with corporate_user_id = " + corporateId));
    }
}