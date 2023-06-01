package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.hk.wepoor.vo.CardHistoryVO;


@Mapper
public interface CardHistoryMapper {
	
	List<CardHistoryVO> selectAll(); // 카드내역 전체조회
	
	CardHistoryVO select(int card_id); //fail_id로 하나만 조회 (card_id=5인사람 조회해서 보여줘)
	
	int insert(CardHistoryVO cardVO); //삽입
	
	int update(CardHistoryVO cardVO); //수정(결제수단변경 예시)
	
	
	
}
