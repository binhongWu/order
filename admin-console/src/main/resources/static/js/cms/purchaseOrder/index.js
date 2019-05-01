layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var purchaseOrderTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),purchaseOrderTable)
            }
        },
        initTable:function(){
            purchaseOrderTable = table.render({
                elem : '#purchaseOrderTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/purchaseOrder/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'checkbox',
                fixed:'left',
                }, 
                {
                field : 'orderId', 
                title : '订单单号',
                fixed:'left',
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
                field : 'orderDate', 
                title : '订单日期',
                }, 
                {
                field : 'deliverDate', 
                title : '交货日期',
                }, 
                {
                field : 'paymentMethodText', //数据字典类型为 purchase_order_paymentmethod
                title : '付款方式',
                }, 
                {
                field : 'paymentAmount', 
                title : '付款金额',
                }, 
                {
                field : 'tradeLocal', 
                title : '交货地点',
                }, 
                // {
                // field : 'checkBy',
                // title : '审核人',
                // },
                // {
                // field : 'checkDate',
                // title : '审核时间',
                // },
                // {
                // field : 'chenkStatusText',//purchase_warehouse_checkstatus
                // title : '审核状态',
                // },
                {
                field : 'finishConditionText', //数据字典类型为 purchase_order_finishcondition
                title : '完成状态',
                },
                {
                field : 'remarks', 
                title : '备注',
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(purchaseOrderTable)', function(obj){
                var purchaseOrder = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),purchaseOrderTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/purchaseOrder/add.do";
                    Common.openDlg(url,"PurchaseOrder管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"purchaseOrderTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/purchaseOrder/edit.do?orderId="+data.orderId;
                    Common.openDlg(url,"PurchaseOrder管理>"+data.orderId+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'purchaseOrderApi' ], function() {
                        var purchaseOrderApi = layui.purchaseOrderApi
                        Common.openConfirm("确认要导出这些采购订单数据?", function() {
                            purchaseOrderApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/purchaseOrder/excel/import.do";
                    //模板,
                    var templatePath= "/cms/purchaseOrder/purchaseOrder_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "PurchaseOrder管理>上传");
                },
                importDocument2:function(){
                    var uploadUrl = Common.ctxPath+"/cms/purchaseOrder/excel/import2.do";
                    //模板,
                    var templatePath= "/cms/purchaseOrder/purchaseOrder_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "PurchaseOrder管理>上传");
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