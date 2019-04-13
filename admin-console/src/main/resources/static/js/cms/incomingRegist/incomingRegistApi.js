/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateIncomingRegist:function(form,callback){
                Lib.submitForm("/cms/incomingRegist/update.json",form,{},callback)
            },
            addIncomingRegist:function(form,callback){
                Lib.submitForm("/cms/incomingRegist/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/incomingRegist/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/incomingRegist/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('incomingRegistApi',api);
});