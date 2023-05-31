package com.hk.wepoor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hk.wepoor.jwt.Jwt;
import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.LoginService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@Autowired
	UserMapper mapper;

	@Autowired
	LoginService logsvc;

	@PostMapping("/login")
	public String login(@RequestParam("userId") String userId, @RequestParam("userPwd") String userPwd,
			HttpServletResponse res) {
		String jwtToken = logsvc.loginCheck(userId, userPwd);
		System.out.println(jwtToken);
		
		if (jwtToken == null || jwtToken.equals("0")) {
			return "0";
		} else {
			
			res.setHeader("Authorization", "Bearer " +jwtToken);
			
			boolean a = Jwt.isValidToken(jwtToken);
			return "access";
		}
	}

}
