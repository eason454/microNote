package com.asiainfo.service.user.interfaces;

/**
 * Created by eason on 2017/2/21.
 */
public interface EncryptionService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
