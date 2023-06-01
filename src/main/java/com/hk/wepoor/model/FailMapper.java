package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.wepoor.vo.AccountVO;
import com.hk.wepoor.vo.FailVO;

@Mapper
public interface FailMapper {
	
	List<FailVO> selectAll(); // 전체조회
	
	FailVO select(int fail_id); //조회 fail_id = 7 인사람 조회하여 보여주세요. 
	
	int insert(FailVO failVO); //삽입 (실패 입력 행 몇개야?)
	
	int update(FailVO failVO);//수정 (데이터 수정)
	
	int delete(int fail_id); //삭제
	
	
}
