package com.hk.wepoor.pay;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PayService;
import com.hk.wepoor.vo.PayVO;

@SpringBootTest
class GetAllPay {
	
	@Autowired
	PayService pay_service;
	
	@Test
	void contextLoads() {
		
		List<PayVO> list = null;
		
		list = pay_service.selectAll();
		
		for(PayVO p:list) {
			System.out.println(p);
		}
		
	}

}
