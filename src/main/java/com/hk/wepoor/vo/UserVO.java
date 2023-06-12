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
	
	
	public UserVO(UserVO reqVO, TokenVO tokenVO) {
		this.userNo = reqVO.getUserNo();
		this.userId = reqVO.getUserId();
		this.userPwd = reqVO.getUserPwd();
		this.userName = reqVO.getUserName();
		this.userNickname = reqVO.getUserNickname();
		this.userPhone = reqVO.getUserPhone();
		this.userPoint = reqVO.getUserPoint();
		this.userSuccess = reqVO.getUserSuccess();
		this.userSeqNo = tokenVO.getUserSeqNo();
		this.accessToken = tokenVO.getAccessToken();
		this.refreshToken = tokenVO.getRefreshToken();
	}
	
	public static UserVO User(UserVO reqVO, TokenVO tokenVO){
		return new UserVO(
				reqVO.getUserNo(),
				reqVO.getUserId(),
				reqVO.getUserPwd(),
				reqVO.getUserName(),
				reqVO.getUserNickname(),
				reqVO.getUserPhone(),
				reqVO.getUserPoint(),
				reqVO.getUserSuccess(),
				tokenVO.getUserSeqNo(),
				tokenVO.getAccessToken(),
				tokenVO.getRefreshToken()
				);
	}
}