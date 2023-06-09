package com.hk.wepoor.vo;

import java.util.List;

import lombok.Data;

@Data
public class CardCodeVO {

	private String user_seq_no;
	private String user_name;
	List<CardCodesVO> inquiry_card_list;
}