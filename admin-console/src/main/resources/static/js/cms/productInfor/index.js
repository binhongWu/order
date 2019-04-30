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
                title : '绘本编码isbn',
                    width : 100
                }, 
                {
                field : 'name', 
                title : '绘本名称',
                    width : 100
                }, 
                // {
                // field : 'picture',
                // title : '图片',
                //     width : 100
                // },
                {
                field : 'author', 
                title : '作者',
                    width : 100
                }, 
                {
                field : 'languageText', //数据字典类型为 product_infor_language
                title : '语种',
                    width : 100
                }, 
                {
                field : 'kindsText', //数据字典类型为 product_infor_kinds
                title : '读者对象',
                    width : 100
                }, 
                {
                field : 'bookKind', 
                title : '图书分类',
                    width : 100
                }, 
                {
                field : 'publishHouse', 
                title : '出版社',
                    width : 100
                }, 
                {
                field : 'publishDate', 
                title : '出版日期',
                    width : 100
                }, 
                {
                field : 'introduction', 
                title : '简介',
                    width : 100
                }, 
                {
                field : 'brand', 
                title : '品牌',
                    width : 100
                }, 
                {
                field : 'scoreText', //数据字典类型为 product_infor_score
                title : '是否是套装',
                    width : 100
                }, 
                {
                field : 'productNum', 
                title : '套装册数',
                    width : 100
                }, 
                {
                field : 'rank', 
                title : '热销商品排名',
                    width : 100
                }, 
                {
                field : 'wareId', 
                title : '仓库系统编码',
                    width : 100
                }, 
                {
                field : 'supplierId', 
                title : '供应商编码',
                    width : 100
                }, 
                {
                field : 'inPrice', 
                title : '入库单价',
                    width : 100
                }, 
                {
                field : 'outPrice', 
                title : '出库单价',
                    width : 100
                }, 
                {
                field : 'existStocks', 
                title : '现有库存',
                    width : 100
                }, 
                {
                field : 'minStocks', 
                title : '最小库存',
                    width : 100
                }, 
                {
                field : 'maxStocks', 
                title : '最大库存',
                    width : 100
                },
                {
                field : 'remarks', 
                title : '备注',
                    width : 100
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
                    var uploadUrl = Common.ctxPath+"/cms/productInfor/excel/import.do";
                    //模板,
                    var templatePath= "/cms/productInfor/productInfor_upload_template.xls";
                    //公共的简单上传文件处理
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