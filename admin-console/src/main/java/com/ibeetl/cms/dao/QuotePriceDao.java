package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * QuotePrice Dao
 */
@SqlResource("cms.quotePrice")
public interface QuotePriceDao extends BaseMapper<QuotePrice>{
    public PageQuery<QuotePrice> queryByCondition(PageQuery query);
    public void batchDelQuotePriceByIds( List<String> ids);
    public QuotePrice getById(Object id);
    public Integer updateCustom(QuotePrice model);
    public List<QuotePrice> findListByCustom(QuotePrice model);
}