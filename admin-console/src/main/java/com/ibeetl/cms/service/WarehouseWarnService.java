package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.admin.core.util.DateUtils;
import com.ibeetl.cms.entity.ProductInfor;
import com.ibeetl.utils.MailUtil;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.WarehouseWarnDao;
import com.ibeetl.cms.entity.WarehouseWarn;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * WarehouseWarn Service
 */

@Service
@Transactional
public class WarehouseWarnService extends BaseService<WarehouseWarn>{

    @Autowired private WarehouseWarnDao warehouseWarnDao;
    @Autowired private CorePlatformService platformService;
    @Autowired
    private ProductInforService productInforService;
    @Autowired
    private MailUtil mailUtil;

    public PageQuery<WarehouseWarn>queryByCondition(PageQuery query){
        PageQuery ret =  warehouseWarnDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelWarehouseWarn(List<String> ids){
        try {
            warehouseWarnDao.batchDelWarehouseWarnByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除WarehouseWarn失败", e);
        }
    }

    /**
     * 根据主键更新，所有值参与更新
     */
    @Override
    public boolean update(WarehouseWarn model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(1L);
        return sqlManager.updateById(model) > 0;
    }

    /**
     * 3、设定一个时间进行存量检索，若库存量小于最小库存量则生成仓库预警记录并发送邮件给仓库管理员进行提示。
     */
    public void queryWarehouseWarn() {
        // （1）每天检索绘本信息的库存量，并返回库存量不足的绘本
        List<ProductInfor> list = productInforService.queryWarehouseWarn();
        if (list.size() > 0) {
            //有存在库存不足的就设置预警
            // 方式一  通集合的下标遍历
//            for(int i=0;i<list.size();i++){
//
//            }
            // 方式二  通过把集合的每个元素看成一个对象，就可以快速遍历list
            for (ProductInfor productInfor : list) {
                // 设置预警记录 下一次预警时间
                WarehouseWarn warehouseWarn = warehouseWarnDao.findByCode(productInfor.getCode());
                // 如果这个预警还存在 则判断下一次预警时间是否已过，未过的不进行处理，
                // 已过的设置再下一次预警时间
                if (warehouseWarn != null) {
                    // 时间比较 前者（当前）大于后者 为false  代表着未解决  设置下一次预警时间，并设置备注
                    if(!DateUtils.greaterThan(new Date(),warehouseWarn.getNextAlarmt())){
                        warehouseWarn.setAlarmt(new Date());
                        warehouseWarn.setNextAlarmt(DateUtils.plusDays(new Date(),3));
                        warehouseWarn.setRemarks("非首次预警");
                        sqlManager.updateTemplateById(warehouseWarn);
                    }
                }else{
                    // 初始都为未解决  下一次预警时间设置为3天
                    WarehouseWarn model = new WarehouseWarn();
                    model.setAlarmt(new Date());
                    model.setNextAlarmt(DateUtils.plusDays(new Date(),3));
                    model.setCode(productInfor.getCode());
                    model.setStock(String.valueOf(Integer.parseInt(productInfor.getMinStocks()) - Integer.parseInt(productInfor.getExistStocks())));
                    model.setCheck("1");
                    save(model);
                }
            }
            // 发送邮箱信息
            mailUtil.sendEmail("1137428517@qq.com");
        }

        //（2）解除预警
        List<WarehouseWarn> warehouseWarnList = warehouseWarnDao.findByCheck();
        if (warehouseWarnList.size() > 0) {
            for (WarehouseWarn warehouseWarn : warehouseWarnList) {
                ProductInfor productInfor = productInforService.findByCode(warehouseWarn.getCode());
                if (productInfor != null) {
                    if (Integer.valueOf(productInfor.getExistStocks()) >= Integer.valueOf(productInfor.getMinStocks())) {
                        warehouseWarn.setCheck("0");
//                        warehouseWarn.setStock(productInfor.getExistStocks());
                        update(warehouseWarn);
                    }
                }else{
                    warehouseWarn.setCheck("0");
                    update(warehouseWarn);
                }
            }
        }

    }


    /** -------------------------   暂时没有用到的方法   -------------------------**/

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(WarehouseWarn model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }



    /**
     * 自定义更新
      */
    public boolean updateCustom(WarehouseWarn model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return warehouseWarnDao.updateCustom(model) > 0;
    }

    /**
     * 保存
     * @param model 实体类
     * @return
     */
    @Override
    public boolean save(WarehouseWarn model) {
        model.setCreatedTime(new Date());
        model.setUpdatedTime(new Date());
//        model.setCreatedBy(platformService.getCurrentUser().getId());
//        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public WarehouseWarn getById(Object id){
        return warehouseWarnDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<WarehouseWarn> findListByCustom(WarehouseWarn model) {
        return warehouseWarnDao.findListByCustom(model);
    }



}