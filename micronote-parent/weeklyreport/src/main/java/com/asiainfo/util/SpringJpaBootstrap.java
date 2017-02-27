package com.asiainfo.util;

import com.asiainfo.domain.entity.user.Role;
import com.asiainfo.domain.entity.user.UserProfile;
import com.asiainfo.service.user.interfaces.IRoleService;
import com.asiainfo.service.user.interfaces.IUserProfileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by eason on 2017/2/21.
 */
@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IUserProfileService userProfileService;
    @Autowired
    private IRoleService roleService;
    private Logger log = Logger.getLogger(SpringJpaBootstrap.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//    loadUsers();
//    loadRoles();
//    assignUsersToUserRole();
//    assignUsersToAdminRole();
    }
    private void loadUsers(){
        UserProfile user1 = new UserProfile();
        user1.setUsername("user");
        user1.setPassword("user");
        userProfileService.saveOrUpdate(user1);

        UserProfile user2 = new UserProfile();
        user2.setUsername("admin");
        user2.setPassword("admin");
        userProfileService.saveOrUpdate(user2);
    }
    private void loadRoles(){
        Role role = new Role();
        role.setRole("USER");
        roleService.saveOrUpdate(role);
        log.info("Saved role" + role.getRole());
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
        log.info("Saved role" + adminRole.getRole());
    }

    private void assignUsersToUserRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<UserProfile> users = (List<UserProfile>) userProfileService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("USER")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("user")) {
                        user.addRole(role);
                        userProfileService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<UserProfile> users = (List<UserProfile>) userProfileService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("admin")) {
                        user.addRole(role);
                        userProfileService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
}
