package com.ibeetl.admin.core.web;


import com.ibeetl.admin.core.entity.BaseEntity;
import com.ibeetl.admin.core.entity.CoreOrg;
import com.ibeetl.admin.core.entity.CoreUser;
import com.ibeetl.admin.core.file.FileItem;
import com.ibeetl.admin.core.file.FileService;
import com.ibeetl.admin.core.service.CorePlatformService;
import com.ibeetl.admin.core.util.FileUtil;
import com.ibeetl.admin.core.util.IdGenerator;
import com.ibeetl.admin.core.util.UUIDUtil;
import com.ibeetl.admin.core.util.ValidateConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.List;

/**
 * 文件上传
 * @author xiaohang
 *
 */
@Controller
public class FileUploadController {
	private  final Log log  = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/upload";

    @Autowired
    private CorePlatformService platformService ;

    @Autowired
    private FileService fileService;

    @Autowired
    protected SQLManager sqlManager;

    /*Json*/

    /**
     * 保存路径 /data/images/bizType/yyyyMMdd/filename
     * @param file
     * @param bizType
     * @return
     * @throws IOException
     * 文件信息可以查看表core_file
     * demo：
     *
     * <div class="layui-inline">
     * <button type="button" class="layui-btn" id="uploadBotton">
     * <i class="layui-icon">&#xe67c;</i>上传图片
     * </button>
     * <img id="imageUrl" class="layui-upload-img" style="width: 8em;height: 8em;"/>
     * <input type="text" id="image-input-data" name="image" hidden="true" >
     * </div>
     *
     * <script>
     * layui.use('upload', function(){
     * var upload = layui.upload;
     * var uploadInst = upload.render({
     * elem: '#uploadBotton' //绑定元素
     * ,url: '/upload//uploadImage.json' //上传接口
     * ,data: {"bizType":"shareContentInfo"}      //类型 可以使用业务类名称 只是用于区分数据
     * ,exts: 'jpg|png' //只允许上传压缩文件
     * ,done: function(res){
     * if(res.code == 0){  //上传成功
     * $("#image-input-data").val(res.data.path);//表单提交的值  可以是路径或者biz_id
     * $("#imageUrl").attr("src",res.data.path);
     * }
     * layer.closeAll('loading'); //关闭loading
     * }
     * ,error: function(){
     * layer.closeAll('loading'); //关闭loading
     * }
     * ,before: function(obj){
     * layer.load(); //上传loading
     * }
     * });
     * </script>
     */
    @PostMapping(MODEL + "/uploadImage.json")
    @ResponseBody
    public JsonResult uploadImage(@RequestParam("file") MultipartFile file,String bizType) throws IOException {
        if(file.isEmpty()) {
            return JsonResult.fail();
        }
//        String batchFileID = UUIDUtil.uuid();
        Long batchFileID = IdGenerator.nextId();
        String bizId = batchFileID.toString();
        CoreUser user = platformService.getCurrentUser();
        CoreOrg org = platformService.getCurrentOrg();
        FileItem fileItem = fileService.createImgItem(file.getOriginalFilename(), bizType, bizId, user.getId(), org.getId(), bizId,null);
        OutputStream os = fileItem.openOutpuStream();
        FileUtil.copy(file.getInputStream(), os);
        fileItem.setBizId(bizId);
        return JsonResult.success(fileItem);
    }
    public class LibraryFile {
        @AssignID
        private Long id;
        // 文件名称
        private String name;
        // 路径
        private String path;
        // 文件类别
        private String category;
        // 图片状态: 0-未生效;1-有效;2-已失效,3-已删除
        private Long status;
        // 创建时间
        private java.util.Date createTime;
        private java.util.Date expireTime;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Long getStatus() {
            return status;
        }

        public void setStatus(Long status) {
            this.status = status;
        }

        public java.util.Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(java.util.Date createTime) {
            this.createTime = createTime;
        }

        public java.util.Date getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(java.util.Date expireTime) {
            this.expireTime = expireTime;
        }
    }
}
