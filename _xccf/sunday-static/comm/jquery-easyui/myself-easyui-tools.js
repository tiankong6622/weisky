/** **jQuery easy ui 扩展函数 author ChenJunhui *** */

function isMatchedRegx(str, exp) {
	var reg = str.match(exp);
	if (reg == null) {
		return false;
	} else {
		return true;
	}
}

function validateNumber(str) {
	var exp = /^\d+$/;
	return isMatchedRegx(str, exp);
}

function validateCurrency(str) {
	var exp = /^\d+(\.\d+)?$/;
	return isMatchedRegx(str, exp);
}

function removAlleSelect(selId) {
	var selDomObj = document.getElementById(selId);
	for ( var i = selDomObj.length - 1; i >= 0; i--) {
		selDomObj.remove(i);
	}
}

// 验证-手机号码格式且是否重复
$.extend($.fn.validatebox.defaults.rules, {
	mobileMultiple : {
		validator : function(value, param) {
			var rules = $.fn.validatebox.defaults.rules;
			rules.mobileMultiple.message = '手机号码已存在';
			if (!rules.mobile.validator(value)) {
				rules.mobileMultiple.message = rules.mobile.message;
				return false;
			} else {
				var result = $.ajax({
					url : param + value,
					async : false,
					type : "post"
				}).responseText;
			}
			return result == "0";
		}
	}
});

// 最小长度
$.extend($.fn.validatebox.defaults.rules, {
	minLength : {
		validator : function(value, param) {
			return value.length >= param[0];
		},
		message : '至少输入{0}字符'
	}
});

// 最大长度
$.extend($.fn.validatebox.defaults.rules, {
	maxLength : {
		validator : function(value, param) {
			return value.length <= param[0];
		},
		message : '至多输入{0}字符'
	}
});

// 验证-必须是数字或字母
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			charOrDigit : {
				validator : function(value, param) {
					var exp = /^[A-Za-z0-9]+$/;
					return isMatchedRegx(value, exp);
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0]
						: "必须输入字母或数字!"
			}
		});

// 验证-输入的金额格式
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			currency : {
				validator : function(value, param) {
					return validateCurrency(value);
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0]
						: "金额格式不对!"
			}
		});

// 验证-是否是数字
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			number : {
				validator : function(value, param) {
					return validateNumber(value);
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0]
						: "必须输入数字!"
			}
		});

// 验证-两个值是否相等
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			equals : {
				validator : function(value, param) {
					return value == $(param[0]).val();
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0]
						: "两个值不匹配!"
			}
		});

// 验证-联系电话
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			phone : {
				validator : function(value, param) {
					var exp = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
					return isMatchedRegx(value, exp);
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0]
						: "请输入有效的联系电话"
			}
		});

// 验证-手机号码
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			mobile : {
				validator : function(value, param) {
					var exp = /^((1[0-9]{2})+\d{8})$/;
					return isMatchedRegx(value, exp);
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0]
						: "请输入有效的手机号码"
			}
		});

// 验证-网址
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			Url : {
				validator : function(value, param) {
					var exp = /[a-zA-z]+:\/\/[^\s]+/;
					return isMatchedRegx(value, exp);
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0]
						: "请输入有效的网址，以http://开头"
			}
		});

// 验证-正整数
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			positiveInteger : {
				validator : function(value, param) {
					var exp = /^[0-9]*[1-9][0-9]*$/;
					return isMatchedRegx(value, exp);
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0]
						: "必须输入正整数"
			}
		});

// 验证-非正整数
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			nonPositiveInteger : {
				validator : function(value, param) {
					var exp = /^((-\d+)|(0+))$/;
					return isMatchedRegx(value, exp);
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0]
						: "必须输入非正整数"
			}
		});

// 验证-仅中文
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			isChinese : {
				validator : function(value, param) {
					var exp = /^[\u4e00-\u9fa5]+$/;
					return isMatchedRegx(value, exp);
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0] : "必须输入中文"
			}
		});

// 验证-英文名
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			isNotChinese : {
				validator : function(value, param) {
					var exp = /^\w+$/;
					return isMatchedRegx(value, exp);
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0]
						: "必须输入英文名"
			}
		});

// 验证-日期
jQuery.extend(jQuery.fn.validatebox.defaults.rules,
		{
			isDate : {
				validator : function(value, param) {
					var exp = /^\d{4}(\-|\/|.)\d{1,2}\1\d{1,2}$/;
					return isMatchedRegx(value, exp);
				},
				message : typeof param != 'undefined'
						&& typeof param[0] != 'undefined' ? param[0]
						: "请输入正确日期"
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

function getMergeJsonObject(_jsonObj1, _jsonObj2) {
	if (typeof _jsonObj1 == 'undefined' || typeof _jsonObj2 == 'undefined') {
		return;
	}
	for (_jsonKey in _jsonObj2) {
		eval("_jsonObj1." + _jsonKey + "=_jsonObj2[_jsonKey]");
	}
	return _jsonObj1;
}

function mergeFormToJson(_jsonObj1, _formid) {
	if (typeof _jsonObj1 == 'undefined' || typeof _formid == 'undefined'
			|| _formid == '') {
		return;
	}
	return getMergeJsonObject(_jsonObj1, $("#" + _formid).form2json());
}

function clearInputForm(_formid) {
	$("#" + _formid).form('clear');
}

function closeDiv(_formdivid) {
	$("#" + _formdivid).window("close");
}

function outPutFunction(_functionName, _objId, _linkname, _permcode) {
	return '[<a href="javascript:' + _functionName + '(' + _objId + ')">'
			+ _linkname + '</a>]';
}

function doSearchObject(_tbid, _formid) {
	var queryParams = $('#' + _tbid).datagrid('options').queryParams;
	$('#' + _tbid).datagrid('options').queryParams = mergeFormToJson(
			queryParams, _formid);
	$('#' + _tbid).datagrid('reload');
}

function domarkObject(_tbid, _formid) {
	var queryParams = $('#' + _tbid).datagrid('options').queryParams;
	queryParams.mark = "遗失";
	$('#' + _tbid).datagrid('options').queryParams = mergeFormToJson(
			queryParams, _formid);
	$('#' + _tbid).datagrid('reload');
}

function doSearchObjectForCustomer(_tbid, _formid) {
	var queryParams = $('#' + _tbid).treegrid('options').queryParams;
	$('#' + _tbid).treegrid('options').queryParams = mergeFormToJson(
			queryParams, _formid);
	$('#' + _tbid).treegrid('reload');
}

function openInputAndClearForm(_iptdivid, _iptformid) {
	clearInputForm(_iptformid);
	$('#' + _iptdivid).window('open');
}

function golinkToUrl(_uri) {
	window.location.href = _uri;
}

function getJsRandomStr(len) {
	len = len || 32;
	var _chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz';
	var maxPos = _chars.length;
	var pwd = '';
	for ( var i = 0; i < len; i++) {
		pwd += _chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}

function removeJqueryNode(jid) {
	$("#" + jid).remove();
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