package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * OutboundRedist Dao
 */
@SqlResource("cms.outboundRedist")
public interface OutboundRedistDao extends BaseMapper<OutboundRedist>{
    public PageQuery<OutboundRedist> queryByCondition(PageQuery query);
    public void batchDelOutboundRedistByIds( List<String> ids);
    public OutboundRedist getById(Object id);
    public Integer updateCustom(OutboundRedist model);
    public List<OutboundRedist> findListByCustom(OutboundRedist model);
}