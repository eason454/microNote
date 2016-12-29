package com.asiainfo.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.circuitbreaker.CircuitBreaker;

@RestController
public class GatewayController {
	@Autowired
	CircuitBreaker cb;
	@RequestMapping(path="/testCircuit")
	public String testCircuit(){
		return (String) cb.getStores(new HashMap<String, Object>());
	}
}
