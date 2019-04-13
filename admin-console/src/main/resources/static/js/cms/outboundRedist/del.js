layui.define(['table', 'outboundRedistApi'], function(exports) {
    var outboundRedistApi = layui.outboundRedistApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"outboundRedistTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些OutboundRedist?",function(){
            var ids =Common.concatBatchId(data,"outRegistId");
            outboundRedistApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});