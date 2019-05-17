package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *SalesReturn查询
 */
public class SalesReturnQuery extends PageParam {
    @Query(name = "退回单号", display = true)        
    private Long returnId;
    @Query(name = "订单单号", display = true)        
    private String salesId;
    @Query(name = "退货日期", display = true)        
    private Date returnDate;
    @Query(name = "绘本编码", display = true)        
    private String code;
    @Query(name = "绘本编码", display = true)
    private String status;
    public Long getReturnId(){
        return  returnId;
    }
    public void setReturnId(Long returnId ){
        this.returnId = returnId;
    }
    public String getSalesId(){
        return  salesId;
    }
    public void setSalesId(String salesId ){
        this.salesId = salesId;
    }
    public Date getReturnDate(){
        return  returnDate;
    }
    public void setReturnDate(Date returnDate ){
        this.returnDate = returnDate;
    }
    public String getCode(){
        return  code;
    }
    public void setCode(String code ){
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
