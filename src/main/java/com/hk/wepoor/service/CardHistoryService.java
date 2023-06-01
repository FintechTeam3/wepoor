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
	
	
}
