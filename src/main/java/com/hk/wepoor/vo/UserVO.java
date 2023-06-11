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
	int userSuccess;
	String userSeqNo ;
	String accessToken ;
	String refreshToken ;
	
	
	public UserVO(UserVO reqVO, String[] str) {
		this.userNo = reqVO.getUserNo();
		this.userId = reqVO.getUserId();
		this.userPwd = reqVO.getUserPwd();
		this.userName = reqVO.getUserName();
		this.userNickname = reqVO.getUserNickname();
		this.userPhone = reqVO.getUserPhone();
		this.userPoint = reqVO.getUserPoint();
		this.userSuccess = reqVO.getUserSuccess();
		this.userSeqNo = str[0];
		this.accessToken = str[1];
		this.refreshToken = str[2];
	}
	
	public static UserVO User(UserVO reqVO, String[] str){
		return new UserVO(
				reqVO.getUserNo(),
				reqVO.getUserId(),
				reqVO.getUserPwd(),
				reqVO.getUserName(),
				reqVO.getUserNickname(),
				reqVO.getUserPhone(),
				reqVO.getUserPoint(),
				reqVO.getUserSuccess(),
				str[0],
				str[1],
				str[2]
				);
	}
}