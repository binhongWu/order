package com.ibeetl.cms.web;

import com.ibeetl.admin.core.annotation.Function;
import com.ibeetl.admin.core.file.FileItem;
import com.ibeetl.admin.core.file.FileService;
import com.ibeetl.admin.core.util.ConvertUtil;
import com.ibeetl.admin.core.util.DateUtil;
import com.ibeetl.admin.core.util.PlatformException;
import com.ibeetl.admin.core.util.ValidateConfig;
import com.ibeetl.admin.core.web.JsonResult;
import com.ibeetl.cms.entity.OutboundRedist;
import com.ibeetl.cms.entity.ProductInfor;
import com.ibeetl.cms.service.OutboundRedistService;
import com.ibeetl.cms.service.ProductInforService;
import com.ibeetl.cms.web.query.ProductInforQuery;
import com.ibeetl.cms.web.query.ProductInforQuery2;
import com.ibeetl.cms.web.query.RankInfoQuery;
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
 * 中英文绘本信息 接口
 */
@Controller
public class ProductInforController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/cms/productInfor";


    @Autowired private ProductInforService productInforService;
    @Autowired
    private OutboundRedistService outboundRedistService;
    
    @Autowired
    FileService fileService;
    /* 页面 */

    /**
     * 中英文绘本信息页面
     * @return
     */
    @GetMapping(MODEL + "/index.do")
    @Function("productInfor.query")
    @ResponseBody
    /*ModelAndView指模型和视图的集合*/
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/cms/productInfor/index.html") ;
        /*
        设置modle，其中第一个参数是name，第二个参数是对象。
         */
        view.addObject("search", ProductInforQuery.class.getName());
        return view;
    }

    /**
     * 中英文绘本信息页面数据（含搜索条件）
     * @param condtion
     * @return
     */
    @PostMapping(MODEL + "/list.json")
    @Function("productInfor.query")
    @ResponseBody
    public JsonResult<PageQuery> list(ProductInforQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        productInforService.queryByCondition(page);
        return JsonResult.success(page);
    }

    /**
     * 添加页面
     * @return
     */
    @GetMapping(MODEL + "/add.do")
    @Function("productInfor.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/cms/productInfor/add.html");
        return view;
    }

    /**
     * 添加数据保存
     * @param productInfor
     * @return
     */
    @PostMapping(MODEL + "/add.json")
    @Function("productInfor.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)ProductInfor productInfor)
    {
        productInforService.save(productInfor);
        return JsonResult.success();
    }

    /**
     * 编辑页面
     * @param id
     * @return
     */
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

    /**
     * 编辑页面保存
     * @param productInfor
     * @return
     */
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

    /**
     * 根据id 可批量删除
     * @param ids
     * @return
     */
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

    /**
     * 导出数据
     * @param response
     * @param condtion
     * @return
     */
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
        //取出全部符合条件的  设置page对象的第一页就要取出全部数据
        page.setPageSize(Integer.MAX_VALUE);
        page.setPageNumber(1);
        page.setTotalRow(Integer.MAX_VALUE);
        //本次导出需要的数据
        List<ProductInfor> list =productInforService.queryByCondition(page).getList();
        //System.out.println(list);
        // 使用JXLS 语法来把数据弄到execl模板，然后下载文件
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelTemplate)) {
            if(is==null) {
                throw new PlatformException("模板资源不存在："+excelTemplate);
            }
            FileItem item = fileService.createFileTemp("绘本信息_"+DateUtil.now("yyyyMMddHHmmss")+".xls");
            // 使用流的方式读取文件内容 并下载
            OutputStream os = item.openOutpuStream();
            Context context = new Context();
            context.putVar("list", list);
            JxlsHelper.getInstance().processTemplate(is, os, context);
            os.close();
            //下载参考FileSystemContorller
            // 得到文件路径，然后前端会进行下载
            return  JsonResult.success(item.getPath());
        } catch (IOException e) {
            throw new PlatformException(e.getMessage());
        }

    }

    /**
     * 导入数据
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(MODEL + "/excel/import.do")
    @Function("productInfor.importDocument")
    @ResponseBody
    public JsonResult importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return JsonResult.fail();
        }
        //根据文件流的方式来获取导入的文件
        InputStream ins = file.getInputStream();
        InputStream inputXML = Thread.currentThread().getContextClassLoader().getResourceAsStream("excelTemplates/cms/productInfor/product_info_import.xml");
        XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
        InputStream inputXLS = ins;
        List<ProductInfor> datas = new ArrayList<>();
        Map beans = new HashMap();
        beans.put("list", datas);
        ReaderConfig.getInstance().setSkipErrors(true);
        //JXLS 读取上传文件，然后和导入的XML文件相呼应
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
            //读取后的数据进行保存
            this.productInforService.saveImport(datas);
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
     * 中英文绘本信息购买页面
     * @return
     */
    @GetMapping(MODEL + "/productInforIndex.do")
    @Function("productInfor.purchase")
    @ResponseBody
    public ModelAndView productInforIndex() {
        ModelAndView view = new ModelAndView("/cms/productInfor/productInforIndex.html") ;
        view.addObject("search", ProductInforQuery.class.getName());
        return view;
    }

    /**
     *中英文绘本信息购买页面数据（含搜索条件）
     * @param condtion
     * @return
     */
    @PostMapping(MODEL + "/productInforIndexList.json")
    @Function("productInfor.purchase")
    @ResponseBody
    public JsonResult<PageQuery> productInforIndexList(ProductInforQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        productInforService.queryByCondition(page);
        return JsonResult.success(page);
    }

    /**
     * 中英文绘本信息购买页面查看详情 页面+数据
     * @param id
     * @return
     */
    @GetMapping(MODEL + "/getInfo.do")
    @Function("productInfor.purchase")
    @ResponseBody
    public ModelAndView getInfo(Long id) {
        ModelAndView view = new ModelAndView("/cms/productInfor/getInfo.html");
        ProductInfor productInfor = productInforService.queryById(id);
        //System.out.println(productInfor);
        List<FileItem> fileItems = fileService.queryByBiz("ProductInfo",productInfor.getPicture());
        if(fileItems.size() > 0){
            FileItem fileItem = fileItems.get(0);
            productInfor.setPictureUrl(fileItem.getPath());
        }
        view.addObject("productInfor", productInfor);
        return view;
    }

    /**
     * 中英文绘本信息购买页面购买 页面+数据
     * @param id
     * @return
     */
    @GetMapping(MODEL + "/purchase.do")
    @Function("productInfor.purchase")
    @ResponseBody
    public ModelAndView purchase(Long id) {
        ModelAndView view = new ModelAndView("/cms/productInfor/purchase.html");
        ProductInfor productInfor = productInforService.queryById(id);
        List<FileItem> fileItems = fileService.queryByBiz("ProductInfo",productInfor.getPicture());
        if(fileItems.size() > 0){
            FileItem fileItem = fileItems.get(0);
            productInfor.setPictureUrl(fileItem.getPath());
        }
        view.addObject("productInfor", productInfor);
        return view;
    }

    /**
     *
     * @return
     */
    @GetMapping(MODEL + "/rankInfo.do")
    @Function("rankInfo.query")
    @ResponseBody
    public ModelAndView rankInfo(){
        ModelAndView view = new ModelAndView("/cms/productInfor/rankInfo.html");
        view.addObject("search", RankInfoQuery.class.getName());
        return view;
    }

    @PostMapping(MODEL + "/rankInfo.json")
    @Function("rankInfo.query")
    @ResponseBody
    public JsonResult<PageQuery> rankInfoList(RankInfoQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        outboundRedistService.rankInfoList(page);
//        productInforService.queryByCondition(page);
        return JsonResult.success(page);
    }


    /** -------------------------   暂时没有用到的方法   -------------------------**/

    /**
     * 仓库盘点页面
     * @return
     */
    @GetMapping(MODEL + "/statistics.do")
    @Function("productInfor.query")
    @ResponseBody
    public ModelAndView statisticsIndex() {
        ModelAndView view = new ModelAndView("/cms/wareHouse/statisticsIndex.html") ;
        view.addObject("search", ProductInforQuery2.class.getName());
        return view;
    }

    /**
     * 仓库盘点数据
     * @return
     */
    @PostMapping(MODEL + "/statistics.json")
    @Function("productInfor.query")
    @ResponseBody
    public JsonResult<PageQuery> statistics(ProductInforQuery2 condtion){
        PageQuery page = condtion.getPageQuery();
        productInforService.statistics(page);
        return JsonResult.success(page);
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






   
    @GetMapping(MODEL + "/view.json")
    @Function("productInfor.query")
    @ResponseBody
    public JsonResult<ProductInfor>queryInfo(Long id) {
        ProductInfor productInfor = productInforService.queryById( id);
        return  JsonResult.success(productInfor);
    }


    
    


    

}
