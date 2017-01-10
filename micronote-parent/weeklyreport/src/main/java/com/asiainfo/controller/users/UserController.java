package com.asiainfo.controller.users;

import com.asiainfo.domain.entity.user.User;
import com.asiainfo.service.weeklyreport.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private UserService userService;
    @RequestMapping(path = "/queryUsers",method = RequestMethod.GET)
    public List<User> queryUsers(){
       return userService.queryUsers();
    }
    public User createUser(User user){
        return userService.createUser(user);
    }

    @RequestMapping(path = "/queryusersbypages", method = RequestMethod.GET)
    public Page<User> queryUsersByPages(Pageable pageable){
        return userService.queryUserByPages(pageable);
    }
}
