Validator = {
	Require: /.+/,
	Email: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
	Phone: /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
	Mobile: /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/,
	Url: /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
	IdCard: "this.IsIdCard(value)",
	Currency: /^\d+(\.\d+)?$/,
	Number: /^\d+$/,
	Zip: /^[1-9]\d{5}$/,
	QQ: /^[1-9]\d{4,8}$/,
	Integer: /^[-\+]?\d+$/,
	Double: /^[-\+]?\d+(\.\d+)?$/,
	English: /^[A-Za-z]+$/,
	Chinese: /^[\u0391-\uFFE5]+$/,
	Username: /^[a-zA-z0-9_]{6,16}$/,
	UnSafe: /^(([\'\"]*)|.{0,5})$|\s/,
	IsSafe: function (str) { return !this.UnSafe.test(str); },
	SafeString: "this.IsSafe(value)",
	Filter: "this.DoFilter(value, getAttribute('accept'))",
	Limit: "this.limit(value.length,getAttribute('min'),  getAttribute('max'))",
	LimitB: "this.limit(this.LenB(value), getAttribute('min'), getAttribute('max'))",
	Date: "this.IsDate(value, getAttribute('min'), getAttribute('format'))",
	Repeat: "value == document.getElementsByName(getAttribute('to'))[0].value",
	Range: "getAttribute('min') < (value|0)",
	Compare: "this.compare(value,getAttribute('operator'),getAttribute('to'))",
	Custom: "this.Exec(value, getAttribute('regexp'))",
	Group: "this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))",
	ErrorItem: [document.forms[0]],
	ErrorMessage: ["以下原因导致提交失败：\t\t\t\t"],
	Validate: function (theForm, mode) {
		var obj = theForm || event.srcElement;
		var count = obj.elements.length;
		this.ErrorMessage.length = 1;
		this.ErrorItem.length = 1;
		this.ErrorItem[0] = obj;
		for (var i = 0; i < count; i++) {
			with (obj.elements[i]) {
				var _dataType = getAttribute("dataType");
				if (typeof (_dataType) == "object" || typeof (this[_dataType]) == "undefined") continue;
				this.ClearState(obj.elements[i]);
				if (getAttribute("require") == "false" && value == "") continue;
				switch (_dataType) {
					case "IdCard":
					case "Date":
					case "Repeat":
					case "Range":
					case "Compare":
					case "Custom":
					case "Group":
					case "Limit":
					case "LimitB":
					case "SafeString":
					case "Filter":
						if (!eval(this[_dataType])) {
							this.AddError(i, getAttribute("msg"));
						}
						break;
					default:
						if (!this[_dataType].test(value)) {
							this.AddError(i, getAttribute("msg"));
						}
						break;
				}
			}
		}
		if (this.ErrorMessage.length > 1) {
			mode = mode || 1;
			var errCount = this.ErrorItem.length;
			switch (mode) {
				case 2:
					for (var i = 1; i < errCount; i++)
						this.ErrorItem[i].style.color = "red";
				case 1:
					alert(this.ErrorMessage.join("\n"));
					this.ErrorItem[1].focus();
					break;
				case 3:
					for (var i = 1; i < errCount; i++) {
						try {
							var alarmDiv = this.ErrorItem[i].nextSibling;
							alarmDiv.className = "alarm alarmNo";
							alarmDiv.id = "__ErrorMessagePanel";
							alarmDiv.innerHTML = this.ErrorMessage[i].replace(/\d+:/, "");
//							var span = document.createElement("div");
//							span.id = "__ErrorMessagePanel";
//							span.style.color = "red";
//							span.style.position = "absolute";
//							span.style.top = "0px";
//							span.style.left = "310px";
//							span.style.width = "300px";
//							this.ErrorItem[i].parentNode.appendChild(span);
//							span.innerHTML = this.ErrorMessage[i].replace(/\d+:/, "*");
						}
						catch (e) { alert(e.description); }
					}
				//	this.ErrorItem[1].focus();
					break;
				default:
					alert(this.ErrorMessage.join("\n"));
					break;
			}
			return false;
		}
		return true;
	},
	limit: function (len, min, max) {
		min = min || 0;
		max = max || Number.MAX_VALUE;
		return min <= len && len <= max;
	},
	LenB: function (str) {
		return str.replace(/[^\x00-\xff]/g, "**").length;
	},
	ClearState: function (elem) {
		with (elem) {
			var lastNode = parentNode.childNodes[parentNode.childNodes.length - 1];
			if (style.color == "red")
				style.color = "";
			if (lastNode.id == "__ErrorMessagePanel")
				lastNode.className = "alarm alarmYes";
				lastNode.innerHTML = "";
		}
	},
	AddError: function (index, str) {
		this.ErrorItem[this.ErrorItem.length] = this.ErrorItem[0].elements[index];
		this.ErrorMessage[this.ErrorMessage.length] = this.ErrorMessage.length + ":" + str;
	},
	Exec: function (op, reg) {
		return new RegExp(reg, "g").test(op);
	},
	compare: function (op1, operator, op2) {
		switch (operator) {
			case "NotEqual":
				return (op1 != op2);
			case "GreaterThan":
				return (op1 > op2);
			case "GreaterThanEqual":
				return (op1 >= op2);
			case "LessThan":
				return (op1 < op2);
			case "LessThanEqual":
				return (op1 <= op2);
			default:
				return (op1 == op2);
		}
	},
	MustChecked: function (name, min, max) {
		var groups = document.getElementsByName(name);
		var hasChecked = 0;
		min = min || 1;
		max = max || groups.length;
		for (var i = groups.length - 1; i >= 0; i--)
			if (groups[i].checked) hasChecked++;
		return min <= hasChecked && hasChecked <= max;
	},
	DoFilter: function (input, filter) {
		return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, filter.split(/\s*,\s*/).join("|")), "gi").test(input);
	},
	IsIdCard: function (number) {
		var date, Ai;
		var verify = "10x98765432";
		var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
		var area = ['', '', '', '', '', '', '', '', '', '', '', '北京', '天津', '河北', '山西', '内蒙古', '', '', '', '', '', '辽宁', '吉林', '黑龙江', '', '', '', '', '', '', '', '上海', '江苏', '浙江', '安微', '福建', '江西', '山东', '', '', '', '河南', '湖北', '湖南', '广东', '广西', '海南', '', '', '', '重庆', '四川', '贵州', '云南', '西藏', '', '', '', '', '', '', '陕西', '甘肃', '青海', '宁夏', '新疆', '', '', '', '', '', '台湾', '', '', '', '', '', '', '', '', '', '香港', '澳门', '', '', '', '', '', '', '', '', '国外'];
		var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
		if (re == null) return false;
		if (re[1] >= area.length || area[re[1]] == "") return false;
		if (re[2].length == 12) {
			Ai = number.substr(0, 17);
			date = [re[9], re[10], re[11]].join("-");
		}
		else {
			Ai = number.substr(0, 6) + "19" + number.substr(6);
			date = ["19" + re[4], re[5], re[6]].join("-");
		}
		if (!this.IsDate(date, "ymd")) return false;
		var sum = 0;
		for (var i = 0; i <= 16; i++) {
			sum += Ai.charAt(i) * Wi[i];
		}
		Ai += verify.charAt(sum % 11);
		return (number.length == 15 || number.length == 18 && number == Ai);
	},
	IsDate: function (op, formatString) {
		formatString = formatString || "ymd";
		var m, year, month, day;
		switch (formatString) {
			case "ymd":
				m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
				if (m == null) return false;
				day = m[6];
				month = m[5] * 1;
				year = (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
				break;
			case "dmy":
				m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
				if (m == null) return false;
				day = m[1];
				month = m[3] * 1;
				year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
				break;
			default:
				break;
		}
		if (!parseInt(month)) return false;
		month = month == 0 ? 12 : month;
		var date = new Date(year, month - 1, day);
		return (typeof (date) == "object" && year == date.getFullYear() && month == (date.getMonth() + 1) && day == date.getDate());
		function GetFullYear(y) { return ((y < 30 ? "20" : "19") + y) | 0; }
	}
}

		function checkUserName() {	
			var Username = document.getElementById("Username");
			var Uspan = document.getElementById("UsernameSpan");
			if (Username.value == "") {
				Username.nextSibling.innerHTML = "账号不能为空";
				Username.nextSibling.className = "alarm alarmNo";
				Username.focus(); 
				return false;
			}
			if (!/^[a-zA-z0-9_]+$/.test(Username.value)) {
				Username.nextSibling.innerHTML = "账号必须是字母、数字";
				Username.nextSibling.className = "alarm alarmNo";
				Username.focus();
				return false;
			}
			if (Username.value.length < 6 || Username.value.length > 16) {
				Username.nextSibling.innerHTML = "账号长度必须6~16位"; 
				Username.nextSibling.className = "alarm alarmNo";
				Username.focus();
				return false;
			}
			Username.nextSibling.innerHTML = "";
			Username.nextSibling.className = "alarm alarmYes";
			return true;
		}
		
		function checkPassword() {	
			var Password = document.getElementById("Password");
			var Repeat = document.getElementById("Repeat");
			if (Password.value == "") {
				Password.nextSibling.innerHTML = "密码不能为空";
				Password.nextSibling.className = "alarm alarmNo";
				Password.focus(); 
				return false;
			}
			if (Password.value.length < 6 || Password.value.length > 20) {
				Password.nextSibling.innerHTML = "密码长度必须6~20位"; 
				Password.nextSibling.className = "alarm alarmNo";
				Password.focus();
				return false;
			}
			if (/^(([\'\"]*)|.{0,5})$|\s/.test(Password.value)) {
				Password.nextSibling.innerHTML = "密码必须是字母、数字";
				Password.nextSibling.className = "alarm alarmNo";
				Password.focus();
				return false;
			}
			Password.nextSibling.innerHTML = "";
			Password.nextSibling.className = "alarm alarmYes";
			checkRepeat();
		}	
		
		function checkRepeat() {	
			var Password = document.getElementById("Password");
			var Repeat = document.getElementById("Repeat");
			if (Repeat.value == "") {
				Repeat.nextSibling.innerHTML = "不能为空";
				Repeat.nextSibling.className = "alarm alarmNo";
				return false;
			}
			if (Repeat.value != Password.value) {
				Repeat.nextSibling.innerHTML = "两次输入的密码不一致"; 
				Repeat.nextSibling.className = "alarm alarmNo";
				return false;
			}
			Repeat.nextSibling.innerHTML = "";
			Repeat.nextSibling.className = "alarm alarmYes";
			return true;
		}	
		function checkName() {	
			var Name = document.getElementById("Name");
			if (Name.value == "") {
				Name.nextSibling.innerHTML = "真实姓名不能为空";
				Name.nextSibling.className = "alarm alarmNo";
				Name.focus(); 
				return false;
			}
			if (!/^[\u0391-\uFFE5]+$/.test(Name.value)) {
				Name.nextSibling.innerHTML = "真实姓名只允许中文";
				Name.nextSibling.className = "alarm alarmNo";
				Name.focus();
				return false;
			}
			Name.nextSibling.innerHTML = "";
			Name.nextSibling.className = "alarm alarmYes";
			return true;
		}
		//        /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/
		//        /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/
		function checkIdCard() {	
			var IdCard = document.getElementById("IdCard");
			if (IdCard.value == "") {
				IdCard.nextSibling.innerHTML = "身份证号不能为空";
				IdCard.nextSibling.className = "alarm alarmNo";
				IdCard.focus(); 
				return false;
			}
		//	alert(IsIdCards(IdCard.value));
			if (!/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/.test(IdCard.value)) {
				IdCard.nextSibling.innerHTML = "身份证号无效";
				IdCard.nextSibling.className = "alarm alarmNo";
				IdCard.focus();
				return false;
			}
			IdCard.nextSibling.innerHTML = "";
			IdCard.nextSibling.className = "alarm alarmYes";
			return true;
		}