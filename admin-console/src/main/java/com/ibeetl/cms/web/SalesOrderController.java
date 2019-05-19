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
 * 销售订单 接口
 */
@Controller
public class SalesOrderController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/cms/salesOrder";


    @Autowired private SalesOrderService salesOrderService;
    
    @Autowired
    FileService fileService;


    /**
     * 销售订单页面
     * @return
     */
    @GetMapping(MODEL + "/index.do")
    @Function("salesOrder.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/cms/salesOrder/index.html") ;
        view.addObject("search", SalesOrderQuery.class.getName());
        return view;
    }

    /**
     * 销售订单页面数据
     * @param condtion
     * @return
     */
    @PostMapping(MODEL + "/list.json")
    @Function("salesOrder.query")
    @ResponseBody
    public JsonResult<PageQuery> list(SalesOrderQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        salesOrderService.queryByCondition(page);
        return JsonResult.success(page);
    }

    /**
     * 添加页面
     * @return
     */
    @GetMapping(MODEL + "/add.do")
    @Function("salesOrder.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/cms/salesOrder/add.html");
        return view;
    }

    /**
     * 添加数据保存
     * @param salesOrder
     * @return
     */
    @PostMapping(MODEL + "/add.json")
    @Function("salesOrder.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)SalesOrder salesOrder)
    {
        salesOrderService.save(salesOrder);
        return JsonResult.success();
    }


    /**
     * 编辑页面
     * @param salesId
     * @return
     */
    @GetMapping(MODEL + "/edit.do")
    @Function("salesOrder.edit")
    @ResponseBody
    public ModelAndView edit(Long salesId) {
        ModelAndView view = new ModelAndView("/cms/salesOrder/edit.html");
        SalesOrder salesOrder = salesOrderService.queryById(salesId);
        view.addObject("salesOrder", salesOrder);
        return view;
    }

    /**
     * 编辑保存
     * @param salesOrder
     * @return
     */
    @PostMapping(MODEL + "/update.json")
    @Function("salesOrder.update")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  SalesOrder salesOrder) {
        boolean success = salesOrderService.update(salesOrder);
        if (success) {
            return JsonResult.success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }

    /**
     * 导出数据
     * @param response
     * @param condtion
     * @return
     */
    @PostMapping(MODEL + "/excel/export.json")
    @Function("salesOrder.exportDocument")
    @ResponseBody
    public JsonResult<String> export(HttpServletResponse response,SalesOrderQuery condtion) {
        /**
         * 1)需要用你自己编写一个的excel模板
         * 2)通常excel导出需要关联更多数据，因此salesOrderService.queryByCondition方法经常不符合需求，需要重写一个为模板导出的查询
         * 3)参考ConsoleDictController来实现模板导入导出
         */
        String excelTemplate ="excelTemplates/cms/salesOrder/sales_order_export.xls";
        PageQuery<SalesOrder> page = condtion.getPageQuery();
        //取出全部符合条件的
        page.setPageSize(Integer.MAX_VALUE);
        page.setPageNumber(1);
        page.setTotalRow(Integer.MAX_VALUE);
        //本次导出需要的数据
        List<SalesOrder> list =salesOrderService.queryByCondition(page).getList();
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelTemplate)) {
            if(is==null) {
                throw new PlatformException("模板资源不存在："+excelTemplate);
            }
            FileItem item = fileService.createFileTemp("销售订单_"+DateUtil.now("yyyyMMddHHmmss")+".xls");
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


    /** -------------------------   暂时没有用到的方法   -------------------------**/


        /**
         * 根据ID查找
         */
    @PostMapping(MODEL + "/queryById.json")
    @Function("salesOrder.query")
    @ResponseBody
    public JsonResult<SalesOrder> queryById(Object id){
        SalesOrder data = salesOrderService.queryById(id);
        return JsonResult.success(data);
    }






   
    @GetMapping(MODEL + "/view.json")
    @Function("salesOrder.query")
    @ResponseBody
    public JsonResult<SalesOrder>queryInfo(Long salesId) {
        SalesOrder salesOrder = salesOrderService.queryById( salesId);
        return  JsonResult.success(salesOrder);
    }

    @PostMapping(MODEL + "/delete.json")
    @Function("salesOrder.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<String> idList = ConvertUtil.str2Strings(ids);
        salesOrderService.batchDelSalesOrder(idList);
        return JsonResult.success();
    }
    
    

    
    @PostMapping(MODEL + "/excel/import.do")
    @Function("salesOrder.importDocument")
    @ResponseBody
    public JsonResult importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return JsonResult.fail();
        }
        InputStream ins = file.getInputStream();
        InputStream inputXML = Thread.currentThread().getContextClassLoader().getResourceAsStream("excelTemplates/cms/salesOrder/sales_order_import.xml");
        XLSReader mainReader = ReaderBuilder.buildFromXML( inputXML );
        InputStream inputXLS = ins;
        List<SalesOrder> datas = new ArrayList<>();
        Map beans = new HashMap();
        beans.put("list", datas);
        ReaderConfig.getInstance().setSkipErrors( true );
        XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
        List<XLSReadMessage>  errors = readStatus.getReadMessages();
        if(!errors.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for(XLSReadMessage msg:errors) {
                sb.append(parseXLSReadMessage(msg));
                sb.append(",");
            }
            sb.setLength(sb.length()-1);
            return JsonResult.failMessage("解析excel出错:"+sb.toString());
        }
        try {
            this.salesOrderService.saveImport(datas);
            return JsonResult.success();
        }catch(Exception ex) {
            return JsonResult.failMessage(ex.getMessage());
        }
    }

    private String parseXLSReadMessage(XLSReadMessage msg) {
        String str = msg.getMessage();
        int start = "Can't read cell ".length();
        int end = str.indexOf("on");
        return str.substring(start,end);
    }
    
    

}
