package com.model;

public class MappedField {
    private String corporateField;
    private String apiField;
    
    public MappedField(String corporateField, String apiField) {
        this.corporateField = corporateField;
        this.apiField = apiField;
    }

    public String getCorporateField() {
        return corporateField;
    }

    public void setCorporateField(String corporateField) {
        this.corporateField = corporateField;
    }

    public String getApiField() {
        return apiField;
    }

    public void setApiField(String apiField) {
        this.apiField = apiField;
    }
}
