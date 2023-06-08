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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		// form 데이터는 modelAttribute로 넘깁니다.
		// reqVo에 담긴 값들을 uservo로 넘겨주고 
		UserVO uservo = UserVO.User(reqVo);
		
		// insert 합니다.
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
		// session에 값이 들어있으면 비우지 말 것.
		HttpSession session = req.getSession(false);
		
		// session의 userId를 저장
		String userId = (String) session.getAttribute("userId");
		
		// 사용자의 정보를 가져온다.
		UserVO userVO = mapper.getUserByUserId(userId);
		
		// 필요한 정보를 set 시킨다.
		req.setAttribute("userNickname", userVO.getUserNickname());
	    req.setAttribute("userPoint", userVO.getUserPoint());
	    
		return "mypage";
	}
	
	// 내 정보 수정페이지 - 혜정
	@GetMapping("/mymodify")
	public String mymodify(HttpServletRequest req) {
		// session에 값이 들어있으면 비우지 말 것.
		HttpSession session = req.getSession(false);
		
		// session의 userNo를 저장
		int userNo = (int) session.getAttribute("userNo");
		
		// userNo으로 사용자의 정보를 가져온다.
		UserVO userVO = mapper.getUserByUserNo(userNo);
		
		// 필요한 정보를 set 한다.
		req.setAttribute("userPoint", userVO.getUserPoint());
		req.setAttribute("userNickname", userVO.getUserNickname());
		
		return "mymodify";
	}
	
	// 닉네임 중복 체크 - 혜정
	@PostMapping("/nickCheck")
	@ResponseBody
	public String nickCheck(@RequestParam("userNick") String userNick) {
		String result = "";
		
		// 넘어온 데이터(닉네임)로 DB에 조회되는 값이 있는지 찾는다.
		UserVO userVO = mapper.getUserNick(userNick);
		
		// 만약 조회가 될 경우, 중복으로 f를 return하고 조회가 될 경우 t를 return한다.
		if(userVO != null) {
			result = "f";
		} else {
			result = "t";
		}
		
		return result;
	}
	
	// 사용자 정보 수정 - 혜정
	@PostMapping("/userModify")
	public String userModify(@ModelAttribute UserVO reqVo) {
		
		// reqVo에 담긴 값들을 uservo로 넘겨주고
		UserVO uservo = UserVO.User(reqVo);
		
		// 받아온 정보로 사용자의 정보를 수정합니다.
		mapper.updateMy(uservo);
		
		// mymodify 페이지 새로고침
		return "redirect:/mymodify";
	}
	
	// 사용자 탈퇴 - 혜정
	@PostMapping("/deleteUser")
	@ResponseBody
	public int deleteUser(@RequestParam("userNo") int userNo, HttpServletRequest request, HttpServletResponse response) {
		// userNo을 받아와 삭제 작업을 수행합니다.
		mapper.deleteUser(userNo);
		
		// 세션 비우기
		HttpSession session = request.getSession(false);
		session.invalidate();
		
		// 쿠키 삭제
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
		
		return 0;
	}
	
}
