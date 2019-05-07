package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * SalesOrderBak Dao
 */
@SqlResource("cms.salesOrderBak")
public interface SalesOrderBakDao extends BaseMapper<SalesOrderBak>{
    public PageQuery<SalesOrderBak> queryByCondition(PageQuery query);
    public void batchDelSalesOrderBakByIds( List<String> ids);
    public SalesOrderBak getById(Object id);
    public Integer updateCustom(SalesOrderBak model);
    public List<SalesOrderBak> findListByCustom(SalesOrderBak model);

    SalesOrderBak getBySalId(Long salesId);
}