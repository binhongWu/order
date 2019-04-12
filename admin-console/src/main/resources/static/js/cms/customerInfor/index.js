layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var customerInforTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),customerInforTable)
            }
        },
        initTable:function(){
            customerInforTable = table.render({
                elem : '#customerInforTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/customerInfor/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'radio',
                fixed:'left',
                }, 
                {
                field : 'clientId', 
                title : '客户编号',
                fixed:'left',
                }, 
                {
                field : 'name', 
                title : '客户名称',
                }, 
                {
                field : 'sexText', //数据字典类型为 customer_infor_sex
                title : '性别',
                }, 
                {
                field : 'tel', 
                title : '电话',
                }, 
                {
                field : 'email', 
                title : '邮箱',
                }, 
                {
                field : 'intergral', 
                title : '积分',
                }, 
                {
                field : 'levelText', //数据字典类型为 customer_infor_level
                title : '级别',
                },
                {
                field : 'remarks', 
                title : '备注',
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(customerInforTable)', function(obj){
                var customerInfor = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),customerInforTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/customerInfor/add.do";
                    Common.openDlg(url,"CustomerInfor管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"customerInforTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/customerInfor/edit.do?clientId="+data.clientId;
                    Common.openDlg(url,"CustomerInfor管理>"+data.clientId+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'customerInforApi' ], function() {
                        var customerInforApi = layui.customerInforApi
                        Common.openConfirm("确认要导出这些CustomerInfor数据?", function() {
                            customerInforApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/customerInfor/excel/import.do";
                    //模板,
                    var templatePath= "/cms/customerInfor/customerInfor_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "CustomerInfor管理>上传");
                }
            };
            $('.ext-toolbar').on('click', function() {
                var type = $(this).data('type');
                toolbar[type] ? toolbar[type].call(this) : '';
            });
        }
	}
    exports('index',view);
	
});