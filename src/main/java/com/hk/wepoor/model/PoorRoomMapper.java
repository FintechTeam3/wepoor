package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.wepoor.vo.PoorRoomVO;

@Mapper
public interface PoorRoomMapper {
	List<PoorRoomVO> selectAll();
	PoorRoomVO select(int i);
	int insert(PoorRoomVO poorRoomVO);
	int delete(int room_id);
	int update(PoorRoomVO poorRoomVO);
}