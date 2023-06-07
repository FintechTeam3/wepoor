package com.hk.wepoor.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import reactor.netty.http.server.HttpServerRequest;

@Controller
public class UserController {

	@Autowired
	UserMapper mapper;

	@GetMapping("/login_page")
	public String login() {
		return "login";
	}

	@GetMapping("/join")
	public String join() {
		return "join";
	}

	@PostMapping("/join_insert")
	public String joinInsert(@RequestParam("userId") String userId, @RequestParam("userPhone") String userPhone,
			@RequestParam("userName") String userName, @RequestParam("userNickname") String userNickname,
			@RequestParam("userPwd") String userPwd) {

		UserVO uservo = new UserVO();
		uservo.setUserId(userId);
		uservo.setUserName(userName);
		uservo.setUserNickname(userNickname);
		uservo.setUserPhone(userPhone);
		uservo.setUserPwd(userPwd);

		mapper.insertUser(uservo);

		return "redirect:/login_page";
	}

	@PostMapping("/id_check")
	@ResponseBody
	public int idCheck(@RequestParam("userId") String userId) {

		List<HashMap<String, String>> mails = mapper.getAllUserId();

		for (int i = 0; i < mails.size(); i++) {
			for (Entry<String, String> elem : mails.get(i).entrySet()) {

				if (elem.getValue().equals(userId)) {
					return 1;
				}

			}
		}

		return 0;
	}
	
	@GetMapping("/addtional_info")
	public String info() {
		return "additional_info";
	}
	
	// 마이페이지 닉네임, 포인트 불러오기 - 혜정
	@GetMapping("/mypage")
	public String mypage(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		
		String userId = (String) session.getAttribute("userId");
		
		UserVO userVO = mapper.getUserByUserId(userId);
		req.setAttribute("userNickname", userVO.getUserNickname());
	    req.setAttribute("userPoint", userVO.getUserPoint());
		return "mypage";
	}
	
	@GetMapping("/mymodify")
	public String mymodify() {
		return "mymodify";
	}
	
}
