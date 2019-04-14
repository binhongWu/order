package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *PurchaseWarehouse查询
 */
public class PurchaseWarehouseQuery extends PageParam {
    @Query(name = "入库单号", display = true)        
    private String enterId;
    @Query(name = "订单单号", display = true)        
    private String orderId;
    @Query(name = "绘本编码", display = true)        
    private String code;
    @Query(name = "录入时间", display = true)        
    private Date entryDate;
    @Query(name = "审核时间", display = true)        
    private Date checkDate;
    @Query(name = "审核状态（0：待审核 1：通过 2：拒绝）", display = true,type=Query.TYPE_DICT,dict="purchase_warehouse_checkstatus")
    private String checkStatus;
    public String getEnterId(){
        return  enterId;
    }
    public void setEnterId(String enterId ){
        this.enterId = enterId;
    }
    public String getOrderId(){
        return  orderId;
    }
    public void setOrderId(String orderId ){
        this.orderId = orderId;
    }
    public String getCode(){
        return  code;
    }
    public void setCode(String code ){
        this.code = code;
    }
    public Date getEntryDate(){
        return  entryDate;
    }
    public void setEntryDate(Date entryDate ){
        this.entryDate = entryDate;
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
