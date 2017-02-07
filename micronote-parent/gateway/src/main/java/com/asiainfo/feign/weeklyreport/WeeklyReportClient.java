package com.asiainfo.feign.weeklyreport;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asiainfo.domain.User;

/**
 * Created by eason on 2017/2/6.
 */
@FeignClient("weeklyreport")
public interface WeeklyReportClient {
    @RequestMapping(value = "/microNote/queryUser/{user_id}",method=RequestMethod.GET)
    User getUser(@PathVariable("user_id") String userId);
    @RequestMapping(path="/microNote/createUser",method=RequestMethod.POST)
    User createUser(User user);
    
}
