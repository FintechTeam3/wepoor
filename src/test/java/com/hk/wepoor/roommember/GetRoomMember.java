package com.hk.wepoor.roommember;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PoorRoomService;
import com.hk.wepoor.service.RoomMemberService;
import com.hk.wepoor.vo.PoorRoomVO;
import com.hk.wepoor.vo.RoomMemberVO;

@SpringBootTest
class GetRoomMember {

	@Autowired
	RoomMemberService roomMember_service;

	@Test
	void contextLoads() {

		RoomMemberVO roomMemberVO = roomMember_service.select(1);

		System.out.println("★★★★★★★★★★"+roomMemberVO+"★★★★★★★★★★★★");

	}

}
