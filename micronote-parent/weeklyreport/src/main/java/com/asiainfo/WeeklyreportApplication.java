package com.asiainfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WeeklyreportApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeeklyreportApplication.class, args);
	}
}
