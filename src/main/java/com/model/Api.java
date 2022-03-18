package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Api")
@Table(name = "api")
public class Api {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long apiId;
    
    @Column(name = "api_name")
    private String apiName;

    @Column(name = "min_amount")
    private double minAmount;

    @Column(name = "max_amount")
    private double maxAmount;

    public Api() {}

    public Api(String apiName, double minAmount, double maxAmount) {
        this.apiName = apiName;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public Api(long apiId, String apiName, double minAmount, double maxAmount) {
        this.apiId = apiId;
        this.apiName = apiName;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public long getApiId() {
        return apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }



    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }
}