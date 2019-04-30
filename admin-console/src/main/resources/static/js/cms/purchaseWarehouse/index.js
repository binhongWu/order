layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var purchaseWarehouseTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),purchaseWarehouseTable)
            }
        },
        initTable:function(){
            purchaseWarehouseTable = table.render({
                elem : '#purchaseWarehouseTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/purchaseWarehouse/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'checkbox',
                fixed:'left',
                }, 
                {
                field : 'enterId', 
                title : '入库单号',
                fixed:'left',
                }, 
                {
                field : 'orderId', 
                title : '订单单号',
                }, 
                {
                field : 'code', 
                title : '绘本编码',
                }, 
                {
                field : 'number', 
                title : '绘本数量',
                }, 
                {
                field : 'price', 
                title : '绘本单价',
                }, 
                {
                field : 'supplierId', 
                title : '供应商编号',
                }, 
                {
                field : 'paymentAmount', 
                title : '付款金额',
                }, 
                {
                field : 'purchaseDate', 
                title : '采购日期',
                }, 
                {
                field : 'buyerBy', 
                title : '采购人',
                }, 
                {
                field : 'checkBy', 
                title : '审核人',
                }, 
                {
                field : 'checkDate', 
                title : '审核时间',
                }, 
                {
                field : 'checkStatusText', //数据字典类型为 purchase_warehouse_checkstatus
                title : '审核状态（0：待审核 1：通过 2：拒绝）',
                }, 
                {
                field : 'createdBy', 
                title : '创建人',
                }, 
                {
                field : 'createdTime', 
                title : '创建时间',
                }, 
                {
                field : 'updatedBy', 
                title : '更新人',
                }, 
                {
                field : 'updatedTime', 
                title : '更新时间',
                }, 
                {
                field : 'del', 
                title : '删除标记{0:正常,1:已删除}',
                }, 
                {
                field : 'remarks', 
                title : '备注',
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(purchaseWarehouseTable)', function(obj){
                var purchaseWarehouse = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),purchaseWarehouseTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/purchaseWarehouse/add.do";
                    Common.openDlg(url,"PurchaseWarehouse管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"purchaseWarehouseTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/purchaseWarehouse/edit.do?enterId="+data.enterId;
                    Common.openDlg(url,"PurchaseWarehouse管理>"+data.enterId+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'purchaseWarehouseApi' ], function() {
                        var purchaseWarehouseApi = layui.purchaseWarehouseApi
                        Common.openConfirm("确认要导出这些PurchaseWarehouse数据?", function() {
                            purchaseWarehouseApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/purchaseWarehouse/excel/import.do";
                    //模板,
                    var templatePath= "/cms/purchaseWarehouse/purchaseWarehouse_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "PurchaseWarehouse管理>上传");
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