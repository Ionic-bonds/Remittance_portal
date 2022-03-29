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

@Entity(name = "Transaction")
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long transactionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "corporate_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CorporateUser corporateUser;

    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "outcome")
    private String outcome;

    @CreationTimestamp
    @Column(name="transaction_date")
    private LocalDateTime transactionDate;

    public long getTransactionId() {
        return transactionId;
    }

    public CorporateUser getCorporateUser() {
        return corporateUser;
    }

    public void setCorporateUser(CorporateUser corporateUser) {
        this.corporateUser = corporateUser;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
