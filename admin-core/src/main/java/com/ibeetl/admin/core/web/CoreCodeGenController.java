package com.ibeetl.admin.core.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.*;
import java.util.Map.Entry;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.dao.CoreFunctionDao;
import com.ibeetl.admin.core.entity.CoreDict;
import com.ibeetl.admin.core.entity.CoreFunction;
import com.ibeetl.admin.core.entity.CoreMenu;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibeetl.admin.core.gen.AutoGen;
import com.ibeetl.admin.core.gen.HtmlGen;
import com.ibeetl.admin.core.gen.JSGen;
import com.ibeetl.admin.core.gen.JavaCodeGen;
import com.ibeetl.admin.core.gen.MavenProjectTarget;
import com.ibeetl.admin.core.gen.MdGen;
import com.ibeetl.admin.core.gen.WebTarget;
import com.ibeetl.admin.core.gen.model.Attribute;
import com.ibeetl.admin.core.gen.model.Entity;
import com.ibeetl.admin.core.service.CoreCodeGenService;
import com.ibeetl.admin.core.util.PlatformException;

@Controller
public class CoreCodeGenController {
	private final Log log = LogFactory.getLog(this.getClass());
	private static final String MODEL = "/core/codeGen";

	@Autowired
	CoreCodeGenService codeGenService;

	@Autowired
	 SQLManager sqlManager;


	@GetMapping(MODEL + "/index.do")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/core/codeGen/index.html");
		return view;

	}

	@GetMapping(MODEL + "/tableDetail.do")
	public ModelAndView tableDetail(String table) {
		ModelAndView view = new ModelAndView("/core/codeGen/edit.html");
		Entity entity = codeGenService.getEntityInfo(table);
		view.addObject("entity", entity);
		return view;

	}
	
	@GetMapping(MODEL + "/project.do")
    public ModelAndView project() {
        ModelAndView view = new ModelAndView("/core/codeGen/project.html");
        File file = new  File(MavenProjectTarget.detectRootPath());
        String root = file.getParent();
        //设置生成项目为当前运行项目的上一级项目
        view.addObject("path",root+File.separator+"sample");
        view.addObject("basePackage","com.corp.xxx");
        return view;
      
    }
	
    @PostMapping(MODEL + "/projectGen.json")
    @ResponseBody
    public JsonResult project(String path,String basePackage,String includeConsole) throws IOException {
        //includeConsole 当前版本忽略，总是添加一个系统管理功能，可以在pom中移除console
        
        //生成maven项目结构
        File maven = new  File(path);
        maven.mkdirs();
        File src = new File(maven,"src");
        src.mkdirs();
        File main = new File(src,"main");
        main.mkdir();
        File test = new File(src,"test");
        test.mkdir();
        File javsSource = new File(main,"java"); 
        javsSource.mkdir();
        File resource = new File(main,"resources"); 
        resource.mkdir();
        File sql = new File(resource,"sql"); 
        sql.mkdir();
        File staticFile = new File(resource,"static"); 
        staticFile.mkdir();
        File templatesFile = new File(resource,"templates"); 
        templatesFile.mkdir();
        
        String codePath = basePackage.replace(".", "/");
        File codeFile = new File(javsSource,codePath);
        codeFile.mkdirs();
        Configuration conf = Configuration.defaultConfiguration();
        String tempalteRoot = "codeTemplate/maven/";
        ClasspathResourceLoader loader = new ClasspathResourceLoader(Thread.currentThread().getContextClassLoader(),tempalteRoot);
        GroupTemplate gt = new GroupTemplate(loader,conf);
        FileWriter fw = null;
        
        //先生成入口程序
        Template mainJavaTempalte = gt.getTemplate("/main.java");
        mainJavaTempalte.binding("basePackage", basePackage);
        fw = new FileWriter(new File(codeFile,"MainApplication.java"));
        mainJavaTempalte.renderTo(fw);
       
        
        //生成pom文件
        Template pomTemplate = gt.getTemplate("/pomTemplate.xml");
        int index = basePackage.lastIndexOf(".");
        String projectGrop = basePackage.substring(0, index);
        String projectName = basePackage.substring(index+1);
        pomTemplate.binding("group", projectGrop);
        pomTemplate.binding("project", projectName);
        fw = new FileWriter(new File(maven,"pom.xml"));
        pomTemplate.renderTo(fw);
        
        //复制当前项目的配置文件
        File config = copy(resource,"application.properties");
        copy(resource,"beetl.properties");
        copy(resource,"btsql-ext.properties");
        copy(resource,"banner.txt");
   
        return JsonResult.success();
    }
    
    
    private File copy(File root,String fileName) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream(fileName);
        if(input==null) {
            log.info("copy "+fileName+" error,不存在"); 
            return null;
        }
        
        File target = new File(root,fileName);
        FileOutputStream output = new FileOutputStream(target);
        try {
                
            byte[] buf = new byte[1024];        
            int bytesRead;        
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
         } finally {
             input.close();
             output.close();
         }
        return target;
    }
	
	
	

	@PostMapping(MODEL + "/table.json")
	@ResponseBody
	public JsonResult<List<Entity>> getTable() {
		List<Entity> list = codeGenService.getAllEntityInfo();
		return JsonResult.success(list);
	}

	@PostMapping(MODEL + "/tableDetail.json")
	@ResponseBody
	public JsonResult<Entity> getInfo(String table) {
		Entity entity = codeGenService.getEntityInfo(table);
		if (entity == null) {
			JsonResult.failMessage("表不存在");
		}
		return JsonResult.success(entity);

	}

	@PostMapping(MODEL + "/gen.json")
	@ResponseBody
	public JsonResult gen(EntityInfo data,String path) {
		Entity  entity = getEntitiyInfo(data);
		String genJavaFlag=data.getGenJavaFlag();
		String genJavaEntityFlag=data.getGenJavaEntityFlag();
		String urlBase = data.getUrlBase();
		String basePackage = data.getBasePackage();
        String tableComment = sqlManager.execute(new SQLReady(" SELECT TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES  WHERE TABLE_NAME = ? ",entity.getTableName()),String.class).get(0);

		MavenProjectTarget target = new MavenProjectTarget(entity, basePackage);
		//生成到path目录下，按照maven工程解构生成
		File file = new File(path);
		if(!file.exists()) {
		    throw new PlatformException("路径不存在 "+path);
		}
		target.setTargetPath(path);
		target.setUrlBase(urlBase);

        //字典类型转换
        try{
            for ( Attribute attribute  : entity.getList()) {
                if (!attribute.getDictType().isEmpty()){
					//字典类型字段 时间查询处理  TYPE_DATE_BETWEEN = 2
					if(StringUtils.containsIgnoreCase(attribute.getDictType(),"date")){
						attribute.setDictType("");
						attribute.setQuertType("TYPE_DATE_BETWEEN");
						continue;
					}
                    /*获取必要信息*/
                    String dictType = attribute.getDictType();
                    String dictTypeName = StringUtils.lowerCase(entity.getTableName()+"_"+attribute.getName());
                    attribute.setDictType(dictTypeName);
                    /*开始处理*/
                    dictType=StringUtils.remove(dictType," ");
                    dictType=StringUtils.replace(dictType,"，",",");
                    dictType=StringUtils.replace(dictType,"：",":");
                    String[] dictList = StringUtils.split(dictType,",");
                    int i = 1;
                    for (String dict:dictList) {
                        String[] K_V = StringUtils.split(dict,":");
                        String value = K_V[0];
                        String key = K_V[1];
                        //判断如果字典已存在 则不再生成
						String dictNum = sqlManager.execute(new SQLReady(" select count(1) from core_dict where type  = ? ",dictTypeName),String.class).get(0);
						if(Long.valueOf(dictNum) > 0){
							break;
						}
                        CoreDict coreDict = new CoreDict();
                        coreDict.setValue(value);
                        coreDict.setName(key);
                        coreDict.setType(dictTypeName);
                        coreDict.setTypeName(tableComment+"-"+attribute.getDisplayName());
                        coreDict.setSort(i++);
                        coreDict.setDelFlag(0);
                        coreDict.setCreateTime(new Date());
                        coreDict.setRemark(entity.getSystem());
                        sqlManager.insert(coreDict);
                    }
                }
            }
        }catch (Exception e){
            throw new PlatformException("字典类型转换失败，请先修改");
        }

        //代码生成
		//根据选择进行生成对应文件代码
		if(StringUtils.isNotEmpty(genJavaEntityFlag)){
			JavaCodeGen javaGen = new JavaCodeGen(basePackage, entity);
			entity.setGenFlag(true);
			javaGen.make(target, entity);
			return JsonResult.success();
		}
		else if(StringUtils.isNotEmpty(genJavaFlag)){
			JavaCodeGen javaGen = new JavaCodeGen(basePackage, entity);
			javaGen.make(target, entity);
			return JsonResult.success();
		}
		else{
			JSGen jsGen = new JSGen();
			jsGen.make(target, entity);

			HtmlGen htmlGen = new HtmlGen();
			htmlGen.make(target, entity);

			JavaCodeGen javaGen = new JavaCodeGen(basePackage, entity);
			javaGen.make(target, entity);

			MdGen mdGen = new MdGen();
			mdGen.make(target, entity);
		}

        //生成菜单和功能点
		//判断如果菜单已生成则 不再生成
		String functionNum = sqlManager.execute(new SQLReady(" select count(1) from core_function where code = ? ",entity.getCode()),String.class).get(0);
		if(Long.valueOf(functionNum) == 0){
			CoreFunction coreFunction = new CoreFunction();
			coreFunction.setCode(entity.getCode());
			coreFunction.setName(tableComment);
			coreFunction.setCreateTime(new Date());
			coreFunction.setAccessUrl("/"+urlBase+"/"+entity.getCode()+"/index.do");
			coreFunction.setParentId(0L);
			coreFunction.setType("FN0");
			sqlManager.insert(coreFunction,true);

			Long parent_id = coreFunction.getId();

			coreFunction.setCode(entity.getCode()+".query");
			coreFunction.setName(tableComment+"查询");
			coreFunction.setAccessUrl(null);
			coreFunction.setParentId(parent_id);
			sqlManager.insert(coreFunction);

			coreFunction.setCode(entity.getCode()+".add");
			coreFunction.setName(tableComment+"新增");
			coreFunction.setAccessUrl(null);
			coreFunction.setParentId(parent_id);
			sqlManager.insert(coreFunction);

			coreFunction.setCode(entity.getCode()+".update");
			coreFunction.setName(tableComment+"更新");
			coreFunction.setAccessUrl(null);
			coreFunction.setParentId(parent_id);
			sqlManager.insert(coreFunction);

			coreFunction.setCode(entity.getCode()+".edit");
			coreFunction.setName(tableComment+"更新按钮");
			coreFunction.setAccessUrl(null);
			coreFunction.setParentId(parent_id);
			sqlManager.insert(coreFunction);

			coreFunction.setCode(entity.getCode()+".delete");
			coreFunction.setName(tableComment+"删除");
			coreFunction.setAccessUrl(null);
			coreFunction.setParentId(parent_id);
			sqlManager.insert(coreFunction);

			coreFunction.setCode(entity.getCode()+".del");
			coreFunction.setName(tableComment+"删除按钮");
			coreFunction.setAccessUrl(null);
			coreFunction.setParentId(parent_id);
			sqlManager.insert(coreFunction);

			if (entity.isIncludeExcel()){
				coreFunction.setCode(entity.getCode()+".importDocument");
				coreFunction.setName(tableComment+"导入");
				coreFunction.setAccessUrl(null);
				coreFunction.setParentId(parent_id);
				sqlManager.insert(coreFunction);

				coreFunction.setCode(entity.getCode()+".exportDocument");
				coreFunction.setName(tableComment+"导出");
				coreFunction.setAccessUrl(null);
				coreFunction.setParentId(parent_id);
				sqlManager.insert(coreFunction);
			}
			CoreMenu coreMenu = new CoreMenu();
			coreMenu.setCode(tableComment);
			coreMenu.setName(tableComment);
			coreMenu.setCreateTime(new Date());
			coreMenu.setFunctionId(parent_id);
			coreMenu.setType("MENU_M");
			coreMenu.setParentMenuId(0L);
			coreMenu.setSeq(1);
			sqlManager.insert(coreMenu);
		}

		return JsonResult.success();

	}
	
	@PostMapping(MODEL + "/getPath.json")
    @ResponseBody
    public JsonResult<String> getPath() {
	    String path = MavenProjectTarget.detectRootPath();
	    return JsonResult.success(path);
	}

	@PostMapping(MODEL + "/html.json")
	@ResponseBody
	public JsonResult<Map<String, String>> html(EntityInfo data) {
		
		Entity entity = getEntitiyInfo(data);
		String urlBase = data.getUrlBase();
		String basePackage = data.getBasePackage();
		WebTarget webTarget = new WebTarget(entity, basePackage);
		webTarget.setUrlBase(urlBase);
		HtmlGen htmlGen = new HtmlGen();
		htmlGen.make(webTarget, entity);
		Map<String, String> content = new HashMap<String, String>();
		for (Entry<Object, String> entry : webTarget.map.entrySet()) {
			AutoGen gen = (AutoGen) entry.getKey();
			String code = entry.getValue();
			content.put(gen.getName(), code);
		}
		return JsonResult.success(content);

	}
	
	@PostMapping(MODEL + "/js.json")
	@ResponseBody
	public JsonResult<Map<String, String>> js(EntityInfo data) {
		
		Entity entity = getEntitiyInfo(data);
		String urlBase = data.getUrlBase();
		String basePackage = data.getBasePackage();
		WebTarget webTarget = new WebTarget(entity, basePackage);
		webTarget.setUrlBase(urlBase);
		JSGen jsGen = new JSGen();
		jsGen.make(webTarget, entity);
		Map<String, String> content = new HashMap<String, String>();
		for (Entry<Object, String> entry : webTarget.map.entrySet()) {
			AutoGen gen = (AutoGen) entry.getKey();
			String code = entry.getValue();
			content.put(gen.getName(), code);
		}
		return JsonResult.success(content);

	}
	
	@PostMapping(MODEL + "/java.json")
	@ResponseBody
	public JsonResult<Map<String, String>> javaCode(EntityInfo data) {
		
		Entity entity = getEntitiyInfo(data);

		//字典类型转换
		dictChange(entity);

		String urlBase = data.getUrlBase();
		String basePackage = data.getBasePackage();
		WebTarget webTarget = new WebTarget(entity, basePackage);
		webTarget.setUrlBase(urlBase);
		JavaCodeGen javaGen = new JavaCodeGen(basePackage,entity);
		javaGen.make(webTarget, entity);
		Map<String, String> content = new HashMap<String, String>();
		for (Entry<Object, String> entry : webTarget.map.entrySet()) {
			AutoGen gen = (AutoGen) entry.getKey();
			String code = entry.getValue();
			content.put(gen.getName(), code);
		}
		return JsonResult.success(content);

	}
	
	@PostMapping(MODEL + "/sql.json")
	@ResponseBody
	public JsonResult<Map<String, String>> sql(EntityInfo data) {
		
		Entity entity = getEntitiyInfo(data);

		//字典类型转换
		dictChange(entity);

		String urlBase = data.getUrlBase();
		String basePackage = data.getBasePackage();
		WebTarget webTarget = new WebTarget(entity, basePackage);
		webTarget.setUrlBase(urlBase);
		MdGen javaGen = new MdGen();
		javaGen.make(webTarget, entity);
		Map<String, String> content = new HashMap<String, String>();
		for (Entry<Object, String> entry : webTarget.map.entrySet()) {
			AutoGen gen = (AutoGen) entry.getKey();
			String code = entry.getValue();
			content.put(gen.getName(), code);
		}
		return JsonResult.success(content);

	}

	private void dictChange(Entity entity) {
		try{
			for ( Attribute attribute  : entity.getList()) {
				if (!attribute.getDictType().isEmpty()){
					//字典类型字段 时间查询处理  TYPE_DATE_BETWEEN = 2
					if(StringUtils.containsIgnoreCase(attribute.getDictType(),"date")){
						attribute.setDictType("");
						attribute.setQuertType("TYPE_DATE_BETWEEN");
						continue;
					}
					/*获取必要信息*/
					String dictType = attribute.getDictType();
					String dictTypeName = StringUtils.lowerCase(entity.getTableName()+"_"+attribute.getName());
					attribute.setDictType(dictTypeName);
					/*开始处理*/
					dictType=StringUtils.remove(dictType," ");
					dictType=StringUtils.replace(dictType,"，",",");
					dictType=StringUtils.replace(dictType,"：",":");
					String[] dictList = StringUtils.split(dictType,",");
				}
			}
		}catch (Exception e){
			throw new PlatformException("字典类型转换失败，请先修改");
		}
	}

	private Entity getEntitiyInfo(EntityInfo data) {
		Entity info = data.getEntity();
		String urlBase = data.getUrlBase();
		String basePackage = data.getBasePackage();
		Entity entity = codeGenService.getEntityInfo(info.getTableName());
		entity.setCode(info.getCode());
		entity.setDisplayName(info.getDisplayName());
		entity.setSystem(info.getSystem());
		entity.setAttachment(data.entity.isAttachment());
		entity.setIncludeExcel(data.entity.isIncludeExcel());
		for (int i = 0; i < entity.getList().size(); i++) {
		    Attribute attr = entity.getList().get(i);
		    attr.setDisplayName(info.getList().get(i).getDisplayName());
		    attr.setShowInQuery(info.getList().get(i).isShowInQuery());
		    attr.setDictType(info.getList().get(i).getDictType());
		    attr.setVerifyList(info.getList().get(i).getVerifyList());
		    if(attr.getName().equals(data.getNameAttr())) {
		        entity.setNameAttribute(attr);
		    }
		}
		
		if (StringUtils.isEmpty(entity.getCode()) || StringUtils.isEmpty(entity.getSystem())) {
			throw new PlatformException("code,system不能为空");
		}
		return entity;
	}
	

	

	@GetMapping(MODEL + "/{table}/test.json")
	@ResponseBody
	public void test(@PathVariable String table) {

		Entity entity = new Entity();
		entity.setCode("blog");
		entity.setName("CmsBlog");
		entity.setDisplayName("博客");
		entity.setTableName("CMS_BLOG");
		entity.setSystem("console");

		Attribute idAttr = new Attribute();
		idAttr.setColName("id");
		idAttr.setJavaType("Long");
		idAttr.setName("id");
		idAttr.setDisplayName("编号");
		idAttr.setId(true);
		entity.setIdAttribute(idAttr);

		Attribute nameAttr = new Attribute();
		nameAttr.setColName("title");
		nameAttr.setJavaType("String");
		nameAttr.setName("title");
		nameAttr.setDisplayName("标题");
		nameAttr.setShowInQuery(true);

		Attribute contentAttr = new Attribute();
		contentAttr.setColName("content");
		contentAttr.setJavaType("String");
		contentAttr.setName("content");
		contentAttr.setDisplayName("内容");
		contentAttr.setShowInQuery(true);

		Attribute createTimeAttr = new Attribute();
		createTimeAttr.setColName("create_time");
		createTimeAttr.setJavaType("Date");
		createTimeAttr.setName("createTime");
		createTimeAttr.setDisplayName("创建日期");
		createTimeAttr.setShowInQuery(true);

		Attribute createUserIdAttr = new Attribute();
		createUserIdAttr.setColName("create_user_id");
		createUserIdAttr.setJavaType("Long");
		createUserIdAttr.setName("createUserId");
		createUserIdAttr.setDisplayName("创建人");
		createUserIdAttr.setShowInQuery(true);

		Attribute typeAttr = new Attribute();
		typeAttr.setColName("type");
		typeAttr.setJavaType("String");
		typeAttr.setName("type");
		typeAttr.setDisplayName("博客类型");
		typeAttr.setShowInQuery(true);

		entity.getList().add(idAttr);
		entity.getList().add(nameAttr);
		entity.getList().add(contentAttr);
		entity.getList().add(createTimeAttr);
		entity.getList().add(createUserIdAttr);
		entity.getList().add(typeAttr);

		entity.setNameAttribute(nameAttr);
		entity.setComment("这是一个测试模型");
		// ConsoleTarget target = new ConsoleTarget();

		String basePackage = "com.ibeetl.admin.console";
		MavenProjectTarget target = new MavenProjectTarget(entity, basePackage);
		target.setUrlBase("admin");

		JSGen jsGen = new JSGen();
		jsGen.make(target, entity);

		HtmlGen htmlGen = new HtmlGen();
		htmlGen.make(target, entity);

		JavaCodeGen javaGen = new JavaCodeGen(basePackage, entity);
		javaGen.make(target, entity);

		MdGen mdGen = new MdGen();
		mdGen.make(target, entity);

	}

}

class EntityInfo {
	Entity entity;
	String urlBase;
	String basePackage;
	String nameAttr;

	String genJavaEntityFlag;
	String genJavaFlag;


	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public String getUrlBase() {
		return urlBase;
	}

	public void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

    public String getNameAttr() {
        return nameAttr;
    }

    public void setNameAttr(String nameAttr) {
        this.nameAttr = nameAttr;
    }

	public String getGenJavaEntityFlag() {
		return genJavaEntityFlag;
	}

	public void setGenJavaEntityFlag(String genJavaEntityFlag) {
		this.genJavaEntityFlag = genJavaEntityFlag;
	}

	public String getGenJavaFlag() {
		return genJavaFlag;
	}

	public void setGenJavaFlag(String genJavaFlag) {
		this.genJavaFlag = genJavaFlag;
	}

}
