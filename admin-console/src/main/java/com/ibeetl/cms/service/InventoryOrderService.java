package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.InventoryOrderDao;
import com.ibeetl.cms.entity.InventoryOrder;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * InventoryOrder Service
 */

@Service
@Transactional
public class InventoryOrderService extends BaseService<InventoryOrder>{

    @Autowired private InventoryOrderDao inventoryOrderDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<InventoryOrder>queryByCondition(PageQuery query){
        PageQuery ret =  inventoryOrderDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelInventoryOrder(List<String> ids){
        try {
            inventoryOrderDao.batchDelInventoryOrderByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除InventoryOrder失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(InventoryOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(InventoryOrder model) {
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(InventoryOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return inventoryOrderDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(InventoryOrder model) {
        model.setCreatedTime(new Date());
        model.setUpdatedTime(new Date());
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public InventoryOrder getById(Object id){
        return inventoryOrderDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<InventoryOrder> findListByCustom(InventoryOrder model) {
        return inventoryOrderDao.findListByCustom(model);
    }

    public List<InventoryOrder> isSynchronization() {
        return inventoryOrderDao.isSynchronization();
    }
}