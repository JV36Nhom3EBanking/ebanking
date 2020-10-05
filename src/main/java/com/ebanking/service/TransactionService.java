/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.dao.TransactionDaoIF;
import com.ebanking.entity.Transaction;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huy
 */
@Service
public class TransactionService implements TransactionServiceIF {
    
    @Autowired
    TransactionDaoIF transactionDao;

    @Override
    public List<Transaction> getTransactionsByDate(LocalDate transactionDate1, LocalDate transactionDate2) {
        return transactionDao.findByTransactionDateBetween(transactionDate1, transactionDate2);
    }

    @Override
    public Transaction getTransaction(int id) {
        Optional<Transaction> transaction = transactionDao.findById(id);
	return transaction.isPresent() ? transaction.get() : null;
    }

    @Override
    public List<Transaction> getTransactionsByDateAndAccountId(LocalDate transactionDate1, LocalDate transactionDate2, int id) {
        return transactionDao.getTransactionByDateAndAccountId(transactionDate1, transactionDate2, id, id);
    }
}
