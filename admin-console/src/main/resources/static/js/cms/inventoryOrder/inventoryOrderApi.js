/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateInventoryOrder:function(form,callback){
                Lib.submitForm("/cms/inventoryOrder/update.json",form,{},callback)
            },
            addInventoryOrder:function(form,callback){
                Lib.submitForm("/cms/inventoryOrder/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/inventoryOrder/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/inventoryOrder/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('inventoryOrderApi',api);
});