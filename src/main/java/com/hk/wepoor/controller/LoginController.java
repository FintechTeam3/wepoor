package com.hk.wepoor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hk.wepoor.config.MyApiClient;
import com.hk.wepoor.jwt.Jwt;
import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.LoginService;
import com.hk.wepoor.vo.KakaoTokenVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import okhttp3.logging.HttpLoggingInterceptor;
import reactor.core.publisher.Mono;

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
			return "redirect:/login_page";
		} else {

			Cookie cookie = new Cookie("jwtToken", jwtToken);

			cookie.setHttpOnly(true);
			cookie.setMaxAge(3600);
			res.addCookie(cookie);

			Jws<Claims> claims = Jwt.parseToken(jwtToken);
			logger.info("userId: " + claims.getBody().get("userId"));
			logger.info("claims:" + claims.getBody());

			return "redirect:/category";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				logger.info(cookieName);
				if (cookieName.equals("jwtToken")) {
					c.setMaxAge(0);
					response.addCookie(c);
					return "redirect:/login_page";
				}
			}
		}

		return "redirect:/login_page";
	}
	
	@GetMapping("/kakaoLogin")
	public String test(HttpServletRequest req, HttpServletResponse res) {
		
		String code = req.getParameter("code");
		System.out.println(code);
		KakaoTokenVO tokenRequestResponse = logsvc.getAccessToken(code);
		 
        Gson gson = new Gson();
		String jsonResult = gson.toJson(tokenRequestResponse);
		String accessToken = tokenRequestResponse.getAccess_token();
		String refreshToken = tokenRequestResponse.getRefresh_token();
		
		JsonObject data = JsonParser.parseString(jsonResult).getAsJsonObject(); // vo생성없이 json객체 다루기
		String access_token = data.get("access_token").getAsString();
		System.out.println(access_token);
		
		
		String kakaoUser = logsvc.getKakaoUser(accessToken);
		JsonObject data2 = JsonParser.parseString(kakaoUser).getAsJsonObject();
		Long user_id = data2.get("id").getAsLong();
		
		req.setAttribute("requestAccess", jsonResult);
		req.setAttribute("userinfo", kakaoUser);
		req.setAttribute("user_id", user_id);
		req.setAttribute("access_token", access_token);
		
		HttpSession session = req.getSession();
		session.setAttribute("user_id", user_id);
		Cookie cookie = new Cookie("access_token", access_token);

		cookie.setHttpOnly(true);
		cookie.setMaxAge(3600);
		res.addCookie(cookie);
		return "kakaologin";
	}
	
	
	
	@GetMapping("/kakaoLogout")
	public String logoutKakao(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		
		
		String access_token = "";
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				logger.info(cookieName);
				if (cookieName.equals("access_token")) {
					access_token = c.getValue();
				}
			}
		}
		
		Long user_id = (Long) session.getAttribute("user_id");
		System.out.println(user_id);
		System.out.println(access_token);
		String id = logsvc.kakaoLogout(user_id, access_token);
		System.out.println(id);
		return "redirect:/login_page";
	}
	
	
	@GetMapping("/kakaoSignout")
	public String signoutKakao(HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		String access_token = "";
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				logger.info(cookieName);
				if (cookieName.equals("access_token")) {
					access_token = c.getValue();
				}
			}
		}

		Long user_id = (Long) session.getAttribute("user_id");
		System.out.println(user_id);
		System.out.println(access_token);
		String id = logsvc.kakaoSignout(user_id, access_token);
		System.out.println(id);
		return "redirect:/login_page";
	}	
	
	
}
