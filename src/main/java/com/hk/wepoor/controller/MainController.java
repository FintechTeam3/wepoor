package com.hk.wepoor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	UserMapper userMapper;
	
	// 메인페이지
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/roomMake")
	public String roomMake() {
		return "roomMake";
	}
	
	@GetMapping("/services")
	public String services() {
		return "services";
	}
	
	// 페이지 프레임
	@GetMapping("/page")
	public String page() {
		return "page";
	}
	
	// 기프트 카드
	@GetMapping("/gift")
	public String gift(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		int userNo = (int) session.getAttribute("userNo");
		UserVO userVO = userMapper.getUserByUserNo(userNo);
		model.addAttribute("userNickname", userVO.getUserNickname());
		return "gift";
	}
	
}
