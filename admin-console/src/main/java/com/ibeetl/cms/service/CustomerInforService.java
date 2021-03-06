package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.admin.core.conf.PasswordConfig;
import com.ibeetl.admin.core.util.enums.DelFlagEnum;
import com.ibeetl.admin.core.util.enums.GeneralStateEnum;
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
    @Autowired
    PasswordConfig.PasswordEncryptService passwordEncryptService;

    public PageQuery<CustomerInfor>queryByCondition(PageQuery query){
        PageQuery ret =  customerInforDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
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
     * 保存
     * @param model 实体类
     * @return
     */
    @Override
    public boolean save(CustomerInfor model) {
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setCreatedTime(new Date());
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        //随机数生成7位随机数 客户编号
        String code = String.valueOf(Math.random()).replace(".", "").substring(1, 7);
        model.setClientCode("C"+code);
        model.setIntergral("0");
        model.setLevel("0");
        return sqlManager.insertTemplate(model, true) > 0;
    }

    /**
     * 保存导入数据
     * @param datas
     */
    public void saveImport(List<CustomerInforData> datas) {
        for(CustomerInforData customerInforData:datas){
            CustomerInfor customerInfor = new CustomerInfor();
            customerInfor.setClientCode("C"+String.valueOf(Math.random()).replace(".", "").substring(1, 7));
            customerInfor.setName(customerInforData.getName());
            customerInfor.setSex(customerInforData.getSex().equals("女")?"1":"0");
            customerInfor.setTel(customerInforData.getTel());
            customerInfor.setEmail(customerInforData.getEmail());
            customerInfor.setIntergral("0");
            customerInfor.setLevel("0");
            customerInfor.setCreatedBy(platformService.getCurrentUser().getId());
            customerInfor.setCreatedTime(new Date());
            customerInfor.setUpdatedTime(new Date());
            customerInfor.setUpdatedBy(platformService.getCurrentUser().getId());
            save(customerInfor);
        }
    }

    /**
     * 根据客户编码查找客户
     * @param clientId
     * @return
     */
    public CustomerInfor findByCode(String clientId) {
        return customerInforDao.findByCode(clientId);
    }




    /** -------------------------   暂时没有用到的方法   -------------------------**/


    public CustomerInfor getById(Object id){
        return customerInforDao.getById(id);
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
     * 自定义更新
      */
    public boolean updateCustom(CustomerInfor model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return customerInforDao.updateCustom(model) > 0;
    }

    /**
     * 按条件查找全部数据
     */
    public List<CustomerInfor> findListByCustom(CustomerInfor model) {
        return customerInforDao.findListByCustom(model);
    }


}