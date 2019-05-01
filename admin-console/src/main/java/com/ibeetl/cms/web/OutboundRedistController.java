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
 * OutboundRedist 接口
 */
@Controller
public class OutboundRedistController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/cms/outboundRedist";


    @Autowired private OutboundRedistService outboundRedistService;
    
    @Autowired
    FileService fileService;
    /* 页面 */

    @GetMapping(MODEL + "/index.do")
    @Function("outboundRedist.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/cms/outboundRedist/index.html") ;
        view.addObject("search", OutboundRedistQuery.class.getName());
        return view;
    }

    @GetMapping(MODEL + "/edit.do")
    @Function("outboundRedist.edit")
    @ResponseBody
    public ModelAndView edit(Long outRegistId) {
        ModelAndView view = new ModelAndView("/cms/outboundRedist/edit.html");
        OutboundRedist outboundRedist = outboundRedistService.queryById(outRegistId);
        view.addObject("outboundRedist", outboundRedist);
        return view;
    }

    @GetMapping(MODEL + "/add.do")
    @Function("outboundRedist.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/cms/outboundRedist/add.html");
        return view;
    }

    /* ajax json */

    @PostMapping(MODEL + "/list.json")
    @Function("outboundRedist.query")
    @ResponseBody
    public JsonResult<PageQuery> list(OutboundRedistQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        outboundRedistService.queryByCondition(page);
        return JsonResult.success(page);
    }

        /**
         * 根据ID查找
         */
    @PostMapping(MODEL + "/queryById.json")
    @Function("outboundRedist.query")
    @ResponseBody
    public JsonResult<OutboundRedist> queryById(Object id){
        OutboundRedist data = outboundRedistService.queryById(id);
        return JsonResult.success(data);
    }

    @PostMapping(MODEL + "/add.json")
    @Function("outboundRedist.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)OutboundRedist outboundRedist)
    {
        outboundRedistService.save(outboundRedist);
        return JsonResult.success();
    }

    @PostMapping(MODEL + "/update.json")
    @Function("outboundRedist.update")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  OutboundRedist outboundRedist) {
        boolean success = outboundRedistService.update(outboundRedist);
        if (success) {
            return JsonResult.success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }


   
    @GetMapping(MODEL + "/view.json")
    @Function("outboundRedist.query")
    @ResponseBody
    public JsonResult<OutboundRedist>queryInfo(Long outRegistId) {
        OutboundRedist outboundRedist = outboundRedistService.queryById( outRegistId);
        return  JsonResult.success(outboundRedist);
    }

    @PostMapping(MODEL + "/delete.json")
    @Function("outboundRedist.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<String> idList = ConvertUtil.str2Strings(ids);
        outboundRedistService.batchDelOutboundRedist(idList);
        return JsonResult.success();
    }
    
    
    @PostMapping(MODEL + "/excel/export.json")
    @Function("outboundRedist.exportDocument")
    @ResponseBody
    public JsonResult<String> export(HttpServletResponse response,OutboundRedistQuery condtion) {
        /**
         * 1)需要用你自己编写一个的excel模板
         * 2)通常excel导出需要关联更多数据，因此outboundRedistService.queryByCondition方法经常不符合需求，需要重写一个为模板导出的查询
         * 3)参考ConsoleDictController来实现模板导入导出
         */
        String excelTemplate ="excelTemplates/cms/outboundRedist/outbound_regist_export.xls";
        PageQuery<OutboundRedist> page = condtion.getPageQuery();
        //取出全部符合条件的
        page.setPageSize(Integer.MAX_VALUE);
        page.setPageNumber(1);
        page.setTotalRow(Integer.MAX_VALUE);
        //本次导出需要的数据
        List<OutboundRedist> list =outboundRedistService.queryByCondition(page).getList();
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelTemplate)) {
            if(is==null) {
                throw new PlatformException("模板资源不存在："+excelTemplate);
            }
            FileItem item = fileService.createFileTemp("出库登记信息_"+DateUtil.now("yyyyMMddHHmmss")+".xls");
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
    @Function("outboundRedist.importDocument")
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
