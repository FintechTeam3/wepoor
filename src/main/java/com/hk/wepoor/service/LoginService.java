package com.hk.wepoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.hk.wepoor.config.MyApiClient;
import com.hk.wepoor.jwt.Jwt;
import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.vo.KakaoTokenVO;
import com.hk.wepoor.vo.UserVO;

@Service
public class LoginService {

	@Autowired
	UserMapper mapper;
	
	@Autowired
	MyApiClient myapiclient;
	
	
	public String loginCheck(String userId, String pwd) {
		String jwtToken = "0";
		
		UserVO member = mapper.getUserByUserId(userId);
		String changedPwd = mapper.HashedPwd(pwd);

		if (member == null) {
			return null;
		} else {
			if (changedPwd.equals(member.getUserPwd())) {
				jwtToken = Jwt.createJwt(userId, member.getUserNo());

			} else {
				return null;
			}
		}
		return jwtToken;
	}
	
	
	
	public KakaoTokenVO getAccessToken(String code) {
		WebClient webclient = myapiclient.webCreate();
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", "e4e9f889e1bb27e6af7af657094f2743");
        formData.add("redirect_uri", "http://itrepository.co.kr/kakaoLogin");
        formData.add("code", code);
        formData.add("client_secret", "s8i5uCnOFtBMvYZYmhffq10NRP2n9LF8");
        
        KakaoTokenVO result = webclient.post()
         .uri("https://kauth.kakao.com/oauth/token")
         .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
         .bodyValue(formData)
         .retrieve()
         .bodyToMono(new ParameterizedTypeReference<KakaoTokenVO>(){})
         .block();
     
		return result;
	}
	
	
	
	public String getKakaoUser(String accessToken) {
		WebClient webclient = myapiclient.webCreate();

		String result = webclient.post()
				.uri("https://kapi.kakao.com/v2/user/me?secure_resource=true")
				.header("Authorization", "Bearer " + accessToken)
				.header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
				.retrieve()
				.bodyToMono(String.class)
				.block();

		return result;

	}
	
	public String kakaoLogout(Long targetId, String accessToken) {
	    WebClient webclient = myapiclient.webCreate();
	    String requestBody = String.format("{\"target_id_type\": \"user_id\", \"target_id\": %d}", targetId);
	    
	    String result = webclient.post()
	            .uri("https://kapi.kakao.com/v1/user/logout")
	            .header("Authorization", "Bearer " + accessToken)
	            .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
	            .bodyValue(requestBody)
	            .retrieve()
	            .bodyToMono(String.class)
	            .block();
	   
	   return result;
	}
	
	
	public String kakaoSignout(Long targetId, String accessToken) {

		WebClient webclient = myapiclient.webCreate();
		String requestBody = String.format("{\"target_id_type\": \"user_id\", \"target_id\": %d}", targetId);

		String result = webclient.post()
				.uri("https://kapi.kakao.com/v1/user/unlink")
				.header("Authorization", "Bearer " + accessToken)
				.header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
				.bodyValue(requestBody)
				.retrieve()
				.bodyToMono(String.class).block();

		return result;
	}
	
	
	
}
