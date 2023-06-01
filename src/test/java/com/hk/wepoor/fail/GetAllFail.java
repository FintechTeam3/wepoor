package com.hk.wepoor.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.FailService;
import com.hk.wepoor.vo.FailVO;
import com.hk.wepoor.vo.UserVO;

@SpringBootTest
class GetAllFail {
	
	@Autowired
	FailService failservice;
	
	@Test
	void contextLoads() {
		
		List<FailVO> list = null;
		
		list = failservice.selectAll();
		
		for(FailVO fail8:list) {
			System.out.println(fail8);
		}
		
		
	}

}
