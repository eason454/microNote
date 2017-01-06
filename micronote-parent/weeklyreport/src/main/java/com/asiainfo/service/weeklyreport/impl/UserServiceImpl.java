package com.asiainfo.service.weeklyreport.impl;

import com.asiainfo.domain.entity.user.User;
import com.asiainfo.repository.user.UserRepository;
import com.asiainfo.service.weeklyreport.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eason on 2017/1/6.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> queryUsers() {
        return userRepository.findAll();
    }
}
