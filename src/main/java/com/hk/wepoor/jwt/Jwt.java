package com.hk.wepoor.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class Jwt {

	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public static String createJwt(String userId, int userNo) {

		Long expiredMs = 1000 * 60 * 60l;
		

		// payload저장
		Claims claims = Jwts.claims();

		claims.put("userId", userId);
		claims.put("userNo", userNo);
		return Jwts.builder().setHeaderParam("type", "JWT").setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis())) // 발행일
				.setExpiration(new Date(System.currentTimeMillis() + expiredMs)) // 만료일
				.signWith(key) // 사인할 암호알고리즘설정
				.compact();

	}

	public static Jws<Claims> parseToken(String jwtToken) {

		SecretKey secretKeyByte = Keys.hmacShaKeyFor(key.getEncoded());
		JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKeyByte.getEncoded()).build();

		return parser.parseClaimsJws(jwtToken);

	}

}
