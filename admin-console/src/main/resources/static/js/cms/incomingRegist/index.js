layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var incomingRegistTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),incomingRegistTable)
            }
        },
        initTable:function(){
            incomingRegistTable = table.render({
                elem : '#incomingRegistTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/incomingRegist/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'checkbox',
                fixed:'left',
                }, 
                {
                field : 'inRegistId', 
                title : '入库单号',
                fixed:'left',
                }, 
                {
                field : 'orderId', 
                title : '关联订单号',
                }, 
                {
                field : 'inRegistDate', 
                title : '入库日期',
                }, 
                {
                field : 'code', 
                title : '绘本编码',
                }, 
                // {
                // field : 'supplierId',
                // title : '供应商编码',
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
                title : '总额',
                }, 
                {
                field : 'statusText', //数据字典类型为 incoming_regist_status
                title : '状态位（0:采购入库 1：销售退货入库2：其他）',
                },
                {
                field : 'remarks', 
                title : '备注',
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(incomingRegistTable)', function(obj){
                var incomingRegist = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),incomingRegistTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/incomingRegist/add.do";
                    Common.openDlg(url,"IncomingRegist管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"incomingRegistTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/incomingRegist/edit.do?inRegistId="+data.inRegistId;
                    Common.openDlg(url,"IncomingRegist管理>"+data.inRegistId+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'incomingRegistApi' ], function() {
                        var incomingRegistApi = layui.incomingRegistApi
                        Common.openConfirm("确认要导出这些IncomingRegist数据?", function() {
                            incomingRegistApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/incomingRegist/excel/import.do";
                    //模板,
                    var templatePath= "/cms/incomingRegist/incomingRegist_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "IncomingRegist管理>上传");
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