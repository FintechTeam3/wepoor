package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	UserMapper usermapper;
	
	public List<UserVO> selectAll() {
		List<UserVO> list = usermapper.selectAll();
		return list;
	}
	
}
