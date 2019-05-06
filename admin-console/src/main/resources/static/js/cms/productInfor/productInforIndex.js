layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var productInforTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),productInforTable)
            }
        },
        initTable:function(){
            productInforTable = table.render({
                elem : '#productInforTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/productInfor/productInforIndexList.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                    {
                        type : 'checkbox',
                        fixed:'left',
                    },
                    {
                        field : 'name',
                        title : '绘本名称',
                    },
                    {
                        field : 'outPrice',
                        title : '单价（元）',
                    },
                    {
                        field : 'picture',
                        title : '图片',
                        templet: '<div><img src="{{d.pictureUrl}}"></div>'
                    },
                    {
                        field : 'author',
                        title : '作者'
                    },
                    {
                        field : 'languageText', //数据字典类型为 product_infor_language
                        title : '语种'
                    },
                    {
                        field : 'kindsText', //数据字典类型为 product_infor_kinds
                        title : '推荐读者对象'
                    },
                    {
                        field : 'bookKind',
                        title : '图书分类'
                    },
                    {
                        field : 'publishHouse',
                        title : '出版社'
                    },
                    {
                        field : 'publishDate',
                        title : '出版日期'
                    }
                ] ]

            });

            table.on('checkbox(productInforTable)', function(obj){
                var productInfor = obj.data;
                if(obj.checked){
                    //按钮逻辑Lib.buttonEnable()
                }else{

                }
            })
        },

        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),productInforTable,form);
        },
        initToolBar:function(){
            toolbar = {
                purchase : function() { // 获取选中数据
                    var data = Common.getOneFromTable(table,"productInforTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/productInfor/purchase.do?id="+data.id;
                    Common.openDlg(url,"绘本信息>购买");
                },
                getInfo : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"productInforTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/productInfor/getInfo.do?id="+data.id;
                    Common.openDlg(url,"绘本信息>"+data.id+">详情");
                }
            };
            $('.ext-toolbar').on('click', function() {
                var type = $(this).data('type');
                toolbar[type] ? toolbar[type].call(this) : '';
            });
        }
    }
    exports('productInforIndex',view);

});