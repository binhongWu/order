package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.cms.entity.SalesOrder;
import com.ibeetl.cms.entity.SalesOutStack;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.SalesOrderBakDao;
import com.ibeetl.cms.entity.SalesOrderBak;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * SalesOrderBak Service
 */

@Service
@Transactional
public class SalesOrderBakService extends BaseService<SalesOrderBak>{

    @Autowired private SalesOrderBakDao salesOrderBakDao;
    @Autowired private CorePlatformService platformService;


    public PageQuery<SalesOrderBak>queryByCondition(PageQuery query){
        PageQuery ret =  salesOrderBakDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelSalesOrderBak(List<String> ids){
        try {
            salesOrderBakDao.batchDelSalesOrderBakByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除SalesOrderBak失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(SalesOrderBak model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(SalesOrderBak model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(SalesOrderBak model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return salesOrderBakDao.updateCustom(model) > 0;
    }
    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private SalesOutStackService salesOutStackService;

    @Override
    public boolean save(SalesOrderBak model) {
        model.setCreatedTime(new Date());
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        model.setSalesDate(new Date());
        model.setSalesBy(platformService.getCurrentUser().getName());
        model.setFinishedStatus("0");
        //2、保存到销售订单等等....
        SalesOrder salesOrder = new SalesOrder();
        //复制相同的属性
        BeanUtils.copyProperties(model,salesOrder);
        //保存到销售订单
        salesOrderService.save(salesOrder);
        //临时表关联销售订单号
        model.setSalesOrderId(salesOrder.getSalesId());
        //判断直销的保存到销售出库
        if("0".equals(model.getOrderFor())){
            SalesOutStack salesOutStack = new SalesOutStack();



            salesOutStackService.save(salesOutStack);
        }
        //1、保存到临时销售订单
        return sqlManager.insertTemplate(model, true) > 0;
    }




    public SalesOrderBak getById(Object id){
        return salesOrderBakDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<SalesOrderBak> findListByCustom(SalesOrderBak model) {
        return salesOrderBakDao.findListByCustom(model);
    }
}