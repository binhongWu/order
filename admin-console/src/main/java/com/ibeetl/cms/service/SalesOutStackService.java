package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.cms.entity.SalesOrderBak;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.SalesOutStackDao;
import com.ibeetl.cms.entity.SalesOutStack;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * SalesOutStack Service
 */

@Service
@Transactional
public class SalesOutStackService extends BaseService<SalesOutStack>{

    @Autowired private SalesOutStackDao salesOutStackDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<SalesOutStack>queryByCondition(PageQuery query){
        PageQuery ret =  salesOutStackDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelSalesOutStack(List<String> ids){
        try {
            salesOutStackDao.batchDelSalesOutStackByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除SalesOutStack失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(SalesOutStack model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(SalesOutStack model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(SalesOutStack model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return salesOutStackDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(SalesOutStack model) {
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setCreatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        model.setUpdatedTime(new Date());
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public SalesOutStack getById(Object id){
        return salesOutStackDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<SalesOutStack> findListByCustom(SalesOutStack model) {
        return salesOutStackDao.findListByCustom(model);
    }

    public void saveImport(List<SalesOutStack> datas) {
        for (SalesOutStack model : datas) {
            model.setSalesOutStackId(null);
            save(model);
        }
    }

    public SalesOutStack getBySalId(Long salesId) {
        return salesOutStackDao.getBySalId(salesId);
    }
}