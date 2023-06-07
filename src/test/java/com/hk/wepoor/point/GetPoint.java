package com.hk.wepoor.point;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PointService;
import com.hk.wepoor.vo.PointVO;

@SpringBootTest
class GetPoint {
	
	@Autowired
	PointService point_service;
	
	@Test
	void contextLoads() {
		
		PointVO pointVO = point_service.select(1);
		
		System.out.println(pointVO);
		
		
	}

}
