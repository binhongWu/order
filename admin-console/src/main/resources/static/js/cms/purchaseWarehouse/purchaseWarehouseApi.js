/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updatePurchaseWarehouse:function(form,callback){
                Lib.submitForm("/cms/purchaseWarehouse/update.json",form,{},callback)
            },
            addPurchaseWarehouse:function(form,callback){
                Lib.submitForm("/cms/purchaseWarehouse/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/purchaseWarehouse/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/purchaseWarehouse/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('purchaseWarehouseApi',api);
});