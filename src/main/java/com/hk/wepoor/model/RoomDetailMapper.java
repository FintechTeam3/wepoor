package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.wepoor.vo.RoomDetailVO;
import com.hk.wepoor.vo.UserVO;

@Mapper
public interface RoomDetailMapper {

	public List<RoomDetailVO> getInfo(int userNo);
	
	int getUserByUserSuccess(int userNo);
}
