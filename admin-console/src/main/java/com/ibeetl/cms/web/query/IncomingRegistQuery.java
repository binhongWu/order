package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *入库登记信息搜索条件
 */
public class IncomingRegistQuery extends PageParam {
    @Query(name = "入库单号", display = true)        
    private Long inRegistId;
    @Query(name = "关联订单号", display = true)        
    private String orderId;
    @Query(name = "入库日期", display = true)        
    private Date inRegistDate;
    public Long getInRegistId(){
        return  inRegistId;
    }
    public void setInRegistId(Long inRegistId ){
        this.inRegistId = inRegistId;
    }
    public String getOrderId(){
        return  orderId;
    }
    public void setOrderId(String orderId ){
        this.orderId = orderId;
    }
    public Date getInRegistDate(){
        return  inRegistDate;
    }
    public void setInRegistDate(Date inRegistDate ){
        this.inRegistDate = inRegistDate;
    }
 
}
