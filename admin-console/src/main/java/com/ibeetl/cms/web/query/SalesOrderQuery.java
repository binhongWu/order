package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *SalesOrder查询
 */
public class SalesOrderQuery extends PageParam {
    @Query(name = "订单单号", display = true)        
    private String salesId;
    @Query(name = "绘本编码", display = true)        
    private String code;
    @Query(name = "订单日期", display = true)        
    private Date orderDate;
    @Query(name = "销售日期", display = true)        
    private Date salesDate;
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
    public Date getOrderDate(){
        return  orderDate;
    }
    public void setOrderDate(Date orderDate ){
        this.orderDate = orderDate;
    }
    public Date getSalesDate(){
        return  salesDate;
    }
    public void setSalesDate(Date salesDate ){
        this.salesDate = salesDate;
    }
 
}
