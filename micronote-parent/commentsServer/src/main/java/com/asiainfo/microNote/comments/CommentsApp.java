package com.asiainfo.microNote.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 评论应用
 * @author yi
 */
@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
@EnableFeignClients
public class CommentsApp 
{
    public static void main( String[] args )
    {
       SpringApplication.run(CommentsApp.class, args);
    }
}
