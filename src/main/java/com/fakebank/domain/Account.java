package com.fakebank.domain;

/**
 * Created by Javan on 9/17/16.
 */

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    private Date created;
    private Date updated;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;


    private Account(){

    }


    @ManyToOne(optional = false)
    private User user;

    public Account(AccountType accountType, BigDecimal balance){
        this.setAccountType(accountType);
        this.setBalance(balance);
    }


    public Account(BigDecimal balance){
        this.setBalance(balance);
    }

    public enum AccountType {
        SAVINGS, CHECKING


    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountType getAccountType(){
        return accountType;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
    protected void onCreate(){
        created = updated = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        updated = new Date();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }



    @Override
    public String toString() {
        return "Account [balance=" + balance + "]";
    }

}