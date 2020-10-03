/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Account;
import com.ebanking.entity.Customer;
import com.ebanking.entity.SearchTransactionModel;
import com.ebanking.service.AccountServiceIF;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.TransactionServiceIF;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Huy Hoang
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    AccountServiceIF accountService;
    
    @Autowired
    CustomerServiceIF customerService;
    
    @Autowired
    TransactionServiceIF transactionService;

    @GetMapping("/info")
    public String getInfo(HttpSession httpSession, ModelMap modelMap) {
        if (httpSession.getAttribute("user") != null) {
            Customer customer = (Customer) httpSession.getAttribute("user");
            modelMap.addAttribute("customer", customer);
            String name = customer.getName();
            modelMap.addAttribute("name", name);
            String chucaidau = customer.getEmail().substring(0,1);
            modelMap.addAttribute("chucaidau", chucaidau);
            System.out.println(customer.getAccounts().get(0).getBalance());
        }
        return "viewcustomerinfo";
    }
    
    @GetMapping("/account/{id}")
    public String getInfoAccount(@PathVariable int id, ModelMap modelMap, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            Customer customer = (Customer) httpSession.getAttribute("user");
            modelMap.addAttribute("customer", customer);
            String name = customer.getName();
            modelMap.addAttribute("name", name);
            String chucaidau = customer.getEmail().substring(0,1);
            modelMap.addAttribute("chucaidau", chucaidau);
            System.out.println(customer.getAccounts().get(0).getBalance());
        }
        Account account = accountService.getAccount(id);
        modelMap.addAttribute("account", account);
        SearchTransactionModel searchTransactionModel = new SearchTransactionModel();
        modelMap.addAttribute("searchTransaction", searchTransactionModel);
        
        return "viewaccountinfo";
    }
    
    @PostMapping("/account/searchTransaction")
    public String getTransaction(@ModelAttribute SearchTransactionModel searchTransactionModel, ModelMap modelMap) {
        
        return "";
    }
}
