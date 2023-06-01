package com.hk.wepoor.poorroom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PoorRoomService;
import com.hk.wepoor.vo.PoorRoomVO;

@SpringBootTest
class UpdatePoorRoom {
	
	@Autowired
	PoorRoomService poorroom_service;
	
	@Test
	void contextLoads() {
		PoorRoomVO PoorroomVO = new PoorRoomVO(5,2,"요기요싫어","20230605",30000);
		
		int affectRowCount = poorroom_service.update(PoorroomVO);
		
		if(affectRowCount == 1){
			System.out.println("★★★★★★★★★★★★수정성공★★★★★★★★★★★★");
		}else {
			System.out.println("★★★★★★★★★★★★수정실패★★★★★★★★★★★★");
		}
		
	}

}
