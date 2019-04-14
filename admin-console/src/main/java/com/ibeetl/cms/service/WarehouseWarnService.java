package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.WarehouseWarnDao;
import com.ibeetl.cms.entity.WarehouseWarn;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * WarehouseWarn Service
 */

@Service
@Transactional
public class WarehouseWarnService extends BaseService<WarehouseWarn>{

    @Autowired private WarehouseWarnDao warehouseWarnDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<WarehouseWarn>queryByCondition(PageQuery query){
        PageQuery ret =  warehouseWarnDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelWarehouseWarn(List<String> ids){
        try {
            warehouseWarnDao.batchDelWarehouseWarnByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除WarehouseWarn失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(WarehouseWarn model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(WarehouseWarn model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(WarehouseWarn model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return warehouseWarnDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(WarehouseWarn model) {
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public WarehouseWarn getById(Object id){
        return warehouseWarnDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<WarehouseWarn> findListByCustom(WarehouseWarn model) {
        return warehouseWarnDao.findListByCustom(model);
    }
}