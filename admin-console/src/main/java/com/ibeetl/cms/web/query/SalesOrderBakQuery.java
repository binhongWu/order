package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *SalesOrderBak查询
 */
public class SalesOrderBakQuery extends PageParam {
    @Query(name = "订单单号", display = true)        
    private Long salesId;
    @Query(name = "绘本编码", display = true)        
    private String code;
    @Query(name = "客户编码", display = true)        
    private String clientId;
    @Query(name = "销售日期", display = true,type = Query.TYPE_DATE_BETWEEN)
    private String orderDate;
    private Date orderDateStart;
    private Date orderDateEnd;
    @Query(name = "销售人", display = true)        
    private String salesBy;
    @Query(name = "销售方式", display = true,type=Query.TYPE_DICT,dict="sales_order_orderfor")
    private String orderFor;
    @Query(name = "完成状态", display = true,type=Query.TYPE_DICT,dict="sales_order_finishedstatus")
    private String finishedStatus;
    public Long getSalesId(){
        return  salesId;
    }
    public void setSalesId(Long salesId ){
        this.salesId = salesId;
    }
    public String getCode(){
        return  code;
    }
    public void setCode(String code ){
        this.code = code;
    }
    public String getClientId(){
        return  clientId;
    }
    public void setClientId(String clientId ){
        this.clientId = clientId;
    }
    public String getSalesBy(){
        return  salesBy;
    }
    public void setSalesBy(String salesBy ){
        this.salesBy = salesBy;
    }
    public String getOrderFor(){
        return  orderFor;
    }
    public void setOrderFor(String orderFor ){
        this.orderFor = orderFor;
    }
    public String getFinishedStatus(){
        return  finishedStatus;
    }
    public void setFinishedStatus(String finishedStatus ){
        this.finishedStatus = finishedStatus;
    }

    public String getOrderDate(){
        return  orderDate;
    }
    public void setOrderDate(String orderDate ){
        this.orderDate = orderDate;
        if(StringUtils.isEmpty(orderDate)){
            return ;
        }
        Date[] ds = Tool.parseDataRange(orderDate);
        this.orderDateStart = ds[0];
        this.orderDateEnd = ds[1];
    }

    public Date getOrderDateStart() {
        return orderDateStart;
    }

    public void setOrderDateStart(Date orderDateStart) {
        this.orderDateStart = orderDateStart;
    }

    public Date getOrderDateEnd() {
        return orderDateEnd;
    }

    public void setOrderDateEnd(Date orderDateEnd) {
        this.orderDateEnd = orderDateEnd;
    }
}
