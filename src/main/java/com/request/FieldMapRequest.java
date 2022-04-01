package com.request;

public class FieldMapRequest {
    private long corporateFieldId;
    private long apiFieldId;

    public long getCorporateFieldId() {
        return corporateFieldId;
    }

    public void setCorporateFieldId(long corporateField) {
        this.corporateFieldId = corporateField;
    }

    public long getApiFieldId() {
        return apiFieldId;
    }

    public void setApiFieldId(long apiFieldId) {
        this.apiFieldId = apiFieldId;
    }
}
