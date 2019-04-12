package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.SalesOrderDao;
import com.ibeetl.cms.entity.SalesOrder;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * SalesOrder Service
 */

@Service
@Transactional
public class SalesOrderService extends BaseService<SalesOrder>{

    @Autowired private SalesOrderDao salesOrderDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<SalesOrder>queryByCondition(PageQuery query){
        PageQuery ret =  salesOrderDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelSalesOrder(List<String> ids){
        try {
            salesOrderDao.batchDelSalesOrderByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除SalesOrder失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(SalesOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(SalesOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(SalesOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return salesOrderDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(SalesOrder model) {
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public SalesOrder getById(Object id){
        return salesOrderDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<SalesOrder> findListByCustom(SalesOrder model) {
        return salesOrderDao.findListByCustom(model);
    }
}