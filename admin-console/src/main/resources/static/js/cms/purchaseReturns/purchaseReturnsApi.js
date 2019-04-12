/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updatePurchaseReturns:function(form,callback){
                Lib.submitForm("/cms/purchaseReturns/update.json",form,{},callback)
            },
            addPurchaseReturns:function(form,callback){
                Lib.submitForm("/cms/purchaseReturns/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/purchaseReturns/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/purchaseReturns/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('purchaseReturnsApi',api);
});