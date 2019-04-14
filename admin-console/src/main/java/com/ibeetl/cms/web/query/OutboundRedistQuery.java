package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *OutboundRedist查询
 */
public class OutboundRedistQuery extends PageParam {
    @Query(name = "出库单号", display = true)        
    private String outRegistId;
    @Query(name = "出库日期", display = true)        
    private Date outRegistDate;
    @Query(name = "登记时间", display = true)        
    private Date registrateDate;
    @Query(name = "审核时间", display = true)        
    private Date checkDate;
    @Query(name = "审核状态", display = true)        
    private String checkStatus;
    public String getOutRegistId(){
        return  outRegistId;
    }
    public void setOutRegistId(String outRegistId ){
        this.outRegistId = outRegistId;
    }
    public Date getOutRegistDate(){
        return  outRegistDate;
    }
    public void setOutRegistDate(Date outRegistDate ){
        this.outRegistDate = outRegistDate;
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
