package com.ebanking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String Default() {
		return "index";
	}
}
