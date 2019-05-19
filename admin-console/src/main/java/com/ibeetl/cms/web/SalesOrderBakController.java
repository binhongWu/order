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
 * 销售订单（客户联） 接口
 */
@Controller
public class SalesOrderBakController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/cms/salesOrderBak";


    @Autowired private SalesOrderBakService salesOrderBakService;
    
    @Autowired
    FileService fileService;

    /**
     * 销售订单（客户联）页面
     * @return
     */
    @GetMapping(MODEL + "/index.do")
    @Function("salesOrderBak.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/cms/salesOrderBak/index.html") ;
        view.addObject("search", SalesOrderBakQuery.class.getName());
        return view;
    }

    /**
     * 销售订单（客户联）页面数据
     * @param condtion
     * @return
     */
    @PostMapping(MODEL + "/list.json")
    @Function("salesOrderBak.query")
    @ResponseBody
    public JsonResult<PageQuery> list(SalesOrderBakQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        salesOrderBakService.queryByCondition(page);
        return JsonResult.success(page);
    }

    /**
     * 退货记录页面
     * @param salesId
     * @return
     */
    @GetMapping(MODEL + "/edit.do")
    @Function("salesOrderBak.edit")
    @ResponseBody
    public ModelAndView edit(Long salesId) {
        ModelAndView view = new ModelAndView("/cms/salesOrderBak/edit.html");
        SalesOrderBak salesOrderBak = salesOrderBakService.queryById(salesId);
        view.addObject("salesOrderBak", salesOrderBak);
        return view;
    }

    /**
     * 退货申请数据保存
     * @param salesOrderBak
     * @return
     */
    @PostMapping(MODEL + "/applyReturn.json")
    @Function("salesOrderBak.update")
    @ResponseBody
    public JsonResult<String> applyReturn(@Validated(ValidateConfig.UPDATE.class)  SalesOrderBak salesOrderBak) {
        SalesOrderBak model = salesOrderBakService.getById(salesOrderBak.getSalesId());
        boolean success = salesOrderBakService.saveApplyReturn(model);
        if (success) {
            return JsonResult.success();
        } else {
            return JsonResult.failMessage("失败");
        }
    }

    /**
     * 购买完成后产生临时订单
     * @param salesOrderBak
     * @return
     */
    @PostMapping(MODEL + "/add.json")
    @Function("salesOrderBak.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)SalesOrderBak salesOrderBak)
    {
        boolean success = salesOrderBakService.save(salesOrderBak);
        if(success){
            boolean result = salesOrderBakService.saveOthersInfo(salesOrderBak);
            if(result){
                return JsonResult.success();
            }
        }
        return JsonResult.failMessage("购买失败，请检查订单信息、购买人信息重试");
    }


    /** -------------------------   暂时没有用到的方法   -------------------------**/

    /**
     * 添加页面
     * @return
     */
    @GetMapping(MODEL + "/add.do")
    @Function("salesOrderBak.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/cms/salesOrderBak/add.html");
        return view;
    }







    /* ajax json */



        /**
         * 根据ID查找
         */
    @PostMapping(MODEL + "/queryById.json")
    @Function("salesOrderBak.query")
    @ResponseBody
    public JsonResult<SalesOrderBak> queryById(Object id){
        SalesOrderBak data = salesOrderBakService.queryById(id);
        return JsonResult.success(data);
    }



    @PostMapping(MODEL + "/update.json")
    @Function("salesOrderBak.update")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  SalesOrderBak salesOrderBak) {
        boolean success = salesOrderBakService.update(salesOrderBak);
        if (success) {
            return JsonResult.success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }


   
    @GetMapping(MODEL + "/view.json")
    @Function("salesOrderBak.query")
    @ResponseBody
    public JsonResult<SalesOrderBak>queryInfo(Long salesId) {
        SalesOrderBak salesOrderBak = salesOrderBakService.queryById( salesId);
        return  JsonResult.success(salesOrderBak);
    }

    @PostMapping(MODEL + "/delete.json")
    @Function("salesOrderBak.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<String> idList = ConvertUtil.str2Strings(ids);
        salesOrderBakService.batchDelSalesOrderBak(idList);
        return JsonResult.success();
    }
    

}
