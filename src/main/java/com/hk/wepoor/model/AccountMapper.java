package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.wepoor.vo.AccountVO;

@Mapper
public interface AccountMapper {
	
	List<AccountVO> selectAll();
	AccountVO select(int i);
	int insert(AccountVO accountVO);
	int delete(int user_no);
	int update(AccountVO accountVO);
	
}
