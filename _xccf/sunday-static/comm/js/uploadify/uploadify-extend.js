/**
 * 初始化 uploadify 上传文件框
 * author ChenJunhui
 * @param fileInputIds  uploadify对应得 input file框的ID  多个用逗号隔开
 * @param ismulti上传多个还是一个 true or false
 * @param _uploadLimit 最大上传数量 
 * @param _fileSizeLimit 最大上传文件尺寸
 * @param _fileTypeExts 限制的文件类型 '*.gif; *.jpg; *.png' 默认 不填 '*.*'
 */
function initUploadifys(uploadid,ismulti,_uploadLimit,_fileSizeLimit,_fileTypeExts){
	  if(typeof _fileTypeExts=='undefined'){
		  _fileTypeExts='*.*';
	  }
	  $("#"+uploadid).uploadify({  
	    	'multi' : ismulti,
	    	'uploadLimit' : _uploadLimit,
	    	'fileTypeExts' : _fileTypeExts,
	        method   : 'post',  
	        swf  : '/uploadify.swf',
	        uploader: '/uploadify',
	        fileObjName : 'file',
	        successTimeout : 800,
	        removeCompleted : false,
	        fileSizeLimit : _fileSizeLimit+'MB',  
	        buttonText : '选择文件',  
	        height : 20,  
	        width : 120 ,
	        'onUploadSuccess' : function(file, data, response) {
	        			var upfobj = $("#"+uploadid).data('uploadify').queueData.files[file.id];
	        			if(typeof upfobj !='undefined' && upfobj){
	        				upfobj.filereponse=data;
	        			}
	        			console.log(data);
	        			return data;
	        }
	    });  
}

function bindUploadifyToForm(uploadid,hiddenfield){
	var upfobj = $("#"+uploadid).data('uploadify').queueData;
	var imgvalue = [];
	if(typeof upfobj !='undefined' && upfobj){
		for(var k in upfobj.files){
			imgvalue.push(upfobj.files[k].filereponse);
		}
	}
	$("#"+hiddenfield).val(imgvalue.join(","));
}