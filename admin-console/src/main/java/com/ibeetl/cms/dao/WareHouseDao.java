package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * WareHouse Dao
 */
@SqlResource("cms.wareHouse")
public interface WareHouseDao extends BaseMapper<WareHouse>{
    public PageQuery<WareHouse> queryByCondition(PageQuery query);
    public void batchDelWareHouseByIds( List<String> ids);
    public WareHouse getById(Object id);
    public Integer updateCustom(WareHouse model);
    public List<WareHouse> findListByCustom(WareHouse model);
}