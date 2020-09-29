package com.ebanking.controller;

import com.ebanking.entity.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {

    @GetMapping
    public String Default() {
        return "index";
    }

    @GetMapping(value = "login")
    public String Login(Model model) {
        LoginModel loginModel = new LoginModel();
        model.addAttribute("loginModel", loginModel);
        return "login";
    }
}
