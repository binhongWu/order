/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateWarehouseWarn:function(form,callback){
                Lib.submitForm("/cms/warehouseWarn/update.json",form,{},callback)
            },
            addWarehouseWarn:function(form,callback){
                Lib.submitForm("/cms/warehouseWarn/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/warehouseWarn/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/warehouseWarn/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('warehouseWarnApi',api);
});