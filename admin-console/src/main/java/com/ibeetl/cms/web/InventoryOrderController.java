package com.ibeetl.cms.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.sql.core.engine.PageQuery;
import org.jxls.common.Context;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReadMessage;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ibeetl.admin.console.web.dto.DictExcelImportData;
import com.ibeetl.admin.console.web.query.UserQuery;
import com.ibeetl.admin.core.annotation.Function;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.entity.CoreDict;
import com.ibeetl.admin.core.entity.CoreUser;
import com.ibeetl.admin.core.file.FileItem;
import com.ibeetl.admin.core.file.FileService;
import com.ibeetl.admin.core.web.JsonResult;
import com.ibeetl.admin.core.util.*;
import com.ibeetl.cms.entity.*;
import com.ibeetl.cms.service.*;
import com.ibeetl.cms.web.query.*;

/**
 * InventoryOrder 接口
 */
@Controller
public class InventoryOrderController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/cms/inventoryOrder";


    @Autowired private InventoryOrderService inventoryOrderService;
    @Autowired
    private ProductInforService productInforService;
    @Autowired
    FileService fileService;
    /* 页面 */

    @GetMapping(MODEL + "/index.do")
    @Function("inventoryOrder.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/cms/inventoryOrder/index.html") ;
        view.addObject("search", InventoryOrderQuery.class.getName());
        return view;
    }
    @PostMapping(MODEL + "/list.json")
    @Function("inventoryOrder.query")
    @ResponseBody
    public JsonResult<PageQuery> list(InventoryOrderQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        inventoryOrderService.queryByCondition(page);
        return JsonResult.success(page);
    }

    /**
     * 盘点
     * @param id
     * @return
     */
    @GetMapping(MODEL + "/edit.do")
    @Function("inventoryOrder.edit")
    @ResponseBody
    public ModelAndView edit(Long id) {
        ModelAndView view = new ModelAndView("/cms/inventoryOrder/edit.html");
        InventoryOrder inventoryOrder = inventoryOrderService.queryById(id);
        view.addObject("inventoryOrder", inventoryOrder);
        return view;
    }

    @PostMapping(MODEL + "/update.json")
    @Function("inventoryOrder.update")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  InventoryOrder inventoryOrder) {
        InventoryOrder inventoryOrderNew = inventoryOrderService.getById(inventoryOrder.getId());
        inventoryOrderNew.setUpdatedTime(new Date());
        inventoryOrderNew.setInventoryStocks(inventoryOrder.getInventoryStocks());
        boolean success = inventoryOrderService.update(inventoryOrderNew);
        if (success) {
            if(!inventoryOrderNew.getExistStocks().equals(inventoryOrder.getInventoryStocks())){
                ProductInfor productInfor = productInforService.findByCode(inventoryOrder.getCode());
                productInfor.setExistStocks(inventoryOrder.getInventoryStocks());
                productInforService.update(productInfor);
            }
            return JsonResult.success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }


    /**
     * 今日是否已经同步过
     * @return
     */
    @PostMapping(MODEL + "/isSynchronization.do")
    @Function("inventoryOrder.query")
    @ResponseBody
    public JsonResult isSynchronization()
    {
        List<InventoryOrder> datas=inventoryOrderService.isSynchronization();
        if (datas.size() > 0) {
            return JsonResult.fail();
        }
        return JsonResult.success();
    }

    /**
     * 同步绘本库存信息到盘点记录
     * @return
     */
    @PostMapping(MODEL + "/synchronization.json")
    @Function("inventoryOrder.query")
    @ResponseBody
    public JsonResult synchronization()
    {
        try {
            List<ProductInfor> dataList = productInforService.findAll();
            if (dataList.size() > 0) {
                for (ProductInfor productInfor : dataList) {
                    InventoryOrder inventoryOrder = new InventoryOrder();
                    inventoryOrder.setCode(productInfor.getCode());
                    inventoryOrder.setName(productInfor.getName());
                    inventoryOrder.setKinds(productInfor.getKinds());
                    inventoryOrder.setLanguage(productInfor.getLanguage());
                    inventoryOrder.setBookKind(productInfor.getBookKind());
                    inventoryOrder.setExistStocks(productInfor.getExistStocks());
                    inventoryOrderService.save(inventoryOrder);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failMessage("系统错误请联系管理员！");
        }
        return JsonResult.success();
    }
















    @GetMapping(MODEL + "/add.do")
    @Function("inventoryOrder.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/cms/inventoryOrder/add.html");
        return view;
    }
        /**
         * 根据ID查找
         */
    @PostMapping(MODEL + "/queryById.json")
    @Function("inventoryOrder.query")
    @ResponseBody
    public JsonResult<InventoryOrder> queryById(Object id){
        InventoryOrder data = inventoryOrderService.queryById(id);
        return JsonResult.success(data);
    }

    @PostMapping(MODEL + "/add.json")
    @Function("inventoryOrder.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)InventoryOrder inventoryOrder)
    {
        inventoryOrderService.save(inventoryOrder);
        return JsonResult.success();
    }




   
    @GetMapping(MODEL + "/view.json")
    @Function("inventoryOrder.query")
    @ResponseBody
    public JsonResult<InventoryOrder>queryInfo(Long id) {
        InventoryOrder inventoryOrder = inventoryOrderService.queryById( id);
        return  JsonResult.success(inventoryOrder);
    }

    @PostMapping(MODEL + "/delete.json")
    @Function("inventoryOrder.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<String> idList = ConvertUtil.str2Strings(ids);
        inventoryOrderService.batchDelInventoryOrder(idList);
        return JsonResult.success();
    }
    
    
    @PostMapping(MODEL + "/excel/export.json")
    @Function("inventoryOrder.exportDocument")
    @ResponseBody
    public JsonResult<String> export(HttpServletResponse response,InventoryOrderQuery condtion) {
        /**
         * 1)需要用你自己编写一个的excel模板
         * 2)通常excel导出需要关联更多数据，因此inventoryOrderService.queryByCondition方法经常不符合需求，需要重写一个为模板导出的查询
         * 3)参考ConsoleDictController来实现模板导入导出
         */
        String excelTemplate ="excelTemplates/cms/inventoryOrder/你的excel模板文件名字.xls";
        PageQuery<InventoryOrder> page = condtion.getPageQuery();
        //取出全部符合条件的
        page.setPageSize(Integer.MAX_VALUE);
        page.setPageNumber(1);
        page.setTotalRow(Integer.MAX_VALUE);
        //本次导出需要的数据
        List<InventoryOrder> list =inventoryOrderService.queryByCondition(page).getList();
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelTemplate)) {
            if(is==null) {
                throw new PlatformException("模板资源不存在："+excelTemplate);
            }
            FileItem item = fileService.createFileTemp("InventoryOrder_"+DateUtil.now("yyyyMMddHHmmss")+".xls");
            OutputStream os = item.openOutpuStream();
            Context context = new Context();
            context.putVar("list", list);
            JxlsHelper.getInstance().processTemplate(is, os, context);
            os.close();
            //下载参考FileSystemContorller
            return  JsonResult.success(item.getPath());
        } catch (IOException e) {
            throw new PlatformException(e.getMessage());
        }
        
    }
    
    @PostMapping(MODEL + "/excel/import.do")
    @Function("inventoryOrder.importDocument")
    @ResponseBody
    public JsonResult importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
           return JsonResult.fail();
        }
        InputStream ins = file.getInputStream();
        /*解析模板并导入到数据库里,参考DictConsoleContorller，使用jxls reader读取excel数据*/
        ins.close();
        return JsonResult.success();
    }
    
    

}
