package com.asiainfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;
import org.springframework.context.annotation.Bean;

import com.asiainfo.filter.SampleFilter;

@SpringBootApplication
@EnableSidecar
@EnableHystrixDashboard
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	@Bean
	public SampleFilter sampleFilter(){
		return new SampleFilter();
	}
}
