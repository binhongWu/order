package com.ibeetl.bdata.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.bdata.entity.*;

/**
 * SalesReturn Dao
 */
@SqlResource("cms.salesReturn")
public interface SalesReturnDao extends BaseMapper<SalesReturn>{
    public PageQuery<SalesReturn> queryByCondition(PageQuery query);
    public void batchDelSalesReturnByIds( List<String> ids);
    public SalesReturn getById(Object id);
    public Integer updateCustom(SalesReturn model);
    public List<SalesReturn> findListByCustom(SalesReturn model);
}