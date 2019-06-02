layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var salesReturnTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),salesReturnTable)
            }
        },
        initTable:function(){
            salesReturnTable = table.render({
                elem : '#salesReturnTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/salesReturn/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'checkbox',
                fixed:'left',
                }, 
                {
                field : 'returnId', 
                title : '退回单号',
                fixed:'left',
                }, 
                {
                field : 'salesId', 
                title : '订单单号',
                }, 
                {
                field : 'returnDate', 
                title : '退货日期',
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
                field : 'paymentAmount', 
                title : '付款金额',
                }, 
                // {
                // field : 'salesBy',
                // title : '销售人',
                // },
                // {
                // field : 'checkmanBy',
                // title : '验收人',
                // },
                {
                field : 'checkBy',
                title : '审核人',
                },
                {
                field : 'checkDate', 
                title : '审核时间',
                }, 
                {
                field : 'statsText',// sales_return_status
                title : '审核状态',
                },
                {
                field : 'remarks', 
                title : '备注',
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(salesReturnTable)', function(obj){
                var salesReturn = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),salesReturnTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/salesReturn/add.do";
                    Common.openDlg(url,"SalesReturn管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"salesReturnTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/salesReturn/edit.do?returnId="+data.returnId;
                    Common.openDlg(url,"SalesReturn管理>"+data.returnId+">编辑");
                },
                audio : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"salesReturnTable");
                    if(data.stats !== "0"){
                        Common.info("该订单已审核，请勿重复审核");
                        return
                    }
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/salesReturn/audio.do?returnId="+data.returnId;
                    Common.openDlg(url,"SalesReturn管理>"+data.returnId+">审核");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'salesReturnApi' ], function() {
                        var salesReturnApi = layui.salesReturnApi
                        Common.openConfirm("确认要导出这些SalesReturn数据?", function() {
                            salesReturnApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/salesReturn/excel/import.do";
                    //模板,
                    var templatePath= "/cms/salesReturn/salesReturn_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "SalesReturn管理>上传");
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