package com.hk.wepoor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.wepoor.service.UserService;
import com.hk.wepoor.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	UserService userservice;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("list")
	public String selectAll(Model model) {
		List<UserVO> list = userservice.selectAll();
		model.addAttribute("list", list);
		return "list";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("/services")
	public String services() {
		return "services";
	}
	
	@GetMapping("/teashop")
	public String teashop() {
		return "teashop";
	}
	
}
