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
    @Query(name = "入库日期", display = true,type = Query.TYPE_DATE_BETWEEN)
    private String orderDate;
    private Date orderDateStart;
    private Date orderDateEnd;
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
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
