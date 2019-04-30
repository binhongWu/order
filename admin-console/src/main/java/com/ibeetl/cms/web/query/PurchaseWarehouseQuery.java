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
    private Long enterId;
    @Query(name = "订单单号", display = true)        
    private String orderId;
    @Query(name = "绘本编码", display = true)        
    private String code;
    @Query(name = "供应商编号", display = true)        
    private String supplierId;
    @Query(name = "采购日期", display = true)        
    private Date purchaseDate;
    public Long getEnterId(){
        return  enterId;
    }
    public void setEnterId(Long enterId ){
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
    public String getSupplierId(){
        return  supplierId;
    }
    public void setSupplierId(String supplierId ){
        this.supplierId = supplierId;
    }
    public Date getPurchaseDate(){
        return  purchaseDate;
    }
    public void setPurchaseDate(Date purchaseDate ){
        this.purchaseDate = purchaseDate;
    }
 
}
