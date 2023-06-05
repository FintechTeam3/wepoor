package com.hk.wepoor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.*")
public class WepoorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WepoorApplication.class, args);

	}

}
