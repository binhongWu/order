layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var inventoryOrderTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),inventoryOrderTable)
            }
        },
        initTable:function(){
            inventoryOrderTable = table.render({
                elem : '#inventoryOrderTable',
                height : Lib.getTableHeight(1),
                method : 'post',
                url : Common.ctxPath + '/cms/inventoryOrder/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                {
                type : 'checkbox',
                fixed:'left',
                },
                {
                field : 'code', 
                title : '绘本编码',
                },
                {
                field : 'name',
                title : '绘本名称',
                },
                    {
                field : 'language', 
                title : '语种',
                }, 
                {
                field : 'kinds', 
                title : '读者对象',
                }, 
                {
                field : 'bookKind', 
                title : '图书分类',
                }, 
                {
                field : 'existStocks', 
                title : '现有库存',
                }, 
                {
                field : 'inventoryStocks', 
                title : '盘点库存',
                },
                {
                field : 'createdTime', 
                title : '创建时间',
                },
                {
                field : 'updatedTime', 
                title : '修改时间',
                },
                {
                field : 'remarks', 
                title : '备注',
                } 
        
                ] ]
        
            });
            
            table.on('checkbox(inventoryOrderTable)', function(obj){
                var inventoryOrder = obj.data;
                if(obj.checked){
                   //按钮逻辑Lib.buttonEnable()
                }else{
                    
                }
             })
        },
        	
        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),inventoryOrderTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    layer.open({
                        type: 1,
                        title: '同步系统记录绘本库存量',
                        area: ['300px', '200px'],
                        btn: ['确定','取消'],
                        btnAlign: 'c'
                        ,yes :function () {
                            // 查找今日是否已经同步过了
                            $.ajax({
                                type:"POST",
                                url:'/cms/inventoryOrder/isSynchronization.do',
                                success : function (rsp) {
                                    var msg= rsp.msg;
                                    if(rsp.code !== 0){
                                        layer.open({
                                            type: 1,
                                            title: false,
                                            area: ['300px', '200px'],
                                            btn: ['确定', '取消'],
                                            btnAlign: 'c',
                                            content: '<div style="margin-top:30px;text-align: center">今日已经同步过，是否重复同步？</div>'
                                            , yes: function () {
                                                $.ajax({
                                                    type:"POST",
                                                    url:'/cms/inventoryOrder/synchronization.json',
                                                    success : function (rsp) {
                                                        var msg= rsp.msg;
                                                        if(rsp.code === 0){
                                                            Common.info("同步成功，请进行盘点！");
                                                            Lib.closeFrame();
                                                            layer.closeAll();
                                                        }else{
                                                            layer.msg(msg);
                                                            Lib.closeFrame();
                                                        }
                                                    }
                                                })
                                            }
                                        });
                                    }else{
                                        $.ajax({
                                            type:"POST",
                                            url:'/cms/inventoryOrder/synchronization.json',
                                            success : function (rsp) {
                                                var msg= rsp.msg;
                                                if(rsp.code === 0){
                                                    Common.info("同步成功，请进行盘点！");
                                                    Lib.closeFrame();
                                                    layer.closeAll();
                                                }else{
                                                    layer.msg(msg);
                                                    Lib.closeFrame();
                                                }
                                            }
                                        })
                                    }
                                }
                            })
                        }
                        , btn2: function () {
                            layer.closeAll();
                        },
                        content: '<div style="text-align: center;vertical-align: center;padding-top: 30px">确定要同步绘本当前库存信息？</div>' //这里content是一个普通的String

                    });
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"inventoryOrderTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/inventoryOrder/edit.do?id="+data.id;
                    Common.openDlg(url,"InventoryOrder管理>"+data.id+">编辑");
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