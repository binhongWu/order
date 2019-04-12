layui.define(['table', 'salesOutStackApi'], function(exports) {
    var salesOutStackApi = layui.salesOutStackApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"salesOutStackTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些SalesOutStack?",function(){
            var ids =Common.concatBatchId(data,"salesOutStackId");
            salesOutStackApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});