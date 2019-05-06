layui.define([ 'form', 'laydate', 'table','productInforApi'], function(exports) {
    var form = layui.form;
    var productInforApi = layui.productInforApi;
    var index = layui.index;
    var view = {
        init:function(){
            Lib.initGenrealForm($("#updateForm"),form);
            this.initSubmit();
        },
        initSubmit:function(){
            $("#updateButton").click(function(){
                form.on('submit(form)', function(){
                    productInforApi.updateProductInfor($('#updateForm'),function(){
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
    };
    exports('purchase',view);
});