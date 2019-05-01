package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.OutboundRedistDao;
import com.ibeetl.cms.entity.OutboundRedist;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * OutboundRedist Service
 */

@Service
@Transactional
public class OutboundRedistService extends BaseService<OutboundRedist>{

    @Autowired private OutboundRedistDao outboundRedistDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<OutboundRedist>queryByCondition(PageQuery query){
        PageQuery ret =  outboundRedistDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelOutboundRedist(List<String> ids){
        try {
            outboundRedistDao.batchDelOutboundRedistByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除OutboundRedist失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(OutboundRedist model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(OutboundRedist model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(OutboundRedist model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return outboundRedistDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(OutboundRedist model) {
        model.setCreatedTime(new Date());
        model.setUpdatedTime(new Date());
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public OutboundRedist getById(Object id){
        return outboundRedistDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<OutboundRedist> findListByCustom(OutboundRedist model) {
        return outboundRedistDao.findListByCustom(model);
    }
}