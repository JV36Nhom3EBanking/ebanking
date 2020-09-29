/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.LoginModel;
import com.ebanking.service.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Huy Hoang
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    CustomerServiceIF customerService;
    
    @GetMapping
    public String Default(Model model) {
        LoginModel loginModel = new LoginModel();
        model.addAttribute("loginModel", loginModel);
        return "login";
    }
    
    @PostMapping(value = "/confirmLogin")
    public String Login(@ModelAttribute("loginModel") LoginModel loginModel, Model model) {
        boolean flag = customerService.login(loginModel.getUsername(), loginModel.getPassword());
        System.out.println(flag);
        if(customerService.login(loginModel.getUsername(), loginModel.getPassword())) {
            return "redirect:/trangchu";
        }
        else {
            return "redirect:/login";
        }
    }
}
