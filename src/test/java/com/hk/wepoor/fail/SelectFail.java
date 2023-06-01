package com.hk.wepoor.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.FailService;
import com.hk.wepoor.vo.FailVO;
import com.hk.wepoor.vo.UserVO;

@SpringBootTest
class SelectFail {
	
	@Autowired
	FailService failservice;
	
	@Test
	void contextLoads() {
		
		FailVO failSelect = null;
		
		failSelect = failservice.select(6);	
		
		System.out.println(failSelect);
		
	}

}
