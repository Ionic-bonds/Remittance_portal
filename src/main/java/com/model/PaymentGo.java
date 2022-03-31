package com.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentGo extends CommonApi {
    private String payeePhone;
    private LocalDate payeeBirthDate;
    private String payeeBankName;
    private String payeeBranchName;
    private String payeeCaNo;
    private String remitAccountNo;
    private String transCurrency;
    private String settleCurrency;
    private String merTransAmount;

    // Common Fields
    @Override
    @JsonProperty("receivingAmount")
    public double getAmount() {
        return super.getAmount();
    }
    
    @Override
    @JsonProperty("referenceId")
    public String getReferenceId() {
        return super.getReferenceId();
    }
    
    @Override
    @JsonProperty("payeeAccountNo")
    public String getBankAccountNumber() {
        return super.getBankAccountNumber();
    }
    
    @Override
    @JsonProperty("remitGivenName")
    public String getSenderFirstName() {
        return super.getSenderFirstName();
    }
    
    @Override
    @JsonProperty("remitSurname")
    public String getSenderLastName() {
        return super.getSenderLastName();
    }
    
    @Override
    @JsonProperty("remitCaNo")
    public String getSenderIdNumber() {
        return super.getSenderIdNumber();
    }
    
    @Override
    @JsonProperty("remitAddress")
    public String getSenderAddress() {
        return super.getSenderAddress();
    }
    
    @Override
    @JsonProperty("remitCountryCode")
    public String getSenderCountry() {
        return super.getSenderCountry();
    }
    
    @Override
    @JsonProperty("nationality")
    public String getSenderNationality() {
        return super.getSenderNationality();
    }
    
    @Override
    @JsonProperty("remitCaType")
    public int getSenderIdType() {
        return super.getSenderIdType();
    }
    
    @Override
    @JsonProperty("payeeGivenName")
    public String getReceiverFirstName() {
        return super.getReceiverFirstName();
    }
    
    @Override
    @JsonProperty("payeeSurname")
    public String getReceiverLastName() {
        return super.getReceiverLastName();
    }
    
    @Override
    @JsonProperty("payeeCaType")
    public int getReceiverIdType() {
        return super.getReceiverIdType();
    }
    
    @Override
    @JsonProperty("sourceOfFunds")
    public int getSourceOfFund() {
        return super.getSourceOfFund();
    }
    
    @Override
    @JsonProperty("remitPurpose")
    public int getPurposeOfRemit() {
        return super.getPurposeOfRemit();
    }
    
    // PaymentGo Fields
    @JsonProperty("payeePhone")
    public String getPayeePhone() {
        return payeePhone;
    }

    public void setPayeePhone(String payeePhone) {
        this.payeePhone = payeePhone;
    }

    @JsonProperty("payeeBirthDate")
    public LocalDate getPayeeBirthDate() {
        return payeeBirthDate;
    }

    public void setPayeeBirthDate(LocalDate payeeBirthDate) {
        this.payeeBirthDate = payeeBirthDate;
    }

    @JsonProperty("payeeBankName")
    public String getPayeeBankName() {
        return payeeBankName;
    }

    public void setPayeeBankName(String payeeBankName) {
        this.payeeBankName = payeeBankName;
    }

    @JsonProperty("payeeBranchName")
    public String getPayeeBranchName() {
        return payeeBranchName;
    }

    public void setPayeeBranchName(String payeeBranchName) {
        this.payeeBranchName = payeeBranchName;
    }

    @JsonProperty("payeeCaNo")
    public String getPayeeCaNo() {
        return payeeCaNo;
    }

    public void setPayeeCaNo(String payeeCaNo) {
        this.payeeCaNo = payeeCaNo;
    }

    @JsonProperty("remitAccountNo")
    public String getRemitAccountNo() {
        return remitAccountNo;
    }

    public void setRemitAccountNo(String remitAccountNo) {
        this.remitAccountNo = remitAccountNo;
    }

    @JsonProperty("transCurrency")
    public String getTransCurrency() {
        return transCurrency;
    }

    public void setTransCurrency(String transCurrency) {
        this.transCurrency = transCurrency;
    }

    @JsonProperty("settleCurrency")
    public String getSettleCurrency() {
        return settleCurrency;
    }

    public void setSettleCurrency(String settleCurrency) {
        this.settleCurrency = settleCurrency;
    }

    @JsonProperty("merTransAmount")
    public String getMerTransAmount() {
        return merTransAmount;
    }

    public void setMerTransAmount(String merTransAmount) {
        this.merTransAmount = merTransAmount;
    }

    @Override
    public void apiSetter(String value, String dbField) {
        switch (dbField) {
            // Common Fields
            case "referenceId":
                super.setReferenceId(value);
                break;
            case "receivingAmount":
                super.setAmount(Double.parseDouble(value));
                break;
            case "payeeAccountNo":
                super.setBankAccountNumber(value);
                break;
            case "remitGivenName":
                super.setSenderFirstName(value);
                break;
            case "remitSurname":
                super.setSenderLastName(value);
                break;
            case "remitCaNo":
                super.setSenderIdNumber(value);
                break;
            case "remitAddress":
                super.setSenderAddress(value);
                break;
            case "remitCountryCode":
                super.setSenderCountry(value);
                break;
            case "nationality":
                super.setSenderNationality(value);
                break;
            case "remitCaType":
                super.setSenderIdType(Integer.parseInt(value));
                break;
            case "payeeGivenName":
                super.setReceiverFirstName(value);
                break;
            case "payeeSurname":
                super.setReceiverLastName(value);
                break;
            case "payeeCaType":
                super.setReceiverIdType(Integer.parseInt(value));
                break;
            case "sourceOfFunds":
                super.setSourceOfFund(Integer.parseInt(value));
                break;
            case "remitPurpose":
                super.setPurposeOfRemit(Integer.parseInt(value));
                break;
            // PaymentGo Fields
            case "payeePhone":
                setPayeePhone(value);
                break;
            case "payeeBirthDate":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                setPayeeBirthDate(LocalDate.parse(value, formatter));
                break;
            case "payeeBankName":
                setPayeeBankName(value);
                break;
            case "payeeBranchName":
                setPayeeBranchName(value);
                break;
            case "payeeCaNo":
                setPayeeCaNo(value);
                break;
            case "remitAccountNo":
                setRemitAccountNo(value);
                break;
            case "transCurrency":
                setTransCurrency(value);
                break;
            case "settleCurrency":
                setSettleCurrency(value);
                break;
            case "merTransAmount":
                setMerTransAmount(value);
                break;
        }
    }
}
