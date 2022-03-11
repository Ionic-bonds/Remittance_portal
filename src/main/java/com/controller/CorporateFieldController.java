package com.controller;
import java.util.List;

import com.exception.ResourceNotFoundException;
import com.model.CorporateField;
import com.model.CorporateUser;
import com.repository.CorporateFieldRepository;
import com.repository.CorporateUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CorporateFieldController {
    @Autowired
    private CorporateUserRepository corporateUserRepository;
    @Autowired
    private CorporateFieldRepository corporateFieldRepository;

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
        CorporateUser corporateUser = corporateUserRepository.findById(corporateUserId).orElseThrow(() 
            -> new ResourceNotFoundException("No Corporate User found with corporate_user_id = " + corporateUserId));
        return new ResponseEntity<>(corporateUser, HttpStatus.OK);
    }

    // Add new Corporate User
    @PostMapping("/addCorpUser")
    public ResponseEntity<CorporateUser> addCorpUser(@RequestBody CorporateUser corporateUser) {
        CorporateUser _corporateUser = corporateUserRepository.save(new CorporateUser(corporateUser.getEmail(), corporateUser.getPassword()));
        return new ResponseEntity<>(_corporateUser, HttpStatus.CREATED);
    }

    // Update Corporate User
    @PutMapping("/updateCorpUser/{corporateUserId}")
    public ResponseEntity<CorporateUser> updateCorpUser(@PathVariable("corporateUserId") long corporateUserId, @RequestBody CorporateUser corporateUser) {
        CorporateUser _api = corporateUserRepository.findById(corporateUserId)
            .orElseThrow(() -> new ResourceNotFoundException("No Corporate User found with corporate_user_id = " + corporateUserId));
        _api.setEmail(corporateUser.getEmail());
        _api.setPassword(corporateUser.getPassword());
        return new ResponseEntity<>(corporateUserRepository.save(_api), HttpStatus.OK);
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
        CorporateField corporateField = corporateFieldRepository.findById(corporateFieldId).orElseThrow(() 
            -> new ResourceNotFoundException("No Corporate Field found with corporate_field_id = " + corporateFieldId));
        return new ResponseEntity<>(corporateField, HttpStatus.OK);
    }

    // Get all Corporate Fields by corporate_user_id
    @GetMapping("/getAllCorpFieldByCorpUserId/{corporateId}")
    public ResponseEntity<List<CorporateField>> getAllCorpFieldByCorpUserId(@PathVariable(value = "corporateId") Long corporateId) {
        CorporateUser searchCorporate = corporateUserRepository.findById(corporateId).orElseThrow(() 
            -> new ResourceNotFoundException("No Corporate found with corporate_id = " + corporateId));
        if (searchCorporate == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CorporateField> corporateFields = corporateFieldRepository.findAllCorpFieldByUserId(searchCorporate);
        if (corporateFields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(corporateFields, HttpStatus.OK);
    }

    // Add new Corporate Field
    @PostMapping("/addCorpField/{corporateUserId}")
    public ResponseEntity<CorporateField> addCorpField(@PathVariable(value = "corporateUserId") long corporateUserId, @RequestBody CorporateField corporateFieldRequest) {
        CorporateField corporateField = corporateUserRepository.findById(corporateUserId).map(corporateUser -> { 
            corporateFieldRequest.setCorporateUser(corporateUser);
            return corporateFieldRepository.save(corporateFieldRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No Corporate User found with corporate_user_id = " + corporateUserId));
        return new ResponseEntity<>(corporateField, HttpStatus.CREATED);
    }

    // Update Corporate Field
    @PutMapping("/updateCorpField/{corporateFieldId}")
    public ResponseEntity<CorporateField> updateCorpField(@PathVariable("corporateFieldId") long corporateFieldId, @RequestBody CorporateField corporateField) {
        CorporateField _api = corporateFieldRepository.findById(corporateFieldId)
            .orElseThrow(() -> new ResourceNotFoundException("No Corporate Field found with corporate_field_id = " + corporateFieldId));
        _api.setCorporateFieldName(corporateField.getCorporateFieldName());
        return new ResponseEntity<>(corporateFieldRepository.save(_api), HttpStatus.OK);
    }

    // Delete Corporate Field


}