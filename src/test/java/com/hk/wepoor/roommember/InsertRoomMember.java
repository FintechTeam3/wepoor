package com.hk.wepoor.roommember;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.RoomMemberService;
import com.hk.wepoor.vo.RoomMemberVO;

@SpringBootTest
class InsertRoomMember {
	
	@Autowired
	RoomMemberService roomMember_service;
	
	@Test
	void contextLoads() {
		RoomMemberVO roomMemberVO = new RoomMemberVO(0,11,0);
		
		int affectRowCount = roomMember_service.create(roomMemberVO);
		
		if(affectRowCount == 1){
			System.out.println("★★★★★★★★★★★★입력성공★★★★★★★★★★★★");
		}else {
			System.out.println("★★★★★★★★★★★★입력실패★★★★★★★★★★★★");
		}
		
	}

}
