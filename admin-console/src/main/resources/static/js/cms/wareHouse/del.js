layui.define(['table', 'wareHouseApi'], function(exports) {
    var wareHouseApi = layui.wareHouseApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"wareHouseTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些WareHouse?",function(){
            var ids =Common.concatBatchId(data,"id");
            wareHouseApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});