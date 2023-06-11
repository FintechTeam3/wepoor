package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.RoomDetailMapper;
import com.hk.wepoor.vo.RoomDetailVO;
import com.hk.wepoor.vo.UserVO;

@Service
public class RoomDetailService {

	@Autowired
	RoomDetailMapper mapper;

	public List<RoomDetailVO> selectAll(int userNo) {

		return mapper.getInfo(userNo);
	}
	
	public String userSuccess(int userNo){
		// 사용자의 정보를 가져온다.
		int num = mapper.getUserByUserSuccess(userNo);
		
		String result = "실패";
		
		if(num == 1) {
			result = "성공";
		}
		System.out.println("@@@@@@@@@@@@@"+result);
		return result;
	}

}
