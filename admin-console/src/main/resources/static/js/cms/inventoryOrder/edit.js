layui.define([ 'form', 'laydate', 'table','inventoryOrderApi'], function(exports) {
    var form = layui.form;
    var inventoryOrderApi = layui.inventoryOrderApi;
    var index = layui.index;
    var view = {
        init:function(){
	        Lib.initGenrealForm($("#updateForm"),form);
	        this.initSubmit();
        },
        initSubmit:function(){
            $("#updateButton").click(function(){
                $("#code").removeAttr("disabled");
                $("#existStocks").removeAttr("disabled");
                form.on('submit(form)', function(){
                    inventoryOrderApi.updateInventoryOrder($('#updateForm'),function(){
                       parent.window.dataReload();
                       Common.info("更新成功");
                       Lib.closeFrame();
                    });
                });
            });
            $("#updateButton-cancel").click(function(){
                Lib.closeFrame();
            });
        }
            
    }
    exports('edit',view);
	
});