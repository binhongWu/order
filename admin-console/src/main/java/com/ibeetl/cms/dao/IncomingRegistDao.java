package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * IncomingRegist Dao
 */
@SqlResource("cms.incomingRegist")
public interface IncomingRegistDao extends BaseMapper<IncomingRegist>{
    public PageQuery<IncomingRegist> queryByCondition(PageQuery query);
    public void batchDelIncomingRegistByIds( List<String> ids);
    public IncomingRegist getById(Object id);
    public Integer updateCustom(IncomingRegist model);
    public List<IncomingRegist> findListByCustom(IncomingRegist model);
}