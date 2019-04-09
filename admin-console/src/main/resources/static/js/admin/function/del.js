layui.define(['table', 'functionApi'], function(exports) {
	var functionApi = layui.functionApi;
	var table=layui.table;
	var view = {
			init:function(){
				
			},
			delBatch:function(){
				var data = Common.getMoreDataFromTable(table,"functionTable");
				if(data==null){
					return ;
				}
				Common.openConfirm("确认删除?",function(){
					var ids =Common.concatBatchId(data);
					functionApi.del(ids,function(){
						Common.info("删除成功");
						dataReload();
					})
				})
				
			}
				
	}
	 exports('del',view);
	
});