package com.hk.wepoor.vo;

import lombok.Data;

@Data
public class TokenVO {

	private String userSeqNo;
	private String accessToken;
	private String refreshToken;
}