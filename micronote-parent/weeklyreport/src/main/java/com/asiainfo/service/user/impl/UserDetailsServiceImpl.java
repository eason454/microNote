package com.asiainfo.service.user.impl;

import com.asiainfo.domain.entity.user.UserProfile;
import com.asiainfo.service.user.interfaces.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 提供给security调用的查询用户信息的service
 * Created by eason on 2017/2/21.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserProfileService userProfileService;
    @Autowired
    @Qualifier("userProfileToUserDetails")
    private Converter<UserProfile,UserDetails> converter;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileService.findByUsername(username);
        return converter.convert(userProfile);
    }
}
