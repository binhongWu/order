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
 * 供应商信息接口
 */
@Controller
public class SupplieInforController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/cms/supplieInfor";


    @Autowired private SupplieInforService supplieInforService;
    
    @Autowired
    FileService fileService;

    /**
     * 供应商信息页面
     * @return
     */
    @GetMapping(MODEL + "/index.do")
    @Function("supplieInfor.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/cms/supplieInfor/index.html") ;
        view.addObject("search", SupplieInforQuery.class.getName());
        return view;
    }

    /**
     * 供应商信息页面数据
     * @param condtion
     * @return
     */
    @PostMapping(MODEL + "/list.json")
    @Function("supplieInfor.query")
    @ResponseBody
    public JsonResult<PageQuery> list(SupplieInforQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        supplieInforService.queryByCondition(page);
        return JsonResult.success(page);
    }

    /**
     * 添加页面
     * @return
     */
    @GetMapping(MODEL + "/add.do")
    @Function("supplieInfor.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/cms/supplieInfor/add.html");
        return view;
    }

    /**
     * 添加页面数据
     * @param supplieInfor
     * @return
     */
    @PostMapping(MODEL + "/add.json")
    @Function("supplieInfor.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)SupplieInfor supplieInfor)
    {
        supplieInforService.save(supplieInfor);
        return JsonResult.success();
    }

    /**
     * 编辑页面
     * @param supplierId
     * @return
     */
    @GetMapping(MODEL + "/edit.do")
    @Function("supplieInfor.edit")
    @ResponseBody
    public ModelAndView edit(Long supplierId) {
        ModelAndView view = new ModelAndView("/cms/supplieInfor/edit.html");
        SupplieInfor supplieInfor = supplieInforService.queryById(supplierId);
        view.addObject("supplieInfor", supplieInfor);
        return view;
    }

    /**
     * 编辑保存
     * @param supplieInfor
     * @return
     */
    @PostMapping(MODEL + "/update.json")
    @Function("supplieInfor.update")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  SupplieInfor supplieInfor) {
        boolean success = supplieInforService.update(supplieInfor);
        if (success) {
            return JsonResult.success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }

    /**
     * 根据ID批量删除
     * @param ids
     * @return
     */
    @PostMapping(MODEL + "/delete.json")
    @Function("supplieInfor.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<String> idList = ConvertUtil.str2Strings(ids);
        supplieInforService.batchDelSupplieInfor(idList);
        return JsonResult.success();
    }

    /**
     * 导出数据
     * @param response
     * @param condtion
     * @return
     */
    @PostMapping(MODEL + "/excel/export.json")
    @Function("supplieInfor.exportDocument")
    @ResponseBody
    public JsonResult<String> export(HttpServletResponse response,SupplieInforQuery condtion) {
        /**
         * 1)需要用你自己编写一个的excel模板
         * 2)通常excel导出需要关联更多数据，因此supplieInforService.queryByCondition方法经常不符合需求，需要重写一个为模板导出的查询
         * 3)参考ConsoleDictController来实现模板导入导出
         */
        String excelTemplate ="excelTemplates/cms/supplieInfor/supplie_Info_export.xls";
        PageQuery<SupplieInfor> page = condtion.getPageQuery();
        //取出全部符合条件的
        page.setPageSize(Integer.MAX_VALUE);
        page.setPageNumber(1);
        page.setTotalRow(Integer.MAX_VALUE);
        //本次导出需要的数据
        List<SupplieInfor> list =supplieInforService.queryByCondition(page).getList();
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelTemplate)) {
            if(is==null) {
                throw new PlatformException("模板资源不存在："+excelTemplate);
            }
            FileItem item = fileService.createFileTemp("SupplieInfor_"+DateUtil.now("yyyyMMddHHmmss")+".xls");
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
    @Function("supplieInfor.query")
    @ResponseBody
    public JsonResult<SupplieInfor> queryById(Object id){
        SupplieInfor data = supplieInforService.queryById(id);
        return JsonResult.success(data);
    }





   
    @GetMapping(MODEL + "/view.json")
    @Function("supplieInfor.query")
    @ResponseBody
    public JsonResult<SupplieInfor>queryInfo(Long supplierId) {
        SupplieInfor supplieInfor = supplieInforService.queryById( supplierId);
        return  JsonResult.success(supplieInfor);
    }


    
    

    
    @PostMapping(MODEL + "/excel/import.do")
    @Function("supplieInfor.importDocument")
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
