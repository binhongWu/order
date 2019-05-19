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
 * PurchaseOrder 接口
 */
@Controller
public class PurchaseOrderController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/cms/purchaseOrder";


    @Autowired private PurchaseOrderService purchaseOrderService;
    
    @Autowired
    FileService fileService;

    /**
     * 采购订单页面
     * @return
     */
    @GetMapping(MODEL + "/index.do")
    @Function("purchaseOrder.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/cms/purchaseOrder/index.html") ;
        view.addObject("search", PurchaseOrderQuery.class.getName());
        return view;
    }

    /**
     * 采购订单页面数据（含搜索条件）
     * @param condtion
     * @return
     */
    @PostMapping(MODEL + "/list.json")
    @Function("purchaseOrder.query")
    @ResponseBody
    public JsonResult<PageQuery> list(PurchaseOrderQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        purchaseOrderService.queryByCondition(page);
        return JsonResult.success(page);
    }

    /**
     * 添加页面
     * @return
     */
    @GetMapping(MODEL + "/add.do")
    @Function("purchaseOrder.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/cms/purchaseOrder/add.html");
        return view;
    }

    /**
     * 添加数据页面
     * @param purchaseOrder
     * @return
     */
    @PostMapping(MODEL + "/add.json")
    @Function("purchaseOrder.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)PurchaseOrder purchaseOrder)
    {
        purchaseOrderService.save(purchaseOrder);
        return JsonResult.success();
    }

    /**
     * 编辑页面
     * @param orderId
     * @return
     */
    @GetMapping(MODEL + "/edit.do")
    @Function("purchaseOrder.edit")
    @ResponseBody
    public ModelAndView edit(Long orderId) {
        ModelAndView view = new ModelAndView("/cms/purchaseOrder/edit.html");
        PurchaseOrder purchaseOrder = purchaseOrderService.queryById(orderId);
        view.addObject("purchaseOrder", purchaseOrder);
        return view;
    }

    /**
     * 编辑保存
     * @param purchaseOrder
     * @return
     */
    @PostMapping(MODEL + "/update.json")
    @Function("purchaseOrder.update")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  PurchaseOrder purchaseOrder) {
        boolean success = purchaseOrderService.updateByList(purchaseOrder);
        if (success) {
            return JsonResult.success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }

    /**
     * 根据id 可批量删除
     * @param ids
     * @return
     */
    @PostMapping(MODEL + "/delete.json")
    @Function("purchaseOrder.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<String> idList = ConvertUtil.str2Strings(ids);
        purchaseOrderService.batchDelPurchaseOrder(idList);
        return JsonResult.success();
    }

    /**
     * 导出数据
     * @param response
     * @param condtion
     * @return
     */
    @PostMapping(MODEL + "/excel/export.json")
    @Function("purchaseOrder.exportDocument")
    @ResponseBody
    public JsonResult<String> export(HttpServletResponse response,PurchaseOrderQuery condtion) {
        /**
         * 1)需要用你自己编写一个的excel模板
         * 2)通常excel导出需要关联更多数据，因此purchaseOrderService.queryByCondition方法经常不符合需求，需要重写一个为模板导出的查询
         * 3)参考ConsoleDictController来实现模板导入导出
         */
        String excelTemplate ="excelTemplates/cms/purchaseOrder/purchase_order_export.xls";
        PageQuery<PurchaseOrder> page = condtion.getPageQuery();
        //取出全部符合条件的
        page.setPageSize(Integer.MAX_VALUE);
        page.setPageNumber(1);
        page.setTotalRow(Integer.MAX_VALUE);
        //本次导出需要的数据
        List<PurchaseOrder> list =purchaseOrderService.queryByCondition(page).getList();
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelTemplate)) {
            if(is==null) {
                throw new PlatformException("模板资源不存在："+excelTemplate);
            }
            FileItem item = fileService.createFileTemp("采购订单_"+DateUtil.now("yyyyMMddHHmmss")+".xls");
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

    /**
     * 初始导入，这时候采购订单为未完成
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(MODEL + "/excel/import.do")
    @Function("purchaseOrder.importDocument")
    @ResponseBody
    public JsonResult importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return JsonResult.fail();
        }
        InputStream ins = file.getInputStream();
        InputStream inputXML = Thread.currentThread().getContextClassLoader().getResourceAsStream("excelTemplates/cms/purchaseOrder/purchase_order_import.xml");
        XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
        InputStream inputXLS = ins;
        List<PurchaseOrder> datas = new ArrayList<>();
        Map beans = new HashMap();
        beans.put("list", datas);
        ReaderConfig.getInstance().setSkipErrors(true);
        XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
        List<XLSReadMessage> errors = readStatus.getReadMessages();
        if (!errors.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (XLSReadMessage msg : errors) {
                sb.append(parseXLSReadMessage(msg));
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
            return JsonResult.failMessage("解析excel出错:" + sb.toString());
        }
        try {
            this.purchaseOrderService.saveImport(datas);
            return JsonResult.success();
        } catch (Exception ex) {
            return JsonResult.failMessage(ex.getMessage());
        }
    }

    /**
     * 导入数据错误地方的定位
     * @param msg
     * @return
     */
    private String parseXLSReadMessage(XLSReadMessage msg) {
        String str = msg.getMessage();
        int start = "Can't read cell ".length();
        int end = str.indexOf("on");
        return str.substring(start, end);
    }

    /**
     * 二次导入
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(MODEL + "/excel/import2.do")
    @Function("purchaseOrder.importDocument")
    @ResponseBody
    public JsonResult importExcel2(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return JsonResult.fail();
        }
        InputStream ins = file.getInputStream();
        InputStream inputXML = Thread.currentThread().getContextClassLoader().getResourceAsStream("excelTemplates/cms/purchaseOrder/purchase_order_import2.xml");
        XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
        InputStream inputXLS = ins;
        List<PurchaseOrder> datas = new ArrayList<>();
        Map beans = new HashMap();
        beans.put("list", datas);
        ReaderConfig.getInstance().setSkipErrors(true);
        XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
        List<XLSReadMessage> errors = readStatus.getReadMessages();
        if (!errors.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (XLSReadMessage msg : errors) {
                sb.append(parseXLSReadMessage(msg));
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
            return JsonResult.failMessage("解析excel出错:" + sb.toString());
        }
        try {
            this.purchaseOrderService.saveImport2(datas);
            return JsonResult.success();
        } catch (Exception ex) {
            return JsonResult.failMessage(ex.getMessage());
        }
    }

    /**
     * ?
     * @param id
     * @return
     */
    @PostMapping(MODEL + "/purchase.do")
    @ResponseBody
    public JsonResult purchase(Long id){
        System.out.println("进入购买！");
        return JsonResult.success();
    }


    /** -------------------------   暂时没有用到的方法   -------------------------**/

        /**
         * 根据ID查找
         */
    @PostMapping(MODEL + "/queryById.json")
    @Function("purchaseOrder.query")
    @ResponseBody
    public JsonResult<PurchaseOrder> queryById(Object id){
        PurchaseOrder data = purchaseOrderService.queryById(id);
        return JsonResult.success(data);
    }

   
    @GetMapping(MODEL + "/view.json")
    @Function("purchaseOrder.query")
    @ResponseBody
    public JsonResult<PurchaseOrder>queryInfo(Long orderId) {
        PurchaseOrder purchaseOrder = purchaseOrderService.queryById( orderId);
        return  JsonResult.success(purchaseOrder);
    }


    
    

    

}
