package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.AccountMapper;
import com.hk.wepoor.vo.AccountVO;

@Service
public class AccountService {
	
	@Autowired
	AccountMapper account_mapper;
	
	public List<AccountVO> selectAll() {
		List<AccountVO> list = account_mapper.selectAll();
		return list;
	}
	
	public AccountVO select(int user_no) {
		AccountVO accountVO = account_mapper.select(user_no);
		return accountVO;
	}
	
	public int create(AccountVO accountVO) {
		int affectRowCount = account_mapper.insert(accountVO);
		return affectRowCount;
	}
	
	public int delete(int user_no) {
		int affectRowCount = account_mapper.delete(user_no);
		return affectRowCount;
	}
	
	public int update(AccountVO accountVO) {
		int affectRowCount = account_mapper.update(accountVO);
		return affectRowCount;
	}
	
}
