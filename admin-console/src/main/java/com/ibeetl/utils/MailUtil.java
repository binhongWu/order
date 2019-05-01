package com.ibeetl.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Component;

/**
 * 邮件发送验证码
 * @author admin
 */
@Component
@Slf4j
public class MailUtil {
    /**
     * 发送邮箱验证码
     * @param emailAddress 收件人
     * @return
     */
    public boolean sendEmail(String emailAddress){
        try {
            //不用更改
            HtmlEmail email = new HtmlEmail();
            //需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setHostName("smtp.qq.com");
            email.setCharset("UTF-8");
            // 收件地址
            email.addTo(emailAddress);
            //此处填发件邮箱地址和用户名,用户名可以任意填写
            email.setFrom("1137428517@qq.com", "pjh");
            //此处填写发件邮箱地址和客户端授权码
            email.setAuthentication("1137428517@qq.com", "wbopqkypwierideb");
            //此处填写邮件名，邮件名可任意填写
            email.setSubject("爱书小屋仓库预警");
            //此处填写邮件内容
            email.setMsg("请登录系统查看预警！");
            email.send();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
