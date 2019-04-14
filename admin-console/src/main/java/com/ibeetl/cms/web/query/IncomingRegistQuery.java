package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *IncomingRegist查询
 */
public class IncomingRegistQuery extends PageParam {
    @Query(name = "入库单号", display = true)        
    private String inRegistId;
    @Query(name = "入库日期", display = true)        
    private Date inRegistDate;
    @Query(name = "绘本编码", display = true)        
    private String code;
    @Query(name = "登记时间", display = true)        
    private Date registrateDate;
    @Query(name = "审核时间", display = true)        
    private Date checkDate;
    @Query(name = "审核状态", display = true)        
    private String checkStatus;
    public String getInRegistId(){
        return  inRegistId;
    }
    public void setInRegistId(String inRegistId ){
        this.inRegistId = inRegistId;
    }
    public Date getInRegistDate(){
        return  inRegistDate;
    }
    public void setInRegistDate(Date inRegistDate ){
        this.inRegistDate = inRegistDate;
    }
    public String getCode(){
        return  code;
    }
    public void setCode(String code ){
        this.code = code;
    }
    public Date getRegistrateDate(){
        return  registrateDate;
    }
    public void setRegistrateDate(Date registrateDate ){
        this.registrateDate = registrateDate;
    }
    public Date getCheckDate(){
        return  checkDate;
    }
    public void setCheckDate(Date checkDate ){
        this.checkDate = checkDate;
    }
    public String getCheckStatus(){
        return  checkStatus;
    }
    public void setCheckStatus(String checkStatus ){
        this.checkStatus = checkStatus;
    }
 
}
