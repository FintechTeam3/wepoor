package com.hk.wepoor.pay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PayService;
import com.hk.wepoor.vo.PayVO;

@SpringBootTest
class InsertPay {
	
	@Autowired
	PayService pay_service;
	
	@Test
	void contextLoads() {
		
		PayVO payVO = null;
		payVO = new PayVO(0, 1,1, "20230601", 50000, "신한카드!", "카드", 0, null, null, null);
		
		int affectRowCount = pay_service.create(payVO);
		
		if(affectRowCount == 1) {
			System.out.println(affectRowCount);
		} else {
			System.out.println("실패~!");
		}
		
	}

}
