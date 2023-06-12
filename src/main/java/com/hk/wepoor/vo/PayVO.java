package com.hk.wepoor.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayVO {
	
	int pay_id;
	int user_no;
	int cate_id;
	String pay_date;
	int pay_price;
	String pay_card;
	String pay_how;
	
	// 결제내역조회
	int usePoint;
	String cate_name;
	String cate_date;
	String cate_cost;
}
