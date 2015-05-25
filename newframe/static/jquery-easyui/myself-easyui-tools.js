/****jQuery easy ui 扩展函数 author ChenJunhui ****/

function isMatchedRegx(str,exp){
	  var reg = str.match(exp);
	  if(reg==null){
	    return false;
	  }
	  else{
	     return true;
	  }
}
function validateNumber(str){
	var exp = /^\d+$/;
	return isMatchedRegx(str,exp);
}

function validateCurrency(str){
	var exp = /^\d+(\.\d+)?$/;
	return isMatchedRegx(str,exp);
}

function removAlleSelect(selId){
	var selDomObj = document.getElementById(selId);
	for(var i=selDomObj.length-1;i>=0;i--){
		selDomObj.remove(i);
	}
}

jQuery.extend(jQuery.fn.validatebox.defaults.rules, {   
    charOrDigit: {   
        validator: function(value,param){   
			var exp=/^[A-Za-z0-9]+$/;
			return isMatchedRegx(value,exp); 
        },   
        message: typeof param!='undefined' && typeof param[0] != 'undefined'?param[0]:"必须输入字母或数字!"
    }   
}); 

jQuery.extend(jQuery.fn.validatebox.defaults.rules, {   
    currency: {   
        validator: function(value,param){   
			return validateCurrency(value); 
        },   
        message: typeof param!='undefined' && typeof param[0] != 'undefined'?param[0]:"金额格式不对!"
    }   
}); 

jQuery.extend(jQuery.fn.validatebox.defaults.rules, {   
    number: {   
        validator: function(value,param){   
			return validateNumber(value); 
        },   
        message: typeof param!='undefined' && typeof param[0] != 'undefined'?param[0]:"必须输入数字!"
    }   
}); 

jQuery.extend(jQuery.fn.validatebox.defaults.rules, {   
    equals: {   
        validator: function(value,param){   
            return value == $(param[0]).val();   
        },   
         message: typeof param!='undefined' && typeof param[0] != 'undefined'?param[0]:"两个值不匹配!"
    }   
});

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


function getMergeJsonObject(_jsonObj1,_jsonObj2){
	if(typeof _jsonObj1=='undefined' || typeof _jsonObj2=='undefined'){
		return;
	}
	for(_jsonKey in _jsonObj2){
		eval("_jsonObj1."+_jsonKey+"=_jsonObj2[_jsonKey]");
	}
	return _jsonObj1;
}

function mergeFormToJson(_jsonObj1,_formid){
	if(typeof _jsonObj1=='undefined' || typeof _formid=='undefined' || _formid==''){
		return;
	}
	return getMergeJsonObject(_jsonObj1,$("#"+_formid).form2json());
}

function clearInputForm(_formid){
	$("#"+_formid).form('clear');
}

function closeDiv(_formdivid){
	$("#"+_formdivid).window("close");
}

function outPutFunction(_functionName,_objId,_linkname,_permcode){
	return '[<a href="javascript:'+_functionName+'('+_objId+')">'+_linkname+'</a>]';
}

function doSearchObject(_tbid,_formid){
	 var queryParams = $('#'+_tbid).datagrid('options').queryParams; 
	 $('#'+_tbid).datagrid('options').queryParams=mergeFormToJson(queryParams,_formid);
	 $('#'+_tbid).datagrid('reload'); 
}

function openInputAndClearForm(_iptdivid,_iptformid){
	clearInputForm(_iptformid);
	$('#'+_iptdivid).window('open');
}

function golinkToUrl(_uri){
	window.location.href=_uri;
}

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


function removeJqueryNode(jid){
	$("#"+jid).remove();
}

function SetWinHeight(obj) { 
	var win = obj;
	if (document.getElementById) {
		if (win && !window.opera) {
			if (win.contentDocument && win.contentDocument.body.offsetHeight)
				win.height = win.contentDocument.body.offsetHeight;
			else if (win.Document && win.Document.body.scrollHeight)
				win.height = win.Document.body.scrollHeight;
		}
	} 
}