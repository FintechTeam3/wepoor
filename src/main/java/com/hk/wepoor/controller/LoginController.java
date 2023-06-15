package com.hk.wepoor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
 import com.hk.wepoor.jwt.Jwt;
import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.LoginService;
import com.hk.wepoor.service.UserService;
import com.hk.wepoor.vo.KakaoTokenVO;
import com.hk.wepoor.vo.TokenVO;
import com.hk.wepoor.vo.UserVO;

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
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String login(@RequestParam("userId") String userId, @RequestParam("userPwd") String userPwd,
			HttpServletResponse res,RedirectAttributes redirectAttributes){
		String jwtToken = logsvc.loginCheck(userId, userPwd);
		logger.info("JWTTOKEN: " + jwtToken);

		if (jwtToken == null || jwtToken.equals("0")) {
			redirectAttributes.addFlashAttribute("loginError",1);
			return "redirect:/login_page";
		} else {

			Cookie cookie = new Cookie("jwtToken", jwtToken);

			cookie.setHttpOnly(true);
			cookie.setMaxAge(3600);
			res.addCookie(cookie);

			Jws<Claims> claims = Jwt.parseToken(jwtToken);
			logger.info("userId: " + claims.getBody().get("userId"));
			logger.info("claims:" + claims.getBody());

			return "redirect:/weekend"; //weekend.html페이지로 이동
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		session.invalidate();
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				
				if (cookieName.equals("jwtToken")) {
					c.setMaxAge(0);
					response.addCookie(c);
				}else if(cookieName.equals("JSESSIONID")) {
					c.setMaxAge(0);
					response.addCookie(c);
				}else if(cookieName.equals("kakao_access_token")) {
					c.setMaxAge(0);
					response.addCookie(c);
				}
				
			}
		}

		return "redirect:/login_page";
	}

	@GetMapping("/kakaoLogin")
	public String kakao_login(HttpServletRequest req, HttpServletResponse res) {

		String code = req.getParameter("code");
		logger.info("code: ", code);
		KakaoTokenVO tokenRequestResponse = logsvc.getAccessToken(code);

		/*
		 * String jsonResult = gson.toJson(tokenRequestResponse); String accessToken =
		 * tokenRequestResponse.getAccess_token(); String refreshToken =
		 * tokenRequestResponse.getRefresh_token();
		 * 
		 * JsonObject data = JsonParser.parseString(jsonResult).getAsJsonObject(); //
		 * vo생성없이 json객체 다루기 String access_token =
		 * data.get("access_token").getAsString(); System.out.println(access_token);
		 */
		String accessToken = tokenRequestResponse.getAccess_token();
		String refreshToken = tokenRequestResponse.getRefresh_token();

		// kakao_access_token 쿠키 저장
		Cookie cookie1 = new Cookie("kakao_access_token", accessToken);
		cookie1.setHttpOnly(true);
		cookie1.setMaxAge(3600);
		res.addCookie(cookie1);

		String kakaoUser = logsvc.getKakaoUser(accessToken);
		JsonObject JsonkakaoUser = JsonParser.parseString(kakaoUser).getAsJsonObject();
		logger.info("data: " + kakaoUser);
		String userId = JsonkakaoUser.get("id").getAsString() + "@kakao";

		List<HashMap<String, String>> mails = mapper.getAllUserId();

		for (int i = 0; i < mails.size(); i++) {
			for (Entry<String, String> elem : mails.get(i).entrySet()) {

				if (elem.getValue().equals(userId)) {

					// jwt토큰 쿠키 저장
					String jwtToken = logsvc.loginCheck(userId, "-");
					
					Cookie cookie2 = new Cookie("jwtToken", jwtToken);
					cookie2.setHttpOnly(true);
					cookie2.setMaxAge(3600);
					res.addCookie(cookie2);

					logger.info("JWTTOKEN: " + jwtToken);
					logger.info("kakao_access_token: " + accessToken);

					return "redirect:/weekend";

				}

			}
		}

		String userNickname = JsonkakaoUser.get("kakao_account").getAsJsonObject()
				.get("profile").getAsJsonObject()
				.get("nickname").getAsString();
		
		String profileImg = JsonkakaoUser.get("kakao_account").getAsJsonObject().get("profile").getAsJsonObject()
				.get("profile_image_url").getAsString();
		if (JsonkakaoUser.get("email") != null) {
			String email = JsonkakaoUser.get("email").getAsString();
		}

		HttpSession session = req.getSession();
		session.setAttribute("userId", userId);
		session.setAttribute("userNickname", userNickname);
		session.setAttribute("profileImg", profileImg);

		return "additional_info";
	}
    TokenVO	tokenVO;
	
	// 사용자인증 & 토큰받기
	@GetMapping("/requesttoken2")
	public String reques(@RequestParam("code") String code) {

		TokenVO tokenVO = userService.requesttoken(code,"requesttoken2"); 

		this.tokenVO=tokenVO;
		 
		  return "end"; 
	}

	@PostMapping("/kakaoSignin")
	public String kakao_signin(@RequestParam("userName") String userName, @RequestParam("userPhone") String userPhone,
			HttpServletRequest req, HttpServletResponse res) {

		HttpSession session = req.getSession(false);
		String userId = (String) session.getAttribute("userId");
		String userNickname = (String) session.getAttribute("userNickname");
		String profileImg = (String) session.getAttribute("profileImg");
		
		UserVO uservo = new UserVO();
		uservo.setUserId(userId);
		uservo.setUserName(userName);
		uservo.setUserNickname(userNickname);
		uservo.setUserPhone(userPhone);
		uservo.setUserPwd("-");
		uservo.setUserSeqNo(tokenVO.getUserSeqNo());
		uservo.setAccessToken(tokenVO.getAccessToken());
		uservo.setRefreshToken(tokenVO.getRefreshToken());
 
		mapper.insertUser(uservo);

		session.invalidate();

		String jwtToken = logsvc.loginCheck(userId, "-");
		Cookie cookie2 = new Cookie("jwtToken", jwtToken);
		cookie2.setHttpOnly(true);
		cookie2.setMaxAge(3600);
		res.addCookie(cookie2);

		logger.info("JWTTOKEN: " + jwtToken);

		return "redirect:/weekend";
	}

	/*
	 * @GetMapping("/kakaoLogout") public String logoutKakao(HttpServletRequest req)
	 * { HttpSession session = req.getSession(false);
	 * 
	 * String access_token = ""; Cookie[] cookies = req.getCookies(); if (cookies !=
	 * null) { for (Cookie c : cookies) { String cookieName = c.getName();
	 * logger.info(cookieName); if (cookieName.equals("access_token")) {
	 * access_token = c.getValue(); } } }
	 * 
	 * Long user_id = (Long) session.getAttribute("user_id");
	 * System.out.println(user_id); System.out.println(access_token); String id =
	 * logsvc.kakaoLogout(user_id, access_token); System.out.println(id); return
	 * "redirect:/login_page"; }
	 */

	@GetMapping("/kakaoSignout")
	public String signoutKakao(HttpServletRequest req, @RequestParam("user_id") String user_id, @RequestParam("user_no") int user_no) {
	
		String access_token = "";
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				logger.info(cookieName);
				if (cookieName.equals("kakao_access_token")) {
					access_token = c.getValue();
				}
			}
		}
		System.out.println(user_id);
		
		String[] str_arr = user_id.split("@");
		Long user_id_long = Long.parseLong(str_arr[0]);
		
		
		
		
		System.out.print(user_id_long);
		System.out.println(access_token);
		String id = logsvc.kakaoSignout(user_id_long, access_token);
		System.out.println(id);
		userService.deleteUser(user_no);
		
		return "redirect:/logout";
	}

	
	
	
}
