package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *SalesOutStack查询
 */
public class SalesOutStackQuery extends PageParam {
    @Query(name = "出库单号", display = true)        
    private Long salesOutStackId;
    @Query(name = "销售单号", display = true)        
    private String salesId;
    @Query(name = "绘本编码", display = true)        
    private String code;
    @Query(name = "销售日期", display = true,type = Query.TYPE_DATE_BETWEEN)
    private String orderDate;
    private Date orderDateStart;
    private Date orderDateEnd;
    public Long getSalesOutStackId(){
        return  salesOutStackId;
    }
    public void setSalesOutStackId(Long salesOutStackId ){
        this.salesOutStackId = salesOutStackId;
    }
    public String getSalesId(){
        return  salesId;
    }
    public void setSalesId(String salesId ){
        this.salesId = salesId;
    }
    public String getCode(){
        return  code;
    }
    public void setCode(String code ){
        this.code = code;
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
