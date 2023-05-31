package com.hk.wepoor.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.AccountService;
import com.hk.wepoor.service.CommunityService;
import com.hk.wepoor.vo.AccountVO;
import com.hk.wepoor.vo.CommunityVO;

@SpringBootTest
class GetCommunity {
	
	@Autowired
	CommunityService community_service;
	
	@Test
	void contextLoads() {
		
		CommunityVO community = null;
		
		community = community_service.select(1);
		
		System.out.println(community);
		
		
	}

}
