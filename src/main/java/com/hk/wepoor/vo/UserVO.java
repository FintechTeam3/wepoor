package com.hk.wepoor.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVO {
	
	int userNo;
	int cateId;
	String userId;
	String userPwd;
	String userName;
	String userNickname;
	String userPhone;
	int userPoint;
	String userSeqNo ;
	String accessToken ;
	String refreshToken ;
	
}
