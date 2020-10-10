/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.dao.AccountDaoIF;
import com.ebanking.dao.TransactionDaoIF;
import com.ebanking.entity.Account;
import com.ebanking.entity.Transaction;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Huy Hoang
 */
@Service
public class AccountService implements AccountServiceIF {

    @Autowired
    AccountDaoIF accountDao;
    
    @Autowired
    TransactionDaoIF transactionDao;

    @Override
    public Account getAccount(int id) {
        Optional<Account> account = accountDao.findById(id);
        return account.isPresent() ? account.get() : null;
    }

    @Override
    public Account getAccountByAccountNoAndBankId(int accountNo, int bankId) {
        return accountDao.getAccountByAccountNoAndBankId(accountNo, bankId);
    }

    @Override
    public Account getAccountByAccountNo(int accountNo) {
        return accountDao.findByAccountNo(accountNo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void TransferMoney(Account accountFrom, Account accountTo, int amout, String message, String type, String feeCarier, int fee) {
        if(feeCarier.equals("nguoichuyen")) {
            accountFrom.setBalance(accountFrom.getBalance() - amout - fee);
            accountTo.setBalance(accountTo.getBalance() + amout);
            
            Transaction transaction = new Transaction();
            transaction.setAccount1(accountFrom);
            transaction.setAccount2(accountTo);
            transaction.setAmount(amout);
            transaction.setMessage(message);
            transaction.setType(type);
            transaction.setTransactionDate(LocalDate.now());
            
            accountDao.save(accountFrom);
            accountDao.save(accountTo);
            transactionDao.save(transaction);
        } else {
            accountFrom.setBalance(accountFrom.getBalance() - amout);
            accountTo.setBalance(accountTo.getBalance() + amout - fee);
            
            Transaction transaction = new Transaction();
            transaction.setAccount1(accountFrom);
            transaction.setAccount2(accountTo);
            transaction.setAmount(amout);
            transaction.setMessage(message);
            transaction.setType(type);
            transaction.setTransactionDate(LocalDate.now());
            
            accountDao.save(accountFrom);
            accountDao.save(accountTo);
            transactionDao.save(transaction);
        }
    }

}
