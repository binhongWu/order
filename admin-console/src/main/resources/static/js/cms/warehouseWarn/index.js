layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var warehouseWarnTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),warehouseWarnTable)
            }
        },
        initTable:function(){
            warehouseWarnTable = table.render({
                elem : '#warehouseWarnTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/warehouseWarn/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'checkbox',
                fixed:'left',
                }, 
                {
                field : 'warningId', 
                title : '预警单号',
                fixed:'left',
                }, 
                {
                field : 'alarmt', 
                title : '预警时间',
                }, 
                {
                field : 'nextAlarmt', 
                title : '下一次预警时间',
                }, 
                {
                field : 'code', 
                title : '绘本编码',
                }, 
                {
                field : 'stock', 
                title : '现有库存',
                }, 
                {
                field : 'checkText', //数据字典类型为 warehouse_warn_check
                title : '0:解决 1：未解决',
                },
                {
                field : 'remarks', 
                title : '备注',
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(warehouseWarnTable)', function(obj){
                var warehouseWarn = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),warehouseWarnTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/warehouseWarn/add.do";
                    Common.openDlg(url,"WarehouseWarn管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"warehouseWarnTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/warehouseWarn/edit.do?warningId="+data.warningId;
                    Common.openDlg(url,"WarehouseWarn管理>"+data.warningId+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'warehouseWarnApi' ], function() {
                        var warehouseWarnApi = layui.warehouseWarnApi
                        Common.openConfirm("确认要导出这些WarehouseWarn数据?", function() {
                            warehouseWarnApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/warehouseWarn/excel/import.do";
                    //模板,
                    var templatePath= "/cms/warehouseWarn/warehouseWarn_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "WarehouseWarn管理>上传");
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