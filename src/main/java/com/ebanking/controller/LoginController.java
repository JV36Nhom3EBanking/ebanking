/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Customer;
import com.ebanking.entity.LoginModel;
import com.ebanking.recaptcha.VerifyCaptcha;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.TellerServiceIF;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String Default(Model model) {
        LoginModel loginModel = new LoginModel();
        model.addAttribute("loginModel", loginModel);
        return "login";
    }

    @PostMapping(value = "/confirmLogin")
    public String Login(@ModelAttribute("loginModel") LoginModel loginModel, Model model, HttpServletRequest request, HttpServletResponse response) {
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        if (VerifyCaptcha.verify(gRecaptchaResponse)) {
            if (customerService.login(loginModel.getUsername(), loginModel.getPassword()) || tellerService.login(loginModel.getUsername(), loginModel.getPassword())) {
                Customer customer = customerService.findByUsername(loginModel.getUsername());
                model.addAttribute("user", customer);
                return "redirect:/trangchu";
            } else {

                String error = "Wrong username or password. Please check again!";
                model.addAttribute("error", error);
                return "login";
            }
        } else {

            String error = "You don't check the captcha. Please check the captcha and try again!";
            model.addAttribute("error", error);
            return "login";
        }
    }
}
