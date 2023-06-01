package com.hk.wepoor.cardhistory;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CardHistoryService;
import com.hk.wepoor.vo.CardHistoryVO;

@SpringBootTest
class UpdateCardHistory {
	
	@Autowired
	CardHistoryService cardservice;
	
	@Test
	void contextLoads() {
		
		CardHistoryVO cardVO = null;
		cardVO = new CardHistoryVO(5,"하나카드","20230602",15000,"스타벅스");
		
		int cardUpdateCount=cardservice.update(cardVO);
		System.out.println(cardUpdateCount);
	}
}
