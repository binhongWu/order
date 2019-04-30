package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.cms.web.dto.CustomerInforData;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.CustomerInforDao;
import com.ibeetl.cms.entity.CustomerInfor;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * CustomerInfor Service
 */

@Service
@Transactional
public class CustomerInforService extends BaseService<CustomerInfor>{

    @Autowired private CustomerInforDao customerInforDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<CustomerInfor>queryByCondition(PageQuery query){
        PageQuery ret =  customerInforDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelCustomerInfor(List<String> ids){
        try {
            customerInforDao.batchDelCustomerInforByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除CustomerInfor失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(CustomerInfor model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(CustomerInfor model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(CustomerInfor model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return customerInforDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(CustomerInfor model) {
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setCreatedTime(new Date());
        //随机数生成7位随机数 客户编号
        String code = String.valueOf(Math.random()).replace(".", "").substring(1, 7);
        model.setClientCode("C"+code);
        model.setIntergral("0");
        model.setLevel("0");
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public CustomerInfor getById(Object id){
        return customerInforDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<CustomerInfor> findListByCustom(CustomerInfor model) {
        return customerInforDao.findListByCustom(model);
    }

    public void saveImport(List<CustomerInforData> datas) {

    }
}