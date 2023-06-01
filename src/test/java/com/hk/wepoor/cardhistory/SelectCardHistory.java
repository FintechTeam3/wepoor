package com.hk.wepoor.cardhistory;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CardHistoryService;
import com.hk.wepoor.vo.CardHistoryVO;

@SpringBootTest
class SelectCardHistory {
	
	@Autowired
	CardHistoryService cardservice;
	
	@Test
	void contextLoads() {
	
		CardHistoryVO cardSelect = null;
	
		cardSelect = cardservice.select(6);	
	
		System.out.println(cardSelect);
	
	}
}
