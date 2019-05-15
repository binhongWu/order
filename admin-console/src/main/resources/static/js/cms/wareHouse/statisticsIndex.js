layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var wareHouseTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),wareHouseTable)
            }
        },
        initTable:function(){
            wareHouseTable = table.render({
                elem : '#wareHouseTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/productInfor/statistics.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10
                ,cols : [ [ // 表头
                    // {
                    //     type : 'checkbox',
                    //     fixed:'left'
                    // },
                    {
                        field : 'wareId',
                        title : '仓库系统编码'
                    },
                    {
                        field : 'code',
                        title : '绘本编码'
                    },
                    {
                        field : 'name',
                        title : '绘本名称'
                    },
                    {
                        field : 'kinds',
                        title : '绘本对象'
                    },
                    {
                        field : 'bookKind',
                        title : '绘本分类'
                    },
                    {
                        field : 'existStocks',
                        title : '现有库存'
                        // ,templet:function(d){
                        //     return d.existStocks.split(".")[0];
                        // }
                    },
                    {
                        field : 'minStocks',
                        title : '最小库存'
                    },
                    {
                        field : 'maxStocks', //数据字典类型为 product_infor_kinds
                        title : '最大库存'
                    },
                    {
                        field : '', //数据字典类型为 product_infor_kinds
                        title : '最近一个月销量'
                        ,templet:function(d){
                            if (d.rank != null) {
                                return d.rank.split(".")[0];
                            }
                            return 0
                        }
                    },
                    {
                        field : '', //数据字典类型为 product_infor_kinds
                        title : '最近一个月采购'
                        ,templet:function(d){
                            if (d.remarks != null) {
                                return d.remarks.split(".")[0];
                            }
                            return 0
                        }
                    }
                ] ]

            });
            
            table.on('checkbox(wareHouseTable)', function(obj){
                var wareHouse = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),wareHouseTable,form);
        }
	}
    exports('statisticsIndex',view);
	
});