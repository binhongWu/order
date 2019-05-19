package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.IncomingRegistDao;
import com.ibeetl.cms.entity.IncomingRegist;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * IncomingRegist Service
 */

@Service
@Transactional
public class IncomingRegistService extends BaseService<IncomingRegist>{

    @Autowired private IncomingRegistDao incomingRegistDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<IncomingRegist>queryByCondition(PageQuery query){
        PageQuery ret =  incomingRegistDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    /**
     * 保存
     * @param model 实体类
     * @return
     */
    @Override
    public boolean save(IncomingRegist model) {
        model.setCreatedTime(new Date());
        model.setUpdatedTime(new Date());
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.insertTemplate(model, true) > 0;
    }

    /**
     * 根据主键更新，所有值参与更新
     */
    @Override
    public boolean update(IncomingRegist model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
    }

    /** -------------------------   暂时没有用到的方法   -------------------------**/

    public void batchDelIncomingRegist(List<String> ids){
        try {
            incomingRegistDao.batchDelIncomingRegistByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除IncomingRegist失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(IncomingRegist model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }



    /**
     * 自定义更新
      */
    public boolean updateCustom(IncomingRegist model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return incomingRegistDao.updateCustom(model) > 0;
    }



    public IncomingRegist getById(Object id){
        return incomingRegistDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<IncomingRegist> findListByCustom(IncomingRegist model) {
        return incomingRegistDao.findListByCustom(model);
    }
}