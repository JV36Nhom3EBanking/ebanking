/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.dao.CustomerDaoIF;
import com.ebanking.entity.Customer;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
    public void saveCustomer(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Optional<Customer> customer = customerDao.findById(id);
	return customer.isPresent() ? customer.get() : null;
    }

    @Override
    public Customer findCustomer(String name, LocalDate birthdate, String cmnd) {
        return customerDao.findCustomer(name, birthdate, cmnd);
    }

}
