package com.ibeetl.cms.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
import com.ibeetl.admin.core.util.Tool;
import org.apache.commons.lang3.StringUtils;
/**
 *QuotePrice查询
 */
public class QuotePriceQuery extends PageParam {
    @Query(name = "报价单号", display = true)        
    private Long quoteId;
    @Query(name = "绘本编码", display = true)        
    private String code;
    public Long getQuoteId(){
        return  quoteId;
    }
    public void setQuoteId(Long quoteId ){
        this.quoteId = quoteId;
    }
    public String getCode(){
        return  code;
    }
    public void setCode(String code ){
        this.code = code;
    }
 
}
