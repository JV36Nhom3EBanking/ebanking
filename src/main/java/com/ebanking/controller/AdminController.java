/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Account;
import com.ebanking.entity.Bank;
import com.ebanking.entity.Customer;
import com.ebanking.entity.OpenAccountModel;
import com.ebanking.entity.SearchTransactionModel;
import com.ebanking.entity.Teller;
import com.ebanking.entity.Transaction;
import com.ebanking.entity.User;
import com.ebanking.service.AccountServiceIF;
import com.ebanking.service.BankServiceIF;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.MoneyFormatter;
import com.ebanking.service.TransactionServiceIF;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
 * @author Huy
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    CustomerServiceIF customerService;

    @Autowired
    AccountServiceIF accountService;

    @Autowired
    TransactionServiceIF transactionService;

    @Autowired
    BankServiceIF bankService;

    @Autowired
    MoneyFormatter moneyFormatter;

    @GetMapping("/trangchu")
    public String Default(ModelMap modelMap, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        return "admin/home";
    }

    @GetMapping("/customer")
    public String viewListCustomer(ModelMap modelMap, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        List<Customer> customers = customerService.getCustomers();
        modelMap.addAttribute("customers", customers);

        return "admin/listcustomer";
    }

    @GetMapping("/customer/{id}")
    public String viewCustomerInfo(ModelMap modelMap, HttpSession httpSession, @PathVariable int id) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        Customer customer = customerService.getCustomer(id);
        modelMap.addAttribute("customer", customer);

        return "admin/customerinfo";
    }

    @GetMapping("/account")
    public String viewListAccount(ModelMap modelMap, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        List<Account> accounts = accountService.getAllInternalAccount();
        modelMap.addAttribute("listAccount", accounts);

        return "admin/listaccount";
    }

    @GetMapping("/account/{id}")
    public String getInfoAccount(@PathVariable int id, ModelMap modelMap, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        Account account = accountService.getAccount(id);
        modelMap.addAttribute("account", account);
        SearchTransactionModel searchTransactionModel = new SearchTransactionModel();
        modelMap.addAttribute("searchTransaction", searchTransactionModel);

        return "admin/accountinfo";
    }

    @GetMapping("/transaction")
    public String searchTransaction(ModelMap modelMap, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        List<Account> accounts = accountService.getAllInternalAccount();
        modelMap.addAttribute("listAccount", accounts);
        SearchTransactionModel searchTransactionModel = new SearchTransactionModel();
        modelMap.addAttribute("searchTransaction", searchTransactionModel);

        return "admin/searchtransaction";
    }

    @PostMapping("/account/searchTransaction")
    public String getTransaction(@ModelAttribute SearchTransactionModel searchTransactionModel, ModelMap modelMap, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        int id = searchTransactionModel.getId();
        LocalDate date1 = searchTransactionModel.getDateFrom();
        LocalDate date2 = searchTransactionModel.getDateTo();

        Account account = accountService.getAccount(id);
        modelMap.addAttribute("account", account);

        List<Transaction> transactions = transactionService.getTransactionsByDateAndAccountId(date1, date2, id);
        modelMap.addAttribute("transactions", transactions);
        return "admin/listtransaction";
    }

    @GetMapping("/transaction/{id}")
    public String getTransactionInfo(@PathVariable int id, ModelMap modelMap, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        Transaction transaction = transactionService.getTransaction(id);
        modelMap.addAttribute("transaction", transaction);

        return "admin/transactioninfo";
    }

    @GetMapping("/openAccount")
    public String getOpenAccountPage(ModelMap modelMap, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        List<String> listDistrict = bankService.getListInternalDistrict();
        List<String> listCity = bankService.getListInternalCity();
        OpenAccountModel openAccountModel = new OpenAccountModel();

        modelMap.addAttribute("district", listDistrict);
        modelMap.addAttribute("city", listCity);
        modelMap.addAttribute("openAccountModel", openAccountModel);

        return "admin/openaccount";
    }

    @PostMapping("/confirmopen")
    public String confirmOpenAccount(ModelMap modelMap, HttpSession httpSession, @ModelAttribute OpenAccountModel openAccountModel) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        Customer customer = customerService.findCustomer(openAccountModel.getName(), openAccountModel.getBirthdate(), openAccountModel.getCmnd());
        Bank bank = bankService.getBank(openAccountModel.getDistrict(), openAccountModel.getCity());

        if (customer == null) {
            modelMap.addAttribute("error", "Nhập sai thông tin khách hàng. Xin thử lại.");
            List<String> listDistrict = bankService.getListInternalDistrict();
            List<String> listCity = bankService.getListInternalCity();
            modelMap.addAttribute("district", listDistrict);
            modelMap.addAttribute("city", listCity);
            return "admin/openaccount";
        }

        if (bank == null) {
            modelMap.addAttribute("error", "Nhập sai thông tin ngân hàng quản lý. Xin thử lại.");
            List<String> listDistrict = bankService.getListInternalDistrict();
            List<String> listCity = bankService.getListInternalCity();
            modelMap.addAttribute("district", listDistrict);
            modelMap.addAttribute("city", listCity);
            return "admin/openaccount";
        }

        Account account = new Account();
        account.setOpenDate(LocalDate.now());
        account.setBalance(50000);
        account.setType("ATM");
        account.setStatus("actived");
        account.setCustomer(customer);
        account.setBank(bank);

        accountService.saveAccount(account);

        modelMap.addAttribute("account", accountService.getAccount(account.getId()));

        SearchTransactionModel searchTransactionModel = new SearchTransactionModel();
        modelMap.addAttribute("searchTransaction", searchTransactionModel);

        return "admin/opensuccess";

    }

    @GetMapping("/changeBalance")
    public String getChangeBalancePage(ModelMap modelMap, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        return "admin/changebalance";
    }

    @PostMapping("/findAccount")
    public String findAccount(ModelMap modelMap, HttpSession httpSession, HttpServletRequest httpServletRequest) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        int id = Integer.parseInt(httpServletRequest.getParameter("accountid"));
        Account account = accountService.getAccount(id);

        modelMap.addAttribute("account", account);

        return "admin/changebalancepage";
    }

    @PostMapping("/changeBalanceProcess")
    public String changeBalance(ModelMap modelMap, HttpSession httpSession, HttpServletRequest httpServletRequest) {
        User user = (User) httpSession.getAttribute("user");
        Teller teller = user.getTeller();
        modelMap.addAttribute("teller", teller);
        String name = teller.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = teller.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        int id = Integer.parseInt(httpServletRequest.getParameter("accountid"));
        Account account = accountService.getAccount(id);

        int balance = moneyFormatter.readAmount(httpServletRequest.getParameter("balancechange"));

        account.setBalance(balance);
        accountService.saveAccount(account);

        modelMap.addAttribute("account", account);

        return "admin/changesuccess";
    }
}
