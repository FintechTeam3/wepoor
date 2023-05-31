package com.hk.wepoor.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class Jwt {

	public static String createJwt(String userId, String secretKey, Long expiredMs) {
		// payload저장
		Claims claims = Jwts.claims();
		
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		claims.put("userId", userId);
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis())) // 발행일
				.setExpiration(new Date(System.currentTimeMillis() + expiredMs)) // 만료일
				.signWith(key) // 사인할 암호알고리즘설정
				.compact();

	}

	public static boolean isValidToken(String jwtToken) {
	    
		String random256BitKey = "helloworldwepoortoken1234eqweqweqeqeqweq";
	    SecretKey secretKey = Keys.hmacShaKeyFor(random256BitKey.getBytes());
	    JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey.getEncoded()).build();
	    Jws<Claims> claimsJws = parser.parseClaimsJws(jwtToken);
	    System.out.println(claimsJws);
	    return true;
	}
	
	
	
}
