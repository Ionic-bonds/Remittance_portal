package com.model;

public class FinanceNow extends CommonApi {
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

    public FinanceNow() {}

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

    @Override
    public void apiSetter(Object o, String field) {
        if (field.equals("BankAccountNumber")) {
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
