package com.hk.wepoor.model;

import org.apache.ibatis.annotations.Mapper;

import com.hk.wepoor.vo.GiftVO;

@Mapper
public interface GiftMapper {
	
	int insert(GiftVO giftVO);
	
}
