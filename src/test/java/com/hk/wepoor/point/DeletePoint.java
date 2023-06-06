package com.hk.wepoor.point;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PointService;

@SpringBootTest
class DeletePoint {
	
	@Autowired
	PointService point_service;
	
	@Test
	void contextLoads() {
		
		int affectRowCount = point_service.delete(7);
		
		if(affectRowCount == 1) {
			System.out.println(affectRowCount);
		} else {
			System.out.println("실패~!");
		}
		
	}

}
