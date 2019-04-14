package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.PurchaseWarehouseDao;
import com.ibeetl.cms.entity.PurchaseWarehouse;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * PurchaseWarehouse Service
 */

@Service
@Transactional
public class PurchaseWarehouseService extends BaseService<PurchaseWarehouse>{

    @Autowired private PurchaseWarehouseDao purchaseWarehouseDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<PurchaseWarehouse>queryByCondition(PageQuery query){
        PageQuery ret =  purchaseWarehouseDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelPurchaseWarehouse(List<String> ids){
        try {
            purchaseWarehouseDao.batchDelPurchaseWarehouseByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除PurchaseWarehouse失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(PurchaseWarehouse model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(PurchaseWarehouse model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(PurchaseWarehouse model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return purchaseWarehouseDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(PurchaseWarehouse model) {
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public PurchaseWarehouse getById(Object id){
        return purchaseWarehouseDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<PurchaseWarehouse> findListByCustom(PurchaseWarehouse model) {
        return purchaseWarehouseDao.findListByCustom(model);
    }
}