/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.dao;

import com.ebanking.entity.Teller;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Huy Hoang
 */
public interface TellerDaoIF extends CrudRepository<Teller, Integer>{
    
}
