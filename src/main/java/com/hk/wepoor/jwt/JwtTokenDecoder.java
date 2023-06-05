package com.hk.wepoor.jwt;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenDecoder {

    Key secretKey;
    public static Claims decodeJwtToken(String jwtToken, String secretKey) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims;
    }
}
