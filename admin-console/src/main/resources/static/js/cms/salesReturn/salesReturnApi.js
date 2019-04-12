/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateSalesReturn:function(form,callback){
                Lib.submitForm("/cms/salesReturn/update.json",form,{},callback)
            },
            addSalesReturn:function(form,callback){
                Lib.submitForm("/cms/salesReturn/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/salesReturn/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/salesReturn/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('salesReturnApi',api);
});