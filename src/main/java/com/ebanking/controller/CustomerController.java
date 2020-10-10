/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Account;
import com.ebanking.entity.Customer;
import com.ebanking.entity.InternalTransferModel;
import com.ebanking.entity.SearchTransactionModel;
import com.ebanking.entity.Transaction;
import com.ebanking.otp.OTP;
import com.ebanking.service.AccountServiceIF;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.EmailService;
import com.ebanking.service.TransactionServiceIF;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Huy Hoang
 */
@Controller
@RequestMapping("/customer")
@SessionAttributes("otp")
public class CustomerController {

    @Autowired
    AccountServiceIF accountService;

    @Autowired
    CustomerServiceIF customerService;

    @Autowired
    TransactionServiceIF transactionService;

    @Autowired
    EmailService emailService;

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
    public String getTransaction(@ModelAttribute SearchTransactionModel searchTransactionModel, ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        int id = searchTransactionModel.getId();
        LocalDate date1 = searchTransactionModel.getDateFrom();
        LocalDate date2 = searchTransactionModel.getDateTo();

        Account account = accountService.getAccount(id);
        modelMap.addAttribute("account", account);

        List<Transaction> transactions = transactionService.getTransactionsByDateAndAccountId(date1, date2, id);
        modelMap.addAttribute("transactions", transactions);
        return "viewtransactionlist";
    }

    @GetMapping("/account/transaction/{id}")
    public String getTransactionInfo(@PathVariable int id, ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);
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

    @GetMapping("/internaltransfermoney")
    public String getITMPage(ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        List<Account> accounts = customer.getAccounts();
        modelMap.addAttribute("listAccount", accounts);
        InternalTransferModel internalTransferModel = new InternalTransferModel();
        modelMap.addAttribute("internalTransferModel", internalTransferModel);

        return "internaltransfermoney";
    }

    @PostMapping("/enterTransactionInformation")
    public String enterInformation(@ModelAttribute InternalTransferModel internalTransferModel, ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        Account accountFrom = accountService.getAccount(internalTransferModel.getAccountFromNo());
        Account accountTo = accountService.getAccountByAccountNoAndBankId(internalTransferModel.getAccountToNo(), accountFrom.getBank().getId());
        internalTransferModel.setFee(10000);
        internalTransferModel.setAccountFrom(accountFrom);
        internalTransferModel.setAccountTo(accountTo);

        int remain = accountFrom.getBalance() - internalTransferModel.getAmount() - internalTransferModel.getFee();
        if (internalTransferModel.getAmount() > 50000000) {
            modelMap.addAttribute("error", "Số tiền chuyển trong một giao dịch không được vượt quá 50.000.000 VNĐ. Xin quý khách vui lòng thử lại. Chân thành cảm ơn quý khách.");
            List<Account> accounts = customer.getAccounts();
            modelMap.addAttribute("listAccount", accounts);
            return "internaltransfermoney";
        }
        if (remain < 50000) {
            modelMap.addAttribute("error", "Tài khoản của quý khách không đủ để thực hiện giao dịch này. Quý khách vui lòng chuyển thêm tiền vào thẻ để tiếp tục sử dụng dịch vụ này của chúng tôi. Chân thành cảm ơn quý khách.");
            List<Account> accounts = customer.getAccounts();
            modelMap.addAttribute("listAccount", accounts);
            return "internaltransfermoney";
        } else if (accountTo != null) {
            modelMap.addAttribute("internalTransferModel", internalTransferModel);
            return "confirmtransactioninformation";
        } else {
            modelMap.addAttribute("error", "Không tìm thấy tài khoản thụ hưởng chỉ định. Vui lòng kiểm tra lại thông tin. Cảm ơn quý khách");
            List<Account> accounts = customer.getAccounts();
            modelMap.addAttribute("listAccount", accounts);
            return "internaltransfermoney";
        }

    }

    @PostMapping("/confirmTransactionInformation")
    public String confirmInformation(@ModelAttribute InternalTransferModel internalTransferModel, ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        String captcha = httpSession.getAttribute("captcha_security").toString();
        String verifyCaptcha = internalTransferModel.getCaptcha();
        if (verifyCaptcha.equals(captcha)) {
            Account accountFrom = accountService.getAccountByAccountNo(internalTransferModel.getAccountFromNo());
            Account accountTo = accountService.getAccountByAccountNo(internalTransferModel.getAccountToNo());

            internalTransferModel.setAccountFrom(accountFrom);
            internalTransferModel.setAccountTo(accountTo);

            modelMap.addAttribute("internalTransferModel", internalTransferModel);

            String otp = OTP.createOTP();
            modelMap.addAttribute("otp", otp);
//            emailService.sendEmail("huyhoang76114@gmail.com", "Ebanking OTP", "Hệ thống Ebanking xin thông báo mã OTP của quý khách là : " + otp);
            return "confirmtransaction";
        } else {
            String error = "Wrong input captcha. Please check your input captcha and try again!";
            modelMap.addAttribute("internalTransferModel", internalTransferModel);
            modelMap.addAttribute("error", error);
            return "confirmtransactioninformation";
        }
    }

    @PostMapping("/confirmTransaction")
    public String confirmTransaction(@ModelAttribute InternalTransferModel internalTransferModel, ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

//        String otp = (String) httpSession.getAttribute("otp");
//        if(internalTransferModel.getOtp().equals(otp)) {
//            Account accountFrom = accountService.getAccountByAccountNo(internalTransferModel.getAccountFromNo());
//            Account accountTo = accountService.getAccountByAccountNo(internalTransferModel.getAccountToNo());
//            
//            int amount = internalTransferModel.getAmount();
//            String message = internalTransferModel.getMessage();
//            String type = "internal";
//            String feeCarier = internalTransferModel.getFeeCarier();
//            int fee = internalTransferModel.getFee();
//            
//            accountService.TransferMoney(accountTo, accountTo, amount, message, type, feeCarier, fee);
//            return "successtransaction";
//        }
//        else {
//            String error = "Wrong otp. Please check your input captcha and try again!";
//            modelMap.addAttribute("internalTransferModel", internalTransferModel);
//            modelMap.addAttribute("error", error);
//            return "confirmtransaction";
//        }
        Account accountFrom = accountService.getAccountByAccountNo(internalTransferModel.getAccountFromNo());
        Account accountTo = accountService.getAccountByAccountNo(internalTransferModel.getAccountToNo());

        System.out.println(internalTransferModel.getFeeCarier());
        
        int amount = internalTransferModel.getAmount();
        String message = internalTransferModel.getMessage();
        String type = "internal";
        String feeCarier = internalTransferModel.getFeeCarier();
        int fee = internalTransferModel.getFee();

        accountService.TransferMoney(accountFrom, accountTo, amount, message, type, feeCarier, fee);
        return "successtransaction";
    }
}
