package com.hk.wepoor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftVO {
	
	int giftId;
	int user_no;
	String giftName;
	int giftPrice;
	
}