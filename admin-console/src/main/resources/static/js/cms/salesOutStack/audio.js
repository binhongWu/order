layui.define([ 'form', 'laydate', 'table','salesOutStackApi'], function(exports) {
    var form = layui.form;
    var salesOutStackApi = layui.salesOutStackApi;
    var index = layui.index;
    var view = {
        init:function(){
	        Lib.initGenrealForm($("#updateForm"),form);
	        this.initSubmit();
        },
        initSubmit:function(){
            $("#updateButton").click(function(){
                form.on('submit(form)', function(){
                    salesOutStackApi.audioSalesOutStack($('#updateForm'),function(){
                       parent.window.dataReload();
                       Common.info("审核完毕");
                       Lib.closeFrame();
                    });
                });
            });
            $("#updateButton-cancel").click(function(){
                Lib.closeFrame();
            });
        }
            
    }
    exports('audio',view);
	
});