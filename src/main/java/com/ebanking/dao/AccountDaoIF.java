 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.dao;

import org.springframework.data.repository.CrudRepository;

import com.ebanking.entity.Account;

/**
 *
 * @author Huy Hoang
 */
public interface AccountDaoIF extends CrudRepository<Account, Integer> {
    
}
