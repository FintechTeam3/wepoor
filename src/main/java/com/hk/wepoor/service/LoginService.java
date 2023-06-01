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

	public String loginCheck(String userId, String pwd) {
		String jwtToken = "0";

		UserVO member = mapper.getUserByUserId(userId);
		String changedPwd = mapper.HashedPwd(pwd);

		if (member == null) {
			return null;
		} else {
			if (changedPwd.equals(member.getUserPwd())) {
				jwtToken = Jwt.createJwt(userId, member.getUserNickname());

			}
		}
		return jwtToken;
	}
}
