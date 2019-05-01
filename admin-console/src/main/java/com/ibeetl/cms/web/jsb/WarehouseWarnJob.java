package com.ibeetl.cms.web.jsb;

import com.ibeetl.cms.service.WarehouseWarnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class WarehouseWarnJob {

    @Autowired
    private WarehouseWarnService warehouseWarnService;

    @Scheduled(cron = "0 30 11 * * ?")
    public void queryWarehouseWarn(){
        log.info("开始进入预警检测");
        warehouseWarnService.queryWarehouseWarn();
    }
}
