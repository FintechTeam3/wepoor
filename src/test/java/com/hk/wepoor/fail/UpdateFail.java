package com.hk.wepoor.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.FailService;
import com.hk.wepoor.vo.FailVO;
import com.hk.wepoor.vo.UserVO;

@SpringBootTest
class UpdateFail {
	
	@Autowired
	FailService failservice;
	
	@Test
	void contextLoads() {
		
		FailVO failVO = null; //사용 전 미리 비워주는 역할
		failVO = new FailVO(8,2,10);
		
		int failUpdateCount=failservice.update(failVO);
		System.out.println(failUpdateCount);

}
}