package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.PurchaseReturnsDao;
import com.ibeetl.cms.entity.PurchaseReturns;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * PurchaseReturns Service
 */

@Service
@Transactional
public class PurchaseReturnsService extends BaseService<PurchaseReturns>{

    @Autowired private PurchaseReturnsDao purchaseReturnsDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<PurchaseReturns>queryByCondition(PageQuery query){
        PageQuery ret =  purchaseReturnsDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelPurchaseReturns(List<String> ids){
        try {
            purchaseReturnsDao.batchDelPurchaseReturnsByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除PurchaseReturns失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(PurchaseReturns model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(PurchaseReturns model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(PurchaseReturns model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return purchaseReturnsDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(PurchaseReturns model) {
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setCreatedTime(new Date());
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public PurchaseReturns getById(Object id){
        return purchaseReturnsDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<PurchaseReturns> findListByCustom(PurchaseReturns model) {
        return purchaseReturnsDao.findListByCustom(model);
    }
}