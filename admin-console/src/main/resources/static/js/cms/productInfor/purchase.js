layui.define([ 'form', 'laydate', 'table','productInforApi'], function(exports) {
    var form = layui.form;
    var productInforApi = layui.productInforApi;
    var index = layui.index;
    var view = {
        init:function(){
            Lib.initGenrealForm($("#purchaseForm"),form);
            this.initSubmit();
        },
        initSubmit:function(){
            $("#addButton").click(function(){
                $('#code').removeAttr("disabled");
                $('#outPrice').removeAttr("disabled");
                $('#proNum').removeAttr("disabled");
                $('#shouldPrice').removeAttr("disabled");
                $('#realityPrice').removeAttr("disabled");
                if ($('#proNum').val() - $('#existStocks').val() > 0){
                    Common.info("库存量不足");
                    return;
                }
                form.on('submit(form)', function(){
                    productInforApi.purchaseProductInfor($('#purchaseForm'),function(){
                        parent.window.dataReload();
                        Common.info("购买成功");
                        Lib.closeFrame();
                    });
                });
            });
            $("#addButton-cancel").click(function(){
                Lib.closeFrame();
            });
        }
    };
    exports('purchase',view);
});