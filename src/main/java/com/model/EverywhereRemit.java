package com.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EverywhereRemit extends CommonApi {
    private String senderAddressCity;
    private LocalDate senderDateOfBirth;
    private String senderCurrency;
    private String senderAddressCountry;
    private String senderCountryName;
    private String recipientCountry;
    private String recipientMobileNumber;
    private String recipientCurrency;
    private String sourceType;
    private String segment;
    private double units;

    // Common Fields
    @Override
    @JsonProperty("amount")
    public double getAmount() {
        return super.getAmount();
    }
    
    @Override
    @JsonProperty("reference_id")
    public String getReferenceId() {
        return super.getReferenceId();
    }
    
    @Override
    @JsonProperty("recipient_account_number")
    public String getBankAccountNumber() {
        return super.getBankAccountNumber();
    }
    
    @Override
    @JsonProperty("sender_legal_name_first")
    public String getSenderFirstName() {
        return super.getSenderFirstName();
    }
    
    @Override
    @JsonProperty("sender_legal_name_last")
    public String getSenderLastName() {
        return super.getSenderLastName();
    }
    
    @Override
    @JsonProperty("sender_id_number")
    public String getSenderIdNumber() {
        return super.getSenderIdNumber();
    }
    
    @Override
    @JsonProperty("sender_address_line")
    public String getSenderAddress() {
        return super.getSenderAddress();
    }
    
    @Override
    @JsonProperty("sender_id_country")
    public String getSenderCountry() {
        return super.getSenderCountry();
    }
    
    @Override
    @JsonProperty("sender_nationality")
    public String getSenderNationality() {
        return super.getSenderNationality();
    }
    
    @Override
    @JsonProperty("sender_id_type")
    public int getSenderIdType() {
        return super.getSenderIdType();
    }
    
    @Override
    @JsonProperty("recipient_legal_name_first")
    public String getReceiverFirstName() {
        return super.getReceiverFirstName();
    }
    
    @Override
    @JsonProperty("recipient_legal_name_last")
    public String getReceiverLastName() {
        return super.getReceiverLastName();
    }
    
    @Override
    @JsonProperty("recipient_type")
    public int getReceiverIdType() {
        return super.getReceiverIdType();
    }
    
    @Override
    @JsonProperty("source_of_funds")
    public int getSourceOfFund() {
        return super.getSourceOfFund();
    }
    
    @Override
    @JsonProperty("remittance_purpose")
    public int getPurposeOfRemit() {
        return super.getPurposeOfRemit();
    }
    
    // EverywhereRemit Fields
    @JsonProperty("sender_address_city")
    public String getSenderAddressCity() {
        return senderAddressCity;
    }

    public void setSenderAddressCity(String senderAddressCity) {
        this.senderAddressCity = senderAddressCity;
    }

    @JsonProperty("sender_date_of_birth")
    public LocalDate getSenderDateOfBirth() {
        return senderDateOfBirth;
    }

    public void setSenderDateOfBirth(LocalDate senderDateOfBirth) {
        this.senderDateOfBirth = senderDateOfBirth;
    }

    @JsonProperty("sender_currency")
    public String getSenderCurrency() {
        return senderCurrency;
    }

    public void setSenderCurrency(String senderCurrency) {
        this.senderCurrency = senderCurrency;
    }

    @JsonProperty("sender_address_country")
    public String getSenderAddressCountry() {
        return senderAddressCountry;
    }

    public void setSenderAddressCountry(String senderAddressCountry) {
        this.senderAddressCountry = senderAddressCountry;
    }

    @JsonProperty("sender_country")
    public String getSenderCountryName() {
        return senderCountryName;
    }

    public void setSenderCountryName(String senderCountryName) {
        this.senderCountryName = senderCountryName;
    }

    @JsonProperty("recipient_country")
    public String getRecipientCountry() {
        return recipientCountry;
    }

    public void setRecipientCountry(String recipientCountry) {
        this.recipientCountry = recipientCountry;
    }

    @JsonProperty("recipient_mobile_number")
    public String getRecipientMobileNumber() {
        return recipientMobileNumber;
    }

    public void setRecipientMobileNumber(String recipientMobileNumber) {
        this.recipientMobileNumber = recipientMobileNumber;
    }

    @JsonProperty("recipient_currency")
    public String getRecipientCurrency() {
        return recipientCurrency;
    }

    public void setRecipientCurrency(String recipientCurrency) {
        this.recipientCurrency = recipientCurrency;
    }

    @JsonProperty("source_type")
    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    @JsonProperty("segment")
    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    @JsonProperty("units")
    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }


    @Override
    public void apiSetter(String value, String dbField) {
        switch (dbField) {
            // Common Fields
            case "reference_id":
                super.setReferenceId(value);
                break;
            case "amount":
                super.setAmount(Double.parseDouble(value));
                break;
            case "recipient_account_number":
                super.setBankAccountNumber(value);
                break;
            case "sender_legal_name_first":
                super.setSenderFirstName(value);
                break;
            case "sender_legal_name_last":
                super.setSenderLastName(value);
                break;
            case "sender_id_number":
                super.setSenderIdNumber(value);
                break;
            case "sender_address_line":
                super.setSenderAddress(value);
                break;
            case "sender_id_country":
                super.setSenderCountry(value);
                break;
            case "sender_nationality":
                super.setSenderNationality(value);
                break;
            case "sender_id_type":
                super.setSenderIdType(Integer.parseInt(value));
                break;
            case "recipient_legal_name_first":
                super.setReceiverFirstName(value);
                break;
            case "recipient_legal_name_last":
                super.setReceiverLastName(value);
                break;
            case "recipient_type":
                super.setReceiverIdType(Integer.parseInt(value));
                break;
            case "source_of_funds":
                super.setSourceOfFund(Integer.parseInt(value));
                break;
            case "remittance_purpose":
                super.setPurposeOfRemit(Integer.parseInt(value));
                break;
            // EverywhereRemit Fields
            case "sender_address_city":
                setSenderAddressCity(value);
                break;
            case "sender_date_of_birth":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                setSenderDateOfBirth(LocalDate.parse(value, formatter));
                break;
            case "sender_currency":
                setSenderCurrency(value);
                break;
            case "sender_address_country":
                setSenderAddressCountry(value);
                break;
            case "sender_country":
                setSenderCountryName(value);
                break;
            case "recipient_country":
                setRecipientCountry(value);
                break;
            case "recipient_mobile_number":
                setRecipientMobileNumber(value);
                break;
            case "recipient_currency":
                setRecipientCurrency(value);
                break;
            case "source_type":
                setSourceType(value);
                break;
            case "segment":
                setSegment(value);
                break;
            case "units":
                setUnits(Double.parseDouble(value));
                break;
        }
    }
}
