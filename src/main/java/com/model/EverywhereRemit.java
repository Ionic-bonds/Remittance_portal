package com.model;

public class EverywhereRemit extends CommonApi {
    private String source_type;
    private String sender_country;
    private String segment;
    private String sender_legal_name_first;
    private String sender_legal_name_last;
    private String sender_date_of_birth;
    private String sender_nationality;
    private String sender_id_type;
    private String sender_id_country;
    private String sender_id_number;
    private String sender_currency;
    private String sender_address_line;   
    private String sender_address_city;
    private String sender_address_country;
    private String recipient_type;
    private String recipient_country;
    private String recipient_legal_name_first;
    private String recipient_legal_name_last;
    private String recipient_mobile_number;
    private String recipient_account_number;
    private String recipient_currency;
    private String units;
    private String source_of_funds;
    private String remittance_purpose;
    // private String reference_id;
    // private double receiving_amount;
    
    public EverywhereRemit() {}

    public String getSource_type() {
        return source_type;
    }
    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }
    public String getSender_country() {
        return sender_country;
    }
    public void setSender_country(String sender_country) {
        this.sender_country = sender_country;
    }
    public String getSegment() {
        return segment;
    }
    public void setSegment(String segment) {
        this.segment = segment;
    }
    public String getSender_legal_name_first() {
        return sender_legal_name_first;
    }
    public void setSender_legal_name_first(String sender_legal_name_first) {
        this.sender_legal_name_first = sender_legal_name_first;
    }
    public String getSender_legal_name_last() {
        return sender_legal_name_last;
    }
    public void setSender_legal_name_last(String sender_legal_name_last) {
        this.sender_legal_name_last = sender_legal_name_last;
    }
    public String getSender_date_of_birth() {
        return sender_date_of_birth;
    }
    public void setSender_date_of_birth(String sender_date_of_birth) {
        this.sender_date_of_birth = sender_date_of_birth;
    }
    public String getSender_nationality() {
        return sender_nationality;
    }
    public void setSender_nationality(String sender_nationality) {
        this.sender_nationality = sender_nationality;
    }
    public String getSender_id_type() {
        return sender_id_type;
    }
    public void setSender_id_type(String sender_id_type) {
        this.sender_id_type = sender_id_type;
    }
    public String getSender_id_country() {
        return sender_id_country;
    }
    public void setSender_id_country(String sender_id_country) {
        this.sender_id_country = sender_id_country;
    }
    public String getSender_id_number() {
        return sender_id_number;
    }
    public void setSender_id_number(String sender_id_number) {
        this.sender_id_number = sender_id_number;
    }
    public String getSender_currency() {
        return sender_currency;
    }
    public void setSender_currency(String sender_currency) {
        this.sender_currency = sender_currency;
    }
    public String getSender_address_line() {
        return sender_address_line;
    }
    public void setSender_address_line(String sender_address_line) {
        this.sender_address_line = sender_address_line;
    }
    public String getSender_address_city() {
        return sender_address_city;
    }
    public void setSender_address_city(String sender_address_city) {
        this.sender_address_city = sender_address_city;
    }
    public String getSender_address_country() {
        return sender_address_country;
    }
    public void setSender_address_country(String sender_address_country) {
        this.sender_address_country = sender_address_country;
    }
    public String getRecipient_type() {
        return recipient_type;
    }
    public void setRecipient_type(String recipient_type) {
        this.recipient_type = recipient_type;
    }
    public String getRecipient_country() {
        return recipient_country;
    }
    public void setRecipient_country(String recipient_country) {
        this.recipient_country = recipient_country;
    }
    public String getRecipient_legal_name_first() {
        return recipient_legal_name_first;
    }
    public void setRecipient_legal_name_first(String recipient_legal_name_first) {
        this.recipient_legal_name_first = recipient_legal_name_first;
    }
    public String getRecipient_legal_name_last() {
        return recipient_legal_name_last;
    }
    public void setRecipient_legal_name_last(String recipient_legal_name_last) {
        this.recipient_legal_name_last = recipient_legal_name_last;
    }
    public String getRecipient_mobile_number() {
        return recipient_mobile_number;
    }
    public void setRecipient_mobile_number(String recipient_mobile_number) {
        this.recipient_mobile_number = recipient_mobile_number;
    }
    public String getRecipient_account_number() {
        return recipient_account_number;
    }
    public void setRecipient_account_number(String recipient_account_number) {
        this.recipient_account_number = recipient_account_number;
    }
    public String getRecipient_currency() {
        return recipient_currency;
    }
    public void setRecipient_currency(String recipient_currency) {
        this.recipient_currency = recipient_currency;
    }
    public String getUnits() {
        return units;
    }
    public void setUnits(String units) {
        this.units = units;
    }
    public String getSource_of_funds() {
        return source_of_funds;
    }
    public void setSource_of_funds(String source_of_funds) {
        this.source_of_funds = source_of_funds;
    }
    public String getRemittance_purpose() {
        return remittance_purpose;
    }
    public void setRemittance_purpose(String remittance_purpose) {
        this.remittance_purpose = remittance_purpose;
    }
    // public String getReference_id() {
    //     return reference_id;
    // }
    // public void setReference_id(String reference_id) {
    //     this.reference_id = reference_id;
    // }
    // public double getReceiving_amount() {
    //     return receiving_amount;
    // }
    // public void setReceiving_amount(double receiving_amount) {
    //     this.receiving_amount = receiving_amount;
    // }

    public void apiSetter(String value, String dbField) {
        if (dbField.equals("source_type")) {
            setSource_type(value);
        } else if (dbField.equals("sender_country")) {
            setSender_country(value);
        } else if (dbField.equals("segment")) {
            setSegment(value);
        } else if (dbField.equals("sender_legal_name_first")) {
            setSender_legal_name_first(value);
        } else if (dbField.equals("sender_legal_name_last")) {
            setSender_legal_name_last(value);
        } else if (dbField.equals("sender_date_of_birth")) {
            setSender_date_of_birth(value);
        } else if (dbField.equals("sender_nationality")) {
            setSender_nationality(value);
        } else if (dbField.equals("sender_id_type")) {
            setSender_id_type(value);
        } else if (dbField.equals("sender_id_country")) {
            setSender_id_country(value);
        } else if (dbField.equals("sender_id_number")) {
            setSender_id_number(value);
        } else if (dbField.equals("sender_currency")) {
            setSender_currency(value);
        } else if (dbField.equals("sender_address_line")) {  
            setSender_address_line(value);
        } else if (dbField.equals("sender_address_city")) {
            setSender_address_city(value);
        } else if (dbField.equals("sender_address_country")) {
            setSender_address_country(value);
        } else if (dbField.equals("recipient_type")) {
            setRecipient_type(value);
        } else if (dbField.equals("recipient_country")) {
            setRecipient_country(value);
        } else if (dbField.equals("recipient_legal_name_first")) {
            setRecipient_legal_name_first(value);
        } else if (dbField.equals("recipient_legal_name_last")) {
            setRecipient_legal_name_last(value);
        } else if (dbField.equals("recipient_mobile_number")) {
            setRecipient_mobile_number(value);
        } else if (dbField.equals("recipient_account_number")) {
            setRecipient_account_number(value);
        } else if (dbField.equals("recipient_currency")) {
            setRecipient_currency(value);
        } else if (dbField.equals("units")) {
            setUnits(value);
        } else if (dbField.equals("source_of_funds")) {
            setSource_of_funds(value);
        } else if (dbField.equals("remittance_purpose")) {
            setRemittance_purpose(value);
        // Common dbFields
        } else if (dbField.equals("reference_id")) {
            super.setReferenceId(dbField);
        } else if (dbField.equals("receiving_amount")) {
            super.setAmount(Double.parseDouble(dbField));
        }
    }

    // @Override
    // public String toString() {
    //     return "EverywhereRemit [recipient_account_number=" + recipient_account_number + ", recipient_country="
    //             + recipient_country + ", recipient_currency=" + recipient_currency + ", recipient_legal_name_first="
    //             + recipient_legal_name_first + ", recipient_legal_name_last=" + recipient_legal_name_last
    //             + ", recipient_mobile_number=" + recipient_mobile_number + ", recipient_type=" + recipient_type
    //             + ", remittance_purpose=" + remittance_purpose + ", segment=" + segment + ", sender_address_city="
    //             + sender_address_city + ", sender_address_country=" + sender_address_country + ", sender_address_line="
    //             + sender_address_line + ", sender_country=" + sender_country + ", sender_currency=" + sender_currency
    //             + ", sender_date_of_birth=" + sender_date_of_birth + ", sender_id_country=" + sender_id_country
    //             + ", sender_id_number=" + sender_id_number + ", sender_id_type=" + sender_id_type
    //             + ", sender_legal_name_first=" + sender_legal_name_first + ", sender_legal_name_last="
    //             + sender_legal_name_last + ", sender_nationality=" + sender_nationality + ", source_of_funds="
    //             + source_of_funds + ", source_type=" + source_type + ", units=" + units + "]";
    // }


}
