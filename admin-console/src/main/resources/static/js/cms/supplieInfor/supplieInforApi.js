/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateSupplieInfor:function(form,callback){
                Lib.submitForm("/cms/supplieInfor/update.json",form,{},callback)
            },
            addSupplieInfor:function(form,callback){
                Lib.submitForm("/cms/supplieInfor/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/supplieInfor/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/supplieInfor/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('supplieInforApi',api);
});