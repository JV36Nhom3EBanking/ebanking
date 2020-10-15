/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Account;
import com.ebanking.entity.Customer;
import com.ebanking.entity.ExternalTransferModel;
import com.ebanking.entity.InternalTransferModel;
import com.ebanking.entity.SearchTransactionModel;
import com.ebanking.entity.Transaction;
import com.ebanking.otp.OTP;
import com.ebanking.service.AccountServiceIF;
import com.ebanking.service.BankServiceIF;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.EmailService;
import com.ebanking.service.MoneyFormatter;
import com.ebanking.service.TransactionServiceIF;
import com.ebanking.service.WebService;
import java.math.BigDecimal;
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

    @Autowired
    BankServiceIF bankService;

    @Autowired
    WebService webService;

    @Autowired
    MoneyFormatter moneyFormatter;

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

    @PostMapping("/enterInternalTransactionInformation")
    public String enterInternalInformation(@ModelAttribute InternalTransferModel internalTransferModel, ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        Account accountFrom = accountService.getInternalAccount(internalTransferModel.getAccountFromNo());
        Account accountTo = accountService.getInternalAccount(internalTransferModel.getAccountToNo());
        
        internalTransferModel.setAmount(moneyFormatter.readAmount(internalTransferModel.getAmountFormat()));
        internalTransferModel.setAmountByText(moneyFormatter.numberToString(new BigDecimal(internalTransferModel.getAmount())));
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
            return "confirminternaltransactioninformation";
        } else {
            modelMap.addAttribute("error", "Không tìm thấy tài khoản thụ hưởng chỉ định. Vui lòng kiểm tra lại thông tin. Cảm ơn quý khách");
            List<Account> accounts = customer.getAccounts();
            modelMap.addAttribute("listAccount", accounts);
            return "internaltransfermoney";
        }

    }

    @PostMapping("/confirmInternalTransactionInformation")
    public String confirmInternalInformation(@ModelAttribute InternalTransferModel internalTransferModel, ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        String captcha = httpSession.getAttribute("captcha_security").toString();
        String verifyCaptcha = internalTransferModel.getCaptcha();
        if (verifyCaptcha.equals(captcha)) {
            Account accountFrom = accountService.getInternalAccount(internalTransferModel.getAccountFromNo());
            Account accountTo = accountService.getInternalAccount(internalTransferModel.getAccountToNo());

            internalTransferModel.setAccountFrom(accountFrom);
            internalTransferModel.setAccountTo(accountTo);
            System.out.println(internalTransferModel.getAmountByText());
            modelMap.addAttribute("internalTransferModel", internalTransferModel);

            String otp = OTP.createOTP();
            modelMap.addAttribute("otp", otp);
            emailService.sendEmail("huyhoang76114@gmail.com", "Ebanking OTP", "Hệ thống Ebanking xin thông báo mã OTP của quý khách là : " + otp);
            return "confirminternaltransaction";
        } else {
            String error = "Wrong input captcha. Please check your input captcha and try again!";
            modelMap.addAttribute("internalTransferModel", internalTransferModel);
            modelMap.addAttribute("error", error);
            return "confirminternaltransactioninformation";
        }
    }

    @PostMapping("/confirmInternalTransaction")
    public String confirmInternalTransaction(@ModelAttribute InternalTransferModel internalTransferModel, ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        String otp = (String) httpSession.getAttribute("otp");
        if (internalTransferModel.getOtp().equals(otp)) {
            Account accountFrom = accountService.getInternalAccount(internalTransferModel.getAccountFromNo());
            Account accountTo = accountService.getInternalAccount(internalTransferModel.getAccountToNo());

            int amount = internalTransferModel.getAmount();
            String message = internalTransferModel.getMessage();
            String type = "internal";
            String feeCarier = internalTransferModel.getFeeCarier();
            int fee = internalTransferModel.getFee();

            Transaction transaction = accountService.TransferMoney(accountFrom, accountTo, amount, message, type, feeCarier, fee);
            modelMap.addAttribute("transaction", transaction);
            return "successinternaltransaction";
        } else {
            String error = "Wrong otp. Please check your input captcha and try again!";
            modelMap.addAttribute("internalTransferModel", internalTransferModel);
            modelMap.addAttribute("error", error);
            return "confirmtransaction";
        }
    }

    @GetMapping("/externaltransfermoney")
    public String getETMPage(ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        List<Account> accounts = customer.getAccounts();
        modelMap.addAttribute("listAccount", accounts);
        List<String> listBranches = bankService.getListBranches();
        listBranches.remove("VietComBank");
        modelMap.addAttribute("branches", listBranches);
        ExternalTransferModel externalTransferModel = new ExternalTransferModel();
        modelMap.addAttribute("externalTransferModel", externalTransferModel);

        return "externaltransfermoney";
    }

    @PostMapping("/enterExternalTransactionInformation")
    public String enterExternalInformation(@ModelAttribute ExternalTransferModel externalTransferModel, ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        Account accountFrom = accountService.getInternalAccount(externalTransferModel.getAccountFromNo());
        Account accountTo = webService.verifyAccount(externalTransferModel.getAccountToNo(), externalTransferModel.getBankBranch());

        externalTransferModel.setAmount(moneyFormatter.readAmount(externalTransferModel.getAmountFormat()));
        externalTransferModel.setAmountByText(moneyFormatter.numberToString(new BigDecimal(externalTransferModel.getAmount())));
        externalTransferModel.setFee(25000);
        externalTransferModel.setAccountFrom(accountFrom);
        externalTransferModel.setAccountTo(accountTo);

        int remain = accountFrom.getBalance() - externalTransferModel.getAmount() - externalTransferModel.getFee();
        if (externalTransferModel.getAmount() > 50000000) {
            modelMap.addAttribute("error", "Số tiền chuyển trong một giao dịch không được vượt quá 50.000.000 VNĐ. Xin quý khách vui lòng thử lại. Chân thành cảm ơn quý khách.");
            List<Account> accounts = customer.getAccounts();
            modelMap.addAttribute("listAccount", accounts);
            return "externaltransfermoney";
        }
        if (remain < 50000) {
            modelMap.addAttribute("error", "Tài khoản của quý khách không đủ để thực hiện giao dịch này. Quý khách vui lòng chuyển thêm tiền vào thẻ để tiếp tục sử dụng dịch vụ này của chúng tôi. Chân thành cảm ơn quý khách.");
            List<Account> accounts = customer.getAccounts();
            modelMap.addAttribute("listAccount", accounts);
            return "externaltransfermoney";
        } else if (accountTo != null) {
            modelMap.addAttribute("externalTransferModel", externalTransferModel);
            return "confirmexternaltransactioninformation";
        } else {
            modelMap.addAttribute("error", "Không tìm thấy tài khoản thụ hưởng chỉ định. Vui lòng kiểm tra lại thông tin. Cảm ơn quý khách");
            List<Account> accounts = customer.getAccounts();
            modelMap.addAttribute("listAccount", accounts);
            return "externaltransfermoney";
        }

    }

    @PostMapping("/confirmExternalTransactionInformation")
    public String confirmExternalInformation(@ModelAttribute ExternalTransferModel externalTransferModel, ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        String captcha = httpSession.getAttribute("captcha_security").toString();
        String verifyCaptcha = externalTransferModel.getCaptcha();
        if (verifyCaptcha.equals(captcha)) {
            Account accountFrom = accountService.getInternalAccount(externalTransferModel.getAccountFromNo());
            Account accountTo = webService.verifyAccount(externalTransferModel.getAccountToNo(), externalTransferModel.getBankBranch());

            externalTransferModel.setAccountFrom(accountFrom);
            externalTransferModel.setAccountTo(accountTo);

            modelMap.addAttribute("externalTransferModel", externalTransferModel);

            String otp = OTP.createOTP();
            modelMap.addAttribute("otp", otp);
            emailService.sendEmail("huyhoang76114@gmail.com", "Ebanking OTP", "Hệ thống Ebanking xin thông báo mã OTP của quý khách là : " + otp);
            return "confirmexternaltransaction";
        } else {
            String error = "Wrong input captcha. Please check your input captcha and try again!";
            modelMap.addAttribute("externalTransferModel", externalTransferModel);
            modelMap.addAttribute("error", error);
            return "confirmexternaltransactioninformation";
        }
    }

    @PostMapping("/confirmExternalTransaction")
    public String confirmTransaction(@ModelAttribute ExternalTransferModel externalTransferModel, ModelMap modelMap, HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);

        String otp = (String) httpSession.getAttribute("otp");
        if (externalTransferModel.getOtp().equals(otp)) {
            Account accountFrom = accountService.getInternalAccount(externalTransferModel.getAccountFromNo());
            Account accountTo = webService.verifyAccount(externalTransferModel.getAccountToNo(), externalTransferModel.getBankBranch());

            int amount = externalTransferModel.getAmount();
            String message = externalTransferModel.getMessage();
            String type = "external";
            String feeCarier = externalTransferModel.getFeeCarier();
            int fee = externalTransferModel.getFee();

            Transaction transaction = webService.TransferMoney(accountFrom, accountTo, amount, message, type, feeCarier, fee);
            modelMap.addAttribute("transaction", transaction);
            return "successexternaltransaction";
        } else {
            String error = "Wrong otp. Please check your input captcha and try again!";
            modelMap.addAttribute("externalTransferModel", externalTransferModel);
            modelMap.addAttribute("error", error);
            return "confirmexternaltransaction";
        }
    }
}
