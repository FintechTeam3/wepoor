package com.hk.wepoor.roomdetail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.RoomDetailService;
import com.hk.wepoor.vo.RoomDetailVO;

@SpringBootTest
public class SelectRoomDetail {
	
	
	@Autowired
	RoomDetailService svc;
	
	@Test
	void contextLoads() {
		
		List<RoomDetailVO> list = svc.selectAll(39);
		
		for(RoomDetailVO p:list) {
			System.out.println(p);
		}
		
		
	}
	
}
