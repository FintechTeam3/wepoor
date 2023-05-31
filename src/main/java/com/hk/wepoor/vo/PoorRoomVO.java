package com.hk.wepoor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoorRoomVO {
	int room_id;
	int cate_id;
	String room_name;
	String room_date;
	int room_cost;
}
