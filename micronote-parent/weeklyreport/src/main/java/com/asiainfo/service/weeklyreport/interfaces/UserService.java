package com.asiainfo.service.weeklyreport.interfaces;

import com.asiainfo.domain.entity.user.User;
import com.asiainfo.repository.weeklyreport.WeeklyReportRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by eason on 2017/1/6.
 */
public interface UserService {
    public List<User> queryUsers();
}
