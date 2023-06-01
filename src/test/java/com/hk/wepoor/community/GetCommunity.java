package com.hk.wepoor.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CommunityService;
import com.hk.wepoor.vo.CommunityVO;

@SpringBootTest
class GetCommunity {
	
	@Autowired
	CommunityService community_service;
	
	@Test
	void contextLoads() {
		
		CommunityVO communityVO = null;
		
		communityVO = community_service.select(1);
		
		System.out.println(communityVO);
		
		
	}

}
