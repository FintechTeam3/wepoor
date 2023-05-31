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
	
	public AccountVO select(int i) {
		AccountVO account = account_mapper.select(i);
		return account;
	}
	
}
