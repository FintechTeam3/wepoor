package com.hk.wepoor.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
	
	
	public UserVO(UserVO reqVO) {
		this.userNo = reqVO.getUserNo();
		this.cateId = reqVO.getCateId();
		this.userId = reqVO.getUserId();
		this.userPwd = reqVO.getUserPwd();
		this.userName = reqVO.getUserName();
		this.userNickname = reqVO.getUserNickname();
		this.userPhone = reqVO.getUserPhone();
		this.userPoint = reqVO.getUserPoint();
		this.userSeqNo = reqVO.getUserSeqNo();
		this.accessToken = reqVO.getAccessToken();
		this.refreshToken = reqVO.getRefreshToken();
	}
	
	public static UserVO User(UserVO reqVO){
		return new UserVO(
				reqVO.getUserNo(),
				reqVO.getCateId(),
				reqVO.getUserId(),
				reqVO.getUserPwd(),
				reqVO.getUserName(),
				reqVO.getUserNickname(),
				reqVO.getUserPhone(),
				reqVO.getUserPoint(),
				reqVO.getUserSeqNo(),
				reqVO.getAccessToken(),
				reqVO.getRefreshToken()
				);
	}
}