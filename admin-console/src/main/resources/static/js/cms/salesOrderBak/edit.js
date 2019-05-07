layui.define([ 'form', 'laydate', 'table','salesOrderBakApi'], function(exports) {
    var form = layui.form;
    var salesOrderBakApi = layui.salesOrderBakApi;
    var index = layui.index;
    var view = {
        init:function(){
	        Lib.initGenrealForm($("#updateForm"),form);
	        this.initSubmit();
        },
        initSubmit:function(){
            $("#updateButton").click(function(){
                form.on('submit(form)', function(){
                    salesOrderBakApi.updateSalesOrderBak($('#updateForm'),function(){
                       parent.window.dataReload();
                       Common.info("申请成功");
                       // Common.info("退货成功");
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