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
    private Long salesId;
    @Query(name = "绘本编码", display = true)        
    private String code;
    @Query(name = "付款方式（0：支付宝 1：微信 2：银行卡）", display = true,type=Query.TYPE_DICT,dict="sales_order_paymentmethod")
    private String paymentMethod;
    @Query(name = "完成状态（0：完成 1：未完成）", display = true,type=Query.TYPE_DICT,dict="sales_order_finishedstatus")
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
    public String getPaymentMethod(){
        return  paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod ){
        this.paymentMethod = paymentMethod;
    }
    public String getFinishedStatus(){
        return  finishedStatus;
    }
    public void setFinishedStatus(String finishedStatus ){
        this.finishedStatus = finishedStatus;
    }
 
}
