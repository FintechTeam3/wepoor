package com.hk.wepoor.interceptor;



import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import okhttp3.logging.HttpLoggingInterceptor;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

	private final Logger logger =  LoggerFactory.getLogger(HttpLoggingInterceptor.class);
	
	
	//controller로 가기전 수행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		logger.info("[preHandle] preHandle is performed");
		logger.info("[preHandle] request: {}", request);
		logger.info("[preHandle] request path info: {}", request.getPathInfo());
		logger.info("[preHandle] request header names: {}", request.getHeaderNames());
		logger.info("[preHandle] request request URL: {}", request.getRequestURL());
		logger.info("[preHandle] request request URI: {}", request.getRequestURI());
		
		
		response.sendRedirect(request.getContextPath() + "/login_page");
		

		return false; //다음 진행을 나타냄 true = 통과, false=거부
	}
	
}
