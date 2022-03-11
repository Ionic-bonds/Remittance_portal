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

@Entity(name="CorporateField")
@Table(name = "corporate_field")
public class CorporateField {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long corporateFieldId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "corporate_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CorporateUser corporateUser;

    @Column(name = "corporate_field_name")
    private String corporateFieldName;

    public long getCorporateFieldId() {
        return corporateFieldId;
    }

    public CorporateUser getCorporateUser() {
        return corporateUser;
    }

    public void setCorporateUser(CorporateUser corporateUser) {
        this.corporateUser = corporateUser;
    }

    public String getCorporateFieldName() {
        return corporateFieldName;
    }

    public void setCorporateFieldName(String corporateFieldName) {
        this.corporateFieldName = corporateFieldName;
    }
}
