package com.hk.wepoor.category;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.AccountService;
import com.hk.wepoor.vo.AccountVO;

@SpringBootTest
class GetAllAccount {
	
	@Autowired
	AccountService account_service;
	
	@Test
	void contextLoads() {
		
		List<AccountVO> list = null;
		
		list = account_service.selectAll();
		
		for(AccountVO a:list) {
			System.out.println(a);
		}
		
		
	}

}
