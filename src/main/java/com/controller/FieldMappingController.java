package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.exception.ResourceNotFoundException;
import com.model.Api;
import com.model.ApiField;
import com.model.CorporateField;
import com.model.CorporateUser;
import com.model.MappedField;
import com.model.SelectedField;
import com.repository.ApiFieldRepository;
import com.repository.ApiRepository;
import com.repository.CorporateFieldRepository;
import com.repository.CorporateUserRepository;
import com.repository.SelectedFieldRepository;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/uploadFieldMapping/{corporateUserId}/{amountHeader}")
    public ResponseEntity<List<List<MappedField>>> addFieldMapping(
        @PathVariable("corporateUserId") long corporateUserId, 
        @PathVariable("amountHeader") String amountHeader, 
        @RequestParam("file") MultipartFile file) {
        List<List<MappedField>> transactionList = new ArrayList<>();

        CorporateUser corporateUser = corporateUserRepository.findById(corporateUserId).orElseThrow(() 
            -> new ResourceNotFoundException("No Corporate found with corporate_id = " + corporateUserId));
        List<CorporateField> corporateFields = corporateFieldRepository.findAllCorpFieldByUserId(corporateUser);

        Iterator<CorporateField> iterCorpField = corporateFields.iterator();
        HashMap<String, Set<ApiField>> fieldMapping = new HashMap<>();

        while (iterCorpField.hasNext()) {
            CorporateField currentCorpField = iterCorpField.next();
            Set<ApiField> currentApiFields = currentCorpField.getApiFields();
            fieldMapping.put(currentCorpField.getCorporateFieldName(), currentApiFields);
        }

        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sh = workbook.getSheetAt(0);
            Row header = sh.getRow(0);
            int amountColumnIndex = 0;
            boolean amountHeaderExists = false;
            Iterator<Cell> iterHeader = header.iterator();
            // Retrieve column index of amount
            while (iterHeader.hasNext() || amountHeaderExists != true) {
                Cell currentHeader = iterHeader.next();
                if (currentHeader.toString().equals(amountHeader)) {
                    amountColumnIndex = currentHeader.getColumnIndex();
                    amountHeaderExists = true;
                }
            }
            if (amountHeaderExists == false) {
                // Error No amount header
            }

            List<Api> apiList = apiRepository.findAll();
            Iterator<Row> iterApiRow = sh.iterator();
            while (iterApiRow.hasNext()) {
                Row currentRow = iterApiRow.next();
                List<MappedField> transaction = new ArrayList<>();
                try {
                    Double amount = Double.parseDouble(currentRow.getCell(amountColumnIndex).toString());
                    Api searchApi = determineApi(apiList, amount);
                    Iterator<Cell> iterApiCol = currentRow.iterator();
                    Iterator<Cell> iterHeadGet = header.iterator();
                    while (iterApiCol.hasNext()) {
                        Cell currentCol = iterApiCol.next();
                        Cell currentHeader = iterHeadGet.next();
                        Set<ApiField> apiFields = fieldMapping.get(currentHeader.toString());
                        for (ApiField apiField : apiFields) {
                            if (apiField.getApi().getApiId() == searchApi.getApiId()) {
                                String apiFieldDataType = apiField.getDatatype();
                                // SelectedField apiSelectedField = selectedFieldRepository.getById(apiField.getApiFieldId());
                                List<SelectedField> apiSelectedFields = selectedFieldRepository.findAllSelectedByApiFieldId(apiField);
                                // Data Validation
                                if (checkDataType(currentCol, apiFieldDataType, apiSelectedFields) == true) {


                                } else {

                                }
                                MappedField mappedField = new MappedField(apiField.getApiFieldName(), currentCol.toString());
                                transaction.add(mappedField);
                            }
                        }
                    }
                    transactionList.add(transaction);
                // amount column has a non-number value
                } catch (NumberFormatException e) {
                    
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail upload transactions: " + e.getMessage());
        }
        return new ResponseEntity<>(transactionList, HttpStatus.CREATED);
    }

    public Api determineApi(List<Api> apiList, double amount) {
        Api searchApi = new Api();
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

    public boolean checkDataType(Cell column, String dataType, List<SelectedField> selectedFields) {
        if (selectedFields.size() > 0) {
            
        }
        if (dataType == "String") {
            return true;
        // code for checking number
        } else if (dataType == "Number") {
            return true;
        // code for checking date
        } else if (dataType == "Date") {
            
        }
        return true;
    }

    public boolean addFieldMap(ApiField apiField, CorporateField corporateField) {
        corporateField.addApiField(apiField);
        corporateFieldRepository.save(corporateField);

        corporateField.addApiField(apiField);
        apiFieldRepository.save(apiField);
        return true;
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
