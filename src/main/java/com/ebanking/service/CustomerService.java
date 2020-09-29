/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.dao.CustomerDaoIF;
import com.ebanking.entity.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huy Hoang
 */
@Service
public class CustomerService implements CustomerServiceIF {

    @Autowired
    CustomerDaoIF customerDao;
    
    @Override
    public List<Customer> getCustomers() {
        return (List<Customer>) customerDao.findAll();
    }

    @Override
    public boolean login(String username, String password) {
        for (Customer customer : getCustomers()) {
            if (customer.getUsername().equals(username)) {
                if (customer.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
