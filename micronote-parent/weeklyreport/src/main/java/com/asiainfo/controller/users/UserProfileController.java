package com.asiainfo.controller.users;

import com.asiainfo.domain.entity.user.Role;
import com.asiainfo.domain.entity.user.UserProfile;
import com.asiainfo.service.user.interfaces.IRoleService;
import com.asiainfo.service.user.interfaces.IUserProfileService;
import com.asiainfo.util.consts.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eason on 2017/2/28.
 */
@RestController
public class UserProfileController {
    @Autowired
    private IUserProfileService userProfileService;
    @Autowired
    private IRoleService roleService;
    @RequestMapping(path = "/UserProfile/createUser",method = RequestMethod.POST)
    public UserProfile createUser(@RequestBody UserProfile userProfile){
        //assign user role
       Role role= roleService.findByRole(CommonConst.Role.USER);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
       userProfile.setRoles(roles);
       return userProfileService.saveOrUpdate(userProfile);
    }
}
