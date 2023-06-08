package com.hk.wepoor.vo;

import java.util.Date;

import lombok.Data;

@Data
public class RoomDetailVO {
	String user_nickname;
	String cate_name;
	String cate_cost;
	String cate_date;
	Date start_date;
	Date end_date;
	String status = "";
}
