package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *出库登记信息搜索条件
 */
public class OutboundRedistQuery extends PageParam {
    @Query(name = "出库单号", display = true)        
    private Long outRegistId;
    @Query(name = "订单号", display = true)        
    private String outorderId;
    @Query(name = "出库日期", display = true,type = Query.TYPE_DATE_BETWEEN)
    private String orderDate;
    private Date orderDateStart;
    private Date orderDateEnd;
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
