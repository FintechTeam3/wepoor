package com.hk.wepoor.poorroom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PoorRoomService;
import com.hk.wepoor.vo.PoorRoomVO;

@SpringBootTest
class GetPoorRoom {

	@Autowired
	PoorRoomService poorroom_service;

	@Test
	void contextLoads() {

		PoorRoomVO poorroomVO = poorroom_service.select(1);

		System.out.println("★★★★★★★★★★"+poorroomVO+"★★★★★★★★★★★★");

	}

}
