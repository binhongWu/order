package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.cms.entity.IncomingRegist;
import com.ibeetl.cms.entity.SalesOrder;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.SalesReturnDao;
import com.ibeetl.cms.entity.SalesReturn;
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
    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private IncomingRegistService incomingRegistService;

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
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setCreatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        model.setUpdatedTime(new Date());
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

    public void saveImport(List<SalesReturn> datas) {
        for (SalesReturn model : datas) {
            model.setReturnId(null);
            save(model);
            // 将对应的销售订单的完成状态改为失败。
            SalesOrder salesOrder = salesOrderService.queryById(model.getSalesId());
            if (salesOrder != null) {
                salesOrder.setFinishedStatus("1");
            }
            salesOrderService.update(salesOrder);

            IncomingRegist incomingRegist = new IncomingRegist();
            incomingRegist.setOrderId(salesOrder.getSalesId().toString());
            incomingRegist.setInRegistDate(new Date());
            incomingRegist.setCode(salesOrder.getCode());
//            incomingRegist.setSupplierId(salesOrder.getSupplierId());
            incomingRegist.setPrice(salesOrder.getPrice());
            incomingRegist.setNumber(salesOrder.getNumber());
            incomingRegist.setTotal(String.valueOf(Integer.parseInt(salesOrder.getPrice())*Integer.parseInt(salesOrder.getNumber())));
            incomingRegist.setStatus("1");
            incomingRegistService.save(incomingRegist);
        }
    }
}