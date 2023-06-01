package com.hk.wepoor.poorroom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PoorRoomService;

@SpringBootTest
class DeletePoorRoom {

	@Autowired
	PoorRoomService poorroom_service;

	@Test
	void contextLoads() {

		int affectRowCount = poorroom_service.delete(5);

		if (affectRowCount == 1) {
			System.out.println("★★★★★★★★★★★★삭제성공★★★★★★★★★★★★");
		} else {
			System.out.println("★★★★★★★★★★★★삭제실패★★★★★★★★★★★★");
		}

	}

}
