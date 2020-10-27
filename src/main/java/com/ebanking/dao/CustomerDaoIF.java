/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.dao;

import com.ebanking.entity.Customer;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Huy Hoang
 */
public interface CustomerDaoIF extends CrudRepository<Customer, Integer>{
    
    @Query(value = "SELECT * FROM customer WHERE name = ?1 AND birthdate = ?2 AND cmnd = ?3", nativeQuery = true)
    public Customer findCustomer(String name, LocalDate birthdate, String cmnd);
}
