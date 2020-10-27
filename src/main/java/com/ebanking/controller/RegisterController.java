/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Customer;
import com.ebanking.entity.RegisterModel;
import com.ebanking.entity.User;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.EmailService;
import com.ebanking.service.EncrytedPassword;
import com.ebanking.service.RandomPassword;
import com.ebanking.service.UserServiceIF;
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

    @Autowired
    EncrytedPassword encrytedPassword;

    @Autowired
    UserServiceIF userService;

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
            if (!userService.existEmail(registerModel.getUsername())) {
                if (registerModel.getPassword().equals(registerModel.getRePassword())) {
                    User user = new User();
                    Customer customer = new Customer();
                    user.setUsername(registerModel.getUsername());
                    user.setPassword(encrytedPassword.encrytePassword(registerModel.getPassword()));
                    user.setRole("ROLE_USER");
                    userService.saveUser(user);

                    customer.setName(registerModel.getName());
                    customer.setBirthdate(registerModel.getBirthdate());
                    customer.setAddress(registerModel.getAddress());
                    customer.setDistrict(registerModel.getDistrict());
                    customer.setGender(registerModel.getGender());
                    customer.setCity(registerModel.getCity());
                    customer.setNationality(registerModel.getNationality());
                    customer.setCmnd(registerModel.getCmnd());
                    customer.setPhone(registerModel.getPhone());
                    customer.setEmail(registerModel.getEmail());
                    customer.setStatus("actived");
                    customerService.saveCustomer(customer);
                    
                    int customerId = customer.getId();
                    int userId = user.getId();
                    
                    user.setCustomer(customerService.getCustomer(customerId));
                    userService.saveUser(user);
                    
                    customer.setUser(userService.getUserById(userId));
                    customerService.saveCustomer(customer);


                    if (customer.getId() > 0 && user.getId() > 0) {
                        String content = "Chào mừng đến với hệ thống EBanking " + "\n"
                                + "Bấm vào link sau để kích hoạt tài khoản của bạn : "
                                + "http://localhost:8080/ebanking/activation/" + user.getId();
                        emailService.sendEmail("huyhoang76114@gmail.com", "Kích hoạt tài khoản", content);
                        return "registersuccess";
                    } else {
                        String error = "Đã xảy ra lỗi. Quý khách vui lòng thử lại. Mong quý khách thông cảm.";
                        modelMap.addAttribute("error", error);
                        return "register";
                    }
                } else {
                    String error = "Mật khẩu và Nhập lại mật khẩu không trùng khớp. Quý khách vui lòng thử lại. Cảm ơn quý khách.";
                    modelMap.addAttribute("error", error);
                    return "register";
                }
            } else {
                String error = "Username đã tồn tại. Quý khách vui lòng thử lại. Cảm ơn quý khách.";
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
