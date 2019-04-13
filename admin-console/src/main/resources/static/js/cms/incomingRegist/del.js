layui.define(['table', 'incomingRegistApi'], function(exports) {
    var incomingRegistApi = layui.incomingRegistApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"incomingRegistTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些IncomingRegist?",function(){
            var ids =Common.concatBatchId(data,"inRegistId");
            incomingRegistApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});