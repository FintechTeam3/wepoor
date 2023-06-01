package com.hk.wepoor.cardhistory;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CardHistoryService;
import com.hk.wepoor.vo.CardHistoryVO;

@SpringBootTest
class InsertCardHistory {
	
	@Autowired
	CardHistoryService cardservice;
	
	@Test
	void contextLoads() {
	
		CardHistoryVO cardVO = new CardHistoryVO(9,"현대카드","20230301",3000,"컴포즈커피");
	
		int cardHistoryCount=cardservice.create(cardVO);
		
		if(cardHistoryCount==1) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	
	}
}
