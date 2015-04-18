
/**
 常用基本JS  需在jquery存在情况下使用
 author ChenJunhui
**/

function isValidateExp(str,exp){
	var reg = str.match(exp);
	  if(reg==null){
	    return false;
	  }
	  else{
	     return true;
	  }
}
function validateEmail(str){
	 
	  var exp=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	  return isValidateExp(str,exp);
}
function validateMobil(str){
	var exp=/^((\(\d{3}\))|(\d{3}\-))?((13)|(15)|(18))\d{9}$/;
	return isValidateExp(str,exp);
}

function validateNumber(str){
	var exp = /^\d+$/;
	return isValidateExp(str,exp);
}

function filterNumberInput(oEvent){
	if(oEvent.keyCode < 48 || oEvent.keyCode >57){
		oEvent.preventDefault();  //如果输入字符不为数字,取消按键事件
	}
}

function validateCurrency(str){
	var exp = /^\d+(\.\d+)?$/;
	return isValidateExp(str,exp);
}

function validatePhone(str){
	var exp = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,8}$/ ;
	return isValidateExp(str,exp);
}

/**
 * 传用逗号隔开的
 * @param iptids
 * @returns
 */
function isCheckDomsAllChecked(names){
	var nameArr = names.split(",");
	for(var i=0;i<nameArr.length;i++){
		var  jqueryDom = $("input[name='"+nameArr[i]+"']:checked");
		if(jqueryDom.size()==0){
			return false;
		}

	}
	return true;
}

/**
 * 给某个id 或 多个ID 加上数字输入过滤条件
 * @author yulang.cjh
 * @param iptids
 */
function doFilterNumberInput(iptids){
	var selectorstr = getMoreIdSelectorStr(iptids);
	$(selectorstr).change(function(){
		if($(this).val() && !validateNumber($(this).val())){
			$(this).val('');
		}
	});
}

function doValidateCurrency(iptdom){
	if(!validateCurrency(iptdom.value)){
		alert("金额格式不正确 必须填写数字 最多2位小数!");
		iptdom.value='';
	}
}


function doFilterCurrencyInput(iptids){
	var selectorstr = getMoreIdSelectorStr(iptids);
	$(selectorstr).change(function(){
		if($(this).val() && !validateCurrency($(this).val())){
			alert("金额必须填写数字!");
			$(this).val('');
		}
	});
}


/**
 * 浮层居中
 * @param divid
 */
function divFloatMiddle(divid){
	$('#'+divid).css({
		'top':($(window).height() - $('#'+divid).height())/2+$(window).scrollTop(),
		'left':($(window).width() - $('#'+divid).width())/2
	});
	$('#'+divid).show();
}

function hideDom(domid){
	$('#'+domid).hide();
}

function getSelRadioValue(radioName){
	var domRadio = document.getElementsByName(radioName); 
	for(var i=0;i<domRadio.length;i++){
		if(domRadio[i].checked){
			return domRadio[i].value;
		}
	}
	return null;
}

function getNextSibling(elem){
	var b = elem.parentNode.children;
	for(var i=0;i<b.length;i++){
		if(b[i]!==elem){
		   return b[i];
		}
	}
	return null;
}

function removAlleSelect(selId){
	var selDomObj = document.getElementById(selId);
	for(var i=selDomObj.length-1;i>=0;i--){
		selDomObj.remove(i);
	}
}

function setCheckedValue(domId,_value){
	var checkDomObj = document.getElementById(domId);
	if(checkDomObj.checked){
		checkDomObj.value = _value;
	}
}

/**
 * 多选框选中的值按split劈开成字符串
 * @param checkBoxDomName
 * @param split
 * @returns
 */
function  getCheckedBoxValues(checkBoxDomName,split){
	var checked_str = "";
	var theCheckBoxs = document.getElementsByName(checkBoxDomName);
	for(var i=0;i<theCheckBoxs.length;i++){
		if(theCheckBoxs[i].checked)
			 checked_str=checked_str+","+theCheckBoxs[i].value;
	}
	return checked_str==""?"":checked_str.substring(1);
}

function changeAllcheckecBox(checkBoxDomName,_checked){
	var checkboxdom = document.getElementsByName(checkBoxDomName);
	for(var i=0;i<checkboxdom.length;i++){
		checkboxdom[i].checked=_checked;
	}
}


/**
 * 组装多个id jquery 选择器字符串
 * @author ChenJunhui
 * @param _ids
 * @returns {String}
 */
function getMoreIdSelectorStr(_ids){
	var selectorstr = "";
	if(typeof _ids == 'string'){
		selectorstr="#"+_ids;
	}else{
		for(var i=0;i<_ids.length;i++){
			selectorstr=selectorstr+",#"+_ids[i];
		}
		selectorstr=selectorstr.substring(1);
	}
	return selectorstr;
}

/**
 * 给多个ID对应的input元素 过滤空格值
 * @author ChenJunhui
 * @param selectorIds
 */
function trimByInputSelectorId(selectorIds){
	if(typeof selectorIds == 'string'){
		$("#"+selectorIds).val($.trim($("#"+selectorIds).val()));
	}else{
		for(var i=0;i<selectorIds.length;i++){
			try{
				$("#"+selectorIds[i]).val($.trim($("#"+selectorIds[i]).val()));
			}catch(ex){}
		}
	}
}


/**
 * 删除数组
 */
Array.prototype.remove=function(dx)
{
  if(isNaN(dx)||dx>this.length){return false;}
  for(var i=0,n=0;i<this.length;i++)
  {
      if(this[i]!=this[dx])
      {
          this[n++]=this[i]
      }
  }
  this.length-=1
}

function changeTwoDecimal(v) {
    if (isNaN(v)) {
        return 0;
    }
    var fv = parseFloat(v);
    fv = Math.round(fv * 100) / 100; 
    var fs = fv.toString();
    var fp = fs.indexOf('.');
    if (fp < 0) {
        fp = fs.length;
        fs += '.';
    }
    while (fs.length <= fp + 2) { 
        fs += '0';
    }
    return fs;
}

function doSumCurrency(domname){
	var iptdom = document.getElementsByName(domname);
	var totalM=0;
	for(var i=0;i<iptdom.length;i++){
		var curvalue = jQuery.trim(iptdom[i].value);
		if(curvalue!=''){
			totalM=totalM+parseFloat(curvalue);
		}
	}
	return changeTwoDecimal(totalM);
}

function doCountAndSetCurrency(domname,viewDomId,zeroReplace){
	var sumValue = doSumCurrency(domname);
	if(sumValue<=0 && typeof zeroReplace!='undefined'){
		sumValue=zeroReplace;
	}
	$("#"+viewDomId).html("<b>"+sumValue+"</b>");
}