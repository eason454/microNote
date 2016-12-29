package com.asiainfo.circuitbreaker;

import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class CircuitBreaker {
	private Random random=new Random();
	private static Logger log=LoggerFactory.getLogger(CircuitBreaker.class);
	@HystrixCommand(fallbackMethod = "defaultStores")
    public Object getStores(Map<String, Object> parameters) {
        //do stuff that might fail
		int i=random.nextInt(10);
		if(i>3){
			throw new RuntimeException("call service fail");
		}
		return "eason:"+i;
    }

    public Object defaultStores(Map<String, Object> parameters) {
    	log.info("服务挂掉了");
        return "service has down";
    }
}
