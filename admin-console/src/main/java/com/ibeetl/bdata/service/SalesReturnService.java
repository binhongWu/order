package com.ibeetl.bdata.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.bdata.dao.SalesReturnDao;
import com.ibeetl.bdata.entity.SalesReturn;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * SalesReturn Service
 */

@Service
@Transactional
public class SalesReturnService extends BaseService<SalesReturn>{

    @Autowired private SalesReturnDao salesReturnDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<SalesReturn>queryByCondition(PageQuery query){
        PageQuery ret =  salesReturnDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelSalesReturn(List<String> ids){
        try {
            salesReturnDao.batchDelSalesReturnByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除SalesReturn失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(SalesReturn model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(SalesReturn model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(SalesReturn model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return salesReturnDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(SalesReturn model) {
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public SalesReturn getById(Object id){
        return salesReturnDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<SalesReturn> findListByCustom(SalesReturn model) {
        return salesReturnDao.findListByCustom(model);
    }
}