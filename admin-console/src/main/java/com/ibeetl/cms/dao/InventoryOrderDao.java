package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * InventoryOrder Dao
 */
@SqlResource("cms.inventoryOrder")
public interface InventoryOrderDao extends BaseMapper<InventoryOrder>{
    public PageQuery<InventoryOrder> queryByCondition(PageQuery query);
    public void batchDelInventoryOrderByIds( List<String> ids);
    public InventoryOrder getById(Object id);
    public Integer updateCustom(InventoryOrder model);
    public List<InventoryOrder> findListByCustom(InventoryOrder model);

    List<InventoryOrder> isSynchronization();
}