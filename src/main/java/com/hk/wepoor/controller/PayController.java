package com.hk.wepoor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayController {
	
	@GetMapping("paydetail")
	public String paydetail() {
		return "paydetail";
	}
	
}
