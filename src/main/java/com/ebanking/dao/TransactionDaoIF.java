/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.dao;

import com.ebanking.entity.Transaction;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Huy Hoang
 */
public interface TransactionDaoIF extends CrudRepository<Transaction, Integer>{
    public List<Transaction> findByTransactionDateBetween(LocalDate transactionDate1, LocalDate transactionDate2);
    
    @Query(value = "SELECT * FROM transaction WHERE transactionDate BETWEEN ?1 AND ?2 AND accountIdFrom = ?3 OR accountIdTo = ?4", nativeQuery = true)
    public List<Transaction> getTransactionByDateAndAccountId(LocalDate transactionDate1, LocalDate transactionDate2, int idFrom, int idTo);
}
