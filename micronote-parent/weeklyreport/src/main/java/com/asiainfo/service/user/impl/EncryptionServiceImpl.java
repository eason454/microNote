package com.asiainfo.service.user.impl;

import com.asiainfo.service.user.interfaces.EncryptionService;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eason on 2017/2/21.
 */
@Service
public class EncryptionServiceImpl implements EncryptionService{
    @Autowired
    private StrongPasswordEncryptor strongEncryptor;
    @Override
    public String encryptString(String input) {
        return strongEncryptor.encryptPassword(input);
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return strongEncryptor.checkPassword(plainPassword,encryptedPassword);
    }
}
