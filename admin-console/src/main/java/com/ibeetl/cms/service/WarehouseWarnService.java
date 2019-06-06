package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.admin.console.service.DictConsoleService;
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
    @Autowired private DictConsoleService dictService;

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
     * 3、销售出成功后进行存量检索，若库存量小于最小库存量则生成仓库预警记录并发送邮件给仓库管理员进行提示。
     * @param code
     */
    public void queryWarehouseWarn(String code) {
        ProductInfor productInfor = productInforService.findByCode(code);
        if (productInfor != null) {
            if (Integer.valueOf(productInfor.getExistStocks()) < Integer.valueOf(productInfor.getMinStocks())) {
                WarehouseWarn model = new WarehouseWarn();
                model.setAlarmt(new Date());
//                model.setNextAlarmt(DateUtils.plusDays(new Date(),3));
                model.setCode(productInfor.getCode());
                model.setStock(String.valueOf(Integer.parseInt(productInfor.getMinStocks()) - Integer.parseInt(productInfor.getExistStocks())));
                model.setCheck("0");
                save(model);
                // 发送邮箱信息
                // 获取配置得邮箱
                List<String> emailList = dictService.findEmail();
                if (emailList.size() > 0) {
                    for (String email : emailList) {
                        mailUtil.sendEmail(email);
                    }
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


    public WarehouseWarn findById(Long warningId) {
        return warehouseWarnDao.findById(warningId);
    }
}