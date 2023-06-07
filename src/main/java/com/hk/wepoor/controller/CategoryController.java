package com.hk.wepoor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.wepoor.service.CategoryService;
import com.hk.wepoor.vo.CategoryVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService category_service;
	
	// 카테고리 리스트 불러오기 - 혜정
	@GetMapping("/category")
	public String selectAll(Model model, HttpServletRequest req) {
		List<CategoryVO> list = category_service.selectAll();
		model.addAttribute("catelist", list);
		return "category";
	}
	
	// 갑경님 주차 페이지 예시 - 혜정
	@GetMapping("/weekendcategory")
	public String weekendcategory(Model model) {
		List<CategoryVO> list = category_service.selectAllWeekend();
		model.addAttribute("weekendlist", list);
		return "weekendcategory";
	}
	
}
