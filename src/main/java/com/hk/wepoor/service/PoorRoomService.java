package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.PoorRoomMapper;
import com.hk.wepoor.vo.PoorRoomVO;

@Service
public class PoorRoomService {
	
	@Autowired
	PoorRoomMapper poorroom_mapper ;
	
	public List<PoorRoomVO> selectAll(){
		
			List<PoorRoomVO> list = poorroom_mapper.selectAll();
			return list;
	}
	public PoorRoomVO select(int i) {
		PoorRoomVO poorroom = poorroom_mapper.select(i);
		return poorroom;
	}
	
	public int create(PoorRoomVO poorroomVO) {
		int affectRowCount = poorroom_mapper.insert(poorroomVO);
		return affectRowCount;
	}
	
	public int delete(int room_id) {
		int affectRowCount = poorroom_mapper.delete(room_id);
		return affectRowCount;
	}
	
	public int update(PoorRoomVO poorRoomVO) {
		int affectRowCount = poorroom_mapper.update(poorRoomVO);
		return affectRowCount;
	}
	
}
