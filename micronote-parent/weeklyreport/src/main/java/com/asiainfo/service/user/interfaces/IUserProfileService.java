package com.asiainfo.service.user.interfaces;

import com.asiainfo.domain.entity.user.UserProfile;
import com.asiainfo.service.ICRUDService;

/**
 * Created by eason on 2017/2/21.
 */
public interface IUserProfileService extends ICRUDService<UserProfile> {
    UserProfile findByUsername(String username);
}
