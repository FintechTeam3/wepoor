package com.hk.wepoor.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.CommunityService;
import com.hk.wepoor.vo.CommunityVO;

@SpringBootTest
class UpdateAccount {
	
	@Autowired
	CommunityService community_service;
	
	@Test
	void contextLoads() {
		
		CommunityVO communityVO = null;

		communityVO = new CommunityVO(8, 0, 0, "별로 안반가워요", null,null,null,0);
		
		int affectRowCount = community_service.update(communityVO);
		
		if(affectRowCount == 1) {
			System.out.println(affectRowCount);
		} else {
			System.out.println("실패~!");
		}
		
	}

}
