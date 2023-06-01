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
	
	// 전체조회
	public List<FailVO> selectAll() {
		List<FailVO> list = failmapper.selectAll();
		return list;
	}
	
	// 조회 //fail_id = 7 인사람 조회하여 보여주세요.
	public FailVO select(int fail_id) {
		FailVO failSelect= failmapper.select(fail_id); //failSelect에 담았음
		return failSelect;		
	}
	
	
	// 실패 입력
	public int create(FailVO failVO) {
		int failCount=failmapper.insert(failVO); //실패 입력 행 몇개야?
		return failCount;
	}
	
	// 실패 삭제 //삭제 갯수 몇개야 fail_id=7인 거 삭제해 -> 근데 그게 몇개야?
	public int delete(int fail_id) {
		int failDeleteCount=failmapper.delete(fail_id);
		return failDeleteCount;
	}
}
