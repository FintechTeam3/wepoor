package com.hk.wepoor.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hk.wepoor.jwt.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import okhttp3.logging.HttpLoggingInterceptor;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(HttpLoggingInterceptor.class);

	// controller로 가기전 수행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("[preHandle] preHandle is performed");
		logger.info("[preHandle] request: {}", request);
		logger.info("[preHandle] request path info: {}", request.getPathInfo());
		logger.info("[preHandle] request header names: {}", request.getHeaderNames());
		logger.info("[preHandle] request request URL: {}", request.getRequestURL());
		logger.info("[preHandle] request request URI: {}", request.getRequestURI());

		Cookie[] cookies = request.getCookies();
		String jwtToken = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				logger.info(cookieName);
				if (cookieName.equals("jwtToken")) {
					jwtToken = c.getValue();
					break;
				}
			}
		}

		// 토큰이 없으면
		if (jwtToken == null) {
			response.sendRedirect(request.getContextPath() + "/login_page");
			logger.info("[preHandle] no token");
			return false;
		}

		try {

			Jws<Claims> claims = Jwt.parseToken(jwtToken);
			String userId = (String) claims.getBody().get("userId");
			int userNo = (int) claims.getBody().get("userNo");
			HttpSession session = request.getSession();
			session.setAttribute("userNo", userNo);
			session.setAttribute("userId", userId);
	
			logger.info("[preHandle] login success");
			return true; // 다음 진행을 나타냄 true = 통과, false=거부
			
		} catch (ExpiredJwtException e) {
			// 토큰이 유효하지 않다면
			response.sendRedirect(request.getContextPath() + "/login_page");
			logger.info("[preHandle] invalid token");
			return false;
		}

	}

}
