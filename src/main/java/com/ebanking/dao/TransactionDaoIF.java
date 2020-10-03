/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.dao;

import com.ebanking.entity.Transaction;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Huy Hoang
 */
public interface TransactionDaoIF extends CrudRepository<Transaction, Integer>{
    public List<Transaction> findByTransactionDateBetween(Date transactionDate1, Date transactionDate2);
}
