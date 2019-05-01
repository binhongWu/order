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
    private Long warningId;
    @Query(name = "预警时间", display = true)        
    private Date alarmt;
    @Query(name = "绘本编码", display = true)        
    private String code;
    public Long getWarningId(){
        return  warningId;
    }
    public void setWarningId(Long warningId ){
        this.warningId = warningId;
    }
    public Date getAlarmt(){
        return  alarmt;
    }
    public void setAlarmt(Date alarmt ){
        this.alarmt = alarmt;
    }
    public String getCode(){
        return  code;
    }
    public void setCode(String code ){
        this.code = code;
    }
 
}
