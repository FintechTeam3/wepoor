package com.hk.wepoor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hk.wepoor.interceptor.JwtTokenInterceptor;

@Configuration
public class JwtTokenConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JwtTokenInterceptor())
				.addPathPatterns("/category/**", "/community/**", "/mypage/**", "/pointdetail/**","/weekend/**",
								 "/roomdetail/**", "/paydetail/**", "/paycomplete/**", "/payhistory/**", "/gift/**");
//				.excludePathPatterns();
		
	}

}
