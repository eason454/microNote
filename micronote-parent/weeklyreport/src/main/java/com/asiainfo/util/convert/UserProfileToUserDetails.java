package com.asiainfo.util.convert;

import com.asiainfo.domain.entity.user.UserProfile;
import com.asiainfo.service.user.impl.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by eason on 2017/2/21.
 */
@Component("userProfileToUserDetails")
public class UserProfileToUserDetails implements Converter<UserProfile,UserDetails> {
    @Override
    public UserDetails convert(UserProfile source) {
        UserDetailsImpl userDetails=new UserDetailsImpl();
        if (source != null) {
            userDetails.setUsername(source.getUsername());
            userDetails.setPassword(source.getEncryptedPassword());
            userDetails.setEnabled(source.getEnabled());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            source.getRoles().stream().forEach(e->authorities.add(new SimpleGrantedAuthority(e.getRole())));
            userDetails.setAuthorities(authorities);

        }
        return userDetails;
    }
}
