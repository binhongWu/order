package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * WarehouseWarn Dao
 */
@SqlResource("cms.warehouseWarn")
public interface WarehouseWarnDao extends BaseMapper<WarehouseWarn>{
    public PageQuery<WarehouseWarn> queryByCondition(PageQuery query);
    public void batchDelWarehouseWarnByIds( List<String> ids);
    public WarehouseWarn getById(Object id);
    public Integer updateCustom(WarehouseWarn model);
    public List<WarehouseWarn> findListByCustom(WarehouseWarn model);

    WarehouseWarn findByCode(String code);

    List<WarehouseWarn> findByCheck();

    WarehouseWarn findById(Long warningId);
}