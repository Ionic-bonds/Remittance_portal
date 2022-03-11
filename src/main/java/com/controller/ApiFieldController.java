package com.controller;

import java.util.List;

import com.exception.ResourceNotFoundException;
import com.model.Api;
import com.model.ApiField;
import com.model.SelectedField;
import com.repository.ApiFieldRepository;
import com.repository.ApiRepository;
import com.repository.SelectedFieldRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ApiFieldController {
    @Autowired
    private ApiRepository apiRepository;
    @Autowired
    private ApiFieldRepository apiFieldRepository;
    @Autowired
    private SelectedFieldRepository selectedFieldRepository;

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
        Api api = apiRepository.findById(apiId).orElseThrow(() 
            -> new ResourceNotFoundException("No Api found with api_id = " + apiId));
        return new ResponseEntity<>(api, HttpStatus.OK);
    }

    // Add new Api
    @PostMapping("/addApi")
    public ResponseEntity<Api> addApi(@RequestBody Api api) {
        Api _api = apiRepository.save(new Api(api.getApiName()));
        return new ResponseEntity<>(_api, HttpStatus.CREATED);
    }

    // Get all Api Fields
    @GetMapping("/getAllApiField")
    public ResponseEntity<List<ApiField>> getAllApiField() {
        List<ApiField> apis = apiFieldRepository.findAll();
        if (apis.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apis, HttpStatus.OK);
    }

    // Get Api Field by api_field_id
    @GetMapping("/getApiFieldById/{apiFieldId}")
    public ResponseEntity<ApiField> getApiFieldById(@PathVariable("apiFieldId") long apiFieldId) {
        ApiField apiField = apiFieldRepository.findById(apiFieldId).orElseThrow(() 
            -> new ResourceNotFoundException("No Api found with api_field_id = " + apiFieldId));
        return new ResponseEntity<>(apiField, HttpStatus.OK);
    }

    // Get all Api Fields by api_id
    @GetMapping("/getAllApiFieldByApiId/{apiId}")
    public ResponseEntity<List<ApiField>> getAllApiFieldByApiId(@PathVariable(value = "apiId") Long apiId) {
        Api searchApi = apiRepository.findById(apiId).orElseThrow(() 
            -> new ResourceNotFoundException("No Api found with api_id = " + apiId));
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
    public ResponseEntity<ApiField> addApiField(@PathVariable(value = "apiId") Long apiId, @RequestBody ApiField apiFieldRequest) {
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
        SelectedField selectedField = selectedFieldRepository.findById(selectedFieldId).orElseThrow(() 
            -> new ResourceNotFoundException("No Api found with api_field_id = " + selectedFieldId));
        return new ResponseEntity<>(selectedField, HttpStatus.OK);
    }

    // Get all Selected Field by api_field_id
    @GetMapping("/getAllSelectedByApiFieldId/{apiFieldId}")
    public ResponseEntity<List<SelectedField>> getAllSelectedByApiFieldId(@PathVariable(value = "apiFieldId") Long apiFieldId) {
        ApiField searchApiField = apiFieldRepository.findById(apiFieldId).orElseThrow(() 
            -> new ResourceNotFoundException("No Api found with api_field_id = " + apiFieldId));
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
    public ResponseEntity<SelectedField> addSelectedField(@PathVariable(value = "apiFieldId") Long apiFieldId, @RequestBody SelectedField selectedFieldRequest) {
        SelectedField selectedField = apiFieldRepository.findById(apiFieldId).map(apiField -> { 
            selectedFieldRequest.setApiField(apiField);
            return selectedFieldRepository.save(selectedFieldRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No Api Field found with api_field_id = " + apiFieldId));
        return new ResponseEntity<>(selectedField, HttpStatus.CREATED);
    }


    // @DeleteMapping("/deleteApi/{id}")
    // public ResponseEntity<HttpStatus> deleteApi(@PathVariable("id") long id) {
    //     apiRepository.deleteById(id);
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }

    // @DeleteMapping("/deleteAllApi")
    // public ResponseEntity<HttpStatus> deleteAllApis() {
    //     apiRepository.deleteAll();
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
}