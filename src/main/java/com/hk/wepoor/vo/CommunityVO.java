package com.hk.wepoor.vo;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityVO {
	
	int commu_id;
	int top_commu_id;
	int user_no;
	String commu_content;
	Date create_time;

}
