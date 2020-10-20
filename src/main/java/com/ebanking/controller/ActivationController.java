/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Customer;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.EmailService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Huy
 */
@Controller
@RequestMapping("/activation")
public class ActivationController {

    @Autowired
    CustomerServiceIF customerService;
    
    @Autowired
    EmailService emailService;
            
    @GetMapping()
    public String sendEmailActivation(HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        emailService.sendEmail(customer.getEmail(), "Kích hoạt tài khoản", "http://localhost:8080/ebanking/activation/confirmActivation");
        return "activationprocess";
    }
    
    @GetMapping("/confirmActivation")
    public String Activation(HttpSession httpSession) {
        Customer customer = (Customer) httpSession.getAttribute("user");
        customer.setStatus("actived");
        customerService.updateCustomer(customer);
        httpSession.setAttribute("user", null);
        return "activationsuccess";
    }
}
