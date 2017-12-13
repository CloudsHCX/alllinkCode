$(function() {
	/**
	 * 模拟select
	 **/
	$(".select-header").click(function() {
//		$(this).parent().siblings(".select-box").find(".select-content").slideUp("fast");
		$(".select-header").removeClass("select-arrow");
		$(this).parent().siblings(".select-box").find(".select-content").hide();
		
		if($(this).siblings(".select-content").is(":hidden")) {
			$(this).addClass("select-arrow");
			$(this).siblings(".select-content").slideDown("fast");
		} else {
			$(this).removeClass("select-arrow");
			$(this).siblings(".select-content").hide();
		}

		//去除事件冒泡
		var evt = new Object;
		if(typeof(window.event) == "undefined") { //如果是火狐浏览器
			evt = arguments.callee.caller.arguments[0];
		} else {
			evt = event || window.event;
		}
		evt.cancelBubble = true;
	});
	$(document).click(function() {
		$(".select-header").removeClass("select-arrow");
		$(".select-content").hide();
//		$(".select-content").slideUp("fast");
	});
	$(".select-content li").on("click", function() {
		$(this).parent().siblings(".select-header").removeClass("select-arrow");
		$(this).parent().siblings(".select-header").text($(this).text()).end().hide();
//		$(this).parent().siblings(".select-header").text($(this).text()).end().slideUp("fast");
	});
	$(".select-content li").hover(function() {
		$(this).css("background-color", "wheat");
	}, function() {
		$(this).css("background-color", "#FFFFFF");
	});
});