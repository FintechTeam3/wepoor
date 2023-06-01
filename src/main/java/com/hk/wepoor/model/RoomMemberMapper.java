package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.wepoor.vo.RoomMemberVO;

@Mapper 
public interface RoomMemberMapper {
		List<RoomMemberVO> selectAll();
		RoomMemberVO select(int i);
		int insert(RoomMemberVO roomMemberVO);
		int delete(int room_id);
		int update(RoomMemberVO roomMemberVO);
}
