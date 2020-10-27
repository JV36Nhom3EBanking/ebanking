/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Customer;
import com.ebanking.entity.User;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.EmailService;
import com.ebanking.service.UserServiceIF;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Huy
 */
@Controller
@RequestMapping("/activation")
public class ActivationController {

    @Autowired
    UserServiceIF userService;
    
    @Autowired
    EmailService emailService;
            
    @GetMapping("/{id}")
    public String sendEmailActivation(@PathVariable int id) {
        User user = userService.getUserById(id);
        user.setEnabled(true);
        
        userService.saveUser(user);
        return "activationsuccess";
    }
}
