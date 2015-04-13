var $middleObj = {}, $imgArr = $("#imgArr"), $Objid = $("#Objid"), $imageUrl = $(
		"#image").val();

// 上传完成之后执行的事件函数
function uploadDoneCallback() {
	var fileProgressId = this.id, $viewBox = $("#viewImg"), $progressWrap = $("#"
			+ fileProgressId);

	$progressWrap.attr("fileDataId", arguments[0]);

	// 下面可以进行填充了
	var $result = arguments[0];
	var _result = eval("(" + $result + ")");
	$progressWrap.attr("fileUrl", _result.fileUrl);
	// $len++;
	// console.log($len);
	// 设置上传文件数量的限制
	// if ($total - $len === 0) {
	// swfu.setButtonDisabled(true);
	// } else {
	// swfu.setFileUploadLimit($total - $len);
	//     
	// swfu.setButtonDisabled(false);
	// }

	// 添加成功，把值保存近添加的对象里面
	$middleObj["Insert" + fileProgressId] = _result.fileUrl + ":"
			+ _result.fileName;
	var str = "";
	for ( var k in $middleObj) {
		str += k + ":" + $middleObj[k] + ",";
	}
	$imgArr.val(str);
	$("div.progressWrapper").mouseover(function(){
		var $this = $(this), $id = $this.attr("id");
		if($id==fileProgressId){
			var imageUrl = $imageUrl;
			jQuery("#imageLook").attr("style", "width:180px;height:100px");
			jQuery("#imageLook").attr("src", imageUrl+"/tmp/" + _result.fileUrl);
			jQuery("#sub").attr("src", imageUrl+"/tmp/" + _result.fileUrl);
		}
	});
}

// 自定义事件——删除中的文件
function cancelUploadCallback() {

	var $this = this, fileProgressId = this.fileProgressID, $progressId = $("#"
			+ arguments[0]), $fileDataKey = $progressId.attr("fileUrl");
	// 异步删除上传的文件
	$.ajax({
		url : "/ajaxswf/upLoadFile",
		type : "post",
		data : {
			"fileUrl" : $fileDataKey,
			"cmdOperate" : "delete"
		},
		success : function(msg) {
			var $result = eval("(" + msg + ")");
			if ($result.msg != "删除失败") {
				$("#" + fileProgressId + "img").remove();
				delete $middleObj["Insert" + fileProgressId];
				jQuery("#imageLook").attr("src", "");
				jQuery("#imageLook").attr("style", "display:none");
				// $len--;
				// //设置上传文件数量的限制
				// if ($total - $len === 0) {
				// swfu.setButtonDisabled(true);
				// } else {
				// swfu.setFileUploadLimit($total - $len);
				//                  
				// swfu.setButtonDisabled(false);
				// }
				// console.log($total - $len);
				var str = "";
				for ( var k in $middleObj) {
					str += k + ":" + $middleObj[k] + ",";
				}
				$imgArr.val(str);
				$this.setCancelled();
				// cancelUpload
				
			}
		},
		error : function() {
			$this.setStatus("从数据库删除失败!");
			$this.setCancelled();
		}
	});
}

// 修改判断是否已经上传过图片
(function editUpload() {

	if ($imgArr.val() == "")
		return;
	var $imgaddress = eval("(" + $imgArr.val() + ")"), viewimgHtml = "", _len = $imgaddress.length;
	for (var i = 0; i < _len; i++) {
		$middleObj[$imgaddress[i]["fileID"]] = $imgaddress[i]["fileUrl"] + ":"
				+ $imgaddress[i]["fileName"];
		viewimgHtml += '<div class="progressWrapper" data-url="'
				+ $imgaddress[i]["fileUrl"]
				+ '"id="'
				+ $imgaddress[i]["fileID"]
				+ '" style="opacity: 1;" ><div class="progressContainer progressSuccess"><b class="progressTypes t'
				+ $imgaddress[i]["fileType"]
				+ '"></b><span class="progressName" title="'
				+ $imgaddress[i]["fileName"]
				+ '">'
				+ $imgaddress[i]["fileName"]
				+ '</span><span class="progressSize" style="display: block;">'
				+ $imgaddress[i]["fileSize"]
				+ '</span><a data-id="'
				+ $imgaddress[i]["fileID"]
				+ '" data-url="'
				+ $imgaddress[i]["fileUrl"]
				+ '" data-name="'
				+ $imgaddress[i]["fileName"]
				+ '" class="progressDel uploadDel" href="javascript:void(0);" style="display: block;">删除</a></div></div>';
	}
	$("#viewImg").append(viewimgHtml);
	var _str = "";
	for ( var k in $middleObj) {
		_str += k + ":" + $middleObj[k] + ",";
	}
	$imgArr.val(_str);
	// 修改删除
	$("#viewImg")
			.delegate(
					"a.progressDel",
					"click",
					function() {
						var $this = $(this), $id = $this.attr("data-id"), $fileurl = $this
								.attr("data-url");
						$fileName = $this.attr("data-name");

						$.ajax({
							url : "/ajaxswf/upLoadFile",
							type : "post",
							data : {
								"fileUrl" : $fileurl,
								"cmdOperate" : "delete"
							},
							success : function(msg) {
								var $result = eval("(" + msg + ")");
								// swfu.setFileUploadLimit($total - ($len - 1));

								if ($result.msg != "删除失败") {
									$("#" + $id).remove();

									// delete $middleObj[$id];
									$middleObj["Delete" + $id] = $fileurl + ":"
											+ $fileName;

									// 设置上传文件数量的限制
									// $len--;
									// if ($total - $len === 0) {
									// swfu.setButtonDisabled(true);
									// } else {
									// swfu.setFileUploadLimit($total - $len);
									//                   
									// swfu.setButtonDisabled(false);
									// }
									var str = "";
									for ( var k in $middleObj) {
										str += k + ":" + $middleObj[k] + ",";
									}
									// console.log($middleObj);
									$imgArr.val(str);
									jQuery("#imageLook").attr("src", "");
									jQuery("#imageLook").attr("style", "display:none");
								}
							},
							error : function() {
								$this.setStatus("从数据库删除失败!");
								$this.setCancelled();
							}
						});
					});

	// 查看
	$("#viewImg").delegate("div.progressWrapper", "mouseover", function() {
		var $this = $(this), $id = $this.attr("id");
		var $this = $(this), $fileurl = $this.attr("data-url");
		var fileurl = $fileurl;
		var imageUrl = $imageUrl;
		jQuery("#imageLook").attr("style", "width:180px;height:100px");
		jQuery("#imageLook").attr("src", imageUrl + fileurl);
		jQuery("#sub").attr("src", imageUrl + fileurl);
	});
	
})();
