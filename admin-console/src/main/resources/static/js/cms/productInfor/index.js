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
                type : 'radio',
                fixed:'left'
                }, 
                {
                field : 'code', 
                title : '绘本编码',
                fixed:'left'
                }, 
                {
                field : 'name', 
                title : '绘本名称（奖项+名称）'
                }, 
                {
                field : 'foreignName', 
                title : '外文名字'
                }, 
                {
                field : 'kinds', 
                title : '读者对象'
                }, 
                // {
                // field : 'format',
                // title : '开本',
                //     width : 100
                // },
                // {
                // field : 'pages',
                // title : '页数',
                //     width : 100
                // },
                // {
                // field : 'size',
                // title : '商品尺寸',
                //     width : 100
                // },
                // {
                // field : 'weight',
                // title : '商品重量',
                //     width : 100
                // },
                {
                field : 'brand', 
                title : '品牌'
                }, 
                {
                field : 'score', 
                title : '用户评分'
                }, 
                {
                field : 'rank', 
                title : '热销商品排名'
                }, 
                // {
                // field : 'wareId',
                // title : '仓库系统编码',
                //     width : 100
                // },
                // {
                // field : 'supplierId',
                // title : '供应商编码',
                //     width : 100
                // },
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
                // {
                // field : 'minStocks',
                // title : '最小库存'
                // },
                // {
                // field : 'maxStocks',
                // title : '最大库存',
                //     width : 100
                // },
                {
                field : 'publishHouse', 
                title : '出版社',
                    width : 100
                }, 
                // {
                // field : 'createdBy',
                // title : '创建人',
                //     width : 100
                // },
                // {
                // field : 'createdTime',
                // title : '创建时间',
                //     width : 100
                // },
                // {
                // field : 'updatedBy',
                // title : '修改人',
                //     width : 100
                // },
                // {
                // field : 'updatedTime',
                // title : '修改时间',
                //     width : 100
                // },
                // {
                // field : 'del',
                // title : '删除标志位（0：存在 1：删除）',
                //     width : 100
                // },
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
                    var url = "/cms/productInfor/edit.do?code="+data.code;
                    Common.openDlg(url,"ProductInfor管理>"+data.code+">编辑");
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
                        Common.openConfirm("确认要导出这些ProductInfor数据?", function() {
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