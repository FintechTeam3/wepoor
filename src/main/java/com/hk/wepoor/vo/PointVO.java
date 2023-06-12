package com.hk.wepoor.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointVO {
	
	int point_id;
	int user_no;
	String point_date;
	int point_price;
	
	// 방 내역
	String cate_name;
}
