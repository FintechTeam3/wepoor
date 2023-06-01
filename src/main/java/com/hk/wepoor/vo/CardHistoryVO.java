package com.hk.wepoor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardHistoryVO {
	
	int card_id;
	String card_company;
	String card_date;
	int card_cost;
	String card_store;
	
}
