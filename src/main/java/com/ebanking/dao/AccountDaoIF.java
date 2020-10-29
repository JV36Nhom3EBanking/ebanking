/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.dao;

import org.springframework.data.repository.CrudRepository;

import com.ebanking.entity.Account;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Huy Hoang
 */
public interface AccountDaoIF extends CrudRepository<Account, Integer> {

    @Query(value = "SELECT account.* FROM account JOIN bank ON bank.id = account.bankId WHERE account.id = ?1 AND bank.branch = 'VietComBank'", nativeQuery = true)
    public Account getInternalAccount(int id);
    
    @Query(value = "SELECT account.* FROM account JOIN bank ON bank.id = account.bankId WHERE account.id = ?1 AND bank.branch = ?2", nativeQuery = true)
    public Account getAccountByAccountNoAndBankBranch(int id, String branch);
    
    @Query(value = "SELECT account.* FROM account, bank WHERE account.bankId = bank.id AND bank.branch = 'VietComBank'", nativeQuery = true) 
    public List<Account> getAllInternalAccount();
    
    @Query(value = "SELECT account.* FROM account, bank WHERE account.bankId = bank.id AND customerId = ?1 AND bank.branch = 'VietComBank'", nativeQuery = true)
    public List<Account> getCustomerAccount(int id);
    
}
