package com.ibeetl.utils.password;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class SecurityProperties {

    /**
     * 是否启用
     */
    private boolean enable = true;

    /**
     * 应用编码，默认为运营后台
     */
    private String appCode = "1";


    /**
     * 租户用户认证安全设置
     */
    private SysUserAccount sysUserAccount = new SysUserAccount();

    /**
     * 租户用户认证安全配置
     */
    @Data
    public static class SysUserAccount {

        /**
         * 加密信息
         */
        private SecurityProperties.Password password = new SecurityProperties.Password();
    }

    /**
     * 密码信息
     */
    @Data
    public static class Password {

        /**
         * 公盐
         */
        private String staticSalt = "VHLVr4NMrcpTVj81Gj2E";

        /**
         * Hash次数
         */
        private int hashIterations = 1;

        /**
         * 初始密码
         */
        private String defaultPassword = "ckbao9966";

        /**
         * 算法
         */
        private String algorithm = "SHA-512";

    }
}
