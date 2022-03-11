package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "CorporateUser")
@Table(name = "corporate_user")
public class CorporateUser {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long corporateUserId;
    
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public CorporateUser () {}

    public CorporateUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public long getCorporateUserId() {
        return corporateUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
