package com.model;

public class FinanceNow {
    private String BankAccountNumber;
    private String PaymentMode;
    private String PayoutCurrency;
    private String PurposeOfRemittance;
    private String ReceiverAddress;
    private String ReceiverCity;
    private String ReceiverCountry;
    private String ReceiverFirstName;
    private String ReceiverIdNumber;
    private String ReceiverIdType;
    private String ReceiverLastName;
    private String SenderAddress;
    private String SenderCity;   
    private String SenderDateOfBirth;
    private String SenderFirstName;
    private String SenderIdNumber;
    private String SenderIdType;
    private String SenderLastName;
    private String SenderNationality;   
    private String SenderSourceOfFund;
    private String SenderState;
    private String ReceiverNationality;

    public String getBankAccountNumber() {
        return BankAccountNumber;
    }
    public void setBankAccountNumber(String bankAccountNumber) {
        BankAccountNumber = bankAccountNumber;
    }
    public String getPaymentMode() {
        return PaymentMode;
    }
    public void setPaymentMode(String paymentMode) {
        PaymentMode = paymentMode;
    }
    public String getPayoutCurrency() {
        return PayoutCurrency;
    }
    public void setPayoutCurrency(String payoutCurrency) {
        PayoutCurrency = payoutCurrency;
    }
    public String getPurposeOfRemittance() {
        return PurposeOfRemittance;
    }
    public void setPurposeOfRemittance(String purposeOfRemittance) {
        PurposeOfRemittance = purposeOfRemittance;
    }
    public String getReceiverAddress() {
        return ReceiverAddress;
    }
    public void setReceiverAddress(String receiverAddress) {
        ReceiverAddress = receiverAddress;
    }
    public String getReceiverCity() {
        return ReceiverCity;
    }
    public void setReceiverCity(String receiverCity) {
        ReceiverCity = receiverCity;
    }
    public String getReceiverCountry() {
        return ReceiverCountry;
    }
    public void setReceiverCountry(String receiverCountry) {
        ReceiverCountry = receiverCountry;
    }
    public String getReceiverFirstName() {
        return ReceiverFirstName;
    }
    public void setReceiverFirstName(String receiverFirstName) {
        ReceiverFirstName = receiverFirstName;
    }
    public String getReceiverIdNumber() {
        return ReceiverIdNumber;
    }
    public void setReceiverIdNumber(String receiverIdNumber) {
        ReceiverIdNumber = receiverIdNumber;
    }
    public String getReceiverIdType() {
        return ReceiverIdType;
    }
    public void setReceiverIdType(String receiverIdType) {
        ReceiverIdType = receiverIdType;
    }
    public String getReceiverLastName() {
        return ReceiverLastName;
    }
    public void setReceiverLastName(String receiverLastName) {
        ReceiverLastName = receiverLastName;
    }
    public String getSenderAddress() {
        return SenderAddress;
    }
    public void setSenderAddress(String senderAddress) {
        SenderAddress = senderAddress;
    }
    public String getSenderCity() {
        return SenderCity;
    }
    public void setSenderCity(String senderCity) {
        SenderCity = senderCity;
    }
    public String getSenderDateOfBirth() {
        return SenderDateOfBirth;
    }
    public void setSenderDateOfBirth(String senderDateOfBirth) {
        SenderDateOfBirth = senderDateOfBirth;
    }
    public String getSenderFirstName() {
        return SenderFirstName;
    }
    public void setSenderFirstName(String senderFirstName) {
        SenderFirstName = senderFirstName;
    }
    public String getSenderIdNumber() {
        return SenderIdNumber;
    }
    public void setSenderIdNumber(String senderIdNumber) {
        SenderIdNumber = senderIdNumber;
    }
    public String getSenderIdType() {
        return SenderIdType;
    }
    public void setSenderIdType(String senderIdType) {
        SenderIdType = senderIdType;
    }
    public String getSenderLastName() {
        return SenderLastName;
    }
    public void setSenderLastName(String senderLastName) {
        SenderLastName = senderLastName;
    }
    public String getSenderNationality() {
        return SenderNationality;
    }
    public void setSenderNationality(String senderNationality) {
        SenderNationality = senderNationality;
    }
    public String getSenderSourceOfFund() {
        return SenderSourceOfFund;
    }
    public void setSenderSourceOfFund(String senderSourceOfFund) {
        SenderSourceOfFund = senderSourceOfFund;
    }
    public String getSenderState() {
        return SenderState;
    }
    public void setSenderState(String senderState) {
        SenderState = senderState;
    }
    public String getReceiverNationality() {
        return ReceiverNationality;
    }
    public void setReceiverNationality(String receiverNationality) {
        ReceiverNationality = receiverNationality;
    }
    public FinanceNow(String bankAccountNumber, String paymentMode, String payoutCurrency, String purposeOfRemittance,
            String receiverAddress, String receiverCity, String receiverCountry, String receiverFirstName,
            String receiverIdNumber, String receiverIdType, String receiverLastName, String senderAddress,
            String senderCity, String senderDateOfBirth, String senderFirstName, String senderIdNumber,
            String senderIdType, String senderLastName, String senderNationality, String senderSourceOfFund,
            String senderState, String receiverNationality) {
        this.BankAccountNumber = bankAccountNumber;
        this.PaymentMode = paymentMode;
        this.PayoutCurrency = payoutCurrency;
        this.PurposeOfRemittance = purposeOfRemittance;
        this.ReceiverAddress = receiverAddress;
        this.ReceiverCity = receiverCity;
        this.ReceiverCountry = receiverCountry;
        this.ReceiverFirstName = receiverFirstName;
        this.ReceiverIdNumber = receiverIdNumber;
        this.ReceiverIdType = receiverIdType;
        this.ReceiverLastName = receiverLastName;
        this.SenderAddress = senderAddress;
        this.SenderCity = senderCity;
        this.SenderDateOfBirth = senderDateOfBirth;
        this.SenderFirstName = senderFirstName;
        this.SenderIdNumber = senderIdNumber;
        this.SenderIdType = senderIdType;
        this.SenderLastName = senderLastName;
        this.SenderNationality = senderNationality;
        this.SenderSourceOfFund = senderSourceOfFund;
        this.SenderState = senderState;
        this.ReceiverNationality = receiverNationality;
    }
    @Override
    public String toString() {
        return "FinanceNow [BankAccountNumber=" + BankAccountNumber + ", PaymentMode=" + PaymentMode
                + ", PayoutCurrency=" + PayoutCurrency + ", PurposeOfRemittance=" + PurposeOfRemittance
                + ", ReceiverAddress=" + ReceiverAddress + ", ReceiverCity=" + ReceiverCity + ", ReceiverCountry="
                + ReceiverCountry + ", ReceiverFirstName=" + ReceiverFirstName + ", ReceiverIdNumber="
                + ReceiverIdNumber + ", ReceiverIdType=" + ReceiverIdType + ", ReceiverLastName=" + ReceiverLastName
                + ", ReceiverNationality=" + ReceiverNationality + ", SenderAddress=" + SenderAddress + ", SenderCity="
                + SenderCity + ", SenderDateOfBirth=" + SenderDateOfBirth + ", SenderFirstName=" + SenderFirstName
                + ", SenderIdNumber=" + SenderIdNumber + ", SenderIdType=" + SenderIdType + ", SenderLastName="
                + SenderLastName + ", SenderNationality=" + SenderNationality + ", SenderSourceOfFund="
                + SenderSourceOfFund + ", SenderState=" + SenderState + "]";
    }



        

}