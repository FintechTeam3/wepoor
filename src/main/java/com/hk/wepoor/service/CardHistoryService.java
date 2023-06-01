package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.CardHistoryMapper;

import com.hk.wepoor.vo.CardHistoryVO;


@Service
public class CardHistoryService {
	
	@Autowired
	CardHistoryMapper cardmapper;
	
	// 전체조회
	public List<CardHistoryVO> selectAll() {
		List<CardHistoryVO> list = cardmapper.selectAll();
		return list;
	}
	
	//하나만 조회
	public CardHistoryVO select(int card_id) {
		CardHistoryVO cardSelect= cardmapper.select(card_id);
		return cardSelect;
	}
	
	//삽입
	public int create(CardHistoryVO cardVO) {
		int cardHistoryCount=cardmapper.insert(cardVO);
		return cardHistoryCount;
	}
	
	//수정
	public int update(CardHistoryVO cardVO) {
		int cardUpdateCount=cardmapper.update(cardVO);
		return cardUpdateCount;
	}
	
	//삭제
	public int delete(int card_id) {
		int cardDeleteCount=cardmapper.delete(card_id);
		return cardDeleteCount;
	}
}
