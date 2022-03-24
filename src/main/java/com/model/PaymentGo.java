package com.model;

public class PaymentGo extends CommonApi {
    private String remitGivenName;
    private String remitSurname;
    private String remitCaType;
    private String remitCaNo;
    private String merTransAmount;
    private String remitCountryCode;
    private String remitAddress;
    private String nationality;
    private String remitPurpose;
    private String payeeCaType;
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

    public PaymentGo() {}

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

    public String getMerTransAmount() {
        return merTransAmount;
    }

    public void setMerTransAmount(String merTransAmount) {
        this.merTransAmount = merTransAmount;
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

    public String getPayeeCaType() {
        return payeeCaType;
    }

    public void setPayeeCaType(String payeeCaType) {
        this.payeeCaType = payeeCaType;
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

    public void apiSetter(String value, String dbField) {
        if (dbField.equals("remitGivenName")) {
            setRemitGivenName(value);
        } else if (dbField.equals("remitSurname")) {
            setRemitSurname(value);
        } else if (dbField.equals("remitCaType")) {
            setRemitCaType(value);
        } else if (dbField.equals("remitCaNo")) {
            setRemitCaNo(value);
        } else if (dbField.equals("remitCountryCode")) {
            setRemitCountryCode(value);
        } else if (dbField.equals("remitAddress")) {
            setRemitAddress(value);
        } else if (dbField.equals("nationality")) {
            setNationality(value);
        } else if (dbField.equals("remitPurpose")) {
            setRemitPurpose(value);
        } else if (dbField.equals("payeeCaType")) {
            setPayeeCaType(value);
        } else if (dbField.equals("settleCurrency")) {
            setSettleCurrency(value);
        } else if (dbField.equals("transCurrency")) {
            setTransCurrency(value);
        } else if (dbField.equals("payeeGivenName")) {
            setPayeeGivenName(value);
        } else if (dbField.equals("payeeSurname")) { 
            setPayeeSurname(value);
        } else if (dbField.equals("payeeBirthDate")) {
            setPayeeBirthDate(value);
        } else if (dbField.equals("payeeAccountNo")) {
            setPayeeAccountNo(value);
        } else if (dbField.equals("payeePhone")) {
            setPayeePhone(value);
        } else if (dbField.equals("payeeBankName")) {
            setPayeeBankName(value);
        } else if (dbField.equals("payeeBranchName")) {
            setPayeeBranchName(value);
        } else if (dbField.equals("payeeCaNo")) {
            setPayeeCaNo(value);
        } else if (dbField.equals("merTransAmount")) {
            setMerTransAmount(value);
        } else if (dbField.equals("remitAccountNo")) {
            setRemitAccountNo(value);
        } else if (dbField.equals("sourceOfFunds")) {
            setSourceOfFunds(value);
        // Common Fields
        } else if (dbField.equals("referenceId")) {
            super.setReferenceId(value);
        } else if (dbField.equals("receivingAmount")) {
            super.setAmount(Double.parseDouble(value));
        }
    }

    // @Override
    // public String toString() {
    //     return "PaymentGo [nationality=" + nationality + ", payeeAccountNo=" + payeeAccountNo + ", payeeBankName="
    //             + payeeBankName + ", payeeBirthDate=" + payeeBirthDate + ", payeeBranchName=" + payeeBranchName
    //             + ", payeeCaNo=" + payeeCaNo + ", payeeGivenName=" + payeeGivenName + ", payeePhone=" + payeePhone
    //             + ", payeeSurname=" + payeeSurname + ", remitAccountNo=" + remitAccountNo + ", remitAddress="
    //             + remitAddress + ", remitCaNo=" + remitCaNo + ", remitCaType=" + remitCaType + ", remitCountryCode="
    //             + remitCountryCode + ", remitGivenName=" + remitGivenName + ", remitPurpose=" + remitPurpose
    //             + ", remitSurname=" + remitSurname + ", settleCurrency=" + settleCurrency + ", sourceOfFunds="
    //             + sourceOfFunds + ", transCurrency=" + transCurrency + "]";
    // }
    
    
    
}
