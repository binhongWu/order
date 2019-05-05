/**
 * @Description: 菜单管理
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @version 1.6.1
 * @License：MIT
 */
layui.define(['element',"jquery"], function(exports){
    var jquery = layui.jquery;
    var element = layui.element,

  FsTab = function (){
  	this.config = {
  		topMenuFilter:"fsTopMenu",//头部菜单
			leftMenuFilter:"fsLeftMenu",//左边菜单
			tabFilter:"fsTab"//导航栏
		}
	};
	
	FsTab.prototype.render = function(options){
		var thisTab = this;
    $.extend(true, thisTab.config, options);
    
    thisTab.bindDeleteFilter();
    
    thisTab.bindTabFilter();
    
    //绑定左边菜单点击。
    element.on('nav('+thisTab.config.leftMenuFilter+')', function(elem){
	  	var layId = $(elem).attr("lay-id");
	  	if($.isEmpty(layId)){
	  		layId = $.uuid();
	  		$(elem).attr("lay-id",layId);
	  		var dom =$(elem).find("a");
	  		var title = $(elem).html();
	  		var dataUrl = $(elem).attr("dataUrl");
	  		if(!$.isEmpty(dataUrl)){
	  			thisTab.add(title,dataUrl,layId);
	  		}
	  	}
	  	thisTab.tabChange(layId);
	  	$('body').removeClass('site-mobile');
    });


	};
	
	/**
	 * 切换tab
	 */
	FsTab.prototype.tabChange = function(layId) {
		element.tabChange(this.config.tabFilter, layId);
	}
	
  
	/**
   * 新增
   */
	FsTab.prototype.add = function(title,dataUrl,layId) {
		element.tabAdd(this.config.tabFilter, {
		  title: title
		  ,content: '<iframe src="'+dataUrl+'"></iframe>' //支持传入html
		  ,id: layId
		});
        jquery(".layui-tab-title li").on('contextmenu', function (e) {
            $('.rightmenu').hide();
            $('.rightmenu').attr("menu_id",$(this).attr("lay-id"));
            var popupmenu = $(".rightmenu");
            popupmenu.css({left: e.clientX-200+10, top: e.clientY-60+10}).show();
            return false;
        });
	};
  
  
	/**
   * 删除监听
   */
	FsTab.prototype.bindDeleteFilter = function(){
		element.on('tabDelete('+this.config.tabFilter+')', function(data){
	  	var layId = $(this).parentsUntil().attr("lay-id");
	  	// $('.fsMenu .layui-nav-child>dd[lay-id="'+ layId +'"],.fsMenu>li[lay-id="'+ layId +'"]').removeAttr("lay-id");
            jquery("[lay-id='"+layId+"']").removeAttr("lay-id");
		});
	}
	
	/**
	 * 监听tab切换，处理菜单选中
	 */
	FsTab.prototype.bindTabFilter = function(){
		var thisTab = this;
		element.on('tab('+this.config.tabFilter+')', function(data){
			var layId = $(this).attr("lay-id");
			
			thisTab.menuSelectCss(layId);
			
		});
	}
	
	/**
	 * 菜单选中样式
	 */
	FsTab.prototype.menuSelectCss = function(layId){
		if(!$.isEmpty(layId)){
			$('.fsMenu .layui-this').removeClass("layui-this");//清除样式
			
			var dom =$('.fsMenu .layui-nav-child>dd[lay-id="'+ layId +'"],.fsMenu>li[lay-id="'+ layId +'"]');
			dom.addClass("layui-this");//追加样式
			
			//处理头部菜单
			if(dom.length==1){
				var dataPid = null;
				var tagName = dom.get(0).tagName;
				if(tagName == "LI"){
					dataPid = dom.attr("dataPid");
				}else if(tagName == "DD"){
					dataPid = dom.parentsUntil('li').parent().attr("dataPid");
				}
				if(!$.isEmpty(dataPid)){
					$('#fsTopMenu li[dataPid="'+ dataPid +'"]').click();
				}
			}
		}
	}
	
  
	var fsTab = new FsTab();
  //绑定按钮
	exports("fsTab",fsTab);
  
});