package com.asiainfo.service.weeklyreport.interfaces;

import java.util.List;

import com.asiainfo.domain.entity.user.User;

/**
 * Created by eason on 2017/1/6.
 */
public interface UserService {
    public List<User> queryUsers();
}
