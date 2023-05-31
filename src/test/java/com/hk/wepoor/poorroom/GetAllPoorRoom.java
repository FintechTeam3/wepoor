package com.hk.wepoor.poorroom;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.AccountService;
import com.hk.wepoor.service.PoorRoomService;
import com.hk.wepoor.vo.AccountVO;
import com.hk.wepoor.vo.PoorRoomVO;

@SpringBootTest
class GetAllPoorRoom {
	
	@Autowired
	PoorRoomService poorroom_service;
	
	@Test
	void contextLoads() {
		
		List<PoorRoomVO> list = poorroom_service.selectAll();
		
		for(PoorRoomVO a:list) {
			System.out.println(a);
		}
		
		
	}

}
