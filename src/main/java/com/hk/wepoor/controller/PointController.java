package com.hk.wepoor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.PointService;
import com.hk.wepoor.vo.PointVO;
import com.hk.wepoor.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PointController {
	
	@Autowired
	UserMapper user_mapper;
	
	@Autowired
	PointService point_service;
	
	// 포인트 내역 페이지
	@GetMapping("/pointdetail")
	public String pointdetail(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		
		String userId = (String) session.getAttribute("userId");
		UserVO userVO = user_mapper.getUserByUserId(userId);
	    req.setAttribute("userPoint", userVO.getUserPoint());
	    
	    // 포인트 내역 불러오기
		int userNo = (int) session.getAttribute("userNo");
	    List<PointVO> pointdetail = point_service.selectAllUser(userNo);
	

		model.addAttribute("pointlist", pointdetail);

		return "pointdetail";
	}
	
	
	
}
