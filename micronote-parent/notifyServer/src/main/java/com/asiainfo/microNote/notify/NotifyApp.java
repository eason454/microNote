package com.asiainfo.microNote.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 启动通知服务
 * @author yi
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class NotifyApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(NotifyApp.class, args);
    }
}
