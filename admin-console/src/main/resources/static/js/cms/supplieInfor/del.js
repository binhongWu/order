layui.define(['table', 'supplieInforApi'], function(exports) {
    var supplieInforApi = layui.supplieInforApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"supplieInforTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些SupplieInfor?",function(){
            var ids =Common.concatBatchId(data,"supplierId");
            supplieInforApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});