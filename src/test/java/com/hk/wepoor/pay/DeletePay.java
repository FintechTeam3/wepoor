package com.hk.wepoor.pay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PayService;

@SpringBootTest
class DeletePay {
	
	@Autowired
	PayService pay_service;
	
	@Test
	void contextLoads() {
		
		int affectRowCount = pay_service.delete(8);
		
		if(affectRowCount == 1) {
			System.out.println(affectRowCount);
		} else {
			System.out.println("실패~!");
		}
		
	}

}
