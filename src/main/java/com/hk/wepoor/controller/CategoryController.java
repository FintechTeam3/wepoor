package com.hk.wepoor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.wepoor.service.CategoryService;
import com.hk.wepoor.vo.CategoryVO;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService category_service;
	
	@GetMapping("/category")
	public String selectAll(Model model) {
		List<CategoryVO> list = category_service.selectAll();
		model.addAttribute("catelist", list);
		return "category";
	}
	
	
}
