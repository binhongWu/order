package com.ibeetl.cms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.ibeetl.cms.entity.*;
import com.ibeetl.utils.StringUtils;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.PurchaseOrderDao;
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
    @Autowired
    private PurchaseWarehouseService purchaseWarehouseService;
    @Autowired
    private IncomingRegistService incomingRegistService;
    @Autowired
    private ProductInforService productInforService;
    @Autowired
    private PurchaseReturnsService purchaseReturnsService;

    public PageQuery<PurchaseOrder>queryByCondition(PageQuery query){
        PageQuery ret =  purchaseOrderDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
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
     * 保存
     * @param model 实体类
     * @return
     */
    @Override
    public boolean save(PurchaseOrder model) {
        model.setCreatedTime(new Date());
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.insertTemplate(model, true) > 0;
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
            model.setChenkStatus("0");
            save(model);
        }
    }

    /**
     * 1、订单进行状态更改为完成
     * 2、录入到采购入库表中
     * 3、录入到仓库管理的入库登记中
     * 4、修改绘本的库存量
     * 5、检索出所有还待完成的订单全部改为失败状态，并录入到采购退回表中
     * @param datas
     */
    public void saveImport2(List<PurchaseOrder> datas) {
        if (datas.size() > 0) {
            for (PurchaseOrder model : datas) {
                PurchaseOrder purchaseOrder = queryOrderId(model.getOrderId());
                if (purchaseOrder != null) {
                    // 1
                    model.setUpdatedTime(new Date());
                    model.setUpdatedBy(platformService.getCurrentUser().getId());
                    model.setFinishCondition("1");
                    model.setRemarks("");
                    sqlManager.updateTemplateById(model);
                    // 2
                    PurchaseWarehouse purchaseWarehouse = new PurchaseWarehouse();
                    purchaseWarehouse.setOrderId(purchaseOrder.getOrderId().toString());
                    purchaseWarehouse.setCode(purchaseOrder.getCode());
                    purchaseWarehouse.setNumber(purchaseOrder.getNumber());
                    purchaseWarehouse.setPrice(purchaseOrder.getPrice());
                    purchaseWarehouse.setSupplierId(purchaseOrder.getSupplierId());
                    purchaseWarehouse.setPaymentAmount(purchaseOrder.getPaymentAmount());
                    purchaseWarehouse.setPurchaseDate(purchaseOrder.getDeliverDate());
                    purchaseWarehouse.setBuyerBy(platformService.getCurrentUser().getName());
                    purchaseWarehouseService.save(purchaseWarehouse);
                    // 3 录入到仓库管理的入库登记中
                    IncomingRegist incomingRegist = new IncomingRegist();
                    incomingRegist.setOrderId(purchaseOrder.getOrderId().toString());
                    incomingRegist.setInRegistDate(new Date());
                    incomingRegist.setCode(purchaseOrder.getCode());
                    incomingRegist.setSupplierId(purchaseOrder.getSupplierId());
                    incomingRegist.setPrice(purchaseOrder.getPrice());
                    incomingRegist.setNumber(purchaseOrder.getNumber());
                    incomingRegist.setTotal(String.valueOf(Double.valueOf(purchaseOrder.getPrice())*Integer.valueOf(purchaseOrder.getNumber())));
                    incomingRegist.setStatus("0");
                    incomingRegistService.save(incomingRegist);
                    // 4 修改绘本的库存量
                    ProductInfor productInfor = productInforService.findByCode(purchaseOrder.getCode());
                    productInfor.setExistStocks(String.valueOf(Integer.valueOf(productInfor.getExistStocks())+Integer.valueOf(purchaseOrder.getNumber())));
                    sqlManager.updateTemplateById(productInfor);
                }
            }
            // 5 检索出所有还待完成的订单全部改为失败状态，并录入到采购退回表中
            String status = "0";
            List<PurchaseOrder> list = findByFinishCondition(status);
            if (list.size() > 0) {
                for (PurchaseOrder purchaseOrder : list) {
                    purchaseOrder.setFinishCondition("2");
                    update(purchaseOrder);
                    PurchaseReturns purchaseReturns = new PurchaseReturns();
                    purchaseReturns.setCode(purchaseOrder.getCode());
                    purchaseReturns.setNumber(purchaseOrder.getNumber());
                    purchaseReturns.setPrice(purchaseOrder.getPrice());
                    purchaseReturns.setSupplierId(purchaseOrder.getSupplierId());
                    purchaseReturns.setOrderId(purchaseOrder.getOrderId().toString());
                    purchaseReturns.setRefundMethod(purchaseOrder.getPaymentMethod());
                    purchaseReturns.setRefundAmount(purchaseOrder.getPaymentAmount());
                    purchaseReturns.setReturnedDate(new Date());
                    purchaseReturnsService.save(purchaseReturns);
                }
            }
        }
    }

    /**
     * 查找满足订单单号的数据
     * @param orderId
     * @return
     */
    private PurchaseOrder queryOrderId(Long orderId) {
        return purchaseOrderDao.queryOrderId(orderId);
    }

    /**
     * 查找状态为完成的数据
     * @param status
     * @return
     */
    private List<PurchaseOrder> findByFinishCondition(String status) {
        return purchaseOrderDao.findByFinishCondition(status);
    }

    /**
     * 编辑状态改变为完成，则入库登记等一系列操作，如果没改则正常保存操作即可
     * @param model
     * @return
     */
    public boolean updateByList(PurchaseOrder model) {
        PurchaseOrder purchaseOrder = queryOrderId(model.getOrderId());
        if(purchaseOrder.getFinishCondition().equals(model.getFinishCondition())){
            update(model);
            return true;
        }
        else if("2".equals(model.getFinishCondition())){
            update(model);
            PurchaseReturns purchaseReturns = new PurchaseReturns();
            purchaseReturns.setCode(model.getCode());
            purchaseReturns.setNumber(model.getNumber());
            purchaseReturns.setPrice(model.getPrice());
            purchaseReturns.setSupplierId(model.getSupplierId());
            purchaseReturns.setOrderId(model.getOrderId().toString());
            purchaseReturns.setRefundMethod(model.getPaymentMethod());
            purchaseReturns.setRefundAmount(model.getPaymentAmount());
            purchaseReturns.setReturnedDate(new Date());
            purchaseReturnsService.save(purchaseReturns);
            return true;
        }
        else if("1".equals(model.getFinishCondition())){
            List<PurchaseOrder> list = new ArrayList<>();
            list.add(model);
            try {
                saveImport2(list);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /** -------------------------   暂时没有用到的方法   -------------------------**/

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
     * 自定义更新
      */
    public boolean updateCustom(PurchaseOrder model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return purchaseOrderDao.updateCustom(model) > 0;
    }



    public PurchaseOrder getById(Object id){
        return purchaseOrderDao.getById(id);
    }


}