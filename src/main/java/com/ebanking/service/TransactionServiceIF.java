    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.entity.Transaction;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Huy Hoang
 */
public interface TransactionServiceIF {
    public List<Transaction> getTransactionsByDate(LocalDate transactionDate1, LocalDate transactionDate2);
}
