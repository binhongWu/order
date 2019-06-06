layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var salesOutStackTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),salesOutStackTable)
            }
        },
        initTable:function(){
            salesOutStackTable = table.render({
                elem : '#salesOutStackTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/salesOutStack/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                toolbar: true,
                defaultToolbar: ['filter'],
                cols : [ [ // 表头
                {
                type : 'checkbox',
                fixed:'left',
                }, 
                {
                field : 'salesOutStackId', 
                title : '出库单号',
                fixed:'left',
                },
                {
                field : 'salesId', 
                title : '销售单号',
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
                field : 'salesDate', 
                title : '销售日期',
                },
                {
                field : 'clientId', 
                title : '客户信息',
                },
                {
                field : 'paymentAmount', 
                title : '付款金额',
                },
                {
                field : 'paymentMethodText', //数据字典类型为 sales_out_stack_paymentmethod
                title : '付款方式',
                },
                {
                field : 'salesBy', 
                title : '销售人',
                },
                // {
                // field : 'shipBy',
                // title : '发货人',
                // },
                {
                field : 'deliveryAddress', 
                title : '送货地址',
                },
                {
                field : 'checkBy',
                title : '审核人',
                    width : 100
                },
                {
                field : 'checkDate',
                title : '审核时间',
                    width : 100
                },
                {
                field : 'checkStatusText',
                title : '审核状态'
                },
                {
                field : 'remarks', 
                title : '备注',
                }
        
                ] ]
        
            });
            
            table.on('checkbox(salesOutStackTable)', function(obj){
                var salesOutStack = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),salesOutStackTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/salesOutStack/add.do";
                    Common.openDlg(url,"SalesOutStack管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"salesOutStackTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/salesOutStack/edit.do?salesOutStackId="+data.salesOutStackId;
                    Common.openDlg(url,"SalesOutStack管理>"+data.salesOutStackId+">编辑");
                },
                audio : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"salesOutStackTable");
                    if(data==null){
                        return ;
                    }
                    if(data.checkStatus !== "0"){
                        Common.info("该订单已审核，请勿重复审核");
                        return
                    }
                    var url = "/cms/salesOutStack/audio.do?salesOutStackId="+data.salesOutStackId;
                    Common.openDlg(url,"SalesOutStack管理>"+data.salesOutStackId+">审核");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'salesOutStackApi' ], function() {
                        var salesOutStackApi = layui.salesOutStackApi
                        Common.openConfirm("确认要导出这些销售出库数据?", function() {
                            salesOutStackApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/salesOutStack/excel/import.do";
                    //模板,
                    var templatePath= "/cms/salesOutStack/salesOutStack_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "SalesOutStack管理>上传");
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