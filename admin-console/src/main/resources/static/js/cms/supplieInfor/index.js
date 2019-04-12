layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var supplieInforTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),supplieInforTable)
            }
        },
        initTable:function(){
            supplieInforTable = table.render({
                elem : '#supplieInforTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/supplieInfor/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'radio',
                fixed:'left',
                }, 
                {
                field : 'supplierId', 
                title : '供货商编号',
                fixed:'left',
                }, 
                {
                field : 'supplierName', 
                title : '供货商名称',
                }, 
                {
                field : 'books', 
                title : '供应图书种类',
                }, 
                // {
                // field : 'position',
                // title : '职务',
                // },
                {
                field : 'address', 
                title : '地址',
                }, 
                {
                field : 'city', 
                title : '城市',
                }, 
                // {
                // field : 'postcode',
                // title : '邮编',
                // },
                // {
                // field : 'couty',
                // title : '国家',
                // },
                {
                field : 'tel', 
                title : '电话',
                }, 
                {
                field : 'depositBank', 
                title : '开户银行',
                }, 
                {
                field : 'bankAccount', 
                title : '银行账号',
                }, 
                {
                field : 'email', 
                title : '邮箱',
                }, 
                // {
                // field : 'createdBy',
                // title : '创建人',
                // },
                // {
                // field : 'createdTime',
                // title : '创建时间',
                // },
                // {
                // field : 'updatedBy',
                // title : '更新人',
                // },
                // {
                // field : 'updatedTime',
                // title : '更新时间',
                // },
                // {
                // field : 'del',
                // title : '删除标记{0:正常,1:已删除}',
                // },
                {
                field : 'remarks', 
                title : '备注',
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(supplieInforTable)', function(obj){
                var supplieInfor = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),supplieInforTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/supplieInfor/add.do";
                    Common.openDlg(url,"SupplieInfor管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"supplieInforTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/supplieInfor/edit.do?supplierId="+data.supplierId;
                    Common.openDlg(url,"SupplieInfor管理>"+data.supplierId+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'supplieInforApi' ], function() {
                        var supplieInforApi = layui.supplieInforApi
                        Common.openConfirm("确认要导出这些SupplieInfor数据?", function() {
                            supplieInforApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/supplieInfor/excel/import.do";
                    //模板,
                    var templatePath= "/cms/supplieInfor/supplieInfor_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "SupplieInfor管理>上传");
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