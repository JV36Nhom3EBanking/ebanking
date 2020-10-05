/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Account;
import com.ebanking.entity.Customer;
import com.ebanking.entity.SearchTransactionModel;
import com.ebanking.entity.Transaction;
import com.ebanking.service.AccountServiceIF;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.TransactionServiceIF;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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

    @InitBinder
    public void dataBinding(WebDataBinder binder) {

    }

    @GetMapping("/info")
    public String getInfo(HttpSession httpSession, ModelMap modelMap) {
        if (httpSession.getAttribute("user") != null) {
            Customer customer = (Customer) httpSession.getAttribute("user");
            modelMap.addAttribute("customer", customer);
            String name = customer.getName();
            modelMap.addAttribute("name", name);
            String chucaidau = customer.getEmail().substring(0, 1);
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
            String chucaidau = customer.getEmail().substring(0, 1);
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
        int id = searchTransactionModel.getId();
        LocalDate date1 = searchTransactionModel.getDateFrom();
        LocalDate date2 = searchTransactionModel.getDateTo();

        Account account = accountService.getAccount(id);
        modelMap.addAttribute("account", account);

        List<Transaction> listTransaction = transactionService.getTransactionsByDate(date1, date2);
        List<Transaction> transactions = new ArrayList<>();
        for (Transaction value : listTransaction) {
            if (value.getAccount1().equals(account) || value.getAccount2().equals(account)) {
                transactions.add(value);
            }
        }
        modelMap.addAttribute("transactions", transactions);
        return "viewtransactionlist";
    }

    @GetMapping("/account/transaction/{id}")
    public String getTransactionInfo(@PathVariable int id, ModelMap modelMap) {
        Transaction transaction = transactionService.getTransaction(id);
        modelMap.addAttribute("transaction", transaction);

        return "viewtransactioninfo";
    }

    @GetMapping("/account/list")
    public String getListAccount(ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        List<Account> accounts = customer.getAccounts();
        modelMap.addAttribute("listAccount", accounts);
        
        return "viewaccountlist";
    }
    
    @GetMapping("/account/transaction/search")
    public String searchTransaction(ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        List<Account> accounts = customer.getAccounts();
        modelMap.addAttribute("listAccount", accounts);
        SearchTransactionModel searchTransactionModel = new SearchTransactionModel();
        modelMap.addAttribute("searchTransaction", searchTransactionModel);
        
        return "searchtransaction";
    }
}
