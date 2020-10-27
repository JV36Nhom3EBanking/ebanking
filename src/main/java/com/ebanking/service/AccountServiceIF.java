/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.entity.Account;
import com.ebanking.entity.Transaction;
import java.util.List;

/**
 *
 * @author Huy Hoang
 */
public interface AccountServiceIF {
    public Account getAccount(int id);
    public Account getAccountByAccountNoAndBankBranch(int accountNo, String bankBranch);
    public Account getInternalAccount(int accountNo);
    public Transaction TransferMoney(Account accountFrom, Account accountTo, int amount, String message, String type, String feeCarier, int fee);
    public List<Account> getAllInternalAccount();
    public List<Account> getCustomerAccount(int id);
    public void saveAccount(Account account);
}
