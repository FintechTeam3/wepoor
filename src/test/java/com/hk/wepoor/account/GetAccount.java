package com.hk.wepoor.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.AccountService;
import com.hk.wepoor.vo.AccountVO;

@SpringBootTest
class GetAccount {
	
	@Autowired
	AccountService account_service;
	
	@Test
	void contextLoads() {
		
		AccountVO accountVO = null;
		
		accountVO = account_service.select(1);
		
		System.out.println(accountVO);
		
		
	}

}
