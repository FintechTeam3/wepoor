package com.hk.wepoor.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.AccountService;
import com.hk.wepoor.vo.AccountVO;

@SpringBootTest
class DeleteAccount {
	
	@Autowired
	AccountService account_service;
	
	@Test
	void contextLoads() {
		
		int affectRowCount = account_service.delete(9);
		
		if(affectRowCount == 1) {
			System.out.println(affectRowCount);
		} else {
			System.out.println("실패~!");
		}
		
	}

}
