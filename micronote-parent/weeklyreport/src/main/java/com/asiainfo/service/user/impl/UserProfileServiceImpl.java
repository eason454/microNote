package com.asiainfo.service.user.impl;

import com.asiainfo.domain.entity.user.UserProfile;
import com.asiainfo.repository.user.UserProfileRepository;
import com.asiainfo.service.user.interfaces.EncryptionService;
import com.asiainfo.service.user.interfaces.IUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by eason on 2017/2/21.
 */
@Service
public class UserProfileServiceImpl implements IUserProfileService {
    private UserProfileRepository userProfileRepository;
    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }
    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
    @Override
    public List<?> listAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile getById(Long id) {
        return userProfileRepository.findOne(id);
    }

    @Override
    public UserProfile saveOrUpdate(UserProfile domainObject) {
        if (domainObject.getPassword() != null) {
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));//encrypted
        }
        return userProfileRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userProfileRepository.delete(id);
    }

    @Override
    public UserProfile findByUsername(String username) {
        return userProfileRepository.findByUsername(username);
    }
}
