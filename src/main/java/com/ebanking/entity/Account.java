/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Huy Hoang
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int accountNo;
    
    @Temporal(TemporalType.DATE)
    private Date openDate; 
    
    private int balance;
    
    private String type;
    
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "bankId")
    private Bank bank;
    
    @OneToMany(mappedBy = "account1")
    private List<Transaction> transactionFrom;
    
    @OneToMany(mappedBy = "account2")
    private List<Transaction> transactionTo;

    public Account() {
    }

    public Account(int id, int accountNo, Date openDate, int balance, String type, String status, Customer customer, Bank bank, List<Transaction> transactionFrom, List<Transaction> transactionTo) {
        this.id = id;
        this.accountNo = accountNo;
        this.openDate = openDate;
        this.balance = balance;
        this.type = type;
        this.status = status;
        this.customer = customer;
        this.bank = bank;
        this.transactionFrom = transactionFrom;
        this.transactionTo = transactionTo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Transaction> getTransactionFrom() {
        return transactionFrom;
    }

    public void setTransactionFrom(List<Transaction> transactionFrom) {
        this.transactionFrom = transactionFrom;
    }

    public List<Transaction> getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(List<Transaction> transactionTo) {
        this.transactionTo = transactionTo;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
    
    
}
