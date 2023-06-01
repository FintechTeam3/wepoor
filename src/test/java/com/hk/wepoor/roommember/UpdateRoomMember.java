package com.hk.wepoor.roommember;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PoorRoomService;
import com.hk.wepoor.service.RoomMemberService;
import com.hk.wepoor.vo.PoorRoomVO;
import com.hk.wepoor.vo.RoomMemberVO;

@SpringBootTest
class UpdateRoomMember {
	
	@Autowired
	RoomMemberService roomMember_service;
	
	@Test
	void contextLoads() {
		RoomMemberVO roomMemberVO = new RoomMemberVO(10,11,11);
		
		int affectRowCount = roomMember_service.update(roomMemberVO);
		
		if(affectRowCount == 1){
			System.out.println("★★★★★★★★★★★★수정성공★★★★★★★★★★★★");
		}else {
			System.out.println("★★★★★★★★★★★★수정실패★★★★★★★★★★★★");
		}
		
	}

}
