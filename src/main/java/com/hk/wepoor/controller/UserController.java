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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.vo.UserVO;

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
	

}
