package com.asiainfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.asiainfo.filter.RouteFilter;
import com.asiainfo.filter.SampleFilter;
import com.asiainfo.filter.WeeklyReportFilter;

@SpringBootApplication
@EnableSidecar
@EnableHystrixDashboard
@EnableFeignClients
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	@Bean
	public SampleFilter sampleFilter(){
		return new SampleFilter();
	}
	@Bean
	public RouteFilter routeFilter(){
		return new RouteFilter();
	}
	@Bean
	public WeeklyReportFilter weeklyReportFilter(){
		return new WeeklyReportFilter();
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){return builder.build();}
}
