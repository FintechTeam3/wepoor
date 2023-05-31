package com.hk.wepoor.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CategoryService;
@SpringBootTest
class DeleteCategory {
	@Autowired
	CategoryService category_service;
	
	@Test
	void contextLoads() {
		
		int affectRowCount = category_service.delte(7);
		
		if(affectRowCount == 1){
			System.out.println("★★★★★★★★★★★★삭제성공★★★★★★★★★★★★");
		}else {
			System.out.println("★★★★★★★★★★★★삭제실패★★★★★★★★★★★★");
		}

		
	}

}
