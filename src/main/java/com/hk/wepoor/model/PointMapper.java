package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.wepoor.vo.PointVO;

@Mapper
public interface PointMapper {
	
	List<PointVO> selectAll();
	PointVO select(int point_id);
	int insert(PointVO pointVO);
	int delete(int point_id);
	int update(PointVO pointVO);
	
	// 사용자의 포인트 내역 조회
	List<PointVO> selectAllUser(int user_no);
	
}
