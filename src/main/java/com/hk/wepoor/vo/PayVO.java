package com.hk.wepoor.vo;

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

}
