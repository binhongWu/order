package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *WarehouseWarn查询
 */
public class WarehouseWarnQuery extends PageParam {
    @Query(name = "预警单号", display = true)        
    private String warningId;
    @Query(name = "预警时间", display = true)        
    private Date alarmt;
    @Query(name = "下一次预警时间", display = true)        
    private Date nextAlarmt;
    @Query(name = "预警状态", display = true,type=Query.TYPE_DICT,dict="warehouse_warn_check")
    private String check;
    public String getWarningId(){
        return  warningId;
    }
    public void setWarningId(String warningId ){
        this.warningId = warningId;
    }
    public Date getAlarmt(){
        return  alarmt;
    }
    public void setAlarmt(Date alarmt ){
        this.alarmt = alarmt;
    }
    public Date getNextAlarmt(){
        return  nextAlarmt;
    }
    public void setNextAlarmt(Date nextAlarmt ){
        this.nextAlarmt = nextAlarmt;
    }
    public String getCheck(){
        return  check;
    }
    public void setCheck(String check ){
        this.check = check;
    }
 
}
