package com.hk.wepoor.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.AccountService;
import com.hk.wepoor.vo.AccountVO;

@SpringBootTest
class InsertAccount {
	
	@Autowired
	AccountService account_service;
	
	@Test
	void contextLoads() {
		
		AccountVO accountVO = null;
		accountVO = new AccountVO(13, "삽입테스트", "123456789");
		
		int affectRowCount = account_service.create(accountVO);
		
		if(affectRowCount == 1) {
			System.out.println(affectRowCount);
		} else {
			System.out.println("실패~!");
		}
		
	}

}
