package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.PurchaseOrderDao;
import com.ibeetl.cms.entity.PurchaseOrder;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * PurchaseOrder Service
 */

@Service
@Transactional
public class PurchaseOrderService extends BaseService<PurchaseOrder>{

    @Autowired private PurchaseOrderDao purchaseOrderDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<PurchaseOrder>queryByCondition(PageQuery query){
        PageQuery ret =  purchaseOrderDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelPurchaseOrder(List<String> ids){
        try {
            purchaseOrderDao.batchDelPurchaseOrderByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除PurchaseOrder失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(PurchaseOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(PurchaseOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(PurchaseOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return purchaseOrderDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(PurchaseOrder model) {
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public PurchaseOrder getById(Object id){
        return purchaseOrderDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<PurchaseOrder> findListByCustom(PurchaseOrder model) {
        return purchaseOrderDao.findListByCustom(model);
    }
}