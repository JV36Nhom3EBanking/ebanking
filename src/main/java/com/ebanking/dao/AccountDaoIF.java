/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.dao;

import org.springframework.data.repository.CrudRepository;

import com.ebanking.entity.Account;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Huy Hoang
 */
public interface AccountDaoIF extends CrudRepository<Account, Integer> {

    @Query(value = "SELECT account.* FROM account JOIN bank ON bank.id = account.bankId WHERE account.accountNo = ?1 AND bank.branch = 'VietComBank'", nativeQuery = true)
    public Account getInternalAccount(int accountNo);
    
    @Query(value = "SELECT account.* FROM account JOIN bank ON bank.id = account.bankId WHERE account.accountNo = ?1 AND bank.branch = ?2", nativeQuery = true)
    public Account getAccountByAccountNoAndBankBranch(int accountNo, String branch);

    public Account findByAccountNo(int accountno);
}
