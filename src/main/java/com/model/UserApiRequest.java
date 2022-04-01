package com.model;

import java.time.LocalDateTime;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "UserApiRequest")
@Table(name = "user_api_request")
public class UserApiRequest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long userApiRequestId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "corporate_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CorporateUser corporateUser;

    @Column(name = "status_code")
    private int statusCode;

    @Column(name = "message")
    private String message;

    @CreationTimestamp
    @Column(name="request_date")
    private LocalDateTime requestDate;

    public long getUserApiRequestId() {
        return userApiRequestId;
    }

    public CorporateUser getCorporateUser() {
        return corporateUser;
    }

    public void setCorporateUser(CorporateUser corporateUser) {
        this.corporateUser = corporateUser;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }  
}