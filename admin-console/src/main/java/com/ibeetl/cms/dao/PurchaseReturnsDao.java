package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * PurchaseReturns Dao
 */
@SqlResource("cms.purchaseReturns")
public interface PurchaseReturnsDao extends BaseMapper<PurchaseReturns>{
    public PageQuery<PurchaseReturns> queryByCondition(PageQuery query);
    public void batchDelPurchaseReturnsByIds( List<String> ids);
    public PurchaseReturns getById(Object id);
    public Integer updateCustom(PurchaseReturns model);
    public List<PurchaseReturns> findListByCustom(PurchaseReturns model);
}