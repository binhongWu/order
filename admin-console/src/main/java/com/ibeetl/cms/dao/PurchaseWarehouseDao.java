package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * PurchaseWarehouse Dao
 */
@SqlResource("cms.purchaseWarehouse")
public interface PurchaseWarehouseDao extends BaseMapper<PurchaseWarehouse>{
    public PageQuery<PurchaseWarehouse> queryByCondition(PageQuery query);
    public void batchDelPurchaseWarehouseByIds( List<String> ids);
    public PurchaseWarehouse getById(Object id);
    public Integer updateCustom(PurchaseWarehouse model);
    public List<PurchaseWarehouse> findListByCustom(PurchaseWarehouse model);
}