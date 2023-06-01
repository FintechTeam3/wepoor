package com.hk.wepoor.roommember;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.AccountService;
import com.hk.wepoor.service.PoorRoomService;
import com.hk.wepoor.service.RoomMemberService;
import com.hk.wepoor.vo.AccountVO;
import com.hk.wepoor.vo.PoorRoomVO;
import com.hk.wepoor.vo.RoomMemberVO;

@SpringBootTest
class GetAllRoomMember {
	
	@Autowired
	RoomMemberService roomMember_service;
	
	@Test
	void contextLoads() {
		
		List<RoomMemberVO> list = roomMember_service.selectAll();
		
		for(RoomMemberVO a:list) {
			System.out.println(a);
		}
		
		
	}

}
