/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.dao.BankDaoIF;
import com.ebanking.entity.Bank;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huy
 */
@Service
public class BankService implements BankServiceIF {

    @Autowired
    BankDaoIF bankDao;
    
    @Override
    public List<String> getListBranches() {
        return bankDao.getListBranches();
    }

    @Override
    public List<String> getListInternalDistrict() {
        return bankDao.getListInternalDistrict();
    }

    @Override
    public List<String> getListInternalCity() {
        return bankDao.getListInternalCity();
    }

    @Override
    public Bank getBank(String district, String city) {
        return bankDao.getBank(district, city);
    }
    
    
    
    
}
