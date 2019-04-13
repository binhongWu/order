/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateOutboundRedist:function(form,callback){
                Lib.submitForm("/cms/outboundRedist/update.json",form,{},callback)
            },
            addOutboundRedist:function(form,callback){
                Lib.submitForm("/cms/outboundRedist/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/outboundRedist/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/outboundRedist/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('outboundRedistApi',api);
});