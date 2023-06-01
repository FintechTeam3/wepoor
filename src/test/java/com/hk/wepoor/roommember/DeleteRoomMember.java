package com.hk.wepoor.roommember;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PoorRoomService;
import com.hk.wepoor.service.RoomMemberService;

@SpringBootTest
class DeleteRoomMember {

	@Autowired
	RoomMemberService roomMember_service;

	@Test
	void contextLoads() {

		int affectRowCount = roomMember_service.delete(11);

		if (affectRowCount == 1) {
			System.out.println("★★★★★★★★★★★★삭제성공★★★★★★★★★★★★");
		} else {
			System.out.println("★★★★★★★★★★★★삭제실패★★★★★★★★★★★★");
		}

	}

}
