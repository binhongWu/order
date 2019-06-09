package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *InventoryOrder查询
 */
public class InventoryOrderQuery extends PageParam {
    @Query(name = "系统编号", display = true)        
    private Long id;
    @Query(name = "盘点单号", display = true)        
    private Long inventoryId;
    public Long getId(){
        return  id;
    }
    public void setId(Long id ){
        this.id = id;
    }
    public Long getInventoryId(){
        return  inventoryId;
    }
    public void setInventoryId(Long inventoryId ){
        this.inventoryId = inventoryId;
    }
 
}
