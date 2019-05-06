package com.ibeetl.cms.service;

import java.util.List;
import java.util.Date;

import com.ibeetl.admin.core.util.DateUtils;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.ibeetl.cms.dao.ProductInforDao;
import com.ibeetl.cms.entity.ProductInfor;
import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.service.CorePlatformService;

/**
 * ProductInfor Service
 */

@Service
@Transactional
public class ProductInforService extends BaseService<ProductInfor>{

    @Autowired private ProductInforDao productInforDao;
    @Autowired private CorePlatformService platformService;

    public PageQuery<ProductInfor>queryByCondition(PageQuery query){
        PageQuery ret =  productInforDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelProductInfor(List<String> ids){
        try {
            productInforDao.batchDelProductInforByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除ProductInfor失败", e);
        }
    }

    /**
     * 根据主键更新，属性为null的不会更新
     */
    @Override
    public boolean updateTemplate(ProductInfor model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateTemplateById(model) > 0;
    }

     /**
      * 根据主键更新，所有值参与更新
      */
    @Override
    public boolean update(ProductInfor model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.updateById(model) > 0;
     }

    /**
     * 自定义更新
      */
    public boolean updateCustom(ProductInfor model) {
        model.setUpdatedTime(new Date());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return productInforDao.updateCustom(model) > 0;
    }

    @Override
    public boolean save(ProductInfor model) {
        model.setCreatedTime(new Date());
        model.setUpdatedTime(new Date());
        model.setCreatedBy(platformService.getCurrentUser().getId());
        model.setUpdatedBy(platformService.getCurrentUser().getId());
        return sqlManager.insertTemplate(model, true) > 0;
    }

    public ProductInfor getById(Object id){
        return productInforDao.getById(id);
    }

    /**
     * 按条件查找全部数据
     */
    public List<ProductInfor> findListByCustom(ProductInfor model) {
        return productInforDao.findListByCustom(model);
    }

    public void saveImport(List<ProductInfor> datas) {
        for (ProductInfor model : datas) {
            ProductInfor productInfor = findByCode(model.getCode());
            if (productInfor == null) {
                save(model);
            }else{
                productInfor.setName(model.getName());
                productInfor.setAuthor(model.getAuthor());
                productInfor.setLanguage(model.getLanguage());
                productInfor.setKinds(model.getKinds());
                productInfor.setBookKind(model.getBookKind());
                productInfor.setPublishHouse(model.getPublishHouse());
                productInfor.setPublishDate(model.getPublishDate());
                productInfor.setIntroduction(model.getIntroduction());
                productInfor.setBrand(model.getBrand());
                productInfor.setScore(model.getScore());
                productInfor.setProductNum(model.getProductNum());
                productInfor.setExistStocks(String.valueOf(Integer.valueOf(productInfor.getExistStocks())+Integer.valueOf(model.getExistStocks())));
                productInfor.setWareId(model.getWareId());
                productInfor.setSupplierId(model.getSupplierId());
                productInfor.setInPrice(model.getInPrice());
                productInfor.setOutPrice(model.getOutPrice());
                productInfor.setMinStocks(model.getMinStocks());
                productInfor.setMaxStocks(model.getMaxStocks());
                update(productInfor);
            }
        }
    }

    /**
     * 根据编码查找
     * @param code
     * @return
     */
    public ProductInfor findByCode(String code) {
        return productInforDao.findByCode(code);
    }

    /**
     * 检索现有库存 < 最小库存
     * @return
     */
    public List<ProductInfor> queryWarehouseWarn() {
        return productInforDao.queryWarehouseWarn();
    }

    public List<ProductInfor> findAll() {
        return productInforDao.findAll();
    }
}