package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * SalesOutStack Dao
 */
@SqlResource("cms.salesOutStack")
public interface SalesOutStackDao extends BaseMapper<SalesOutStack>{
    public PageQuery<SalesOutStack> queryByCondition(PageQuery query);
    public void batchDelSalesOutStackByIds( List<String> ids);
    public SalesOutStack getById(Object id);
    public Integer updateCustom(SalesOutStack model);
    public List<SalesOutStack> findListByCustom(SalesOutStack model);
}