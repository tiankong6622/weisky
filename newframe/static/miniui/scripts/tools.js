/**
 * 遍历form表单 把不为空的值  组装成字符串返回
 * @param _formId 表单ID
 * @param _joinType 组装成字符串时连接符 例如：_joinType是& aaa&bbb&ccc
 */
function formDataToStr(_formId,_joinType){
	var str = "";
	var arr = new Array();
	var obj = document.getElementById(_formId);
	for(i = 0; i < obj.length-1; i++){
		if(obj[i].name != null && obj[i].name!="" && obj[i].name.length>0){
			if(obj[i].value != null && obj[i].value!="" && obj[i].value.length>0){
				arr[arr.length] = obj[i].name+"="+obj[i].value;
			}
		}
	}
	return  arr.join(_joinType);
}