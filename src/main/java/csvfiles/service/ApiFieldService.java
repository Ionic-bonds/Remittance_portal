package csvfiles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csvfiles.model.Api;
import csvfiles.model.ApiField;
import csvfiles.model.SelectedField;
import csvfiles.repository.ApiFieldRepository;
import csvfiles.repository.ApiRepository;
import csvfiles.repository.SelectedFieldRepository;

@Service
public class ApiFieldService {
    private final ApiFieldRepository apiFieldRepository;
    private final SelectedFieldRepository selectedFieldRepository;
    private final ApiRepository apiRepository;

    @Autowired
    public ApiFieldService(ApiFieldRepository apiFieldRepository, SelectedFieldRepository selectedFieldRepository, ApiRepository apiRepository) {
        this.apiFieldRepository = apiFieldRepository;
        this.selectedFieldRepository = selectedFieldRepository;
        this.apiRepository = apiRepository;
    }

    public List<Api> getAllApi() {
        return apiRepository.findAll();
    }

    // public Api getApiById(long apiId) {
    //     // return apiRepository.getById(apiId);
    //     long id = 1;
    //     Api test = apiRepository.getById(id);
    //     return test;
    // }

    public Api addApi(Api api) {
        return apiRepository.save(api);
    }
    
    public List<ApiField> getAllApiField() {
        return apiFieldRepository.findAll();
    }

    // public ApiField getApiFieldById(long apiFieldId) {
    //     ApiField apiField = apiFieldRepository.getById(apiFieldId);
    //     return new ApiField(
    //         apiField.getApiFieldId(), 
    //         getApiById(apiField.getApiId()),
    //         apiField.getApiFieldName(),
    //         apiField.getDatatype(),
    //         apiField.isMandatory());
    // }

    public List<ApiField> getApiFieldsByApiId(long apiId) {
        return apiFieldRepository.findAllByApiId(apiRepository.getById(apiId));
    }

    // public ApiField addApiField(ApiField apiField) {
    //     // long apiId = apiField.getApiId();
    //     long apiId = 1;
    //     Api api = apiRepository.getById(apiId);

    //     ApiField newApiField = new ApiField();
    //     newApiField.setApiId(api);
    //     newApiField.setApiFieldName(apiField.getApiFieldName());
    //     newApiField.setDatatype(apiField.getDatatype());
    //     newApiField.setMandatory(apiField.isMandatory());
    //     return apiFieldRepository.save(newApiField);

    //     // return apiFieldRepository.save(apiField);
    // }

    public List<SelectedField> getAllSelectedField() {
        return selectedFieldRepository.findAll();
    }

    // public SelectedField getSelectedFieldById(long selectedFieldId) {
    //     return selectedFieldRepository.getById(selectedFieldId);
    // }

    public List<SelectedField> getSelectedFieldByApiFieldId(long apiFieldId) {
        return selectedFieldRepository.findAllByApiFieldId(apiFieldRepository.getById(apiFieldId));
    }
}
