package com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "field_mapping", joinColumns = { 
        @JoinColumn(name = "corporate_field_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "api_field_id") })
    private Set<ApiField> apiFields = new HashSet<>();

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

    public Set<ApiField> getApiFields() {
        return apiFields;
    }

    public void setApiFields(Set<ApiField> apiFields) {
        this.apiFields = apiFields;
    }

    public void addApiField(ApiField apiField) {
        this.apiFields.add(apiField);
        apiField.getCorporateFields().add(this);
    }
    
    public void removeApiField(long apiFieldId) {
        ApiField apiField = this.apiFields.stream().filter(t -> t.getApiFieldId() == apiFieldId).findFirst().orElse(null);
        if (apiField != null) this.apiFields.remove(apiField);
        apiField.getCorporateFields().remove(this);
    }
}
