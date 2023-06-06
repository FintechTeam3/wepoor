package com.hk.wepoor.point;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.wepoor.service.PointService;
import com.hk.wepoor.vo.PointVO;

@SpringBootTest
class GetAllUserPoint {
	
	@Autowired
	PointService point_service;
	
	@Test
	void contextLoads() {
		
		List<PointVO> list = null;
		
		list = point_service.selectAllUser(37);
		
		for(PointVO p:list) {
			System.out.println(p);
		}
		
		
	}

}
