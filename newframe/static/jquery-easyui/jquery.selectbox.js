(function($){
		$.fn.selectbox = function(){
			//用变量idm存储select的id或name
			var idm = $(this).attr("id") || $(this).attr("name");

			if($("#" + idm + "div").length <= 0){//判断动态创建的div是否已经存在，如果不存在则创建
				var divHtml = "<div style='display:none' id='" + idm + "div'><input type='text' id='" + idm + "inputText'/></div>";
				$(this).attr("tabindex",-1).after(divHtml);
				$("#" + idm + "div").css({position:"absolute",top:$(this).position().top -1 ,left:$(this).position().left}).show();
				$("#" + idm + "inputText").val($(this).val()).css({width:$(this).width()-13,height:$(this).height() + 4});
				//select注册onchange事件
				$(this).change(function(){
						$("#" + idm + "inputText").val($(this).val());	
				})				
			}
			//解决ie6下select浮在div上面的bug
			$("#" + idm + "div").bgIframe();
			return $("#" + idm + "inputText").val();
		}
})(jQuery);