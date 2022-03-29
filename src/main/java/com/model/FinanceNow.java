package com.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@XmlAccessorType()
public class FinanceNow extends CommonApi {
    private String bankAccountNumber;
    private String paymentMode;
    private String payoutCurrency;
    private String purposeOfRemittance;
    private String receiverAddress;
    private String senderBeneficiaryRelationship;
    private String receiverCity;
    private String senderCountry;
    private String receiverCountry;
    private String receiverFirstName;
    private String receiverIdNumber;
    private String receiverIdType;
    private String receiverLastName;
    private String senderAddress;
    private String senderCity;   
    private String senderDateOfBirth;
    private String senderFirstName;
    private String senderIdNumber;
    private String senderIdType;
    private String senderLastName;
    private String senderNationality;   
    private String senderSourceOfFund;
    private String senderState;
    private String receiverNationality;

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

    // FinanceNow Fields
    @JsonProperty("BankAccountNumber")
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
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
    @JsonProperty("PurposeOfRemittance")
    public String getPurposeOfRemittance() {
        return purposeOfRemittance;
    }
    public void setPurposeOfRemittance(String purposeOfRemittance) {
        this.purposeOfRemittance = purposeOfRemittance;
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
    @JsonProperty("ReceiverFirstName")
    public String getReceiverFirstName() {
        return receiverFirstName;
    }
    public void setReceiverFirstName(String receiverFirstName) {
        this.receiverFirstName = receiverFirstName;
    }
    @JsonProperty("ReceiverIdNumber")
    public String getReceiverIdNumber() {
        return receiverIdNumber;
    }
    public void setReceiverIdNumber(String receiverIdNumber) {
        this.receiverIdNumber = receiverIdNumber;
    }
    @JsonProperty("ReceiverIdType")
    public String getReceiverIdType() {
        return receiverIdType;
    }
    public void setReceiverIdType(String receiverIdType) {
        this.receiverIdType = receiverIdType;
    }
    @JsonProperty("ReceiverLastName")
    public String getReceiverLastName() {
        return this.receiverLastName;
    }
    public void setReceiverLastName(String receiverLastName) {
        this.receiverLastName = receiverLastName;
    }
    @JsonProperty("SenderAddress")
    public String getSenderAddress() {
        return senderAddress;
    }
    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }
    @JsonProperty("SenderCity")
    public String getSenderCity() {
        return senderCity;
    }
    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }
    @JsonProperty("SenderBeneficiaryRelationship")
    public String getSenderBeneficiaryRelationship() {
        return senderBeneficiaryRelationship;
    }
    public void setSenderBeneficiaryRelationship(String senderBeneficiaryRelationship) {
        this.senderBeneficiaryRelationship = senderBeneficiaryRelationship;
    }
    @JsonProperty("SenderCountry")
    public String getSenderCountry() {
        return senderCountry;
    }
    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }
    @JsonProperty("SenderDateOfBirth")
    public String getSenderDateOfBirth() {
        return senderDateOfBirth;
    }
    public void setSenderDateOfBirth(String senderDateOfBirth) {
        this.senderDateOfBirth = senderDateOfBirth;
    }
    @JsonProperty("SenderFirstName")
    public String getSenderFirstName() {
        return senderFirstName;
    }
    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }
    @JsonProperty("SenderIdNumber")
    public String getSenderIdNumber() {
        return senderIdNumber;
    }
    public void setSenderIdNumber(String senderIdNumber) {
        this.senderIdNumber = senderIdNumber;
    }@JsonProperty("SenderIdType")
    public String getSenderIdType() {
        return senderIdType;
    }
    public void setSenderIdType(String senderIdType) {
        this.senderIdType = senderIdType;
    }
    @JsonProperty("SenderLastName")
    public String getSenderLastName() {
        return senderLastName;
    }
    public void setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }
    @JsonProperty("SenderNationality")
    public String getSenderNationality() {
        return senderNationality;
    }
    public void setSenderNationality(String senderNationality) {
        this.senderNationality = senderNationality;
    }
    @JsonProperty("SenderSourceOfFund")
    public String getSenderSourceOfFund() {
        return senderSourceOfFund;
    }
    public void setSenderSourceOfFund(String senderSourceOfFund) {
        this.senderSourceOfFund = senderSourceOfFund;
    }
    @JsonProperty("SenderState")
    public String getSenderState() {
        return senderState;
    }
    public void setSenderState(String senderState) {
        this.senderState = senderState;
    }
    @JsonProperty("ReceiverNationality")
    public String getReceiverNationality() {
        return receiverNationality;
    }
    public void setReceiverNationality(String receiverNationality) {
        this.receiverNationality = receiverNationality;
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
            // FinanceNow Fields
            case "BankAccountNumber":
                setBankAccountNumber(value);
                break;
            case "PaymentMode":
                setPaymentMode(value);
                break;
            case "PayoutCurrency":
                setPayoutCurrency(value);
                break;
            case "PurposeOfRemittance":
                setPurposeOfRemittance(value);
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
            case "ReceiverFirstName":
                setReceiverFirstName(value);
                break;
            case "ReceiverIdNumber":
                setReceiverIdNumber(value);
                break;
            case "ReceiverIdType":
                setReceiverIdType(value);
                break;
            case "ReceiverLastName":
                setReceiverLastName(value);
                break;
            case "SenderAddress":
                setSenderAddress(value);
                break;
            case "SenderBeneficiaryRelationship":
                setSenderBeneficiaryRelationship(value);
                break;
            case "SenderCity":
                setSenderCity(value);
                break;
            case "SenderCountry":
                setSenderCountry(value);
                break;
            case "SenderDateOfBirth":
                setSenderDateOfBirth(value);
                break;
            case "SenderFirstName":
                setSenderFirstName(value);
                break;
            case "SenderIdNumber":
                setSenderIdNumber(value);
                break;
            case "SenderIdType":
                setSenderIdType(value);
                break;
            case "SenderLastName":
                setSenderLastName(value);
                break;
            case "SenderNationality":
                setSenderNationality(value);
                break;
            case "SenderSourceOfFund":
                setSenderSourceOfFund(value);
                break;
            case "SenderState":
                setSenderState(value);
                break;
            case "ReceiverNationality":
                setReceiverNationality(value);
                break;
        }
    }

    @Override
    public String toString() {
        return "FinanceNow [bankAccountNumber=" + bankAccountNumber + ", paymentMode=" + paymentMode
                + ", payoutCurrency=" + payoutCurrency + ", purposeOfRemittance=" + purposeOfRemittance
                + ", receiverAddress=" + receiverAddress + ", receiverCity=" + receiverCity + ", receiverCountry="
                + receiverCountry + ", receiverFirstName=" + receiverFirstName + ", receiverIdNumber="
                + receiverIdNumber + ", receiverIdType=" + receiverIdType + ", receiverLastName=" + receiverLastName
                + ", receiverNationality=" + receiverNationality + ", senderAddress=" + senderAddress
                + ", senderBeneficiaryRelationship=" + senderBeneficiaryRelationship + ", senderCity=" + senderCity
                + ", senderCountry=" + senderCountry + ", senderDateOfBirth=" + senderDateOfBirth + ", senderFirstName="
                + senderFirstName + ", senderIdNumber=" + senderIdNumber + ", senderIdType=" + senderIdType
                + ", senderLastName=" + senderLastName + ", senderNationality=" + senderNationality
                + ", senderSourceOfFund=" + senderSourceOfFund + ", senderState=" + senderState + "]";
    }
}
