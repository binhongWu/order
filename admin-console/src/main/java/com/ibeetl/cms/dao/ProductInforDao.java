package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * ProductInfor Dao
 */
@SqlResource("cms.productInfor")
public interface ProductInforDao extends BaseMapper<ProductInfor>{
    public PageQuery<ProductInfor> queryByCondition(PageQuery query);
    public void batchDelProductInforByIds( List<String> ids);
    public ProductInfor getById(Object id);
    public Integer updateCustom(ProductInfor model);
    public List<ProductInfor> findListByCustom(ProductInfor model);

    ProductInfor findByCode(String code);

    List<ProductInfor> queryWarehouseWarn();

    List<ProductInfor> findAll();

    PageQuery<ProductInfor> statistics(PageQuery query);
}