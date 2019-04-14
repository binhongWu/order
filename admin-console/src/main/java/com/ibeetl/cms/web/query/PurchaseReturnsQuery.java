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
    private String returnedId;
    @Query(name = "采购订单单号", display = true)        
    private String orderId;
    @Query(name = "退货日期", display = true)        
    private Date returnedDate;
    @Query(name = "审查日期", display = true)        
    private Date checkDate;
    @Query(name = "审核状态（0：待审核 1：通过 2：拒绝）", display = true,type=Query.TYPE_DICT,dict="purchase_returns_checkstatus")
    private String checkStatus;
    public String getReturnedId(){
        return  returnedId;
    }
    public void setReturnedId(String returnedId ){
        this.returnedId = returnedId;
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
