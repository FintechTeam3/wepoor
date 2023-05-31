package com.hk.wepoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hk.wepoor.jwt.Jwt;
import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.vo.UserVO;

@Service
public class LoginService {

	@Autowired
	UserMapper mapper;

//	@Value("${jwt.secret}")
	private String secretKey="helloworldwepoortoken1234eqweqweqeqeqweq";

	private Long expiredMs = 1000 * 60 * 60l; // 1 = 0.001초 => 1000 = 1초

	public String loginCheck(String userId, String pwd) {
		String jwtToken = "0";

		UserVO member = mapper.getUserByUserId(userId);
		String changedPwd = mapper.HashedPwd(pwd);
		
		if(member == null) {
			return null;
		}else {
		if (changedPwd.equals(member.getUserPwd())) {
			jwtToken = Jwt.createJwt(userId, secretKey, expiredMs);

		}
		}
		return jwtToken;
	}
}
