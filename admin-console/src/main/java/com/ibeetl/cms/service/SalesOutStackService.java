package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.cms.entity.*;
import com.ibeetl.utils.StringUtils;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.SalesOutStackDao;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * SalesOutStack Service
 */

@Service
@Transactional
public class SalesOutStackService extends BaseService<SalesOutStack>{

    @Autowired private SalesOutStackDao salesOutStackDao;
    @Autowired private CorePlatformService platformService;
    @Autowired
    private OutboundRedistService outboundRedistService;
    @Autowired
    private CustomerInforService customerInforService;
    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private SalesOrderBakService salesOrderBakService;
    @Autowired
    private ProductInforService productInforService;

    public PageQuery<SalesOutStack>queryByCondition(PageQuery query){
        PageQuery ret =  salesOutStackDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    /**
     * 根据主键更新，所有值参与更新
     */
    @Override
    public boolean update(SalesOutStack model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
    }



    public void batchDelSalesOutStack(List<String> ids){
        try {
            salesOutStackDao.batchDelSalesOutStackByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除SalesOutStack失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(SalesOutStack model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

    /**
     * 保存
     * @param model 实体类
     * @return
     */
    @Override
    public boolean save(SalesOutStack model) {
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setCreatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        model.setUpdatedTime(new Date());
        return sqlManager.insertTemplate(model, true) > 0;
    }

    /**
     * 销售出库审核
     * @param salesOutStack
     * @return
     */
    public boolean audioData(SalesOutStack salesOutStack) {
        try {
            update(salesOutStack);
            CustomerInfor customerInfor = customerInforService.findByCode(salesOutStack.getClientId());
            //（1）通过：出库登记、客户积分
            if(StringUtils.equals(salesOutStack.getCheckStatus(),"1")){
                // 保存到出库登记
                OutboundRedist outboundRedist = new OutboundRedist();
                outboundRedist.setOutorderId(salesOutStack.getSalesOutStackId().toString());
                outboundRedist.setOutRegistDate(salesOutStack.getSalesDate());
                outboundRedist.setCode(salesOutStack.getCode());
                outboundRedist.setNumber(salesOutStack.getNumber());
                outboundRedist.setPrice(salesOutStack.getPrice());
                outboundRedist.setTotal(salesOutStack.getPaymentAmount());
                outboundRedist.setStatus("0");
                outboundRedist.setCreatedTime(new Date());
                outboundRedist.setCreatedBy(platformService.getCurrentUser().getId());
                outboundRedist.setUpdatedTime(new Date());
                outboundRedist.setUpdatedBy(platformService.getCurrentUser().getId());
                outboundRedistService.save(outboundRedist);
                // 修改客户积分
                // 0-5000 初级  5001-10000中级  10000以上高级
                int count = Integer.parseInt(salesOutStack.getPaymentAmount().substring(0, salesOutStack.getPaymentAmount().contains(".") ? salesOutStack.getPaymentAmount().indexOf(".") : salesOutStack.getPaymentAmount().length()));
                customerInfor.setIntergral(String.valueOf(Integer.valueOf(customerInfor.getIntergral())+count));
                if(Integer.valueOf(customerInfor.getIntergral()) <= 5000){
                    customerInfor.setLevel("0");
                }
                if (Integer.valueOf(customerInfor.getIntergral()) > 5000 && Integer.valueOf(customerInfor.getIntergral()) < 10000) {
                    customerInfor.setLevel("1");
                }
                if(Integer.valueOf(customerInfor.getIntergral()) > 10000){
                    customerInfor.setLevel("2");
                }
                customerInforService.update(customerInfor);
            }else {
                // 审核拒绝  销售出库订单状态为拒绝，然后备注写销售交易失败
                salesOutStack.setRemarks("销售交易失败");
                update(salesOutStack);
                // 销售订单（客户联）、销售订单状态改为失败
                SalesOrder salesOrder = salesOrderService.getBySalId(Long.valueOf(salesOutStack.getSalesId()));
                salesOrder.setFinishedStatus("1");
                salesOrderService.update(salesOrder);
                SalesOrderBak salesOrderBak = salesOrderBakService.getBySalId(salesOutStack.getSalesId());
                salesOrderBak.setFinishedStatus("1");
                salesOrderBakService.update(salesOrderBak);
                // 修改库存、客户积分
                // 修改库存
                ProductInfor productInfor = productInforService.findByCode(salesOutStack.getCode());
                productInfor.setExistStocks(String.valueOf(Integer.valueOf(productInfor.getExistStocks()) - Integer.valueOf(salesOutStack.getNumber())));
                productInforService.update(productInfor);
                // 0-5000 初级  5001-10000中级  10000以上高级
                int count = Integer.parseInt(salesOutStack.getPaymentAmount().substring(0, salesOutStack.getPaymentAmount().contains(".") ? salesOutStack.getPaymentAmount().indexOf(".") : salesOutStack.getPaymentAmount().length()));
                if (Integer.valueOf(customerInfor.getIntergral()) - count < 0) {
                    customerInfor.setIntergral("0");
                }else{
                    customerInfor.setIntergral(String.valueOf(Integer.valueOf(customerInfor.getIntergral())-count));
                }
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
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /** -------------------------   暂时没有用到的方法   -------------------------**/

    /**
     * 自定义更新
      */
    public boolean updateCustom(SalesOutStack model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return salesOutStackDao.updateCustom(model) > 0;
    }



    public SalesOutStack getById(Object id){
        return salesOutStackDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<SalesOutStack> findListByCustom(SalesOutStack model) {
        return salesOutStackDao.findListByCustom(model);
    }

    public void saveImport(List<SalesOutStack> datas) {
        for (SalesOutStack model : datas) {
            model.setSalesOutStackId(null);
            save(model);
        }
    }

    public SalesOutStack getBySalId(Long salesId) {
        return salesOutStackDao.getBySalId(salesId);
    }


}