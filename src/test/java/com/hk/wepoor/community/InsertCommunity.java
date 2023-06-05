package com.hk.wepoor.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CommunityService;
import com.hk.wepoor.vo.CommunityVO;

@SpringBootTest
class InsertCommunity {
	
	@Autowired
	CommunityService community_service;
	
	@Test
	void contextLoads() {
		
		CommunityVO communityVO = null;
		communityVO = new CommunityVO(0, 7, 2, "반갑습니다!", null);
		
		int affectRowCount = community_service.create(communityVO);
		
		System.out.println(affectRowCount);
		if(affectRowCount == 1) {
			System.out.println(affectRowCount);
		} else {
			System.out.println("실패~!");
		}
		
	}

}
