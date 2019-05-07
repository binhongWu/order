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
    @Autowired
    private OutboundRedistService outboundRedistService;
    @Autowired
    private ProductInforService productInforService;

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




    public SalesOrderBak getById(Object id){
        return salesOrderBakDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<SalesOrderBak> findListByCustom(SalesOrderBak model) {
        return salesOrderBakDao.findListByCustom(model);
    }

    /**
     * 保存到销售订单、销售出库、出库登记、修改库存
     * @param model
     */
    public void saveOthersInfo(SalesOrderBak model) {
        //2、保存到销售订单等等....
        SalesOrder salesOrder = new SalesOrder();
        //复制相同的属性
        BeanUtils.copyProperties(model,salesOrder);
        //保存到销售订单
        salesOrder.setCheckStatus(model.getCheckStatus());
        salesOrderService.save(salesOrder);
        //临时表关联销售订单号
        model.setSalesOrderId(salesOrder.getSalesId());
        sqlManager.updateTemplateById(model);
        //判断直销的保存到销售出库 --> 保存到出库登记  （此时应该要有一个审核，审核通过了才能发货然后修改库存量，暂时未实现） --> 修改库存
        if("0".equals(model.getOrderFor())){
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
            salesOutStack.setCreatedTime(new Date());
            salesOutStack.setCreatedBy(platformService.getCurrentUser().getId());
            salesOutStack.setUpdatedTime(new Date());
            salesOutStack.setUpdatedBy(platformService.getCurrentUser().getId());
            salesOutStackService.save(salesOutStack);
            // 保存到出库登记
            OutboundRedist outboundRedist = new OutboundRedist();
            outboundRedist.setOutorderId(salesOutStack.getSalesOutStackId().toString());
            outboundRedist.setOutRegistDate(salesOutStack.getSalesDate());
            outboundRedist.setCode(model.getCode());
            outboundRedist.setNumber(model.getNumber());
            outboundRedist.setPrice(model.getPrice());
            outboundRedist.setTotal(model.getCheckStatus());
            outboundRedist.setStatus("0");
            outboundRedist.setCreatedTime(new Date());
            outboundRedist.setCreatedBy(platformService.getCurrentUser().getId());
            outboundRedist.setUpdatedTime(new Date());
            outboundRedist.setUpdatedBy(platformService.getCurrentUser().getId());
            outboundRedistService.save(outboundRedist);
            // 修改库存
            ProductInfor productInfor = productInforService.findByCode(model.getCode());
            productInfor.setExistStocks(String.valueOf(Integer.valueOf(productInfor.getExistStocks()) - Integer.valueOf(model.getNumber())));
            productInforService.update(productInfor);
        }
    }

    @Autowired
    private SalesReturnService salesReturnService;
    @Autowired
    private IncomingRegistService incomingRegistService;
    /**
     * 销售退货
     * @param model
     * @return
     */
    public boolean saveApplyReturn(SalesOrderBak model) {
        //退货单
        SalesReturn salesReturn = new SalesReturn();
        salesReturn.setSalesId(model.getSalesOrderId().toString());
        salesReturn.setReturnDate(new Date());
        salesReturn.setCode(model.getCode());
        salesReturn.setNumber(model.getNumber());
        salesReturn.setPrice(model.getPrice());
        salesReturn.setClientId(model.getClientId());
        salesReturn.setPaymentAmount(model.getPaymentAmount());
        salesReturn.setSalesBy(model.getSalesBy());
        salesReturnService.save(salesReturn);
        // 销售订单改为失败
        SalesOrder salesOrder =salesOrderService.getById(model.getSalesOrderId());
        salesOrder.setFinishedStatus("1");
        salesOrderService.update(salesOrder);
        //入库登记（此处应该是申请）
        IncomingRegist incomingRegist = new IncomingRegist();
        incomingRegist.setOrderId(salesReturn.getReturnId().toString());
        incomingRegist.setInRegistDate(new Date());
        incomingRegist.setCode(model.getCode());
        incomingRegist.setPrice(model.getPrice());
        incomingRegist.setNumber(model.getPrice());
        incomingRegist.setTotal(model.getCheckStatus());
        incomingRegist.setStatus("1");
        incomingRegistService.save(incomingRegist);
        //修改库存量
        ProductInfor productInfor = productInforService.findByCode(model.getCode());
        productInfor.setExistStocks(String.valueOf(Integer.valueOf(productInfor.getExistStocks())+Integer.valueOf(model.getNumber())));
        productInforService.update(productInfor);
        return true;
    }

    public SalesOrderBak getBySalId(Long salesId) {
        return salesOrderBakDao.getBySalId(salesId);
    }
}