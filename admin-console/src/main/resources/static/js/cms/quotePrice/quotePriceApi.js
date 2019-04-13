/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateQuotePrice:function(form,callback){
                Lib.submitForm("/cms/quotePrice/update.json",form,{},callback)
            },
            addQuotePrice:function(form,callback){
                Lib.submitForm("/cms/quotePrice/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/quotePrice/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/quotePrice/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('quotePriceApi',api);
});