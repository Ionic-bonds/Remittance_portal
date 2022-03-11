package com.model;

public class PaymentGo {
    private String remitGivenName;
    private String remitSurname;
    private String remitCaType;
    private String remitCaNo;
    private String remitCountryCode;
    private String remitAddress;
    private String nationality;
    private String remitPurpose;
    private String settleCurrency;
    private String transCurrency;
    private String payeeGivenName;
    private String payeeSurname;   
    private String payeeBirthDate;
    private String payeeAccountNo;
    private String payeePhone;
    private String payeeBankName;
    private String payeeBranchName;
    private String payeeCaNo;
    private String remitAccountNo;
    private String sourceOfFunds;

    public PaymentGo(){

    }

    public PaymentGo(String remitGivenName, String remitSurname, String remitCaType, String remitCaNo,
            String remitCountryCode, String remitAddress, String nationality, String remitPurpose,
            String settleCurrency, String transCurrency, String payeeGivenName, String payeeSurname,
            String payeeBirthDate, String payeeAccountNo, String payeePhone, String payeeBankName,
            String payeeBranchName, String payeeCaNo, String remitAccountNo, String sourceOfFunds) {
        this.remitGivenName = remitGivenName;
        this.remitSurname = remitSurname;
        this.remitCaType = remitCaType;
        this.remitCaNo = remitCaNo;
        this.remitCountryCode = remitCountryCode;
        this.remitAddress = remitAddress;
        this.nationality = nationality;
        this.remitPurpose = remitPurpose;
        this.settleCurrency = settleCurrency;
        this.transCurrency = transCurrency;
        this.payeeGivenName = payeeGivenName;
        this.payeeSurname = payeeSurname;
        this.payeeBirthDate = payeeBirthDate;
        this.payeeAccountNo = payeeAccountNo;
        this.payeePhone = payeePhone;
        this.payeeBankName = payeeBankName;
        this.payeeBranchName = payeeBranchName;
        this.payeeCaNo = payeeCaNo;
        this.remitAccountNo = remitAccountNo;
        this.sourceOfFunds = sourceOfFunds;
    }

    public String getRemitGivenName() {
        return remitGivenName;
    }

    public void setRemitGivenName(String remitGivenName) {
        this.remitGivenName = remitGivenName;
    }

    public String getRemitSurname() {
        return remitSurname;
    }

    public void setRemitSurname(String remitSurname) {
        this.remitSurname = remitSurname;
    }

    public String getRemitCaType() {
        return remitCaType;
    }

    public void setRemitCaType(String remitCaType) {
        this.remitCaType = remitCaType;
    }

    public String getRemitCaNo() {
        return remitCaNo;
    }

    public void setRemitCaNo(String remitCaNo) {
        this.remitCaNo = remitCaNo;
    }

    public String getRemitCountryCode() {
        return remitCountryCode;
    }

    public void setRemitCountryCode(String remitCountryCode) {
        this.remitCountryCode = remitCountryCode;
    }

    public String getRemitAddress() {
        return remitAddress;
    }

    public void setRemitAddress(String remitAddress) {
        this.remitAddress = remitAddress;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRemitPurpose() {
        return remitPurpose;
    }

    public void setRemitPurpose(String remitPurpose) {
        this.remitPurpose = remitPurpose;
    }

    public String getSettleCurrency() {
        return settleCurrency;
    }

    public void setSettleCurrency(String settleCurrency) {
        this.settleCurrency = settleCurrency;
    }

    public String getTransCurrency() {
        return transCurrency;
    }

    public void setTransCurrency(String transCurrency) {
        this.transCurrency = transCurrency;
    }

    public String getPayeeGivenName() {
        return payeeGivenName;
    }

    public void setPayeeGivenName(String payeeGivenName) {
        this.payeeGivenName = payeeGivenName;
    }

    public String getPayeeSurname() {
        return payeeSurname;
    }

    public void setPayeeSurname(String payeeSurname) {
        this.payeeSurname = payeeSurname;
    }

    public String getPayeeBirthDate() {
        return payeeBirthDate;
    }

    public void setPayeeBirthDate(String payeeBirthDate) {
        this.payeeBirthDate = payeeBirthDate;
    }

    public String getPayeeAccountNo() {
        return payeeAccountNo;
    }

    public void setPayeeAccountNo(String payeeAccountNo) {
        this.payeeAccountNo = payeeAccountNo;
    }

    public String getPayeePhone() {
        return payeePhone;
    }

    public void setPayeePhone(String payeePhone) {
        this.payeePhone = payeePhone;
    }

    public String getPayeeBankName() {
        return payeeBankName;
    }

    public void setPayeeBankName(String payeeBankName) {
        this.payeeBankName = payeeBankName;
    }

    public String getPayeeBranchName() {
        return payeeBranchName;
    }

    public void setPayeeBranchName(String payeeBranchName) {
        this.payeeBranchName = payeeBranchName;
    }

    public String getPayeeCaNo() {
        return payeeCaNo;
    }

    public void setPayeeCaNo(String payeeCaNo) {
        this.payeeCaNo = payeeCaNo;
    }

    public String getRemitAccountNo() {
        return remitAccountNo;
    }

    public void setRemitAccountNo(String remitAccountNo) {
        this.remitAccountNo = remitAccountNo;
    }

    public String getSourceOfFunds() {
        return sourceOfFunds;
    }

    public void setSourceOfFunds(String sourceOfFunds) {
        this.sourceOfFunds = sourceOfFunds;
    }

    @Override
    public String toString() {
        return "PaymentGo [nationality=" + nationality + ", payeeAccountNo=" + payeeAccountNo + ", payeeBankName="
                + payeeBankName + ", payeeBirthDate=" + payeeBirthDate + ", payeeBranchName=" + payeeBranchName
                + ", payeeCaNo=" + payeeCaNo + ", payeeGivenName=" + payeeGivenName + ", payeePhone=" + payeePhone
                + ", payeeSurname=" + payeeSurname + ", remitAccountNo=" + remitAccountNo + ", remitAddress="
                + remitAddress + ", remitCaNo=" + remitCaNo + ", remitCaType=" + remitCaType + ", remitCountryCode="
                + remitCountryCode + ", remitGivenName=" + remitGivenName + ", remitPurpose=" + remitPurpose
                + ", remitSurname=" + remitSurname + ", settleCurrency=" + settleCurrency + ", sourceOfFunds="
                + sourceOfFunds + ", transCurrency=" + transCurrency + "]";
    }
    
    
    
}
