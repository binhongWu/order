package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.cms.entity.*;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.SalesReturnDao;
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
    @Autowired
    private SalesOrderBakService salesOrderBakService;
    @Autowired
    private ProductInforService productInforService;
    @Autowired
    private CustomerInforService customerInforService;
    @Autowired
    private OutboundRedistService outboundRedistService;
    @Autowired
    private SalesOutStackService salesOutStackService;

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

    /**
     * 审核
     * @param salesReturn
     * @return
     */
    public boolean audioData(SalesReturn salesReturn) {
        if("2".equals(salesReturn.getStats())){
            // 在备注里写上原因即可，不作额外操作
            if(update(salesReturn)){
                return true;
            }
        }else{
            try {
                // a 将销售退回订单的状态改为审核通过   页面会带进来  直接保存即可
                update(salesReturn);
                // b 关联到销售订单客户联的销售订单单号，找到对应订单 修改失败
                SalesOrderBak salesOrderBak = salesOrderBakService.getBySalId(Long.valueOf(salesReturn.getSalesId()));
                salesOrderBak.setFinishedStatus("1");
                salesOrderBakService.update(salesOrderBak);
                // d 关联到销售订单里改成失败
                SalesOrder salesOrder = salesOrderService.getBySalId(Long.valueOf(salesReturn.getSalesId()));
                salesOrder.setFinishedStatus("1");
                salesOrderService.update(salesOrder);
                // e 入库登记新增记录
                IncomingRegist incomingRegist = new IncomingRegist();
                incomingRegist.setOrderId(salesReturn.getReturnId().toString());
                incomingRegist.setInRegistDate(new Date());
                incomingRegist.setCode(salesReturn.getCode());
                incomingRegist.setPrice(salesReturn.getPrice());
                incomingRegist.setNumber(salesReturn.getNumber());
                incomingRegist.setTotal(salesReturn.getPaymentAmount());
                incomingRegist.setStatus("1");
                incomingRegistService.save(incomingRegist);
                // f 销售出库  修改失败
                SalesOutStack salesOutStack = salesOutStackService.getBySalId(salesOrder.getSalesId());
                salesOutStack.setCheckStatus("1");
                salesOutStack.setRemarks("销售退回");
                salesOutStackService.update(salesOutStack);
                // g 出库登记对应的状态改成其他，然后备注写上销售退回
                OutboundRedist outboundRedist = outboundRedistService.getByOutId(salesOutStack.getSalesOutStackId().toString());
                outboundRedist.setStatus("1");
                outboundRedist.setRemarks("销售退回");
                outboundRedistService.update(outboundRedist);
                // h 修改库存量
                CustomerInfor customerInfor = customerInforService.findByCode(salesReturn.getClientId());
                ProductInfor productInfor = productInforService.findByCode(salesReturn.getCode());
                productInfor.setExistStocks(String.valueOf(Integer.valueOf(productInfor.getExistStocks())+Integer.valueOf(salesReturn.getNumber())));
                productInforService.update(productInfor);
                // 0-5000 初级  5001-10000中级  10000以上高级
                int count = Integer.parseInt(salesReturn.getPaymentAmount().substring(0, salesReturn.getPaymentAmount().contains(".") ? salesReturn.getPaymentAmount().indexOf(".") : salesReturn.getPaymentAmount().length()));
                customerInfor.setIntergral(String.valueOf(Integer.valueOf(customerInfor.getIntergral())-count));
                if(Integer.valueOf(customerInfor.getIntergral()) <= 5000){
                    customerInfor.setLevel("0");
                }
                if (Integer.valueOf(customerInfor.getIntergral()) > 5000 && Integer.valueOf(customerInfor.getIntergral()) <= 10000) {
                    customerInfor.setLevel("1");
                }
                if(Integer.valueOf(customerInfor.getIntergral()) > 10000){
                    customerInfor.setLevel("2");
                }
                customerInforService.update(customerInfor);
                return true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}