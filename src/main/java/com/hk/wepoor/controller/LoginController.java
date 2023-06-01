package com.hk.wepoor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.wepoor.jwt.Jwt;
import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.LoginService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import okhttp3.logging.HttpLoggingInterceptor;

@Controller
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(HttpLoggingInterceptor.class);

	@Autowired
	UserMapper mapper;

	@Autowired
	LoginService logsvc;

	@PostMapping("/login")
	public String login(@RequestParam("userId") String userId, @RequestParam("userPwd") String userPwd,
			HttpServletResponse res) {
		String jwtToken = logsvc.loginCheck(userId, userPwd);
		logger.info("JWTTOKEN: " + jwtToken);

		if (jwtToken == null || jwtToken.equals("0")) {
			return "0";
		} else {

			Cookie cookie = new Cookie("jwtToken", jwtToken);

			cookie.setHttpOnly(true);
			cookie.setMaxAge(3600);
			res.addCookie(cookie);

			Jws<Claims> claims = Jwt.parseToken(jwtToken);
			logger.info("userId: " + claims.getBody().get("userId"));
			logger.info("claims:" + claims.getBody());

			return "redirect:/access";
		}
	}

}
