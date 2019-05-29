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
                cellMinWidth : 100,
                method : 'post',
                url : Common.ctxPath + '/cms/productInfor/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'checkbox',
                fixed:'left',
                }, 
                {
                field : 'code', 
                title : '绘本编码isbn'
                }, 
                {
                field : 'name', 
                title : '绘本名称'
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
                title : '读者对象'
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
                ,templet:function(d){
                    return Common.getDate(d.publishDate,'yyyy-MM-dd');
                }
                }, 
                {
                field : 'introduction', 
                title : '简介'
                }, 
                // {
                // field : 'brand',
                // title : '品牌'
                // },
                {
                field : 'scoreText', //数据字典类型为 product_infor_score
                title : '是否是套装'
                }, 
                {
                field : 'productNum', 
                title : '套装册数'
                }, 
                // {
                // field : 'rank',
                // title : '热销商品排名'
                // },
                {
                field : 'wareId', 
                title : '仓库系统编码'
                }, 
                {
                field : 'supplierId', 
                title : '供应商编码'
                }, 
                {
                field : 'inPrice', 
                title : '入库单价'
                }, 
                {
                field : 'outPrice', 
                title : '出库单价'
                }, 
                {
                field : 'existStocks', 
                title : '现有库存'
                }, 
                {
                field : 'minStocks', 
                title : '最小库存'
                }, 
                {
                field : 'maxStocks', 
                title : '最大库存'
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
                add : function() { // 获取选中数据
                    var url = "/cms/productInfor/add.do";
                    Common.openDlg(url,"ProductInfor管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"productInforTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/productInfor/edit.do?id="+data.id;
                    Common.openDlg(url,"ProductInfor管理>"+data.id+">编辑");
                },
                del : function() { 
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
                ,
                exportDocument : function() {
                    layui.use([ 'productInforApi' ], function() {
                        var productInforApi = layui.productInforApi
                        Common.openConfirm("确认要导出这些绘本信息数据?", function() {
                            productInforApi.exportExcel($("#searchForm"), function(fileId) {
                                Lib.download(fileId);
                            })
                        })
                    });
                },
                importDocument:function(){
                    /*导入数据请求的接口*/
                    var uploadUrl = Common.ctxPath+"/cms/productInfor/excel/import.do";
                    //模板, 不懂，忽略
                    var templatePath= "/cms/productInfor/productInfor_upload_template.xls";
                    //公共的简单上传文件处理  上传到 application-test.properties 中设置的系统文件上传的文件夹里localFile.root=/data（文件夹在项目的根盘符下）
                    var url = "/core/file/simpleUpload.do?uploadUrl="+uploadUrl+"&templatePath="+templatePath;
                    Common.openDlg(url, "ProductInfor管理>上传");
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