package com.hk.wepoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.GiftMapper;
import com.hk.wepoor.vo.GiftVO;

@Service
public class GiftService {
	
	@Autowired
	GiftMapper giftMapper;
	
	public int create(GiftVO giftVO) {
		int affectRowCount = giftMapper.insert(giftVO);
		return affectRowCount;
	}
	
}
