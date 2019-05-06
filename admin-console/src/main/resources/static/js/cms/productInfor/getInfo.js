layui.define([ 'form', 'laydate', 'table','productInforApi'], function(exports) {
    var form = layui.form;
    var view = {
        init:function(){
	        Lib.initGenrealForm($("#updateForm"),form);
        }
    };
    exports('getInfo',view);
});