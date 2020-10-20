/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Customer;
import com.ebanking.entity.RegisterModel;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.EmailService;
import com.ebanking.service.RandomPassword;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Huy Hoang
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    CustomerServiceIF customerService;

    @Autowired
    EmailService emailService;

    @Autowired
    RandomPassword randomPassword;

    @GetMapping
    public String Default(ModelMap modelMap) {
        RegisterModel registerModel = new RegisterModel();
        modelMap.addAttribute("registerModel", registerModel);
        return "register";
    }

    @PostMapping(value = "/confirmRegister")
    public String registerCustomer(ModelMap modelMap, HttpServletRequest httpRequest, @ModelAttribute RegisterModel registerModel, HttpSession httpSession) {
        String captcha = httpSession.getAttribute("captcha_security").toString();
        String verifyCaptcha = registerModel.getCaptcha();
        if (captcha.equals(verifyCaptcha)) {
            Customer customerNew = new Customer();
            customerNew.setName(registerModel.getFirstname().trim() + " " + registerModel.getLastname().trim());
            customerNew.setBirthdate(registerModel.getBirthdate());
            customerNew.setAddress(registerModel.getAddress());
            customerNew.setDistrict(registerModel.getDistrict());
            customerNew.setGender(registerModel.getGender());
            customerNew.setCity(registerModel.getCity());
            customerNew.setNationality(registerModel.getNationality());
            customerNew.setCmnd(registerModel.getCmnd());
            customerNew.setPhone(registerModel.getPhone());
            customerNew.setEmail(registerModel.getEmail());
            customerNew.setRole("customer");
            customerNew.setUsername(registerModel.getEmail());
            String password = randomPassword.GeneratePassword();
            customerNew.setPassword(password);
            customerNew.setStatus("unactived");
            if (customerService.saveCustomer(customerNew)) {
                String content = "Chào mừng đến với hệ thống EBanking " + "\n"
                        + "Username của bạn : " + registerModel.getEmail() + "\n"
                        + "Password của bạn : " + password;
                emailService.sendEmail(customerNew.getUsername(), "Thông tin tài khoản", content);

                return "registersuccess";
            } else {
                String error = "Đã xảy ra lỗi. Quý khách vui lòng thử lại. Mong quý khách thông cảm.";
                modelMap.addAttribute("error", error);
                return "register";
            }
        } else {
            String error = "Captcha nhập sai. Mong quý khách vui lòng thử lại. Cảm ơn quý khách.";
            modelMap.addAttribute("error", error);
            return "register";
        }
    }

}
