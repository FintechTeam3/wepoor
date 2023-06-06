package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.PointMapper;
import com.hk.wepoor.vo.PointVO;

@Service
public class PointService {
	
	@Autowired
	PointMapper point_mapper;
	
	public List<PointVO> selectAll() {
		List<PointVO> list = point_mapper.selectAll();
		return list;
	}
	
	public PointVO select(int point_id) {
		PointVO pointVO = point_mapper.select(point_id);
		return pointVO;
	}
	
	public int create(PointVO pointVO) {
		int affectRowCount = point_mapper.insert(pointVO);
		return affectRowCount;
	}
	
	public int delete(int point_id) {
		int affectRowCount = point_mapper.delete(point_id);
		return affectRowCount;
	}
	
	public int update(PointVO pointVO) {
		int affectRowCount = point_mapper.update(pointVO);
		return affectRowCount;
	}
	
}
