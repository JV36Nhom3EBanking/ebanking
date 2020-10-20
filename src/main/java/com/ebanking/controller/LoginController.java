/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Customer;
import com.ebanking.entity.LoginModel;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.TellerServiceIF;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Huy Hoang
 */
@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {

    @Autowired
    CustomerServiceIF customerService;

    @Autowired
    TellerServiceIF tellerService;

    @GetMapping
    public String Default(HttpSession httpSession, ModelMap modelMap) {
        if (httpSession.getAttribute("user") != null) {
            Customer customer = (Customer) httpSession.getAttribute("user");
            modelMap.addAttribute("customer", customer);
            String name = customer.getName();
            modelMap.addAttribute("name", name);
            String chucaidau = customer.getEmail().substring(0, 1);
            modelMap.addAttribute("chucaidau", chucaidau);
            System.out.println(customer.getAccounts().get(0).getBalance());
            return "redirect:/trangchu";
        }
        else {
            LoginModel loginModel = new LoginModel();
            modelMap.addAttribute("loginModel", loginModel);
            return "login";
        }   
    }

    @PostMapping(value = "/confirmLogin")
    public String Login(@ModelAttribute("loginModel") LoginModel loginModel, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
        String captcha = httpSession.getAttribute("captcha_security").toString();
	String verifyCaptcha = loginModel.getCaptcha();
        if (verifyCaptcha.equals(captcha)) {
            if (customerService.login(loginModel.getUsername(), loginModel.getPassword()) || tellerService.login(loginModel.getUsername(), loginModel.getPassword())) {
                Customer customer = customerService.findByUsername(loginModel.getUsername());
                if(customer.getStatus().equals("actived")) {
                    model.addAttribute("user", customer);
                    return "redirect:/trangchu";
                }
                else {
                    model.addAttribute("user", customer);
                    return "activation";
                }
            } else {

                String error = "Wrong username or password. Please check again!";
                model.addAttribute("error", error);
                return "login";
            }
        } else {

            String error = "Wrong input captcha. Please check your input captcha and try again!";
            model.addAttribute("error", error);
            return "login";
        }
    }
}
