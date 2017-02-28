package com.asiainfo.repository.user;

import com.asiainfo.domain.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by eason on 2017/2/21.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);

}
