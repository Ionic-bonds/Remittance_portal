package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity(name = "SelectedField")
@Table(name = "selected_field")
public class SelectedField {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long selectedFieldId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "api_field_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ApiField apiField;

    @Column(name = "selected_field_code")
    private String selectedFieldCode;

    @Column(name = "selected_field_value")
    private String selectedFieldValue;

    public long getSelectedFieldId() {
        return selectedFieldId;
    }

    public ApiField getApiField() {
        return apiField;
    }

    public void setApiField(ApiField apiField) {
        this.apiField = apiField;
    }

    public String getSelectedFieldCode() {
        return selectedFieldCode;
    }

    public void setSelectedFieldCode(String selectedFieldCode) {
        this.selectedFieldCode = selectedFieldCode;
    }

    public String getSelectedFieldValue() {
        return selectedFieldValue;
    }

    public void setSelectedFieldValue(String selectedFieldValue) {
        this.selectedFieldValue = selectedFieldValue;
    }
}