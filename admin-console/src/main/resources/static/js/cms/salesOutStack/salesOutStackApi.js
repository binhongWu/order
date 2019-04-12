/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateSalesOutStack:function(form,callback){
                Lib.submitForm("/cms/salesOutStack/update.json",form,{},callback)
            },
            addSalesOutStack:function(form,callback){
                Lib.submitForm("/cms/salesOutStack/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/salesOutStack/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/salesOutStack/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('salesOutStackApi',api);
});