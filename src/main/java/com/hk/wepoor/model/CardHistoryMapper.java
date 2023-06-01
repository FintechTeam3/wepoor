package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.hk.wepoor.vo.CardHistoryVO;


@Mapper
public interface CardHistoryMapper {
	
	List<CardHistoryVO> selectAll(); // 카드내역 전체조회
	
	
}
