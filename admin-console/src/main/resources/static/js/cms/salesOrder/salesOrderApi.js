/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateSalesOrder:function(form,callback){
                Lib.submitForm("/cms/salesOrder/update.json",form,{},callback)
            },
            addSalesOrder:function(form,callback){
                Lib.submitForm("/cms/salesOrder/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/salesOrder/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/salesOrder/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('salesOrderApi',api);
});