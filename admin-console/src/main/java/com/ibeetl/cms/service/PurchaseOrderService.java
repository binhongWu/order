package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.cms.entity.ProductInfor;
import com.ibeetl.utils.StringUtils;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.PurchaseOrderDao;
import com.ibeetl.cms.entity.PurchaseOrder;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * PurchaseOrder Service
 */

@Service
@Transactional
public class PurchaseOrderService extends BaseService<PurchaseOrder>{

    @Autowired private PurchaseOrderDao purchaseOrderDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<PurchaseOrder>queryByCondition(PageQuery query){
        PageQuery ret =  purchaseOrderDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelPurchaseOrder(List<String> ids){
        try {
            purchaseOrderDao.batchDelPurchaseOrderByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除PurchaseOrder失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(PurchaseOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(PurchaseOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(PurchaseOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return purchaseOrderDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(PurchaseOrder model) {
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setCreatedTime(new Date());
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public PurchaseOrder getById(Object id){
        return purchaseOrderDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<PurchaseOrder> findListByCustom(PurchaseOrder model) {
        return purchaseOrderDao.findListByCustom(model);
    }

    public void saveImport(List<PurchaseOrder> datas) {
        for (PurchaseOrder model : datas) {
            model.setOrderId(null);
            model.setCreatedBy(platformService.getCurrentUser().getId());
            model.setCreatedTime(new Date());
            model.setUpdatedTime(new Date());
            model.setUpdatedBy(platformService.getCurrentUser().getId());
            save(model);
        }
    }

    /**
     * 更新订单状态  记录到订单入库表  检索出剩下的，默认为未完成记录到退回表
     * @param datas
     */
    public void saveImport2(List<PurchaseOrder> datas) {
        for (PurchaseOrder model : datas) {
            model.setUpdatedTime(new Date());
            model.setUpdatedBy(platformService.getCurrentUser().getId());
            model.setDel("0");
            model.setFinishCondition("1");
            sqlManager.updateById(model);

        }
    }
}