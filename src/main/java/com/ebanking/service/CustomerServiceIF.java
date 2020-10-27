/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.entity.Customer;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Huy Hoang
 */
public interface CustomerServiceIF {
    public List<Customer> getCustomers();
    public void saveCustomer(Customer customer);
    public Customer getCustomer(int id);
    public Customer findCustomer(String name, LocalDate birthdate, String cmnd);
}
