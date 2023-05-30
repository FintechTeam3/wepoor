package com.hk.wepoor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	
	int user_no;
	String user_id;
	String user_pwd;
	String user_name;
	String user_nickname;
	String user_phone;
	int user_point;
	
}
