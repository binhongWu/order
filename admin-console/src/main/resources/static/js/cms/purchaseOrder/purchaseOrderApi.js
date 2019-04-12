/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updatePurchaseOrder:function(form,callback){
                Lib.submitForm("/cms/purchaseOrder/update.json",form,{},callback)
            },
            addPurchaseOrder:function(form,callback){
                Lib.submitForm("/cms/purchaseOrder/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/purchaseOrder/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/purchaseOrder/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('purchaseOrderApi',api);
});