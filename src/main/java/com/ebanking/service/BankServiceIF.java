/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.entity.Bank;
import java.util.List;

/**
 *
 * @author Huy Hoang
 */
public interface BankServiceIF {
    public List<String> getListBranches();
    public List<String> getListInternalDistrict();
    public List<String> getListInternalCity();
    public Bank getBank(String district, String city);
}
