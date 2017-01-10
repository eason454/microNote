package com.asiainfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 评论应用
 * @author yi
 */
@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
public class CommentsApp 
{
    public static void main( String[] args )
    {
       SpringApplication.run(CommentsApp.class, args);
    }
}
