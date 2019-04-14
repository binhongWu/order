package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *PurchaseOrder查询
 */
public class PurchaseOrderQuery extends PageParam {
    @Query(name = "订单单号", display = true)        
    private String orderId;
    @Query(name = "绘本编码", display = true)        
    private String code;
    @Query(name = "供应商编号", display = true)        
    private String supplierId;
    @Query(name = "订单日期", display = true)        
    private Date orderDate;
    @Query(name = "交货日期", display = true)        
    private Date deliverDate;
    @Query(name = "采购日期", display = true)        
    private String buyerBy;
    @Query(name = "审核状态", display = true)        
    private String chenkStatus;
    @Query(name = "完成状态（0：完成 1：未完成）", display = true,type=Query.TYPE_DICT,dict="purchase_order_finishcondition")
    private String finishCondition;
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
    public String getSupplierId(){
        return  supplierId;
    }
    public void setSupplierId(String supplierId ){
        this.supplierId = supplierId;
    }
    public Date getOrderDate(){
        return  orderDate;
    }
    public void setOrderDate(Date orderDate ){
        this.orderDate = orderDate;
    }
    public Date getDeliverDate(){
        return  deliverDate;
    }
    public void setDeliverDate(Date deliverDate ){
        this.deliverDate = deliverDate;
    }
    public String getBuyerBy(){
        return  buyerBy;
    }
    public void setBuyerBy(String buyerBy ){
        this.buyerBy = buyerBy;
    }
    public String getChenkStatus(){
        return  chenkStatus;
    }
    public void setChenkStatus(String chenkStatus ){
        this.chenkStatus = chenkStatus;
    }
    public String getFinishCondition(){
        return  finishCondition;
    }
    public void setFinishCondition(String finishCondition ){
        this.finishCondition = finishCondition;
    }
 
}
