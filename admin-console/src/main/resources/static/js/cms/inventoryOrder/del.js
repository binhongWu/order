layui.define(['table', 'inventoryOrderApi'], function(exports) {
    var inventoryOrderApi = layui.inventoryOrderApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"inventoryOrderTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些InventoryOrder?",function(){
            var ids =Common.concatBatchId(data,"id");
            inventoryOrderApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});