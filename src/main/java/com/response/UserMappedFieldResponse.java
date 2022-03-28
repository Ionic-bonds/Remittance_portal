package com.response;

import java.util.List;

import com.model.ApiField;
import com.model.CorporateField;

public class UserMappedFieldResponse {
    private List<CorporateField> corporateFieldList;
    private List<ApiField> apiFieldList;

    public UserMappedFieldResponse(List<CorporateField> corporateFieldList, List<ApiField> apiFieldList) {
        this.corporateFieldList = corporateFieldList;
        this.apiFieldList = apiFieldList;
    }

    public List<CorporateField> getCorporateFieldList() {
        return corporateFieldList;
    }

    public void setCorporateFieldList(List<CorporateField> corporateFieldList) {
        this.corporateFieldList = corporateFieldList;
    }

    public List<ApiField> getApiFieldList() {
        return apiFieldList;
    }

    public void setApiFieldList(List<ApiField> apiFieldList) {
        this.apiFieldList = apiFieldList;
    }
}
