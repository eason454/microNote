package com.asiainfo.controller.users;

import com.asiainfo.domain.entity.user.User;
import com.asiainfo.service.weeklyreport.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by eason on 2017/1/6.
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping(path = "/queryUsers",method = RequestMethod.GET)
    public List<User> queryUsers(){
       return userService.queryUsers();
    }
    @RequestMapping(path="/createUser",method=RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(path = "/queryusersbypages", method = RequestMethod.GET)
    public List<User> queryUsersByPages(Pageable pageable){
        return userService.queryUserByPages(pageable).getContent();
    }

    @RequestMapping(path = "/queryUser/{user_id}", method = RequestMethod.GET)
    public User queryUserById(@PathVariable("user_id") String userId){
        return userService.queryUserById(userId);
    }
}
