package com.hk.wepoor.community;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CommunityService;
import com.hk.wepoor.vo.CommunityVO;

@SpringBootTest
class GetAllCommunity {
	
	@Autowired
	CommunityService community_service;
	
	@Test
	void contextLoads() {
		
		List<CommunityVO> list = null;
		
		list = community_service.selectAll();
		
		for(CommunityVO c:list) {
			System.out.println(c);
		}
		
		
	}

}
