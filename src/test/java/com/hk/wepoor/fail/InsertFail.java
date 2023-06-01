package com.hk.wepoor.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.FailService;
import com.hk.wepoor.vo.FailVO;
import com.hk.wepoor.vo.UserVO;

@SpringBootTest
class InsertFail {
	
	@Autowired
	FailService failservice;
	
	@Test
	void contextLoads() {
		
		FailVO failVO = new FailVO(0,1,8);
		
		int failCount=failservice.create(failVO);
		
		if(failCount==1) {
			System.out.println("성공");
			
		}else {
			System.out.println("실패");
		}
		
		
	}

}
