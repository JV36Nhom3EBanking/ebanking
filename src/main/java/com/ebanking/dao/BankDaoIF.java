/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.dao;

import com.ebanking.entity.Bank;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Huy Hoang
 */
public interface BankDaoIF extends CrudRepository<Bank, Integer>{
    
    @Query(value = "SELECT DISTINCT bank.branch FROM bank", nativeQuery = true)
    public List<String> getListBranches();
}
