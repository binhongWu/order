layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var quotePriceTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),quotePriceTable)
            }
        },
        initTable:function(){
            quotePriceTable = table.render({
                elem : '#quotePriceTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/quotePrice/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'radio',
                fixed:'left'
                }, 
                {
                field : 'quoteId', 
                title : '报价单号',
                fixed:'left'
                }, 
                {
                field : 'quoteDate', 
                title : '报价日期'
                }, 
                {
                field : 'code', 
                title : '绘本编码'
                }, 
                {
                field : 'price', 
                title : '绘本单价'
                }, 
                {
                field : 'quoteBy', 
                title : '报价人'
                },
                {
                field : 'remarks', 
                title : '备注'
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(quotePriceTable)', function(obj){
                var quotePrice = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),quotePriceTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/quotePrice/add.do";
                    Common.openDlg(url,"QuotePrice管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"quotePriceTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/quotePrice/edit.do?quoteId="+data.quoteId;
                    Common.openDlg(url,"QuotePrice管理>"+data.quoteId+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'quotePriceApi' ], function() {
                        var quotePriceApi = layui.quotePriceApi
                        Common.openConfirm("确认要导出这些QuotePrice数据?", function() {
                            quotePriceApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    var uploadUrl = Common.ctxPath+"/cms/quotePrice/excel/import.do";
                    //模板,
                    var templatePath= "/cms/quotePrice/quotePrice_upload_template.xls";
                    //公共的简单上传文件处理
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "QuotePrice管理>上传");
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