package com.hk.wepoor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MyApiClient {

	@Bean
	public WebClient webCreate() {
		return WebClient.create();
	}

}
