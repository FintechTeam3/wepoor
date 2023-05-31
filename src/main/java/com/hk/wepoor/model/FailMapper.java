package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.wepoor.vo.AccountVO;
import com.hk.wepoor.vo.FailVO;

@Mapper
public interface FailMapper {
	
	List<FailVO> selectAll();
	
	int insert(FailVO failVO);
}
