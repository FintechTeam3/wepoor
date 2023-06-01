package com.hk.wepoor.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CategoryService;
import com.hk.wepoor.vo.CategoryVO;
@SpringBootTest
class GetCategory {
	@Autowired
	CategoryService category_service;
	
	@Test
	void contextLoads() {
		
		CategoryVO cateId = category_service.select(2);
		
		System.out.println("★★★★★★★★★★"+cateId+"★★★★★★★★★★★★");
		

		
	}

}
