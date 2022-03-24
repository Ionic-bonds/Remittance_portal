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

    public FinanceNow() {}

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

    public void apiSetter(String o, String field) {
        if (field.equals("amount")) {
            super.setAmount(Double.parseDouble(o.toString()));
        } else if (field.equals("BankAccountNumber")) {
            setBankAccountNumber(o.toString());
        } else if (field.equals("PaymentMode")) {
            setPaymentMode(o.toString());
        } else if (field.equals("PayoutCurrency")) {
            setPayoutCurrency(o.toString());
        } else if (field.equals("PurposeOfRemittance")) {
            setPurposeOfRemittance(o.toString());
        } else if (field.equals("ReceiverAddress")) {
            setReceiverAddress(o.toString());
        } else if (field.equals("ReceiverCity")) {
            setReceiverCity(o.toString());
        } else if (field.equals("ReceiverCountry")) {
            setReceiverCountry(o.toString());
        } else if (field.equals("ReceiverFirstName")) {
            setReceiverFirstName(o.toString());
        } else if (field.equals("ReceiverIdNumber")) {
            setReceiverIdNumber(o.toString());
        } else if (field.equals("ReceiverIdType")) {
            setReceiverIdType(o.toString());
        } else if (field.equals("ReceiverLastName")) {
            setReceiverLastName(o.toString());
        } else if (field.equals("SenderAddress")) {
            setSenderAddress(o.toString());
        } else if (field.equals("SenderCity")) {   
            setSenderCity(o.toString());  
        } else if (field.equals("SenderDateOfBirth")) {
            setSenderDateOfBirth(o.toString());
        } else if (field.equals("SenderFirstName")) {
            setSenderFirstName(o.toString());
        } else if (field.equals("SenderIdNumber")) {
            setSenderIdNumber(o.toString());
        } else if (field.equals("SenderIdType")) {
            setSenderIdType(o.toString());
        } else if (field.equals("SenderLastName")) {
            setSenderLastName(o.toString());
        } else if (field.equals("SenderNationality")) {  
            setSenderNationality(o.toString()); 
        } else if (field.equals("SenderSourceOfFund")) {
            setSenderSourceOfFund(o.toString());
        } else if (field.equals("SenderState")) {
            setSenderState(o.toString());
        } else if (field.equals("ReceiverNationality")) {
            setReceiverNationality(o.toString());
        }
    }

    // @Override
    // public String toString() {
    //     return "FinanceNow [BankAccountNumber=" + ankAccountNumber + ", PaymentMode=" + PaymentMode
    //             + ", PayoutCurrency=" + PayoutCurrency + ", PurposeOfRemittance=" + PurposeOfRemittance
    //             + ", ReceiverAddress=" + ReceiverAddress + ", ReceiverCity=" + ReceiverCity + ", ReceiverCountry="
    //             + ReceiverCountry + ", ReceiverFirstName=" + ReceiverFirstName + ", ReceiverIdNumber="
    //             + ReceiverIdNumber + ", ReceiverIdType=" + ReceiverIdType + ", ReceiverLastName=" + ReceiverLastName
    //             + ", ReceiverNationality=" + ReceiverNationality + ", SenderAddress=" + SenderAddress + ", SenderCity="
    //             + SenderCity + ", SenderDateOfBirth=" + SenderDateOfBirth + ", SenderFirstName=" + SenderFirstName
    //             + ", SenderIdNumber=" + SenderIdNumber + ", SenderIdType=" + SenderIdType + ", SenderLastName="
    //             + SenderLastName + ", SenderNationality=" + SenderNationality + ", SenderSourceOfFund="
    //             + SenderSourceOfFund + ", SenderState=" + SenderState + "]";
    // }
}
