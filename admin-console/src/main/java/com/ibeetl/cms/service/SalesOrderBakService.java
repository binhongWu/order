package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.cms.entity.*;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.SalesOrderBakDao;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * SalesOrderBak Service
 */

@Service
@Transactional
public class SalesOrderBakService extends BaseService<SalesOrderBak> {

    @Autowired
    private SalesOrderBakDao salesOrderBakDao;
    @Autowired
    private CorePlatformService platformService;
    @Autowired
    private CustomerInforService customerInforService;


    public PageQuery<SalesOrderBak> queryByCondition(PageQuery query) {
        PageQuery ret = salesOrderBakDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    /**
     * 保存
     *
     * @param model 实体类
     * @return
     */
    @Override
    public boolean save(SalesOrderBak model) {
        model.setCreatedTime(new Date());
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        model.setSalesDate(new Date());
        model.setSalesBy(platformService.getCurrentUser().getName());
        model.setFinishedStatus("0");
        //1、保存到临时销售订单
        return sqlManager.insertTemplate(model, true) > 0;
    }


    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private SalesOutStackService salesOutStackService;
    @Autowired
    private OutboundRedistService outboundRedistService;
    @Autowired
    private ProductInforService productInforService;

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    public SalesOrderBak getById(Object id) {
        return salesOrderBakDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<SalesOrderBak> findListByCustom(SalesOrderBak model) {
        return salesOrderBakDao.findListByCustom(model);
    }

    /**
     * 保存到销售订单、销售出库
     *
     * @param model
     */
    public boolean saveOthersInfo(SalesOrderBak model) {
        CustomerInfor customerInfor = customerInforService.findByCode(model.getClientId());
        if (customerInfor == null) {
            return false;
        }
        //2、保存到销售订单等等....
        SalesOrder salesOrder = new SalesOrder();
        //复制相同的属性
        BeanUtils.copyProperties(model, salesOrder);
        salesOrder.setCheckStatus(model.getCheckStatus());
        salesOrderService.save(salesOrder);
        //临时表关联销售订单号
        model.setSalesOrderId(salesOrder.getSalesId());
        sqlManager.updateTemplateById(model);
        SalesOutStack salesOutStack = new SalesOutStack();
        salesOutStack.setSalesId(salesOrder.getSalesId().toString());
        salesOutStack.setCode(model.getCode());
        salesOutStack.setClientId(model.getClientId());
        salesOutStack.setNumber(model.getNumber());
        salesOutStack.setPrice(model.getPrice());
        salesOutStack.setSalesDate(model.getSalesDate());
        salesOutStack.setPaymentAmount(model.getCheckStatus());
        salesOutStack.setPaymentMethod(model.getPaymentMethod());
        salesOutStack.setSalesBy(model.getSalesBy());
        salesOutStack.setDeliveryAddress(model.getTradeLocations());
        //订单状态为待审核
        salesOutStack.setCheckStatus("0");
        salesOutStack.setCreatedTime(new Date());
        salesOutStack.setCreatedBy(platformService.getCurrentUser().getId());
        salesOutStack.setUpdatedTime(new Date());
        salesOutStack.setUpdatedBy(platformService.getCurrentUser().getId());
        salesOutStackService.save(salesOutStack);
        return true;
    }

    @Autowired
    private SalesReturnService salesReturnService;
    @Autowired
    private IncomingRegistService incomingRegistService;

    /**
     * 销售退货
     *
     * @param model
     * @return
     */
    public boolean saveApplyReturn(SalesOrderBak model) {
        CustomerInfor customerInfor = customerInforService.findByCode(model.getClientId());
        if (customerInfor == null) {
            return false;
        }
        //退货单
        SalesReturn salesReturn = new SalesReturn();
        // 客户联的销售单号
        salesReturn.setSalesId(model.getSalesOrderId().toString());
        salesReturn.setReturnDate(new Date());
        salesReturn.setCode(model.getCode());
        salesReturn.setNumber(model.getNumber());
        salesReturn.setPrice(model.getPrice());
        salesReturn.setClientId(model.getClientId());
        salesReturn.setPaymentAmount(model.getCheckStatus());
        salesReturn.setSalesBy(model.getSalesBy());
        //待审核
        salesReturn.setStats("0");
        salesReturnService.save(salesReturn);
        return true;
    }

    public SalesOrderBak getBySalId(String salesId) {
        return salesOrderBakDao.getBySalId(salesId);
    }


    /** -------------------------   暂时没有用到的方法   -------------------------**/

    public void batchDelSalesOrderBak(List<String> ids) {
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

}