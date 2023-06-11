package com.hk.wepoor.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.CategoryService;
import com.hk.wepoor.vo.CategoryVO;
import com.hk.wepoor.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserMapper user_mapper;
	
	// 카테고리 리스트 불러오기 - 혜정
//	@GetMapping("/category")
//	public String selectAll(Model model, HttpServletRequest req) {
//		
//		// jwt에서 넘어온 userid와 userno은 session에 저장되어 있고, 
//		// session을 가져왔을 때 값이 들어있으면 비우지 말 것.
//		HttpSession session = req.getSession(false);
//		
//		// session에 저장되어 있는 userNo을 가져와 변수에 저장시킨다.
//		int userNo = (int) session.getAttribute("userNo");
//		
//		// 가져온 userNo으로 사용자의 정보를 가져온다.
//		UserVO userVO = user_mapper.getUserByUserNo(userNo);
//		
//		// 필요한 정보를 set 시킨다.
//		req.setAttribute("userNickname", userVO.getUserNickname());
//		
//		List<CategoryVO> list = categoryService.selectAll();
//		model.addAttribute("catelist", list);
//		
//		return "category";
//	}
	
	//주차별 기간 목록 보여주는 카테고리
	@GetMapping("/weekend")//weekend.html
	public String weekend(Model m1, HttpServletRequest req) {
		List<CategoryVO> list = categoryService.selectAllWeekend();
		m1.addAttribute("weeklist", list);
		
		// 햄버거 바를 위한 것 - 혜정
		HttpSession session = req.getSession(false);
		int userNo = (int) session.getAttribute("userNo");
		UserVO userVO = user_mapper.getUserByUserNo(userNo);
		req.setAttribute("userNickname", userVO.getUserNickname());
		
		return "weekend"; //weekend.html
		
	}
	
	// N주차 방목록(8개 카테고리)
	@GetMapping("/category")
	public String selectAllRoomList(Model model, @RequestParam("cate_weekend") int cate_weekend, HttpServletRequest req) {
		List<CategoryVO> list = categoryService.selectAllRoomList(cate_weekend);
		model.addAttribute("catelist", list);
		
		// 햄버거 바를 위한 것 - 혜정
		HttpSession session = req.getSession(false);
		int userNo = (int) session.getAttribute("userNo");
		UserVO userVO = user_mapper.getUserByUserNo(userNo);
		req.setAttribute("userNickname", userVO.getUserNickname());
		
		return "category";
	}


	@PostMapping("/roomcheck")
	@ResponseBody
	public boolean roomcheck(@RequestParam("cate_date") String cate_date, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		int user_no = (int) session.getAttribute("userNo");
		
		List<String> list = categoryService.roomCheck(user_no);
		
		Set<String> set = new HashSet<>(list);
		
		boolean check = set.contains(cate_date);
		
		// true이면 중복이 있다 => 기간 내에 하나만 false=> 가능
		return check;
	}


}
