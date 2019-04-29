package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *CustomerInfor查询
 */
public class CustomerInforQuery extends PageParam {
    @Query(name = "客户编号", display = true)        
    private String clientCode;
    @Query(name = "客户名称", display = true)        
    private String name;
    @Query(name = "级别（0：初；1：中；2；高）", display = true,type=Query.TYPE_DICT,dict="customer_infor_level")
    private String level;
    public String getClientCode(){
        return  clientCode;
    }
    public void setClientCode(String clientCode ){
        this.clientCode = clientCode;
    }
    public String getName(){
        return  name;
    }
    public void setName(String name ){
        this.name = name;
    }
    public String getLevel(){
        return  level;
    }
    public void setLevel(String level ){
        this.level = level;
    }
 
}
