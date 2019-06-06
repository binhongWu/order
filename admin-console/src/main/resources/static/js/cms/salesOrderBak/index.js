layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var salesOrderBakTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),salesOrderBakTable)
            }
        },
        initTable:function(){
            salesOrderBakTable = table.render({
                elem : '#salesOrderBakTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/salesOrderBak/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'checkbox',
                fixed:'left',
                }, 
                {
                field : 'salesId', 
                title : '订单（客户联）单号',
                fixed:'left'
                },
                {
                    field : 'salesOrderId',
                    title : '订单单号',
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
                field : 'clientId', 
                title : '客户编码'
                }, 
                {
                field : 'salesDate', 
                title : '销售日期',
                templet:function(d){
                    return Common.getDate(d.salesDate,'yyyy-MM-dd');
                }
                }, 
                {
                field : 'paymentAmount', 
                title : '应付款金额'
                },
                {
                    field : 'checkStatus',
                    title : '实付款金额'
                },
                {
                field : 'salesBy', 
                title : '销售人'
                },
                {
                field : 'tradeLocations', 
                title : '交货地点'
                }, 
                // {
                // field : 'orderForText',
                // title : '销售方式'
                // },
                {
                field : 'finishedStatusText',
                title : '完成状态'
                }
        
                ] ]
        
            });
            
            table.on('checkbox(salesOrderBakTable)', function(obj){
                var salesOrderBak = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),salesOrderBakTable,form);
        },
        initToolBar:function(){
            toolbar = {
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"salesOrderBakTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/salesOrderBak/edit.do?salesId="+data.salesId;
                    Common.openDlg(url,"销售订单>"+data.salesId+">退货确认记录");
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