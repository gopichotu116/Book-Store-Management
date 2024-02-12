package com.bookStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Signup_LoginController {
	
	/* --------------------- Welcome Page ----------------------*/
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	/* --------------------- Login Page ----------------------*/
	
	@GetMapping("/login")
	public String login() {
		return "/login/login";
	}
	
	/* --------------------- SignUp Page ----------------------*/
	
	@GetMapping("/signup")
	public String signup() {
		return "signup/signup";
	}
	
}
