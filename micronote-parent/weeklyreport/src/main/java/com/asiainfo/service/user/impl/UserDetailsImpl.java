package com.asiainfo.service.user.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by eason on 2017/2/21.
 */
public class UserDetailsImpl implements UserDetails {
    //simpleGrantedAuthority 简单的授权的实现，Think of an authority as being a “permission” or a “right” typically expressed as strings.
    private Collection<SimpleGrantedAuthority> authorities;
    private String username;
    private String password;
    private Boolean enabled = true;

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
