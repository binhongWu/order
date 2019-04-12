layui.define(['table', 'purchaseReturnsApi'], function(exports) {
    var purchaseReturnsApi = layui.purchaseReturnsApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"purchaseReturnsTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些PurchaseReturns?",function(){
            var ids =Common.concatBatchId(data,"returnedId");
            purchaseReturnsApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});