/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateCustomerInfor:function(form,callback){
                Lib.submitForm("/cms/customerInfor/update.json",form,{},callback)
            },
            addCustomerInfor:function(form,callback){
                Lib.submitForm("/cms/customerInfor/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/customerInfor/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
            ,
            exportExcel:function(form,callback){
                var formPara = form.serializeJson();
                Common.post("/cms/customerInfor/excel/export.json", formPara, function(fileId) {
                    callback(fileId);
                })
            }
		
    };
    exports('customerInforApi',api);
});