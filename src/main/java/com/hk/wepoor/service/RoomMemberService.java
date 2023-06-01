package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.RoomMemberMapper;
import com.hk.wepoor.vo.RoomMemberVO;

@Service
public class RoomMemberService {

	@Autowired
	RoomMemberMapper roomMember_mapper;

	// <전체조회>
	public List<RoomMemberVO> selectAll() {

		List<RoomMemberVO> list = roomMember_mapper.selectAll();
		return list;
	}

	// <하나조회>
	public RoomMemberVO select(int i) {
		RoomMemberVO roomMember = roomMember_mapper.select(i);
		return roomMember;
	}

	// <등록>
	public int create(RoomMemberVO roomMemberVO) {
		int affectRowCount = roomMember_mapper.insert(roomMemberVO);
		return affectRowCount;
	}

	// <삭제>
	public int delete(int room_id) {
		int affectRowCount = roomMember_mapper.delete(room_id);
		return affectRowCount;
	}

	// <수정>
	public int update(RoomMemberVO roomMemberVO) {
		int affectRowCount = roomMember_mapper.update(roomMemberVO);
		return affectRowCount;
	}

}
