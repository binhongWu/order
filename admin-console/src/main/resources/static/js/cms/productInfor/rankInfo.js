layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var rankInfoTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),rankInfoTable)
            }
        },
        initTable:function(){
            rankInfoTable = table.render({
                elem : '#rankInfoTable',
                height : Lib.getTableHeight(1),
                cellMinWidth : 100,
                method : 'post',
                url : Common.ctxPath + '/cms/productInfor/rankInfo.json' // 数据接口
                ,page : false // 开启分页
                ,limit : 10,
                // toolbar: true,
                // defaultToolbar: ['filter'],
                cols : [ [ // 表头
                {
                field : 'code', 
                title : '绘本编码isbn'
                }, 
                {
                field : 'name', 
                title : '绘本名称'
                },
                {
                    field : 'picture',
                    title : '图片',
                    templet: '<div><img src="{{d.pictureUrl}}"></div>'
                },
                {
                field : 'languageText', //数据字典类型为 product_infor_language
                title : '语种'
                },
                {
                field : 'kindsText', //数据字典类型为 product_infor_kinds
                title : '读者对象'
                },
                {
                    field : 'countNumber',
                    title : '销售数量'
                }
                ] ]
        
            });
            
            table.on('checkbox(rankInfoTable)', function(obj){
                var productInfor = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),rankInfoTable,form);
        }
	};
    exports('rankInfo',view);
	
});