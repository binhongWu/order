package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *PurchaseReturns查询
 */
public class PurchaseReturnsQuery extends PageParam {
    @Query(name = "退回单号", display = true)        
    private Long returnedId;
    @Query(name = "绘本编码", display = true)        
    private String code;
    @Query(name = "采购订单单号", display = true)        
    private String orderId;
    @Query(name = "退货日期", display = true)        
    private Date returnedDate;
    public Long getReturnedId(){
        return  returnedId;
    }
    public void setReturnedId(Long returnedId ){
        this.returnedId = returnedId;
    }
    public String getCode(){
        return  code;
    }
    public void setCode(String code ){
        this.code = code;
    }
    public String getOrderId(){
        return  orderId;
    }
    public void setOrderId(String orderId ){
        this.orderId = orderId;
    }
    public Date getReturnedDate(){
        return  returnedDate;
    }
    public void setReturnedDate(Date returnedDate ){
        this.returnedDate = returnedDate;
    }
 
}
