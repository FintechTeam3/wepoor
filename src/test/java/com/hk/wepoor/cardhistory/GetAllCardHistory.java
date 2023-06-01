package com.hk.wepoor.cardhistory;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CardHistoryService;
import com.hk.wepoor.vo.CardHistoryVO;

@SpringBootTest
class GetAllCardHistory {
	
	@Autowired
	CardHistoryService cardservice;
	
	@Test
	void contextLoads() {
		
		List<CardHistoryVO> list = null;
		
		list = cardservice.selectAll();
		
		for(CardHistoryVO card5:list) {
			System.out.println(card5);
		}
		
		
	}

}
