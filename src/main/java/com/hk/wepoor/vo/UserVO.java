package com.hk.wepoor.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVO {
	
	int userNo;
	String userId;
	String userPwd;
	String userName;
	String userNickname;
	String userPhone;
	int userPoint;
	
	
	public UserVO(UserVO reqVO) {
		this.userNo = reqVO.getUserNo();
		this.userId = reqVO.getUserId();
		this.userPwd = reqVO.getUserPwd();
		this.userName = reqVO.getUserName();
		this.userNickname = reqVO.getUserNickname();
		this.userPhone = reqVO.getUserPhone();
		this.userPoint = reqVO.getUserPoint();
	}
	
	public static UserVO User(UserVO reqVO){
		return new UserVO(
				reqVO.getUserNo(),
				reqVO.getUserId(),
				reqVO.getUserPwd(),
				reqVO.getUserName(),
				reqVO.getUserNickname(),
				reqVO.getUserPhone(),
				reqVO.getUserPoint()
				);
	}
}