package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * SalesOrder Dao
 */
@SqlResource("cms.salesOrder")
public interface SalesOrderDao extends BaseMapper<SalesOrder>{
    public PageQuery<SalesOrder> queryByCondition(PageQuery query);
    public void batchDelSalesOrderByIds( List<String> ids);
    public SalesOrder getById(Object id);
    public Integer updateCustom(SalesOrder model);
    public List<SalesOrder> findListByCustom(SalesOrder model);
}