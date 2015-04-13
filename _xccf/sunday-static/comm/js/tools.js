function isValidateExp(str,exp){
	if(str == undefined) 
		return false;
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

function checkuserName(str)
{
    var exp=/^([0-9A-Za-z_]|[\一-\龥]){1,15}$/;
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

function validateCharOrDigit(str){
	var exp = /^(?:[1-9]\d*|0)$/;
	return isValidateExp(str,exp);
}

//判断是正整数及小数
function validateCharNum(str){
	var exp = /^\d+(\.\d+)?$/;
	return isValidateExp(str,exp);
}

//格式化小数num：待格式化的数字； pos：需要保留的小数位数
function FormatFloat(num,pos){
	 var cn = String(Math.round(num*Math.pow(10,pos))/Math.pow(10,pos));
	 var i = cn.indexOf(".");
	 if(cn.indexOf(".")==-1){
	  cn += ".";
	  while(pos>0){
	   cn += "0";
	   pos--;
	  }
	 }else{
	     while(pos >= num.length - i){
	   cn += "0";
	   pos--;
	     }
	 }
	 return cn;
}

/**
 * 给某个id 或 多个ID 加上数字输入过滤条件
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
 * 按下标删除数组
 */
Array.prototype.remove=function(dx){
  if(isNaN(dx)||dx>this.length){return false;}
  for(var i=0,n=0;i<this.length;i++)
  {
      if(this[i]!=this[dx])
      {
          this[n++]=this[i];
      }
  }
  this.length-=1;
};

/**
 * 把参数的ID都换成金额校验
 * @param iptids
 */
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

/**
 * 按name获取单选框选中的值
 * @param radioName
 * @returns
 */
function getSelRadioValue(radioName){
	var domRadio = document.getElementsByName(radioName); 
	for(var i=0;i<domRadio.length;i++){
		if(domRadio[i].checked){
			return domRadio[i].value;
		}
	}
	return null;
}

/**
 * 给单选框多选框赋值
 * @param domId
 * @param _value
 */
function setCheckedValue(domId,_value){
	var checkDomObj = document.getElementById(domId);
	if(checkDomObj.checked){
		checkDomObj.value = _value;
	}
}

/**
 * 获得多选框的选中值 用逗号隔开
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

/**
 * 将一个name=checkBoxDomName的对选框 选择或反选 
 * @param checkBoxDomName
 * @param _checked true or false
 */
function changeAllcheckecBox(checkBoxDomName,_checked){
	var checkboxdom = document.getElementsByName(checkBoxDomName);
	for(var i=0;i<checkboxdom.length;i++){
		checkboxdom[i].checked=_checked;
	}
}

jQuery.fn.form2json = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

/**
 * 获取两个merge过的json对象并返回
 * @param _jsonObj1
 * @param _jsonObj2
 * @returns
 */
function getMergeJsonObject(_jsonObj1,_jsonObj2){
	if(typeof _jsonObj1=='undefined' || typeof _jsonObj2=='undefined'){
		return;
	}
	for(_jsonKey in _jsonObj2){
		eval("_jsonObj1."+_jsonKey+"=_jsonObj2[_jsonKey]");
	}
	return _jsonObj1;
}

/**
 * merge 两个json对象
 * @param _jsonObj1
 * @param _formid
 * @returns
 */
function mergeFormToJson(_jsonObj1,_formid){
	if(typeof _jsonObj1=='undefined' || typeof _formid=='undefined' || _formid==''){
		return;
	}
	return getMergeJsonObject(_jsonObj1,$("#"+_formid).form2json());
}

/**
 * 根据ID清空下拉选择框
 * @param selId
 */
function removAlleSelect(selId){
	var selDomObj = document.getElementById(selId);
	for(var i=selDomObj.length-1;i>=0;i--){
		selDomObj.remove(i);
	}
}

/**
 * 获得长度为len的随机字符串
 * @param len
 * @returns {String}
 */
function getJsRandomStr(len) {
    len = len || 32;
    var _chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz';
    var maxPos = _chars.length;
    var pwd = '';
    for (var i = 0; i < len; i++) {
        pwd += _chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

/**
 * 获得一个dom元素的下一个兄弟节点
 * @param elem
 * @returns
 */
function getNextSibling(elem){
	var b = elem.parentNode.children;
	for(var i=0;i<b.length;i++){
		if(b[i]!==elem){
		   return b[i];
		}
	}
	return null;
}

/**
 * 将name等于domname输入框中的数字值全部相加
 * @param domname
 * @returns
 */
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

function getFmtDates(domnames){
	var totalMs=domnames.substr(0, 10);
	return totalMs;
}
