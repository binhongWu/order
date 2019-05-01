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
    private Long outRegistId;
    @Query(name = "订单号", display = true)        
    private String outorderId;
    @Query(name = "出库日期", display = true)        
    private Date outRegistDate;
    public Long getOutRegistId(){
        return  outRegistId;
    }
    public void setOutRegistId(Long outRegistId ){
        this.outRegistId = outRegistId;
    }
    public String getOutorderId(){
        return  outorderId;
    }
    public void setOutorderId(String outorderId ){
        this.outorderId = outorderId;
    }
    public Date getOutRegistDate(){
        return  outRegistDate;
    }
    public void setOutRegistDate(Date outRegistDate ){
        this.outRegistDate = outRegistDate;
    }
 
}
