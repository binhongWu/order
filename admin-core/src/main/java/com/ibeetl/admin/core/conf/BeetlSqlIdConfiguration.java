package com.ibeetl.admin.core.conf;

import org.beetl.sql.core.IDAutoGen;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Configuration
public class BeetlSqlIdConfiguration {
    @Autowired
    private SQLManager sqlManager;

    @PostConstruct
    public void addIdUUIDAutoGen() {
        sqlManager.addIdAutonGen("uuid", new IDAutoGen() {
            public Object nextID(String s) {
                return UUID.randomUUID().toString().replaceAll("-","");
            }
        });
    }
}
//实体类代码加上@AssignID("uuid") @AssignID("uuid") public String getId(){return id;}