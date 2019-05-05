package com.ibeetl.admin.console.conf;

import com.ibeetl.admin.core.util.HttpRequestLocal;
import com.ibeetl.cms.entity.CustomerInfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class PlatformService {


    /*当前用户会话*/
    public static final String ACCESS_CURRENT_USER = "customer:user";

    @Autowired
    HttpRequestLocal httpRequestLocal;

    public CustomerInfor getCurrentUser(){
        return (CustomerInfor) httpRequestLocal.getSessionValue(ACCESS_CURRENT_USER);
    }

    public void setLoginUser(CustomerInfor user){
        httpRequestLocal.setSessionValue(PlatformService.ACCESS_CURRENT_USER, user);
    }
}
