package com.hk.wepoor.category;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CategoryService;
import com.hk.wepoor.vo.CategoryVO;

@SpringBootTest
class GetAllCategory {
	@Autowired
	CategoryService category_service;
	
	@Test
	void contextLoads() {
	List<CategoryVO> list = null;
		
		list = category_service.selectAllWeekend();
		
		for(CategoryVO a:list) {
			System.out.println(a);
		}
		
	}

}
