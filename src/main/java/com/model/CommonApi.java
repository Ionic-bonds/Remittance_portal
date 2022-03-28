package com.model;

public class CommonApi {
    private double amount;
    private String referenceId;
    
    public CommonApi() {};

    public CommonApi(double amount, String referenceId) {
        this.amount = amount;
        this.referenceId = referenceId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public void apiSetter(String value, String dbField) {}
}
