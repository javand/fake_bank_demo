package com.fakebank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.TransactionStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
public class Transaction {

    @Id @GeneratedValue
    private long id;
    private BigDecimal amount;
    @ManyToOne(optional = false)
    private Account account;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    private Date created;
    private Date updated;

    private Transaction(){}

    public Transaction(BigDecimal amount, Account account){
        this.setAmount(amount);
        this.setAccount(account);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }

    public enum TransactionStatus {
        OK, INSUFFICIENT_FUNDS
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }


    public TransactionType  getTransactionType() {
        return transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @PrePersist
    protected void onCreate(){
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        updated = new Date();
    }


}
