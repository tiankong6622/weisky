(function($){
		$.fn.selectbox = function(){
			//�ñ���idm�洢select��id��name
			var idm = $(this).attr("id") || $(this).attr("name");

			if($("#" + idm + "div").length <= 0){//�ж϶�̬������div�Ƿ��Ѿ����ڣ�����������򴴽�
				var divHtml = "<div style='display:none' id='" + idm + "div'><input type='text' id='" + idm + "inputText'/></div>";
				$(this).attr("tabindex",-1).after(divHtml);
				$("#" + idm + "div").css({position:"absolute",top:$(this).position().top -1 ,left:$(this).position().left}).show();
				$("#" + idm + "inputText").val($(this).val()).css({width:$(this).width()-13,height:$(this).height() + 4});
				//selectע��onchange�¼�
				$(this).change(function(){
						$("#" + idm + "inputText").val($(this).val());	
				})				
			}
			//���ie6��select����div�����bug
			$("#" + idm + "div").bgIframe();
			return $("#" + idm + "inputText").val();
		}
})(jQuery);