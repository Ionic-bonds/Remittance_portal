package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name="ApiField")
@Table(name = "api_field")
public class ApiField {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long apiFieldId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "api_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Api api;

    @Column(name = "api_field_name")
    private String apiFieldName;

    @Column(name = "datatype")
    private String datatype;

    @Column(name = "mandatory")
    private boolean mandatory;


    public long getApiFieldId() {
        return apiFieldId;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api apiId) {
        this.api = apiId;
    }

    public String getApiFieldName() {
        return apiFieldName;
    }

    public void setApiFieldName(String apiFieldName) {
        this.apiFieldName = apiFieldName;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
}