package com.hk.wepoor.pay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PayService;
import com.hk.wepoor.vo.PayVO;

@SpringBootTest
class GetPay {
	
	@Autowired
	PayService pay_service;
	
	@Test
	void contextLoads() {
		
		PayVO payVO = null;
		
		payVO = pay_service.select(1);
		
		System.out.println(payVO);
		
	}

}
