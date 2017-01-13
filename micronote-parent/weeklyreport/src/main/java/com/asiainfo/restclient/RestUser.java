package com.asiainfo.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Created by eason on 2017/1/11.
 */
public class RestUser {
    @Autowired
    RestTemplate restTemplate;
}
