layui.define(['table', 'customerInforApi'], function(exports) {
    var customerInforApi = layui.customerInforApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"customerInforTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些客户信息数据吗?",function(){
            var ids =Common.concatBatchId(data,"clientId");
            customerInforApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});