package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.SupplieInforDao;
import com.ibeetl.cms.entity.SupplieInfor;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * SupplieInfor Service
 */

@Service
@Transactional
public class SupplieInforService extends BaseService<SupplieInfor>{

    @Autowired private SupplieInforDao supplieInforDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<SupplieInfor>queryByCondition(PageQuery query){
        PageQuery ret =  supplieInforDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelSupplieInfor(List<String> ids){
        try {
            supplieInforDao.batchDelSupplieInforByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除SupplieInfor失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(SupplieInfor model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(SupplieInfor model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(SupplieInfor model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return supplieInforDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(SupplieInfor model) {
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public SupplieInfor getById(Object id){
        return supplieInforDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<SupplieInfor> findListByCustom(SupplieInfor model) {
        return supplieInforDao.findListByCustom(model);
    }
}