package com.controller;

import java.util.List;

import com.exception.ResourceNotFoundException;
import com.model.Api;
import com.model.ApiField;
import com.repository.ApiFieldRepository;
import com.repository.ApiRepository;
import com.repository.ApiSelectedFieldRepository;

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
    private ApiSelectedFieldRepository apiSelectedFieldRepository;

    @GetMapping("/getAllApi")
    public ResponseEntity<List<Api>> getAllApis() {
        List<Api> apis = apiRepository.findAll();
        if (apis.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apis, HttpStatus.OK);
    }

    @GetMapping("/getApiById/{apiId}")
    public ResponseEntity<Api> getApiById(@PathVariable("apiId") long apiId) {
        Api api = apiRepository.findById(apiId).orElseThrow(() 
            -> new ResourceNotFoundException("No Api found with api_id = " + apiId));
        return new ResponseEntity<>(api, HttpStatus.OK);
    }

    @PostMapping("/addApi")
    public ResponseEntity<Api> createApi(@RequestBody Api api) {
        Api _api = apiRepository.save(new Api(api.getApiName()));
        return new ResponseEntity<>(_api, HttpStatus.CREATED);
    }

    // @PutMapping("/updateApi/{id}")
    // public ResponseEntity<Api> updateApi(@PathVariable("id") long id, @RequestBody Api api) {
    //     Api _api = apiRepository.findById(id)
    //             .orElseThrow(() -> new ResourceNotFoundException("No Api found with api_id = " + id));
    //     _api.setApiName(api.getApiName());
    //     return new ResponseEntity<>(apiRepository.save(_api), HttpStatus.OK);
    // }

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

    @GetMapping("/getAllApiField")
    public ResponseEntity<List<ApiField>> getAllApiField() {
        List<ApiField> apis = apiFieldRepository.findAll();
        if (apis.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apis, HttpStatus.OK);
    }

    @GetMapping("/getAllByApiId/{apiId}")
    public ResponseEntity<List<ApiField>> getAllCommentsByTutorialId(@PathVariable(value = "apiId") Long apiId) {
        Api searchApi = apiRepository.findById(apiId).orElseThrow(() 
            -> new ResourceNotFoundException("No Api found with api_id = " + apiId));
        List<ApiField> apiFields = apiFieldRepository.findByApiFieldIdd(searchApi);
        if (apiFields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apiFields, HttpStatus.OK);
    }

    @GetMapping("/getApiFieldById/{apiFieldId}")
    public ResponseEntity<ApiField> getCommentsByTutorialId(@PathVariable(value = "apiFieldId") Long apiFieldId) {
        ApiField apiField = apiFieldRepository.findById(apiFieldId)
            .orElseThrow(() -> new ResourceNotFoundException("Not Api Field with api_field_id = " + apiFieldId));
        return new ResponseEntity<>(apiField, HttpStatus.OK);
    }

    @PostMapping("/addApiField/{apiId}")
    public ResponseEntity<ApiField> createComment(@PathVariable(value = "apiId") Long apiId, @RequestBody ApiField apiFieldRequest) {
        ApiField apiField = apiRepository.findById(apiId).map(api -> { 
            apiFieldRequest.setApi(api);
            return apiFieldRepository.save(apiFieldRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No Api found with api_id = " + apiId));
        return new ResponseEntity<>(apiField, HttpStatus.CREATED);
    }
}