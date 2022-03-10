package csvfiles.controller;
import java.util.List;

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

import csvfiles.model.Api;
import csvfiles.model.ApiField;
import csvfiles.model.SelectedField;
import csvfiles.service.ApiFieldService;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping(path="/api/apiField")
public class ApiFieldController {
    private final ApiFieldService apiFieldService;
    
    @Autowired
    public ApiFieldController(ApiFieldService apiFieldService) {
        this.apiFieldService = apiFieldService;
    }

    @GetMapping("/getAllApi")
    public ResponseEntity<List<Api>> getAllApi() {
        try {
            List<Api> apiFields = apiFieldService.getAllApi();
            if (apiFields.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(apiFields, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/getApiById/{apiId}")
    // public ResponseEntity<Api> getApiById(@PathVariable long apiId) {
    //     Api api = apiFieldService.getApiById(apiId);
    //     // Api test = new Api(100, "Hello World");
        
    //     return new ResponseEntity<>(api, HttpStatus.OK);
    //     // try {
    //     //     Api api = apiFieldService.getApiById(apiId);
    //     //     if (api == null) {
    //     //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     //     }
    //     //     return new ResponseEntity<>(api, HttpStatus.OK);
    //     // } catch (Exception e) {
    //     //     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     // }
    // }

    @PostMapping(path="/addApi")
    public ResponseEntity<Api> addApi(@RequestBody Api api) {
        try {
            Api insert = apiFieldService.addApi(api);
            return new ResponseEntity<>(insert, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllApiField")
    public ResponseEntity<List<ApiField>> getAllApiField() {
        try {
            List<ApiField> apiFields = apiFieldService.getAllApiField();
            if (apiFields.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(apiFields, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/getApiFieldById/{apiFieldId}")
    // public ResponseEntity<ApiField> getApiFieldById(@PathVariable long apiFieldId) {
    //     try {
    //         ApiField apiField = apiFieldService.getApiFieldById(apiFieldId);
    //         if (apiField == null) {
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         }
    //         return new ResponseEntity<>(apiField, HttpStatus.OK);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // Not Working
    // @PostMapping(path="/addApiField")
    // public ResponseEntity<ApiField> addApiField(@RequestBody ApiField apiField) {
    //     // try {
    //     // System.out.println("A");
    //     ApiField insert = apiFieldService.addApiField(apiField);
    //     return new ResponseEntity<>(insert, HttpStatus.OK);
    //     // } catch (Exception e) {
    //     //     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     // }
    // }

    @PostMapping(path="/test")
    public String test(@RequestBody ApiField apiField) {
        
        return "Hello " + apiField.getApiId();
    }




    @GetMapping("/getApiFieldsByApiId/{apiId}")
    public ResponseEntity<List<ApiField>> getApiFieldsByApiId(@PathVariable long apiId) {
        try {
            List<ApiField> apiFields = apiFieldService.getApiFieldsByApiId(apiId);
            if (apiFields.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(apiFields, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






    @GetMapping("/getAllSelectedField")
    public ResponseEntity<List<SelectedField>> getAllSelectedField() {
        try {
            List<SelectedField> apiFields = apiFieldService.getAllSelectedField();
            if (apiFields.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(apiFields, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/getApiFieldById/{selectedFieldId}")
    // public ResponseEntity<SelectedField> getSelectedFieldById(@PathVariable long selectedFieldId) {
    //     try {
    //         SelectedField selectedField = apiFieldService.getSelectedFieldById(selectedFieldId);
    //         if (selectedField == null) {
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         }
    //         return new ResponseEntity<>(selectedField, HttpStatus.OK);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @GetMapping("/getSelectedFieldByApiFieldId/{apiFieldId}")
    public ResponseEntity<List<SelectedField>> getSelectedFieldByApiFieldId(@PathVariable long apiFieldId) {
        try {
            List<SelectedField> selectedFields = apiFieldService.getSelectedFieldByApiFieldId(apiFieldId);
            if (selectedFields.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(selectedFields, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}