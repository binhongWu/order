layui.define([ 'form', 'laydate', 'table','salesReturnApi'], function(exports) {
    var form = layui.form;
    var salesReturnApi = layui.salesReturnApi;
    var index = layui.index;
    var view = {
        init:function(){
	        Lib.initGenrealForm($("#updateForm"),form);
	        this.initSubmit();
        },
        initSubmit:function(){
            $("#updateButton").click(function(){
                form.on('submit(form)', function(){

                    salesReturnApi.audioSalesReturn($('#updateForm'),function(){
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