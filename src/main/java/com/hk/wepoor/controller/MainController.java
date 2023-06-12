package com.hk.wepoor.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.GiftService;
import com.hk.wepoor.service.PointService;
import com.hk.wepoor.vo.GiftVO;
import com.hk.wepoor.vo.PointVO;
import com.hk.wepoor.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	GiftService giftService;
	
	@Autowired
	PointService pointService;
	
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
	
	// 신청하기
	@PostMapping("/giftSend")
	public String giftSend(@ModelAttribute GiftVO giftVO, HttpServletRequest req, int giftPrice) {
		HttpSession session = req.getSession(false);
		int userNo = (int) session.getAttribute("userNo");
		giftVO.setUser_no(userNo);
		giftService.create(giftVO);
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		PointVO pointVO = new PointVO(0, userNo, formatter.format(date), -giftPrice);
		pointService.create(pointVO);
		
		return "redirect:/giftbuypage";
	}
	
	// 기프트 구매 완료 페이지
	@GetMapping("/giftbuypage")
	public String giftbuypage(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		int userNo = (int) session.getAttribute("userNo");
		UserVO userVO = userMapper.getUserByUserNo(userNo);
		model.addAttribute("userNickname", userVO.getUserNickname());
		return "giftbuypage";
	}
	
}
