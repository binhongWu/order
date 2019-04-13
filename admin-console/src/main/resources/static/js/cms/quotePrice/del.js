layui.define(['table', 'quotePriceApi'], function(exports) {
    var quotePriceApi = layui.quotePriceApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"quotePriceTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些QuotePrice?",function(){
            var ids =Common.concatBatchId(data,"quoteId");
            quotePriceApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});