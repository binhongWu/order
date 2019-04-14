package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *ProductInfor查询
 */
public class ProductInforQuery extends PageParam {
    @Query(name = "绘本编码", display = true)        
    private String code;
    @Query(name = "绘本名称（奖项+名称）", display = true)        
    private String name;
    @Query(name = "热销商品排名", display = true)        
    private String rank;
    public String getCode(){
        return  code;
    }
    public void setCode(String code ){
        this.code = code;
    }
    public String getName(){
        return  name;
    }
    public void setName(String name ){
        this.name = name;
    }
    public String getRank(){
        return  rank;
    }
    public void setRank(String rank ){
        this.rank = rank;
    }
 
}
