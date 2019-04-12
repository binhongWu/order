layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var purchaseReturnsTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),purchaseReturnsTable)
            }
        },
        initTable:function(){
            purchaseReturnsTable = table.render({
                elem : '#purchaseReturnsTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/purchaseReturns/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'radio',
                fixed:'left'
                }, 
                {
                field : 'returnedId', 
                title : '退回单号',
                fixed:'left'
                }, 
                {
                field : 'code', 
                title : '绘本编码'
                }, 
                {
                field : 'number', 
                title : '绘本数量'
                }, 
                {
                field : 'price', 
                title : '绘本单价'
                }, 
                {
                field : 'supplierId', 
                title : '供货商编号'
                }, 
                {
                field : 'orderId', 
                title : '采购订单单号'
                }, 
                {
                field : 'refundMethodText', //数据字典类型为 purchase_returns_refundmethod
                title : '退款方式'
                }, 
                {
                field : 'refundAmount', 
                title : '退款金额'
                }, 
                {
                field : 'billId', 
                title : '发票号'
                }, 
                {
                field : 'returnedDate', 
                title : '退货日期'
                }, 
                {
                field : 'sender', 
                title : '发货人'
                }, 
                {
                field : 'entryDate', 
                title : '录入日期'
                }, 
                {
                field : 'checkBy', 
                title : '审查人'
                }, 
                {
                field : 'checkDate', 
                title : '审查日期'
                }, 
                {
                field : 'checkStatusText', //数据字典类型为 purchase_returns_checkstatus
                title : '审核状态'
                },
                {
                field : 'remarks', 
                title : '备注'
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(purchaseReturnsTable)', function(obj){
                var purchaseReturns = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),purchaseReturnsTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/purchaseReturns/add.do";
                    Common.openDlg(url,"PurchaseReturns管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"purchaseReturnsTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/purchaseReturns/edit.do?returnedId="+data.returnedId;
                    Common.openDlg(url,"PurchaseReturns管理>"+data.returnedId+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'purchaseReturnsApi' ], function() {
                        var purchaseReturnsApi = layui.purchaseReturnsApi
                        Common.openConfirm("确认要导出这些PurchaseReturns数据?", function() {
                            purchaseReturnsApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/purchaseReturns/excel/import.do";
                    //模板,
                    var templatePath= "/cms/purchaseReturns/purchaseReturns_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "PurchaseReturns管理>上传");
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