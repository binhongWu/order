package com.ibeetl.utils.password;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PasswordManagerImpl implements PasswordManager {


    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public PasswordBO generatePassword(String salt, String newPassword) {
        if (StringUtils.isBlank(newPassword)) {
            newPassword = securityProperties.getSysUserAccount().getPassword().getDefaultPassword();
        }
        if (StringUtils.isBlank(salt)) {
            salt = generateSalt();
        }
        String encryptedPassword = encryptPassword(salt, newPassword);
        return new PasswordBO(salt, encryptedPassword);
    }

    /**
     * 生成盐值
     *
     * @return
     */
    private String generateSalt() {
        SecureRandomNumberGenerator numberGenerator = new SecureRandomNumberGenerator();
        ByteSource byteSource = numberGenerator.nextBytes();
        return byteSource.toHex();
    }


    /**
     * 生成用户密码
     *
     * @param privateSalt 用户私盐
     * @param newPassword 新的密码
     * @return 密码摘要
     */
    @Override
    public String encryptPassword(String privateSalt, String newPassword) {
        SecurityProperties.Password userPasswordConfig = securityProperties.getSysUserAccount().getPassword();
        String staticSalt = userPasswordConfig.getStaticSalt();
        String hashAlgorithmName = userPasswordConfig.getAlgorithm();
        String encryptSalt = getEncryptSalt(staticSalt, privateSalt);
        SimpleHash hashService = new SimpleHash(hashAlgorithmName, newPassword, encryptSalt, userPasswordConfig.getHashIterations());
        return hashService.toBase64();
    }

    /**
     * 返回摘要盐
     *
     * @param staticSalt
     * @param privateSalt
     * @return
     */
    private String getEncryptSalt(String staticSalt, String privateSalt) {
        String encryptSalt = staticSalt == null ? "" : staticSalt;
        if (privateSalt != null) {
            encryptSalt += privateSalt;
        }
        return encryptSalt;
    }
}
