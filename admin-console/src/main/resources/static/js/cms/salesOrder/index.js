layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var salesOrderTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),salesOrderTable)
            }
        },
        initTable:function(){
            salesOrderTable = table.render({
                elem : '#salesOrderTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/salesOrder/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'checkbox',
                fixed:'left',
                }, 
                {
                field : 'salesId', 
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
                field : 'clientId', 
                title : '客户编码',
                },
                {
                field : 'salesDate', 
                title : '销售日期',
                },
                {
                field : 'paymentAmount', 
                title : '付款金额',
                },
                {
                field : 'salesBy', 
                title : '销售人',
                },
                {
                field : 'paymentMethodText', //数据字典类型为 sales_order_paymentmethod
                title : '付款方式（0：支付宝 1：微信 2：银行卡）',
                },
                {
                field : 'tradeLocations', 
                title : '交货地点',
                },
                {
                field : 'orderForText', //数据字典类型为 sales_order_orderfor
                title : '销售方式',
                },
                {
                field : 'finishedStatusText', //数据字典类型为 sales_order_finishedstatus
                title : '完成状态（0：完成 1：未完成）',
                },
                // {
                // field : 'checkBy',
                // title : '审核人',
                //     width : 100
                // },
                // {
                // field : 'checkStatusText', //数据字典类型为 sales_order_checkstatus
                // title : '审核状态',
                //     width : 100
                // },
                // {
                // field : 'checkDate',
                // title : '审核日期',
                //     width : 100
                // },
                // {
                // field : 'createdBy',
                // title : '创建人',
                //     width : 100
                // },
                // {
                // field : 'createdTime',
                // title : '创建时间',
                //     width : 100
                // },
                // {
                // field : 'updatedBy',
                // title : '更新人',
                //     width : 100
                // },
                // {
                // field : 'updatedTime',
                // title : '更新时间',
                //     width : 100
                // },
                // {
                // field : 'del',
                // title : '删除标记{0:正常,1:已删除}',
                //     width : 100
                // },
                {
                field : 'remarks', 
                title : '备注',
                    width : 100
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(salesOrderTable)', function(obj){
                var salesOrder = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),salesOrderTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/salesOrder/add.do";
                    Common.openDlg(url,"SalesOrder管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"salesOrderTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/salesOrder/edit.do?salesId="+data.salesId;
                    Common.openDlg(url,"SalesOrder管理>"+data.salesId+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'salesOrderApi' ], function() {
                        var salesOrderApi = layui.salesOrderApi
                        Common.openConfirm("确认要导出这些销售订单数据?", function() {
                            salesOrderApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/salesOrder/excel/import.do";
                    //模板,
                    var templatePath= "/cms/salesOrder/salesOrder_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "SalesOrder管理>上传");
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