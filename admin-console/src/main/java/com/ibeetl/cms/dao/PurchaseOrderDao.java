package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * PurchaseOrder Dao
 */
@SqlResource("cms.purchaseOrder")
public interface PurchaseOrderDao extends BaseMapper<PurchaseOrder>{
    public PageQuery<PurchaseOrder> queryByCondition(PageQuery query);
    public void batchDelPurchaseOrderByIds( List<String> ids);
    public PurchaseOrder getById(Object id);
    public Integer updateCustom(PurchaseOrder model);
    public List<PurchaseOrder> findListByCustom(PurchaseOrder model);

    List<PurchaseOrder> findByFinishCondition(String status);
}