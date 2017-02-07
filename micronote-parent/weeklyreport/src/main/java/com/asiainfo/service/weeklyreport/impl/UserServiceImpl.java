package com.asiainfo.service.weeklyreport.impl;

import com.asiainfo.domain.entity.user.User;
import com.asiainfo.repository.user.UserRepository;
import com.asiainfo.service.weeklyreport.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by eason on 2017/1/6.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> queryUserByPages(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User queryUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User queryUserByNumber(String staffNum) {
        return userRepository.findByUserNumber(staffNum);
    }

    @Override
    public List<User> queryUsers() {
        return userRepository.findAll();
    }
}
