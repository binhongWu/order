layui.define(['table', 'salesReturnApi'], function(exports) {
    var salesReturnApi = layui.salesReturnApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"salesReturnTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些信息记录?",function(){
            var ids =Common.concatBatchId(data,"returnId");
            salesReturnApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});