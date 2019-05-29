layui.define([ 'form', 'laydate', 'table','roleApi'], function(exports) {
	var form = layui.form;
	var laydate = layui.laydate;
	var table = layui.table;
	var zTreeObj = null;
	var roleApi = layui.roleApi;
	var checkedRoleId=null;
	var view ={
		
		init:function(roleId){
			this.initFunctionTree();
			this.initSaveFunction();
			
		},
		/*加载功能点树*/
		initFunctionTree:function(){
			var that = this;
			$.post(Common.ctxPath + "/admin/function/tree.json", {}, function(response) {
				var zNodes = response.data;
				var setting  = {"check":{"radioType":true,"enable":true}};
				zTreeObj = $.fn.zTree.init($("#functionTree"), setting, zNodes);
				//功能树加载完毕后在初始化其他控件
				that.initFirstRole();
				that.initRoles();
			
			})
		},
        /*保存授权配置*/
		initSaveFunction:function(){
			$("#save").click(function(){
                console.log(456);
				var nodes = zTreeObj.getCheckedNodes(true);
				var ids = Common.concatBatchId(nodes,"id")
				roleApi.saveFunctions(checkedRoleId,ids)
				
			});
		},
        /*初始加载默认获取列表的第一个角色的被授权的功能点（可为全空）*/
		initFirstRole:function(){
			var firstRole=$(".layui-form").find(":radio")[0]
			checkedRoleId= $(firstRole).val();
			this.loadFunctionByRole(checkedRoleId);
			$(firstRole).prop("checked",true);
			form.render();
			
		},
        /*监听表单的操作，即角色的选择，加载这个角色被授权的功能（可为全空）*/
        initRoles:function(){
            var that = this;
            form.on('radio(roleId)', function(data){
                checkedRoleId = data.value; //被点击的radio的value值
                that.loadFunctionByRole(checkedRoleId);
            })

        },
		/*传递给后台选中的角色被授权了哪些功能，回显再也页面（页面的对应功能被选中）*/
		loadFunctionByRole:function(roleId){
//			debugger;
			var nodes = zTreeObj.getCheckedNodes(true);
			for(var i=0;i<nodes.length;i++){
				zTreeObj.checkNode(nodes[i], false, true);
			}
			roleApi.queryFunctionByRole(roleId,function(fnIds){
				for(var i=0;i<fnIds.length;i++){
					var node = zTreeObj.getNodesByParam("id",fnIds[i]);
					zTreeObj.checkNode(node[0], true, false);
				}
			});
		}
		
	}
	
	
	
	

	 exports('roleFn',view);
	
});