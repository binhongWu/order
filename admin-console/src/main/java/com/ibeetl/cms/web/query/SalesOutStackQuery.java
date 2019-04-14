package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *SalesOutStack查询
 */
public class SalesOutStackQuery extends PageParam {
    @Query(name = "出库单号", display = true)        
    private String salesOutStackId;
    @Query(name = "销售单号", display = true)        
    private String salesId;
    @Query(name = "销售日期", display = true)        
    private Date salesDate;
    @Query(name = "审核时间", display = true)        
    private Date checkDate;
    @Query(name = "审核状态", display = true)        
    private String checkStatus;
    public String getSalesOutStackId(){
        return  salesOutStackId;
    }
    public void setSalesOutStackId(String salesOutStackId ){
        this.salesOutStackId = salesOutStackId;
    }
    public String getSalesId(){
        return  salesId;
    }
    public void setSalesId(String salesId ){
        this.salesId = salesId;
    }
    public Date getSalesDate(){
        return  salesDate;
    }
    public void setSalesDate(Date salesDate ){
        this.salesDate = salesDate;
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
