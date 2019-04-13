layui.define(['table', 'warehouseWarnApi'], function(exports) {
    var warehouseWarnApi = layui.warehouseWarnApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"warehouseWarnTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些WarehouseWarn?",function(){
            var ids =Common.concatBatchId(data,"warningId");
            warehouseWarnApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});