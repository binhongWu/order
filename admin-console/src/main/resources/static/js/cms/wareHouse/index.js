layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var wareHouseTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),wareHouseTable)
            }
        },
        initTable:function(){
            wareHouseTable = table.render({
                elem : '#wareHouseTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/wareHouse/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'radio',
                fixed:'left'
                }, 
                {
                field : 'code', 
                title : '仓库编号'
                }, 
                {
                field : 'name', 
                title : '仓库名称'
                },
                {
                field : 'remarks', 
                title : '备注'
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(wareHouseTable)', function(obj){
                var wareHouse = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),wareHouseTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/wareHouse/add.do";
                    Common.openDlg(url,"WareHouse管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"wareHouseTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/wareHouse/edit.do?id="+data.id;
                    Common.openDlg(url,"WareHouse管理>"+data.id+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'wareHouseApi' ], function() {
                        var wareHouseApi = layui.wareHouseApi
                        Common.openConfirm("确认要导出这些WareHouse数据?", function() {
                            wareHouseApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/wareHouse/excel/import.do";
                    //模板,
                    var templatePath= "/cms/wareHouse/wareHouse_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "WareHouse管理>上传");
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