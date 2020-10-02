/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Huy Hoang
 */
@Controller
@RequestMapping("/account/info")
public class ViewAccountInfoController {

    @GetMapping
    public String Default() {

        return "viewaccountinfo";
    }
}
