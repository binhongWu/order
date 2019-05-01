package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.QuotePriceDao;
import com.ibeetl.cms.entity.QuotePrice;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * QuotePrice Service
 */

@Service
@Transactional
public class QuotePriceService extends BaseService<QuotePrice>{

    @Autowired private QuotePriceDao quotePriceDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<QuotePrice>queryByCondition(PageQuery query){
        PageQuery ret =  quotePriceDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelQuotePrice(List<String> ids){
        try {
            quotePriceDao.batchDelQuotePriceByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除QuotePrice失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(QuotePrice model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(QuotePrice model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(QuotePrice model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return quotePriceDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(QuotePrice model) {
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setCreatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        model.setUpdatedTime(new Date());
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public QuotePrice getById(Object id){
        return quotePriceDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<QuotePrice> findListByCustom(QuotePrice model) {
        return quotePriceDao.findListByCustom(model);
    }
}