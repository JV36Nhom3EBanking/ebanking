/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.entity;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Huy
 */
public class OpenAccountModel {

    private String name;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    
    private String cmnd;
    
    private String district;
    
    private String city;

    public OpenAccountModel() {
    }

    public OpenAccountModel(String name, LocalDate birthdate, String cmnd, String district, String city) {
        this.name = name;
        this.birthdate = birthdate;
        this.cmnd = cmnd;
        this.district = district;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
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
    
    
    
}
