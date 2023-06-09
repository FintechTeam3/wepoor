package com.hk.wepoor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.wepoor.service.CategoryService;
import com.hk.wepoor.vo.CategoryVO;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService category_service;	
	
	/*@GetMapping("/category")
	public String selectAll(Model model) {
		List<CategoryVO> list = category_service.selectAll();
		model.addAttribute("catelist", list);
		return "category";
	}*/
	
	//주차별 기간 목록 보여주는 카테고리
	@GetMapping("/weekend")//weekend.html
	public String weekend(Model m1) {
		List<CategoryVO> list = category_service.selectAllWeekend();
		m1.addAttribute("weeklist", list);
		return "weekend"; //weekend.html
		
	}
	
	// N주차 방목록(8개 카테고리)
	@GetMapping("/category")
	public String selectAllRoomList(Model model, @RequestParam("cate_weekend") int cate_weekend) {
		List<CategoryVO> list = category_service.selectAllRoomList(cate_weekend);
		model.addAttribute("catelist", list);
		return "category";
	}
}
