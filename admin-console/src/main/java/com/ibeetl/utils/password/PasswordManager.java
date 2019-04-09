package com.ibeetl.utils.password;

/**
 * 用户密码管理服务
 */
public interface PasswordManager {


    /**
     * 生成密码服务
     */
    PasswordBO generatePassword(String salt, String newPassword);

    String encryptPassword(String privateSalt, String newPassword);
}
