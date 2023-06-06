package com.hk.wepoor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
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
	
	// 페이지 프레임
	@GetMapping("/page")
	public String page() {
		return "page";
	}
	
}
