package com.hk.wepoor.point;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PointService;
import com.hk.wepoor.vo.PointVO;

@SpringBootTest
class UpdatePoint {
	
	@Autowired
	PointService point_service;
	
	@Test
	void contextLoads() {
		
		PointVO pointVO = new PointVO(7, 38, "2023-06-01", 100000, null);
		
		int affectRowCount = point_service.update(pointVO);
		
		if(affectRowCount == 1) {
			System.out.println(affectRowCount);
		} else {
			System.out.println("실패~!");
		}
		
	}

}
