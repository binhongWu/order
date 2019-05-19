package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.web.query.PageParam;

/**
 *中英文绘本购买信息查询条件
 */
public class ProductInforQuery2 extends PageParam {
    @Query(name = "绘本编码", display = true)
    private String code;
    @Query(name = "绘本名称", display = true)        
    private String name;
    @Query(name = "读者对象", display = true,type=Query.TYPE_DICT,dict="product_infor_kinds")
    private String kinds;
    @Query(name = "图书分类", display = true)        
    private String bookKind;
    @Query(name = "仓库编号", display = true)
    private String wareCode;
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
    public String getKinds(){
        return  kinds;
    }
    public void setKinds(String kinds ){
        this.kinds = kinds;
    }
    public String getBookKind(){
        return  bookKind;
    }
    public void setBookKind(String bookKind ){
        this.bookKind = bookKind;
    }
    public String getWareCode() {
        return wareCode;
    }
    public void setWareCode(String wareCode) {
        this.wareCode = wareCode;
    }
}
