package com.hk.wepoor.category;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CategoryService;


@SpringBootTest
class InsertCategory {
	@Autowired
	CategoryService category_service;

	@Test
	void contextLoads() {
		
		
		int affectRowCount = category_service.insert("장난감");
		
		if(affectRowCount == 1){
			System.out.println("★★★★★★★★★★★★입력성공★★★★★★★★★★★★");
		}else {
			System.out.println("★★★★★★★★★★★★입력실패★★★★★★★★★★★★");
		}
	}

}
