package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * SupplieInfor Dao
 */
@SqlResource("cms.supplieInfor")
public interface SupplieInforDao extends BaseMapper<SupplieInfor>{
    public PageQuery<SupplieInfor> queryByCondition(PageQuery query);
    public void batchDelSupplieInforByIds( List<String> ids);
    public SupplieInfor getById(Object id);
    public Integer updateCustom(SupplieInfor model);
    public List<SupplieInfor> findListByCustom(SupplieInfor model);
}