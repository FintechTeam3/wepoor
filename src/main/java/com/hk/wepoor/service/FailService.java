package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.FailMapper;
import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.vo.FailVO;
import com.hk.wepoor.vo.UserVO;

@Service
public class FailService {
	
	@Autowired
	FailMapper failmapper;
	
	public List<FailVO> selectAll() {
		List<FailVO> list = failmapper.selectAll();
		return list;
	}
	
	public int create(FailVO failVO) {
		int failCount=failmapper.insert(failVO);
		return failCount;
	}
}
