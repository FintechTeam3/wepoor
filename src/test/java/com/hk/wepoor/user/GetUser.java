package com.hk.wepoor.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.AccountService;
import com.hk.wepoor.vo.AccountVO;
import com.hk.wepoor.vo.UserVO;

@SpringBootTest
class GetUser {
	
	@Autowired
	AccountService account_service;
	
	@Autowired
	UserMapper user_mapper;
	
	@Test
	void contextLoads() {
		
		UserVO userVO = null;
		
		userVO = user_mapper.getUserNick("emhaki");
		
		System.out.println(userVO);
		
		
	}

}
