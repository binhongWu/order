/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateProductInfor:function(form,callback){
                Lib.submitForm("/cms/productInfor/update.json",form,{},callback)
            },
            addProductInfor:function(form,callback){
                Lib.submitForm("/cms/productInfor/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/productInfor/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/productInfor/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('productInforApi',api);
});