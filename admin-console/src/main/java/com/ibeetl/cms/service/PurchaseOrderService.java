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
        model.setCreatedTime(new Date());
        model.setCreatedBy(platformService.getCurrentUser().getId());
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

    /**
     * 初始导入  记录所有导入数据并状态都为待完成
     * @param datas
     */
    public void saveImport(List<PurchaseOrder> datas) {
        for (PurchaseOrder model : datas) {
            model.setOrderId(null);
            model.setFinishCondition("0");
            save(model);
        }
    }

    /**
     * 1、订单进行状态更改为完成
     * 2、录入到采购入库表中
     * 3、录入到仓库管理的入库登记中
     * 4、修改绘本的库存量
     * @param datas
     */
    public void saveImport2(List<PurchaseOrder> datas) {
        for (PurchaseOrder model : datas) {
            PurchaseOrder purchaseOrder = queryById(model.getOrderId());
            if (purchaseOrder != null) {
                // 1
                model.setUpdatedTime(new Date());
                model.setUpdatedBy(platformService.getCurrentUser().getId());
                model.setFinishCondition("1");
                sqlManager.updateById(model);
                // 2

                // 3
            }
        }
    }
}