package com.hk.wepoor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/access", method = RequestMethod.GET)
public class AfterLoginController {

	
	@PostMapping("/")
	@ResponseBody
	public String access(HttpServletRequest req) {
		
		String jwtToken = req.getHeader("Authorization");
		return jwtToken;
	}
}
