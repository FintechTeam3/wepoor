package com.hk.wepoor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.CategoryService;
import com.hk.wepoor.service.PayService;
import com.hk.wepoor.service.PointService;
import com.hk.wepoor.vo.CategoryVO;
import com.hk.wepoor.vo.PayVO;
import com.hk.wepoor.vo.PointVO;
import com.hk.wepoor.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PayController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PayService payService;
	
	@Autowired
	PointService pointService;
	
	// 구매 - 혜정
	@GetMapping("paydetail")
	public String paydetail(int cate_id, Model model, HttpServletRequest req) {
		
		CategoryVO cateVO = categoryService.select(cate_id);
		model.addAttribute("category", cateVO);
		
		HttpSession session = req.getSession(false);
		int userNo = (int) session.getAttribute("userNo");
		UserVO userVO = userMapper.getUserByUserNo(userNo);
		req.setAttribute("userPoint", userVO.getUserPoint());
		req.setAttribute("userName", userVO.getUserName());
		req.setAttribute("userNickname", userVO.getUserNickname());
		
		return "paydetail";
	}
	
	// 구매 완료 - 혜정
	@GetMapping("paycomplete")
	public String paycomplete(PayVO payVO, Model model, PointVO pointVO) {
		payService.create(payVO);
		pointService.create(pointVO);
		
		CategoryVO cateVO = categoryService.select(payVO.getCate_id());
		model.addAttribute("category", cateVO);
		
		int pId = payVO.getPay_id();
		PayVO pay = payService.selectPayComplete(pId);
		model.addAttribute("pay", pay);
		
		UserVO userVO = userMapper.getUserByUserNo(payVO.getUser_no());
		model.addAttribute("user", userVO);
		
		int pointId = pointVO.getPoint_id();
		PointVO point = pointService.select(pointId);
		model.addAttribute("point", point);
		
		return "paycomplete";
	}
	
	// 결제 내역 화면 - 혜정
	@GetMapping("payhistory")
	public String payhistory(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		int userNo = (int) session.getAttribute("userNo");
		List<PayVO> list = payService.selectPayHistory(userNo);
		model.addAttribute("payhistorylist", list);
		return "payhistory";
	}
}
