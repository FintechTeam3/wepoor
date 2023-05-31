package com.hk.wepoor.poorroom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PoorRoomService;
import com.hk.wepoor.vo.PoorRoomVO;

@SpringBootTest
class InsertPoorRoom {
	
	@Autowired
	PoorRoomService poorroom_service;
	
	@Test
	void contextLoads() {
		PoorRoomVO poorroomVO = new PoorRoomVO(0,4,"참는자에게복이","20230620",70000);
		
		int affectRowCount = poorroom_service.create(poorroomVO);
		
		if(affectRowCount == 1) {
			System.out.println("입력완료!");
		} else {
			System.out.println("입력실패!");
		}
		
	}

}
