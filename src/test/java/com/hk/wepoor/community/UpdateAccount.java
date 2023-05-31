package com.hk.wepoor.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.AccountService;
import com.hk.wepoor.vo.AccountVO;

@SpringBootTest
class UpdateAccount {
	
	@Autowired
	AccountService account_service;
	
	@Test
	void contextLoads() {
		
		AccountVO accountVO = null;
		accountVO = new AccountVO(8, "신한은행", 1456873546);
		
		int affectRowCount = account_service.update(accountVO);
		
		if(affectRowCount == 1) {
			System.out.println(affectRowCount);
		} else {
			System.out.println("실패~!");
		}
		
	}

}
