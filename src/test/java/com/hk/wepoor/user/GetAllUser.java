package com.hk.wepoor.user;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.UserService;
import com.hk.wepoor.vo.UserVO;

@SpringBootTest
class GetAllUser {
	
	@Autowired
	UserService userservice;
	
	@Test
	void contextLoads() {
		
		List<UserVO> list = null;
		
		list = userservice.selectAll();
		
		for(UserVO u:list) {
			System.out.println(u);
		}
		
		
	}

}
