package com.model;

public class EverywhereRemit {
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
    
    public EverywhereRemit(){

    }

    public EverywhereRemit(String source_type, String sender_country, String segment, String sender_legal_name_first,
            String sender_legal_name_last, String sender_date_of_birth, String sender_nationality,
            String sender_id_type, String sender_id_country, String sender_id_number, String sender_currency,
            String sender_address_line, String sender_address_city, String sender_address_country,
            String recipient_type, String recipient_country, String recipient_legal_name_first,
            String recipient_legal_name_last, String recipient_mobile_number, String recipient_account_number,
            String recipient_currency, String units, String source_of_funds, String remittance_purpose) {
        this.source_type = source_type;
        this.sender_country = sender_country;
        this.segment = segment;
        this.sender_legal_name_first = sender_legal_name_first;
        this.sender_legal_name_last = sender_legal_name_last;
        this.sender_date_of_birth = sender_date_of_birth;
        this.sender_nationality = sender_nationality;
        this.sender_id_type = sender_id_type;
        this.sender_id_country = sender_id_country;
        this.sender_id_number = sender_id_number;
        this.sender_currency = sender_currency;
        this.sender_address_line = sender_address_line;
        this.sender_address_city = sender_address_city;
        this.sender_address_country = sender_address_country;
        this.recipient_type = recipient_type;
        this.recipient_country = recipient_country;
        this.recipient_legal_name_first = recipient_legal_name_first;
        this.recipient_legal_name_last = recipient_legal_name_last;
        this.recipient_mobile_number = recipient_mobile_number;
        this.recipient_account_number = recipient_account_number;
        this.recipient_currency = recipient_currency;
        this.units = units;
        this.source_of_funds = source_of_funds;
        this.remittance_purpose = remittance_purpose;
    }


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
    @Override
    public String toString() {
        return "EverywhereRemit [recipient_account_number=" + recipient_account_number + ", recipient_country="
                + recipient_country + ", recipient_currency=" + recipient_currency + ", recipient_legal_name_first="
                + recipient_legal_name_first + ", recipient_legal_name_last=" + recipient_legal_name_last
                + ", recipient_mobile_number=" + recipient_mobile_number + ", recipient_type=" + recipient_type
                + ", remittance_purpose=" + remittance_purpose + ", segment=" + segment + ", sender_address_city="
                + sender_address_city + ", sender_address_country=" + sender_address_country + ", sender_address_line="
                + sender_address_line + ", sender_country=" + sender_country + ", sender_currency=" + sender_currency
                + ", sender_date_of_birth=" + sender_date_of_birth + ", sender_id_country=" + sender_id_country
                + ", sender_id_number=" + sender_id_number + ", sender_id_type=" + sender_id_type
                + ", sender_legal_name_first=" + sender_legal_name_first + ", sender_legal_name_last="
                + sender_legal_name_last + ", sender_nationality=" + sender_nationality + ", source_of_funds="
                + source_of_funds + ", source_type=" + source_type + ", units=" + units + "]";
    }
}
