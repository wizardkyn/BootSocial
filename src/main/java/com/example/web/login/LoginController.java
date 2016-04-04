package com.example.web.login;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	
	@RequestMapping(value = "/")
	public String home() {

		System.out.println("getPrincipal " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		return "/index.jsp";
	}
	@RequestMapping(value = "/web/sample.do")
	public String sample() {
		return "/sample.jsp";
	}
	@RequestMapping(value="/login.do")
	public String userLogin(ModelMap model) {
		return "/login/signin.jsp";
	}
	@RequestMapping(value="/logout.do")
	public String logout(ModelMap model) {
		return "redirect:/login.do?logout";
	}
}
