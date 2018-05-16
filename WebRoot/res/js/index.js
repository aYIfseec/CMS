var $,tab,dataStr,layer;
layui.config({
	base : "../res/js/"
}).extend({
	"bodyTab" : "bodyTab"
});
layui.use(['bodyTab','form','element','layer','jquery'],function(){
	var form = layui.form,
		element = layui.element;
		$ = layui.$;
    	layer = parent.layer === undefined ? layui.layer : top.layer;
		tab = layui.bodyTab({
			openTabNum : "7",  //最大可打开窗口数量
			//url : "json/navs.json" //获取菜单json地址
		});
		
		$(".clickdd").click(function(){
			var id = $(this).attr('id');
			if(id == "dd1"){
				$('#pcdd1').click();
			} else if (id == "dd2"){
				$('#pcdd2').click();
			} else if (id == "dd3") {
				$('#pcdd3').click();
			} else if (id == "dd4") {
				$('#pcdd4').click();
			} else if (id == "dd5") {
				$('#pcdd5').click();
			}
		});

	// 添加新窗口
	$("body").on("click",".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')",function(){
		//如果不存在子级
		if($(this).siblings().length == 0){
			addTab($(this));
			$('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
		}
		$(this).parent("li").siblings().removeClass("layui-nav-itemed");
	});
});

//打开新窗口
function addTab(_this){
	tab.tabAdd(_this);
}