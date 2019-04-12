package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.ProductInforDao;
import com.ibeetl.cms.entity.ProductInfor;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * ProductInfor Service
 */

@Service
@Transactional
public class ProductInforService extends BaseService<ProductInfor>{

    @Autowired private ProductInforDao productInforDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<ProductInfor>queryByCondition(PageQuery query){
        PageQuery ret =  productInforDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelProductInfor(List<String> ids){
        try {
            productInforDao.batchDelProductInforByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除ProductInfor失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(ProductInfor model) {
//        model.setUpdatedTime(new Date());
//        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(ProductInfor model) {
//        model.setUpdatedTime(new Date());
//        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(ProductInfor model) {
//        model.setUpdatedTime(new Date());
//        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return productInforDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(ProductInfor model) {
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public ProductInfor getById(Object id){
        return productInforDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<ProductInfor> findListByCustom(ProductInfor model) {
        return productInforDao.findListByCustom(model);
    }
}