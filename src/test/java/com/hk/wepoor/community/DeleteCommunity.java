package com.hk.wepoor.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CommunityService;

@SpringBootTest
class DeleteCommunity {
	
	@Autowired
	CommunityService community_service;
	
	@Test
	void contextLoads() {
		
		int affectRowCount = community_service.delete(7);
		
		if(affectRowCount == 1) {
			System.out.println(affectRowCount);
		} else {
			System.out.println("실패~!");
		}
		
	}

}
