package com.hk.wepoor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hk.wepoor.interceptor.JwtTokenInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JwtTokenInterceptor())
				.addPathPatterns("/test/**")
				.excludePathPatterns("/join");
		
	}

}
