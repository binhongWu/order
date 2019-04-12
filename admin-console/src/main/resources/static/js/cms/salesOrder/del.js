layui.define(['table', 'salesOrderApi'], function(exports) {
    var salesOrderApi = layui.salesOrderApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"salesOrderTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些SalesOrder?",function(){
            var ids =Common.concatBatchId(data,"salesId");
            salesOrderApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});