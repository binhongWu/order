layui.define(['table', 'productInforApi'], function(exports) {
    var productInforApi = layui.productInforApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"productInforTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些ProductInfor?",function(){
            var ids =Common.concatBatchId(data,"code");
            productInforApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});