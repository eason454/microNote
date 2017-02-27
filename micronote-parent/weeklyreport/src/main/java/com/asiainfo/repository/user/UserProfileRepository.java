package com.asiainfo.repository.user;

import com.asiainfo.domain.entity.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by eason on 2017/2/21.
 */
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
    UserProfile     findByUsername(String name);
}
