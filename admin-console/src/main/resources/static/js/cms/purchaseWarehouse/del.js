layui.define(['table', 'purchaseWarehouseApi'], function(exports) {
    var purchaseWarehouseApi = layui.purchaseWarehouseApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"purchaseWarehouseTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些PurchaseWarehouse?",function(){
            var ids =Common.concatBatchId(data,"enterId");
            purchaseWarehouseApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});