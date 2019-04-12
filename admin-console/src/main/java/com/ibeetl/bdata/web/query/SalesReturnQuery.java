package com.ibeetl.bdata.web.query;

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
    private String returnId;
    @Query(name = "订单单号", display = true)        
    private String salesId;
    @Query(name = "退货日期", display = true)        
    private Date returnDate;
    @Query(name = "审核时间", display = true)        
    private Date checkDate;
    @Query(name = "录入时间", display = true)        
    private Date entryDate;
    public String getReturnId(){
        return  returnId;
    }
    public void setReturnId(String returnId ){
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
    public Date getCheckDate(){
        return  checkDate;
    }
    public void setCheckDate(Date checkDate ){
        this.checkDate = checkDate;
    }
    public Date getEntryDate(){
        return  entryDate;
    }
    public void setEntryDate(Date entryDate ){
        this.entryDate = entryDate;
    }
 
}
