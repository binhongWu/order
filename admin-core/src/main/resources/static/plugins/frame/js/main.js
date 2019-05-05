/**
 * @Description: 主页面
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.6.1
 * @License：MIT
 */

layui.use(['layer','fsTab',"element","form","jquery"], function(){
	var element = layui.element;
	var fsTab = layui.fsTab;
	var form = layui.form;
	intiCompanySelect();
	var jquery = layui.jquery;

	//初始化显示菜单
	showMenu($("#fsTopMenu li.layui-this").attr("dataPid"));


	window.addEventListener("hashchange", hashChanged, false);

	hashChanged();

    var layId = $.uuid();
    addTab('首页',Common.ctxPath + '/cms/productInfor/index.do',layId);
    element.tabDelete('fsTab', '0');
    element.tabChange('fsTab', layId);
	
	function intiCompanySelect(){
		$("#changeCompanyButton").click(function(){
			layer.open({
				  type: 1
				  ,title: false //不显示标题栏
				  ,closeBtn: false
				  ,area: ['500px', '300px']
				  ,shade: 0.8
				  ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
				  ,resize: false
				  ,btn: ['确定', '取消']
				  ,btnAlign: 'c'
				  ,moveType: 1 //拖拽模式，0或者1
				  ,content: $("#selectCompany")
				  ,yes: function(index,layero){
					  var orgId = $("#companyList").val();
					  window.location.href=Common.ctxPath+"/changeOrg.do?orgId="+orgId;
				  }
				});
		});
        $("#changePassword").click(function(){
            layer.open({
                type: 1
                ,title: false //不显示标题栏
                ,closeBtn: false
                ,area: ['400px', '250px']
                ,shade: 0.8
                ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,resize: false
                ,btn: ['就改成这个密码', '不改了']
                ,btnAlign: 'c'
                ,moveType: 1 //拖拽模式，0或者1
                ,content: '<div id = "passwordFrom" style="padding: 20px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
				'你知道吗？亲！' +
				'<br>我们此后的征途是星辰大海 ^_^ 请认真修改密码' +
				'<br>' +
                '<label class="layui-form-label">旧密码</label>' +
                '<div class="layui-input-inline">' +
                '<input id = "password" type="password" name="password" lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">' +
                '</div>' +
                '<label class="layui-form-label">新密码</label>' +
                '<div class="layui-input-inline">' +
                '<input id = "newPassword" type="password" name="newPassword" lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">' +
                '</div>' +
				'</div>'
                ,yes: function(index,layero){
                	var password = $("#password").val();
                	var newPassword = $("#newPassword").val();
                    Common.post("/core/user/changePassword.json", {"password":password,"newPassword":newPassword}, function(data){
                        Common.success("密码修改成功");
                        setTimeout(function(){layer.closeAll();}, 1000);
                    });
                }
            });
        });
	}

	function hashChanged(){
		//获取路由信息
		var hash = window.location.hash;
		if(!$.isEmpty(hash) && hash.length>1){
			var menuId = hash.substring(1);
			//获取layId
			var dom = $('#fsLeftMenu a[menuId="'+ menuId +'"]').parent();
			if(dom.length>0){
				var layId = dom.attr("lay-id");
				if($.isEmpty(layId)){
					layId = $.uuid();
					dom.attr("lay-id",layId);
					fsTab.add(dom.find("a").html(),dom.find("a").attr("dataUrl"),layId);
				}
				fsTab.tabChange(layId);

				fsTab.menuSelectCss(layId);
			}
		}
	}


	$("#fsTopMenu li").bind("click",function(){
		var dataPid = $(this).attr("dataPid");
		showMenu(dataPid);
	});


	//显示菜单
	function showMenu(dataPid){
		if(!$.isEmpty(dataPid)){
			$('#fsLeftMenu>li').hide();
			$('#fsLeftMenu>li[dataPid="'+ dataPid +'"]').show();
		}
	}

	//渲染tab
	fsTab.render();

	//新增tab
	function addTab(title,dataUrl,layId){
		fsTab.add(title,dataUrl,layId);
	}

	//手机设备的简单适配
	var treeMobile = $('.site-tree-mobile'),
	shadeMobile = $('.site-mobile-shade')

	treeMobile.on('click', function(){
		$('body').addClass('site-mobile');
	});

	shadeMobile.on('click', function(){
		$('body').removeClass('site-mobile');
	});


	//菜单绑定

	$(".fsSwitchMenu").on("click",function(){
		if($(this).find("i.icon-category").length>0){
			$(this).find("i").removeClass("icon-category").addClass("icon-viewgallery");
		}else{
			$(this).find("i").removeClass("icon-viewgallery").addClass("icon-category");
		}
		$(".layui-layout-admin").toggleClass("showMenu");
	});


    /**
     * 注册tab右键菜单点击事件
     */
    jquery(".rightmenu li").click(function (e) {
        if (jquery(this).attr("data-type") == "closethis") {
            // var tabid = $("li[class='layui-this']").attr('lay-id');// 获取当前激活的选项卡ID
            var tabid=$(".rightmenu").attr("menu_id");
            tabDelete(tabid);
        } else if (jquery(this).attr("data-type") == "closeall") {
            var tabtitle = jquery(".layui-tab-title li");
            var ids = new Array();
            jquery.each(tabtitle, function (i) {
                ids[i] = $(this).attr("lay-id");
            })
            tabDeleteAll(ids);
        } else if (jquery(this).attr("data-type") == "closeother") {
            var tabtitle = jquery(".layui-tab-title li");
            var ids = new Array();
            // var tabid = $("li[class='layui-this']").attr('lay-id');// 获取当前激活的选项卡ID
            var tabid=$(".rightmenu").attr("menu_id");
            jquery.each(tabtitle, function (i) {
            	var nId =$(this).attr("lay-id");
            	if(nId!=tabid){
                    ids[i] = nId;
                }
            })
            tabDeleteAll(ids);
        }
        jquery('.rightmenu').hide();
    })

    tabDelete = function (id) {
        console.log("删除的TabID："+id);
        element.tabDelete("fsTab", id);//删除
        jquery("[lay-id='"+id+"']").removeAttr("lay-id");
    }
    tabDeleteAll = function (ids) {
        jquery.each(ids, function (i, item) {
        	if(item!=null){
                element.tabDelete("fsTab", item);
                jquery("[lay-id='"+item+"']").removeAttr("lay-id");
            }
        })
    }

    // 点击空白处关闭右键弹窗
    jquery(document).click(function () {
        jquery('.rightmenu').hide();
    })

});

