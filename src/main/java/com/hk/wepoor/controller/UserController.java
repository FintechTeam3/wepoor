package com.hk.wepoor.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map.Entry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


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
	public String joinInsert(@ModelAttribute UserVO reqVo) {

		UserVO uservo = UserVO.User(reqVo);

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
	public String mymodify(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		int userNo = (int) session.getAttribute("userNo");
		UserVO userVO = mapper.getUserByUserNo(userNo);
		req.setAttribute("userPoint", userVO.getUserPoint());
		return "mymodify";
	}
	
	@PostMapping("/nickCheck")
	@ResponseBody
	public String nickCheck(@RequestParam("userNick") String userNick) {
		System.out.println(userNick);
		String result = "";
		
		UserVO userVO = mapper.getUserNick(userNick);
		if(userVO != null) {
			result = "f";
		} else {
			result = "t";
		}
		System.out.println(result);
		return result;
	}
	
	@PostMapping("/userModify")
	public String userModify(@ModelAttribute UserVO reqVo) {

		UserVO uservo = UserVO.User(reqVo);
		System.out.println(uservo);

		mapper.updateMy(uservo);
		
		return "redirect:/mymodify";
	}
	
	@PostMapping("/deleteUser")
	@ResponseBody
	public int deleteUser(@RequestParam("userNo") int userNo) {
		System.out.println(userNo);
		mapper.deleteUser(userNo);
		return 0;
	}
	
}
