package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.WareHouseDao;
import com.ibeetl.cms.entity.WareHouse;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * WareHouse Service
 */

@Service
@Transactional
public class WareHouseService extends BaseService<WareHouse>{

    @Autowired private WareHouseDao wareHouseDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<WareHouse>queryByCondition(PageQuery query){
        PageQuery ret =  wareHouseDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelWareHouse(List<String> ids){
        try {
            wareHouseDao.batchDelWareHouseByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除WareHouse失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(WareHouse model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(WareHouse model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(WareHouse model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return wareHouseDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(WareHouse model) {
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public WareHouse getById(Object id){
        return wareHouseDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<WareHouse> findListByCustom(WareHouse model) {
        return wareHouseDao.findListByCustom(model);
    }
}