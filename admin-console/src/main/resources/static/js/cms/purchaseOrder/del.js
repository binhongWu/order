layui.define(['table', 'purchaseOrderApi'], function(exports) {
    var purchaseOrderApi = layui.purchaseOrderApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"purchaseOrderTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些PurchaseOrder?",function(){
            var ids =Common.concatBatchId(data,"orderId");
            purchaseOrderApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});