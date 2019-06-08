layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var outboundRedistTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),outboundRedistTable)
            }
        },
        initTable:function(){
            outboundRedistTable = table.render({
                elem : '#outboundRedistTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/outboundRedist/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'checkbox',
                fixed:'left',
                }, 
                {
                field : 'outRegistId', 
                title : '出库单号',
                fixed:'left',
                }, 
                {
                field : 'outorderId', 
                title : '订单号',
                }, 
                {
                field : 'outRegistDate', 
                title : '出库日期',
                }, 
                {
                field : 'code', 
                title : '绘本编码',
                }, 
                // {
                // field : 'supplierId',
                // title : '供货商编码',
                // },
                // {
                // field : 'checkBy',
                // title : '审核人',
                // },
                // {
                // field : 'checkDate',
                // title : '审核时间',
                // },
                // {
                // field : 'checkStatus',
                // title : '审核状态',
                // },
                {
                field : 'price', 
                title : '绘本单价',
                }, 
                {
                field : 'number', 
                title : '绘本数量',
                }, 
                {
                field : 'total', 
                title : '绘本总额',
                }, 
                {
                field : 'statusText', //数据字典类型为 outbound_redist_status
                title : '状态位（0:销售出库 1：其他）',
                },
                {
                field : 'remarks', 
                title : '备注',
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(outboundRedistTable)', function(obj){
                var outboundRedist = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),outboundRedistTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/outboundRedist/add.do";
                    Common.openDlg(url,"OutboundRedist管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"outboundRedistTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/outboundRedist/edit.do?outRegistId="+data.outRegistId;
                    Common.openDlg(url,"OutboundRedist管理>"+data.outRegistId+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'outboundRedistApi' ], function() {
                        var outboundRedistApi = layui.outboundRedistApi
                        Common.openConfirm("确认要导出这些OutboundRedist数据?", function() {
                            outboundRedistApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/outboundRedist/excel/import.do";
                    //模板,
                    var templatePath= "/cms/outboundRedist/outboundRedist_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "OutboundRedist管理>上传");
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