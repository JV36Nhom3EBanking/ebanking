/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Customer;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Huy
 */
@Controller
@RequestMapping("/trangchu")
public class HomeController {
    
    @GetMapping
    public String Default(HttpSession httpSession, ModelMap modelMap) {
        if (httpSession.getAttribute("user") != null) {
            Customer customer = (Customer) httpSession.getAttribute("user");
            modelMap.addAttribute("customer", customer);
            String name = customer.getName();
            modelMap.addAttribute("name", name);
            String chucaidau = customer.getEmail().substring(0,1);
            modelMap.addAttribute("chucaidau", chucaidau);
        }
        return "home";
    }
}
