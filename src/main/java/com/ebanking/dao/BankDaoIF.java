/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.dao;

import com.ebanking.entity.Bank;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Huy Hoang
 */
public interface BankDaoIF extends CrudRepository<Bank, Integer>{
    
}
