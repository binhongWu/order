package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.cms.entity.*;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.SalesOrderDao;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * SalesOrder Service
 */

@Service
@Transactional
public class SalesOrderService extends BaseService<SalesOrder>{

    @Autowired private SalesOrderDao salesOrderDao;
    @Autowired private CorePlatformService platformService;
    @Autowired
    private ProductInforService productInforService;
    @Autowired
    private SalesOutStackService salesOutStackService;
    @Autowired
    private OutboundRedistService outboundRedistService;

    public PageQuery<SalesOrder>queryByCondition(PageQuery query){
        PageQuery ret =  salesOrderDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    /**
     * 根据主键更新，所有值参与更新
     */
    @Override
    public boolean update(SalesOrder model) {
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
    public boolean save(SalesOrder model) {
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setCreatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        model.setUpdatedTime(new Date());
        return sqlManager.insertTemplate(model, true) > 0;
    }

    /**
     * 导入数据  存储到数据库  流程的采购管理 1
     * @param datas
     */
    public void saveImport(List<SalesOrder> datas) {
        for (SalesOrder model : datas) {
            model.setSalesId(null);
            ProductInfor productInfor = productInforService.findByCode(model.getCode());
            if (productInfor != null) {
                // 库存足 直销
                if (Integer.parseInt(productInfor.getExistStocks()) >= Integer.parseInt(model.getNumber())) {
                    model.setOrderFor("0");
                }else{
                    model.setOrderFor("1");
                }
            }
            // 保存销售订单
            save(model);
        }
        // 直销的销售出库
        String status = "0";
        List<SalesOrder> salesOrderList = findOrderForO(status);
        if (salesOrderList.size() > 0) {
            for (SalesOrder salesOrder : salesOrderList) {
                if (salesOrder != null) {
                    // 记录到销售出库
                    SalesOutStack salesOutStack = new SalesOutStack();
                    salesOutStack.setSalesId(salesOrder.getSalesId().toString());
                    salesOutStack.setCode(salesOrder.getCode());
                    salesOutStack.setNumber(salesOrder.getNumber());
                    salesOutStack.setPrice(salesOrder.getPrice());
                    salesOutStack.setSalesDate(salesOrder.getSalesDate());
                    salesOutStack.setClientId(salesOrder.getClientId());
                    salesOutStack.setPaymentAmount(salesOrder.getPaymentAmount());
                    salesOutStack.setPaymentMethod(salesOrder.getPaymentMethod());
                    salesOutStack.setSalesBy(salesOrder.getSalesBy());
                    salesOutStack.setDeliveryAddress(salesOrder.getTradeLocations());
                    salesOutStackService.save(salesOutStack);
                    // 录入到出库登记
                    OutboundRedist outboundRedist = new OutboundRedist();
                    outboundRedist.setOutorderId(salesOrder.getSalesId().toString());
                    outboundRedist.setOutRegistDate(new Date());
                    outboundRedist.setCode(salesOrder.getCode());
                    //供应商
//                    outboundRedist.setSupplierId(salesOrder.getS);
                    outboundRedist.setPrice(salesOrder.getPrice());
                    outboundRedist.setNumber(salesOrder.getNumber());
                    outboundRedist.setTotal(String.valueOf(Double.valueOf(salesOrder.getPrice()) * Integer.parseInt(salesOrder.getNumber())));
                    outboundRedist.setStatus("0");
                    outboundRedistService.save(outboundRedist);
                    // 修改库存
                    ProductInfor productInfor = productInforService.findByCode(salesOrder.getCode());
                    productInfor.setExistStocks(String.valueOf(Integer.parseInt(productInfor.getExistStocks())-Integer.parseInt(salesOutStack.getNumber())));
                    sqlManager.updateTemplateById(productInfor);
                }
            }
        }

    }

    /**
     * 根据订单完成状态查找
     * @param status
     * @return
     */
    private List<SalesOrder> findOrderForO(String status) {
        return salesOrderDao.findOrderForO(status);
    }

    /**
     * 根据订单号（主键）查找
     * @param salesId
     * @return
     */
    public SalesOrder getBySalId(Long salesId) {
        return salesOrderDao.getBySalId(salesId);
    }


    /** -------------------------   暂时没有用到的方法   -------------------------**/

    public void batchDelSalesOrder(List<String> ids){
        try {
            salesOrderDao.batchDelSalesOrderByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除SalesOrder失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(SalesOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }



    /**
     * 自定义更新
      */
    public boolean updateCustom(SalesOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return salesOrderDao.updateCustom(model) > 0;
    }



    public SalesOrder getById(Object id){
        return salesOrderDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<SalesOrder> findListByCustom(SalesOrder model) {
        return salesOrderDao.findListByCustom(model);
    }


}