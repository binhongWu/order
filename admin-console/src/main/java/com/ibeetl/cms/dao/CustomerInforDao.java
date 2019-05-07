package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * CustomerInfor Dao
 */
@SqlResource("cms.customerInfor")
public interface CustomerInforDao extends BaseMapper<CustomerInfor>{
    public PageQuery<CustomerInfor> queryByCondition(PageQuery query);
    public void batchDelCustomerInforByIds( List<String> ids);
    public CustomerInfor getById(Object id);
    public Integer updateCustom(CustomerInfor model);
    public List<CustomerInfor> findListByCustom(CustomerInfor model);

    CustomerInfor findByCode(String clientId);
}