package com.model;

public class CommonApi {
    // Fields Present in All 3 APIs
    private double amount;
    private String referenceId;
    private String senderFirstName;
    private String senderLastName;
    private String senderIdNumber;
    private String senderAddress;
    private String senderCountry;
    private String senderNationality;
    private int senderIdType;
    private String bankAccountNumber;
    private String receiverFirstName;
    private String receiverLastName;
    private int receiverIdType;
    private int sourceOfFund;
    private int purposeOfRemit;
    
    public CommonApi() {};

    public CommonApi(double amount, String referenceId, String senderFirstName, String senderLastName,
            String senderIdNumber, String senderAddress, String senderCountry, String senderNationality,
            int senderIdType, String bankAccountNumber, String receiverFirstName, String receiverLastName,
            int receiverIdType, int sourceOfFund, int purposeOfRemit) {
        this.amount = amount;
        this.referenceId = referenceId;
        this.senderFirstName = senderFirstName;
        this.senderLastName = senderLastName;
        this.senderIdNumber = senderIdNumber;
        this.senderAddress = senderAddress;
        this.senderCountry = senderCountry;
        this.senderNationality = senderNationality;
        this.senderIdType = senderIdType;
        this.bankAccountNumber = bankAccountNumber;
        this.receiverFirstName = receiverFirstName;
        this.receiverLastName = receiverLastName;
        this.receiverIdType = receiverIdType;
        this.sourceOfFund = sourceOfFund;
        this.purposeOfRemit = purposeOfRemit;
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

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public void setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }

    public String getSenderIdNumber() {
        return senderIdNumber;
    }

    public void setSenderIdNumber(String senderIdNumber) {
        this.senderIdNumber = senderIdNumber;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getSenderNationality() {
        return senderNationality;
    }

    public void setSenderNationality(String senderNationality) {
        this.senderNationality = senderNationality;
    }

    public int getSenderIdType() {
        return senderIdType;
    }

    public void setSenderIdType(int senderIdType) {
        this.senderIdType = senderIdType;
    }

    public String getReceiverFirstName() {
        return receiverFirstName;
    }

    public void setReceiverFirstName(String receiverFirstName) {
        this.receiverFirstName = receiverFirstName;
    }

    public String getReceiverLastName() {
        return receiverLastName;
    }

    public void setReceiverLastName(String receiverLastName) {
        this.receiverLastName = receiverLastName;
    }

    public int getReceiverIdType() {
        return receiverIdType;
    }

    public void setReceiverIdType(int receiverIdType) {
        this.receiverIdType = receiverIdType;
    }

    public int getSourceOfFund() {
        return sourceOfFund;
    }

    public void setSourceOfFund(int sourceOfFund) {
        this.sourceOfFund = sourceOfFund;
    }

    public int getPurposeOfRemit() {
        return purposeOfRemit;
    }

    public void setPurposeOfRemit(int purposeOfRemit) {
        this.purposeOfRemit = purposeOfRemit;
    }

    public void apiSetter(String value, String dbField) {}
}
