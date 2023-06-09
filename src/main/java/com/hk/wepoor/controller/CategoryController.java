package com.hk.wepoor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.CategoryService;
import com.hk.wepoor.vo.CategoryVO;

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
	public String weekend(Model m1) {
		List<CategoryVO> list = categoryService.selectAllWeekend();
		m1.addAttribute("weeklist", list);
		return "weekend"; //weekend.html
		
	}
	
	// N주차 방목록(8개 카테고리)
	@GetMapping("/category")
	public String selectAllRoomList(Model model, @RequestParam("cate_weekend") int cate_weekend) {
		List<CategoryVO> list = categoryService.selectAllRoomList(cate_weekend);
		model.addAttribute("catelist", list);
		return "category";
	}
}
