package com.hk.wepoor.category;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CategoryService;
import com.hk.wepoor.vo.CategoryVO;


@SpringBootTest
class UpdateCategory {
	@Autowired
	CategoryService category_service;

	@Test
	void contextLoads() {
		
		
		CategoryVO categoryVO =  new CategoryVO(10, "자동차");
		
		int affectRowCount = category_service.update(categoryVO);
		
		if(affectRowCount == 1){
			System.out.println("★★★★★★★★★★★★입력성공★★★★★★★★★★★★");
		}else {
			System.out.println("★★★★★★★★★★★★입력실패★★★★★★★★★★★★");
		}
	}

}
