package com.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinanceNow extends CommonApi {
    private String senderState;
    private String senderCity;   
    private LocalDate senderDateOfBirth;
    private String receiverAddress;
    private String receiverCity;
    private String receiverCountry;
    private String receiverIdNumber;
    private String receiverNationality;
    private String paymentMode;
    private String payoutCurrency;
    private String senderBeneficiaryRelationship;

    public FinanceNow() {};

    // Common Fields
    @Override
    @JsonProperty("Amount")
    public double getAmount() {
        return super.getAmount();
    }
    
    @Override
    @JsonProperty("ReferenceId")
    public String getReferenceId() {
        return super.getReferenceId();
    }
    
    @Override
    @JsonProperty("BankAccountNumber")
    public String getBankAccountNumber() {
        return super.getBankAccountNumber();
    }
    
    @Override
    @JsonProperty("SenderFirstName")
    public String getSenderFirstName() {
        return super.getSenderFirstName();
    }
    
    @Override
    @JsonProperty("SenderLastName")
    public String getSenderLastName() {
        return super.getSenderLastName();
    }
    
    @Override
    @JsonProperty("SenderIdNumber")
    public String getSenderIdNumber() {
        return super.getSenderIdNumber();
    }
    
    @Override
    @JsonProperty("SenderAddress")
    public String getSenderAddress() {
        return super.getSenderAddress();
    }
    
    @Override
    @JsonProperty("SenderCountry")
    public String getSenderCountry() {
        return super.getSenderCountry();
    }
    
    @Override
    @JsonProperty("SenderNationality")
    public String getSenderNationality() {
        return super.getSenderNationality();
    }
    
    @Override
    @JsonProperty("SenderIdType")
    public int getSenderIdType() {
        return super.getSenderIdType();
    }
    
    @Override
    @JsonProperty("ReceiverFirstName")
    public String getReceiverFirstName() {
        return super.getReceiverFirstName();
    }
    
    @Override
    @JsonProperty("ReceiverLastName")
    public String getReceiverLastName() {
        return super.getReceiverLastName();
    }
    
    @Override
    @JsonProperty("ReceiverIdType")
    public int getReceiverIdType() {
        return super.getReceiverIdType();
    }
    
    @Override
    @JsonProperty("SenderSourceOfFund")
    public int getSourceOfFund() {
        return super.getSourceOfFund();
    }
    
    @Override
    @JsonProperty("PurposeOfRemittance")
    public int getPurposeOfRemit() {
        return super.getPurposeOfRemit();
    }
    
    // FinanceNow Fields
    @JsonProperty("SenderState")
    public String getSenderState() {
        return senderState;
    }
    
    public void setSenderState(String senderState) {
        this.senderState = senderState;
    }
    
    @JsonProperty("SenderCity")
    public String getSenderCity() {
        return senderCity;
    }
    
    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }
    
    @JsonProperty("SenderDateOfBirth")
    public LocalDate getSenderDateOfBirth() {
        return senderDateOfBirth;
    }
    
    public void setSenderDateOfBirth(LocalDate senderDateOfBirth) {
        this.senderDateOfBirth = senderDateOfBirth;
    }
    
    @JsonProperty("ReceiverAddress")
    public String getReceiverAddress() {
        return receiverAddress;
    }
    
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
    
    @JsonProperty("ReceiverCity")
    public String getReceiverCity() {
        return receiverCity;
    }
    
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }
    
    @JsonProperty("ReceiverCountry")
    public String getReceiverCountry() {
        return receiverCountry;
    }
    
    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }
    
    @JsonProperty("ReceiverIdNumber")
    public String getReceiverIdNumber() {
        return receiverIdNumber;
    }
    
    public void setReceiverIdNumber(String receiverIdNumber) {
        this.receiverIdNumber = receiverIdNumber;
    }
    
    @JsonProperty("ReceiverNationality")
    public String getReceiverNationality() {
        return receiverNationality;
    }
    
    public void setReceiverNationality(String receiverNationality) {
        this.receiverNationality = receiverNationality;
    }
    
    @JsonProperty("PaymentMode")
    public String getPaymentMode() {
        return paymentMode;
    }
    
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    
    @JsonProperty("PayoutCurrency")
    public String getPayoutCurrency() {
        return payoutCurrency;
    }
    
    public void setPayoutCurrency(String payoutCurrency) {
        this.payoutCurrency = payoutCurrency;
    }
    
    @JsonProperty("SenderBeneficiaryRelationship")
    public String getSenderBeneficiaryRelationship() {
        return senderBeneficiaryRelationship;
    }
    
    public void setSenderBeneficiaryRelationship(String senderBeneficiaryRelationship) {
        this.senderBeneficiaryRelationship = senderBeneficiaryRelationship;
    }
    
    @Override
    public void apiSetter(String value, String dbField) {
        switch (dbField) {
            // Common Fields
            case "ReferenceId":
                super.setReferenceId(value);
                break;
            case "ReceivingAmount":
                super.setAmount(Double.parseDouble(value));
                break;
            case "BankAccountNumber":
                super.setBankAccountNumber(value);
                break;
            case "SenderFirstName":
                super.setSenderFirstName(value);
                break;
            case "SenderLastName":
                super.setSenderLastName(value);
                break;
            case "SenderIdNumber":
                super.setSenderIdNumber(value);
                break;
            case "SenderAddress":
                super.setSenderAddress(value);
                break;
            case "SenderCountry":
                super.setSenderCountry(value);
                break;
            case "SenderNationality":
                super.setSenderNationality(value);
                break;
            case "SenderIdType":
                super.setSenderIdType(Integer.parseInt(value));
                break;
            case "ReceiverFirstName":
                super.setReceiverFirstName(value);
                break;
            case "ReceiverLastName":
                super.setReceiverLastName(value);
                break;
            case "ReceiverIdType":
                super.setReceiverIdType(Integer.parseInt(value));
                break;
            case "SenderSourceOfFund":
                super.setSourceOfFund(Integer.parseInt(value));
                break;
            case "PurposeOfRemittance":
                super.setPurposeOfRemit(Integer.parseInt(value));
                break;
            // FinanceNow Fields
            case "SenderState":
                setSenderState(value);
                break;
            case "SenderCity":
                setSenderCity(value);
                break;
            case "SenderDateOfBirth":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                setSenderDateOfBirth(LocalDate.parse(value, formatter));
                break;
            case "ReceiverAddress":
                setReceiverAddress(value);
                break;
            case "ReceiverCity":
                setReceiverCity(value);
                break;
            case "ReceiverCountry":
                setReceiverCountry(value);
                break;
            case "ReceiverIdNumber":
                setReceiverIdNumber(value);
                break;
            case "ReceiverNationality":
                setReceiverNationality(value);
                break;
            case "PaymentMode":
                setPaymentMode(value);
                break;
            case "PayoutCurrency":
                setPayoutCurrency(value);
                break;
            case "SenderBeneficiaryRelationship":
                setSenderBeneficiaryRelationship(value);
                break;
        }
    }
}
