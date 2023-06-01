package com.hk.wepoor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/access")
public class AfterLoginController {

	@GetMapping("")
	public String access() {
		return "access";
	}

	@GetMapping("/mypage")
	public String mypage() {

		return "mypage";
	}

}
