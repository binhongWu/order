layui.define(['table', 'salesOrderBakApi'], function(exports) {
    var salesOrderBakApi = layui.salesOrderBakApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"salesOrderBakTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些SalesOrderBak?",function(){
            var ids =Common.concatBatchId(data,"salesId");
            salesOrderBakApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});