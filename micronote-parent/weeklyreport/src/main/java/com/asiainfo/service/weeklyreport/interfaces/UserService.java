package com.asiainfo.service.weeklyreport.interfaces;

import java.util.List;

import com.asiainfo.domain.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by eason on 2017/1/6.
 */
public interface UserService {
    List<User> queryUsers();
    User createUser(User user);
    Page<User> queryUserByPages(Pageable pageable);
    User queryUserById(String id);
}
