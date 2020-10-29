/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.ChangePasswordModel;
import com.ebanking.entity.Customer;
import com.ebanking.entity.User;
import com.ebanking.service.CustomerServiceIF;
import com.ebanking.service.EncrytedPassword;
import com.ebanking.service.UserServiceIF;
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
 * @author Huy
 */
@Controller
@RequestMapping("/customer")
public class ChangePasswordController {
    
    @Autowired
    CustomerServiceIF customerService;
    
    @Autowired
    EncrytedPassword encrytedPassword;
    
    @Autowired
    UserServiceIF userService;
    
    @GetMapping("/changePassword")
    public String getChangePasswordPage(HttpSession httpSession, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute("user");
        Customer customer = user.getCustomer();
        modelMap.addAttribute("customer", customer);
        String name = customer.getName();
        modelMap.addAttribute("name", name);
        String chucaidau = customer.getEmail().substring(0, 1);
        modelMap.addAttribute("chucaidau", chucaidau);
        
        ChangePasswordModel changePasswordModel = new ChangePasswordModel();
        modelMap.addAttribute("changePasswordModel", changePasswordModel);
        
        return "customer/changepassword";
    }
    
    @PostMapping("/confirmChangePassword")
    public String changePassword(@ModelAttribute ChangePasswordModel changePasswordModel, ModelMap modelMap, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        String captcha = httpSession.getAttribute("captcha_security").toString();
        String verifyCaptcha = changePasswordModel.getCaptcha();
        if(captcha.equals(verifyCaptcha)) {
            if(encrytedPassword.comparePassword(changePasswordModel.getPassword(), user.getPassword())) {
                if(changePasswordModel.getNewPassword().equals(changePasswordModel.getReNewPassword())) {
                    String newPassword = encrytedPassword.encrytePassword(changePasswordModel.getNewPassword());
                    userService.changePassword(user, newPassword);
                    return "customer/changepasswordsuccess";
                }
                else {
                    modelMap.addAttribute("error", "Mật khẩu mới và Nhập lại mật khẩu mới không trùng nhau. Vui lòng thử lại. Cảm ơn quý khách.");
                    return "customer/changepassword";
                }
            }
            else {
                modelMap.addAttribute("error", "Mật khẩu hiện tại không đúng. Vui lòng thử lại. Cảm ơn quý khách.");
                return "customer/changepassword";
            }
        }
        else {
            modelMap.addAttribute("error", "Captcha nhập sai. Mong quý khách vui lòng thử lại. Cảm ơn quý khách.");
            return "customer/changepassword";
        }
    }
}
