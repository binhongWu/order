/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateWareHouse:function(form,callback){
                Lib.submitForm("/cms/wareHouse/update.json",form,{},callback)
            },
            addWareHouse:function(form,callback){
                Lib.submitForm("/cms/wareHouse/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/wareHouse/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/wareHouse/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('wareHouseApi',api);
});