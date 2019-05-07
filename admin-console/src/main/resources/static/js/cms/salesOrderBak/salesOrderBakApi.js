/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateSalesOrderBak:function(form,callback){
                Lib.submitForm("/cms/salesOrderBak/update.json",form,{},callback)
            },
            addSalesOrderBak:function(form,callback){
                Lib.submitForm("/cms/salesOrderBak/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/salesOrderBak/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('salesOrderBakApi',api);
});