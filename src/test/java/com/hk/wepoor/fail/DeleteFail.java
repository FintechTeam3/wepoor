package com.hk.wepoor.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.FailService;
import com.hk.wepoor.vo.FailVO;
import com.hk.wepoor.vo.UserVO;

@SpringBootTest
class DeleteFail {
	
	@Autowired
	FailService failservice;
	
	@Test
	void contextLoads() {
		
		int failDeleteCount=failservice.delete(7); //fail_id = 7 인 컬럼 삭제해라 => parameter=7
		System.out.println(failDeleteCount);
		}
		
		
	}

