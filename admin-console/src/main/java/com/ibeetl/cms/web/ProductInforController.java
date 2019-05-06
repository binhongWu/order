package com.ibeetl.cms.web;

import com.ibeetl.admin.core.annotation.Function;
import com.ibeetl.admin.core.file.FileItem;
import com.ibeetl.admin.core.file.FileService;
import com.ibeetl.admin.core.util.ConvertUtil;
import com.ibeetl.admin.core.util.DateUtil;
import com.ibeetl.admin.core.util.PlatformException;
import com.ibeetl.admin.core.util.ValidateConfig;
import com.ibeetl.admin.core.web.JsonResult;
import com.ibeetl.cms.entity.ProductInfor;
import com.ibeetl.cms.service.ProductInforService;
import com.ibeetl.cms.web.query.ProductInforQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.sql.core.engine.PageQuery;
import org.jxls.common.Context;
import org.jxls.reader.*;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProductInfor 接口
 */
@Controller
public class ProductInforController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/cms/productInfor";


    @Autowired private ProductInforService productInforService;
    
    @Autowired
    FileService fileService;
    /* 页面 */

    @GetMapping(MODEL + "/index.do")
    @Function("productInfor.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/cms/productInfor/index.html") ;
        view.addObject("search", ProductInforQuery.class.getName());
        return view;
    }

    /**
     * 购买首页 展示所有绘本信息
     * @return
     */
    @GetMapping(MODEL + "/productInforIndex.do")
    @ResponseBody
    public ModelAndView productInforIndex() {
        ModelAndView view = new ModelAndView("/cms/productInfor/productInforIndex.html") ;
        return view;
    }

    /**
     * 购买首页 数据
     * @param condtion
     * @return
     */
    @PostMapping(MODEL + "/productInforIndexList.json")
    @ResponseBody
    public JsonResult<PageQuery> productInforIndexList(ProductInforQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        productInforService.queryByCondition(page);
        return JsonResult.success(page);
    }

    @PostMapping(MODEL + "/findDetailInfoById.json")
    @ResponseBody
    public JsonResult<ProductInfor> findDetailInfoById(Long id){
        ProductInfor data = productInforService.getById(id);
        return JsonResult.success(data);
    }



    @GetMapping(MODEL + "/edit.do")
    @Function("productInfor.edit")
    @ResponseBody
    public ModelAndView edit(Long id) {
        ModelAndView view = new ModelAndView("/cms/productInfor/edit.html");
        ProductInfor productInfor = productInforService.queryById(id);
        List<FileItem> fileItems = fileService.queryByBiz("ProductInfo",productInfor.getPicture());
        if(fileItems.size() > 0){
            FileItem fileItem = fileItems.get(0);
            productInfor.setPictureUrl(fileItem.getPath());
        }
        view.addObject("productInfor", productInfor);
        return view;
    }

    @GetMapping(MODEL + "/add.do")
    @Function("productInfor.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/cms/productInfor/add.html");
        return view;
    }

    /* ajax json */

    @PostMapping(MODEL + "/list.json")
    @Function("productInfor.query")
    @ResponseBody
    public JsonResult<PageQuery> list(ProductInforQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        productInforService.queryByCondition(page);
        return JsonResult.success(page);
    }

    @GetMapping(MODEL + "/statistics.do")
    @Function("productInfor.query")
    @ResponseBody
    public ModelAndView statisticsIndex() {
        ModelAndView view = new ModelAndView("/cms/wareHouse/statisticsIndex.html") ;
        return view;
    }
    @PostMapping(MODEL + "/statistics.json")
    @Function("productInfor.query")
    @ResponseBody
    public JsonResult<List<ProductInfor>> statistics(){
        List<ProductInfor> statisticsList = productInforService.statistics();
        return JsonResult.success(statisticsList);
    }


        /**
         * 根据ID查找
         */
    @PostMapping(MODEL + "/queryById.json")
    @Function("productInfor.query")
    @ResponseBody
    public JsonResult<ProductInfor> queryById(Object id){
        ProductInfor data = productInforService.queryById(id);
        return JsonResult.success(data);
    }

    @PostMapping(MODEL + "/add.json")
    @Function("productInfor.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)ProductInfor productInfor)
    {
        productInforService.save(productInfor);
        return JsonResult.success();
    }

    @PostMapping(MODEL + "/update.json")
    @Function("productInfor.update")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  ProductInfor productInfor) {
        boolean success = productInforService.update(productInfor);
        if (success) {
            return JsonResult.success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }


   
    @GetMapping(MODEL + "/view.json")
    @Function("productInfor.query")
    @ResponseBody
    public JsonResult<ProductInfor>queryInfo(Long id) {
        ProductInfor productInfor = productInforService.queryById( id);
        return  JsonResult.success(productInfor);
    }

    @PostMapping(MODEL + "/delete.json")
    @Function("productInfor.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<String> idList = ConvertUtil.str2Strings(ids);
        productInforService.batchDelProductInfor(idList);
        return JsonResult.success();
    }
    
    
    @PostMapping(MODEL + "/excel/export.json")
    @Function("productInfor.exportDocument")
    @ResponseBody
    public JsonResult<String> export(HttpServletResponse response,ProductInforQuery condtion) {
        /**
         * 1)需要用你自己编写一个的excel模板
         * 2)通常excel导出需要关联更多数据，因此productInforService.queryByCondition方法经常不符合需求，需要重写一个为模板导出的查询
         * 3)参考ConsoleDictController来实现模板导入导出
         */
        String excelTemplate ="excelTemplates/cms/productInfor/product_info_export.xls";
        PageQuery<ProductInfor> page = condtion.getPageQuery();
        //取出全部符合条件的
        page.setPageSize(Integer.MAX_VALUE);
        page.setPageNumber(1);
        page.setTotalRow(Integer.MAX_VALUE);
        //本次导出需要的数据
        List<ProductInfor> list =productInforService.queryByCondition(page).getList();
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelTemplate)) {
            if(is==null) {
                throw new PlatformException("模板资源不存在："+excelTemplate);
            }
            FileItem item = fileService.createFileTemp("绘本信息_"+DateUtil.now("yyyyMMddHHmmss")+".xls");
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
    @Function("productInfor.importDocument")
    @ResponseBody
    public JsonResult importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return JsonResult.fail();
        }
        InputStream ins = file.getInputStream();
        InputStream inputXML = Thread.currentThread().getContextClassLoader().getResourceAsStream("excelTemplates/cms/productInfor/product_info_import.xml");
        XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
        InputStream inputXLS = ins;
        List<ProductInfor> datas = new ArrayList<>();
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
            this.productInforService.saveImport(datas);
            return JsonResult.success();
        } catch (Exception ex) {
            return JsonResult.failMessage(ex.getMessage());
        }
    }

    private String parseXLSReadMessage(XLSReadMessage msg) {
        String str = msg.getMessage();
        int start = "Can't read cell ".length();
        int end = str.indexOf("on");
        return str.substring(start, end);
    }

    

}
