/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.entity.Customer;
import java.util.List;

/**
 *
 * @author Huy Hoang
 */
public interface CustomerServiceIF {
    public List<Customer> getCustomers();
    public boolean login(String username, String password);
    public Customer findByUsername(String username);
    public boolean saveCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void changePassword(Customer customer, String password);
}
