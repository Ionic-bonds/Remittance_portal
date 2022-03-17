package com.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.exception.ResourceNotFoundException;
import com.message.ResponseMessage;
import com.model.ApiField;
import com.model.CorporateField;
import com.model.CorporateUser;
import com.model.SelectedField;
import com.repository.ApiFieldRepository;
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
    private ApiFieldRepository apiFieldRepository;
    @Autowired
    private CorporateFieldRepository corporateFieldRepository;
    @Autowired
    private CorporateUserRepository corporateUserRepository;
    @Autowired
    private SelectedFieldRepository selectedFieldRepository;

    public boolean checkFieldValidity(ApiField selectedApi, List<String> columnList) {
        boolean validField = false;
        Iterator<String> iterColumn = columnList.iterator();
        while (iterColumn.hasNext()) {
            String column = iterColumn.next();
            String dataType = selectedApi.getDatatype();
            // Check if value matches the required datatype
            if (checkDataType(column, dataType) == true) {
                List<SelectedField> selectedFields = selectedFieldRepository.findAllSelectedByApiFieldId(selectedApi);
                // field is not a selected field, passes data validation
                if (selectedFields.isEmpty()) {
                    return true;
                } else {
                    // *Change to less complexity*
                    Iterator<SelectedField> iterSelected = selectedFields.iterator();
                    while (iterSelected.hasNext()) {
                        SelectedField selected = iterSelected.next();
                        if (selected.getSelectedFieldValue().equals(column)) {
                            validField = true;
                        }
                    }
                }
            }
        }
        return validField;
    }

    public boolean checkDataType(String column, String dataType) {
        if (dataType == "String") {
            return true;
        }
        // code for checking number


        // code for checking date

        return true;
    }

    public boolean addFieldMap(ApiField apiField, CorporateField corporateField) {
        corporateField.addApiField(apiField);
        corporateFieldRepository.save(corporateField);

        corporateField.addApiField(apiField);
        apiFieldRepository.save(apiField);
        return true;
    }

    // // Dummy Code, to be removed
    // @PostMapping("/uploadExcel/{corporateUserId}")
    // public ResponseEntity<ResponseMessage> addFieldMapping(@PathVariable("corporateUserId") long corporateUserId, @RequestParam("file") MultipartFile file) {
    //     String message = "";
    //     try {
    //         Workbook workbook = new XSSFWorkbook(file.getInputStream());
    //         // Reading the first sheet
    //         Sheet sh = workbook.getSheetAt(0);
    //         Row header = sh.getRow(0);
    //         Iterator<Cell> iterHeader = header.iterator();
    //         // Searching for a user by {corporateUserId}
    //         CorporateUser corporateUser = corporateUserRepository.findById(corporateUserId).orElseThrow(() 
    //             -> new ResourceNotFoundException("No Corporate User found with corporate_user_id = " + corporateUserId));

    //         // Iterate through the columns within a row
    //         while (iterHeader.hasNext()) {
    //             String currentCell = iterHeader.next().toString();
    //             // Declaring empty CorporateUser
    //             CorporateField newCorpField = new CorporateField();
    //             // Setting new CorporateUser
    //             newCorpField.setCorporateFieldName(currentCell);
    //             newCorpField.setCorporateUser(corporateUser);
    //             corporateFieldRepository.save(newCorpField);

    //             message += currentCell + " ";
    //         }


    //         // int totalCols = header.getPhysicalNumberOfCells();
    //         // Iterator<Row> rows = sh.iterator();
    //         // while (rows.hasNext()) {
    //         //     Row currentRow = rows.next();
    //         //     for (int i=0; i<totalCols; i++) {
    //         //         message += currentRow.getCell(i) + " ";
    //         //     }
    //         //     message += "\n";
    //         // }
    //     } catch (IOException e) {
    //         throw new RuntimeException("fail to store csv data: " + e.getMessage());
    //     }
    //     return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    // }


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
