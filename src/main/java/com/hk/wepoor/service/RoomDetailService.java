package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.RoomDetailMapper;
import com.hk.wepoor.vo.RoomDetailVO;

@Service
public class RoomDetailService {

	@Autowired
	RoomDetailMapper mapper;

	public List<RoomDetailVO> selectAll(int userNo) {

		return mapper.getInfo(userNo);
	}

}
