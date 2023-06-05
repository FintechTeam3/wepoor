package com.hk.wepoor.vo;

import lombok.Getter;

@Getter
public class KakaoTokenVO {
	private String access_token;
	private String refresh_token;
	private String token_type;
	private String expires_in;
	private String scope;
	
}
