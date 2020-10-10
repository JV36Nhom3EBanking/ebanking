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
    
    @Query(value = "SELECT * FROM account WHERE accountNo = ?1 AND bankId = ?2", nativeQuery = true)
    public Account getAccountByAccountNoAndBankId(int accountNo, int bankId);
    
    public Account findByAccountNo(int accountno);
}
