/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.dao.AccountDaoIF;
import com.ebanking.entity.Account;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huy Hoang
 */
@Service
public class AccountService implements AccountServiceIF {

    @Autowired
    AccountDaoIF accountDao;
    
    @Override
    public Account getAccount(int id) {
       Optional<Account> account = accountDao.findById(id);
	return account.isPresent() ? account.get() : null;
    }

    
}
