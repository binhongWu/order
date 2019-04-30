package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *SupplieInfor查询
 */
public class SupplieInforQuery extends PageParam {
    @Query(name = "供货商编号", display = true)        
    private Long supplierId;
    @Query(name = "供货商名称", display = true)        
    private String supplierName;
    @Query(name = "供应图书种类", display = true)        
    private String books;
    public Long getSupplierId(){
        return  supplierId;
    }
    public void setSupplierId(Long supplierId ){
        this.supplierId = supplierId;
    }
    public String getSupplierName(){
        return  supplierName;
    }
    public void setSupplierName(String supplierName ){
        this.supplierName = supplierName;
    }
    public String getBooks(){
        return  books;
    }
    public void setBooks(String books ){
        this.books = books;
    }
 
}
