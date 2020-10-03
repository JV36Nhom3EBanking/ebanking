/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.dao.TransactionDaoIF;
import com.ebanking.entity.Transaction;
import java.util.Date;
import java.util.List;
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
    public List<Transaction> getTransactionsByDate(Date transactionDate1, Date transactionDate2) {
        return transactionDao.findByTransactionDateBetween(transactionDate1, transactionDate2);
    }
}
