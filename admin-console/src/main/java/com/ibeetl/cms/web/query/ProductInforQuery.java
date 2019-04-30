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
    @Query(name = "绘本编码isbn", display = true)        
    private String code;
    @Query(name = "绘本名称", display = true)        
    private String name;
    @Query(name = "语种", display = true,type=Query.TYPE_DICT,dict="product_infor_language")
    private String language;
    @Query(name = "读者对象", display = true,type=Query.TYPE_DICT,dict="product_infor_kinds")
    private String kinds;
    @Query(name = "是否是套装", display = true,type=Query.TYPE_DICT,dict="product_infor_score")
    private String score;
    @Query(name = "供应商编码", display = true)        
    private String supplierId;
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
    public String getLanguage(){
        return  language;
    }
    public void setLanguage(String language ){
        this.language = language;
    }
    public String getKinds(){
        return  kinds;
    }
    public void setKinds(String kinds ){
        this.kinds = kinds;
    }
    public String getScore(){
        return  score;
    }
    public void setScore(String score ){
        this.score = score;
    }
    public String getSupplierId(){
        return  supplierId;
    }
    public void setSupplierId(String supplierId ){
        this.supplierId = supplierId;
    }
 
}
