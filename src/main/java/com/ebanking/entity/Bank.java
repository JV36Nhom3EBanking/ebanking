/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Huy Hoang
 */
@Entity
@Table(name = "bank")
public class Bank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String branch;
    
    private String district;
    
    private String city;
    
    @OneToMany(mappedBy = "bank")
    private List<Account> accounts;
    
    @OneToMany(mappedBy = "bank")
    private List<Teller> tellers;

    public Bank() {
    }

    public Bank(int id, String branch, String district, String city, List<Account> accounts, List<Teller> tellers) {
        this.id = id;
        this.branch = branch;
        this.district = district;
        this.city = city;
        this.accounts = accounts;
        this.tellers = tellers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Teller> getTellers() {
        return tellers;
    }

    public void setTellers(List<Teller> tellers) {
        this.tellers = tellers;
    }
    
    
    
}
