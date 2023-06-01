package com.hk.wepoor.vo;

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
	
}
