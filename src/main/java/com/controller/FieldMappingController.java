package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.ApiField;
import com.repository.ApiFieldRepository;
import com.repository.CorporateFieldRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FieldMappingController {
    @Autowired
    private ApiFieldRepository apiFieldRepository;
    @Autowired
    private CorporateFieldRepository corporateFieldRepository;


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
                // Add CorporateField
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
