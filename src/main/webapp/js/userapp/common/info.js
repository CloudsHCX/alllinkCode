$(function() {
	redirect = function(obj) {
        activityIdss =$($(obj).children("label").get(0)).text();
        var getTimestamp=new Date().getTime();
        window.open("../../activity/setActivityId?activityId="+activityIdss+"&timestamp="+getTimestamp);
    }
});

$(function () {
	redirectPage = function (curUrl) {
		location.href = curUrl;
    }
});

var curDomObject;
// 点击刪除订单按钮
$(function() {
	a = function(obj) {
		showMaskLayer('确定继续?');
		curDomObject = $(obj).parent().parent();
    };

	$(function() {
		deleteOrder = function() {
			hideMaskLayer();

			var jsonData = {
				"orderIdStr": $($(curDomObject).children("div").get(0)).text(),
                "orderState": "-2"
			};
			var jsonString = JSON.stringify(jsonData);

			//$("#tipContent").text("正在提交");
            // $(curDomObject).hide();
			$.ajax({
				type: "post",
                url: "../../../order/modifyOrderState",
				contentType: "application/json;charset=utf-8",
				async: false,
				data: jsonString,
				dataType: "json",
				success: function(json) {
					// $("#tipContent").text("取消成功");
					// confirmDelete = true;
					$(curDomObject).hide();

				},
				error: function() {
					//				alert(json.message);
				}
			});
		}
	});
});

$(function() {
	$("#phoneNumber").bind("input propertychange change", function(event) {
		if($(this).val() == "") {
			$(this).next().show();
		} else {
			$(this).next().hide();
		}
	});

	myPlaceholder = function(obj) {
        $(obj).on("input propertychange", function () {
			if($(this).val() == "") {
				$(this).next().show();
			} else {
				$(this).next().hide();
			}
		});
	}
});

$(function() {
	showTip = function(msg) {
		$("#tipDiv").css({
			"display": "inline-block"
		});
		$("#tipLabel").text(msg);

		setTimeout(function() {
			$("#tipDiv").css({
				"display": "none"
			});
		}, 3000);
	}
});

$(function() {
	setNullAndFocus = function(obj) {
		$(obj).val("");
		$(obj + "Label").show();
		$(obj).focus();
	}
});

$(function() {
	focusAndGiveTip = function(obj, msg) {
		setNullAndFocus(obj);

		if(msg == "用户名或密码错误") {
			setNullAndFocus("#phoneNumber");
		} else if(msg == "管理员名或密码错误") {
			setNullAndFocus("#adminName");
		}

		showTip(msg);
	}
});

$(function () {
    // flag 0表示正常 1表示加../
    loginout = function (x) {
        var curUrl = x > 0 ? "../" : "";
        var resUrl = curUrl + "../../Login/loginout";
        $.ajax({
            type: "post",
            url: resUrl,
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            async: true,
            success: function (json) {
                if (json.result == "success") {
                    location.href = curUrl + "../login";
                }
            },
            error: function () {

            }
        });
    }
});

$(function() {
	checkPhoneNumberFormat = function() {
		var id = "#phoneNumber";
		var phoneNumber = $(id).val();
		var msg = "";
		var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;

		if(phoneNumber.length == 0) {
			msg = "请输入手机号";
		} else if(!reg.test(phoneNumber)) {
			msg = "手机号无效";
		}

		if(msg != "") {
			focusAndGiveTip(id, msg);
			return false;
		}

		return true;
	}
});

$(function() {
	checkPasswordFormat = function(isLogin) {
		var id = "#password";
		var password = $(id).val();
		var length = password.length;
		var msg = "";

		var reg = /[0-9]+[a-zA-Z]+[0-9a-zA-Z]*|[a-zA-Z]+[0-9]+[0-9a-zA-Z]/;
		if(password == "") {
			msg = "请输入密码";
		} else if(length < 6 || !reg.test(password)) {
			if(isLogin) {
				msg = "手机号或密码错误";
			} else {
				msg = "密码格式错误";
			}
		}

		if(msg != "") {
			focusAndGiveTip(id, msg);

			if(isLogin && password !== "") {
				setNullAndFocus("#phoneNumber");
			} else {
				$("#confirmPassword").val("");
				$("#confirmPasswordLabel").show();
			}

			return false;
		} else if(!isLogin) {
			var confirmPassword = $("#confirmPassword").val();
			msg = "";

			if(confirmPassword == "") {
				id = "#confirmPassword";
				msg = "请输入确认密码";
			} else if(confirmPassword != password) {
				setNullAndFocus("#confirmPassword");
				id = "#password";
				msg = "确认密码与密码不一致";
			}

			if(msg != "") {
				focusAndGiveTip(id, msg);
				return false;
			}
		}

		return true;
	}
});

$(function() {
	checkVerificationCodeFormat = function() {
		var id = "#verificationCode";
		var verificationCode = $(id).val();
		var msg = "";

		var reg = /^\d{6}$/;
		if(verificationCode.length == 0) {
			msg = "请输入验证码";
		} else if(!reg.test(verificationCode)) {
			msg = "验证码错误";
		}

		if(msg != "") {
			focusAndGiveTip(id, msg);
			return false;
		}

		return true;
	}
});

$(function() {
	var time = 60;

	countDown60Seconds = function() {
		var id = "#getVerificationCodeButton";

		if(time == 0) {
			$(id).attr("disabled", false);
			$(id).val("获取验证码");

			time = 60;
		} else {
			$(id).attr("disabled", true);
			$(id).val(time + "s后可重新获取");
			time--;
			setTimeout(function() {
				countDown60Seconds();
			}, 1000);
		}
	}
});

$(function() {
	// reason: 0表示注册，1表示重置密码
	getVerificationCode = function(reason) {
		if(checkPhoneNumberFormat() == false) {
			return false;
		}

		var jsonData = {
			"phoneNumber": $("#phoneNumber").val(),
			"sign": reason.toString()
		};
		var jsonString = JSON.stringify(jsonData);

		//var urlArray = ["/alllink/SendMessage/sms",];
		$.ajax({
			type: "post",
			url: "/alllink/SendMessage/sms",
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			data: jsonString,
			async: false,
			beforeSend: function() {
				showTip("正在发送验证码，请稍等");
			},
			success: function(json) {
				if(json.result == "success") {
					focusAndGiveTip("#verificationCode", "验证码已发送");
					countDown60Seconds();
				} else {
					focusAndGiveTip("#verificationCode", "验证码发送失败");
				}
			},
			error: function() {
				alert("发送验证码失败");
			}
		});
	}
});

$(function() {
	//	curStep: 0表示注册,1表示登录,2表示重置密码
	submitInfoToEnterSystem = function(curStep) {
		if(checkPhoneNumberFormat() == false) {
			return false;
		}

		var isLogin = (curStep == 1) ? true : false;
		if(!isLogin) {
			if(checkVerificationCodeFormat() == false) {
				return false;
			}
		}

		if(checkPasswordFormat(isLogin) == false) {
			return false;
		}

		var idArray = ["#phoneNumber", "#password", "#verificationCode"];
		var jsonData;
		if(isLogin) {
			jsonData = {
				"phone_number": $(idArray[0]).val(),
				"password": hex_md5($(idArray[1]).val())
			};
		} else {
			jsonData = {
				"phone_number": $(idArray[0]).val(),
				"password": hex_md5($(idArray[1]).val()),
				"checkcode": $(idArray[2]).val()
			};
		}

		var jsonString = JSON.stringify(jsonData);

		var urlArray = ["/alllink/Register/toregistered", "/alllink/Login/tologin", "/alllink/user/resetPassword"];
		$.ajax({
			type: "post",
			url: urlArray[curStep],
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			data: jsonString,
			async: false,
			beforeSend: function() {
				showTip('正在检验，请稍等');
			},
			success: function(json) {
				if(json.result == "success") {
					if(curStep == 0) {
						// alert("注册成功，跳转到主页面");
						location.href = '/alllink/userapp/login';
					} else if(curStep == 1) {
						// alert("登录成功，跳转到主页面");
						//location.href = '/alllink/userapp/personalCenter';

                        //若不为空，则跳转到之前的页面
                        var ll = getQueryString("url");
                        if(ll!=null){
                            location.href = ll;
                        }else{
                            // alert("登录成功，跳转到主页面");
                            location.href = '/alllink/userapp/personalCenter';
                        }
					} else if(curStep == 2) {
						// alert("重置密码成功，跳转到登录页面");
						location.href = '/alllink/userapp/login';
					} else {
						alert(json.message);
					}
				}
			},
			error: function() {
				if(curStep == 0) {
					alert("注册失败！");
				} else if(curStep == 1) {
					alert("登录失败！");
				} else if(curStep == 2) {
					alert("重置密码失败！");
				}
			}
		});
	}
});

$(function() {
	var formerId = "#tab0";
	//	$(formerId).css({
	//		"color": "#FFFFFF",
	//		"background-color": "#5599FF"
	//	});

	showSelectedTabInfo = function(id) {
		$(id).slideDown(1000);
    };

	selectTab = function(curId, infoType) {
		if(curId != formerId) {
			$(formerId).css({
				"background-color": "#FFFFFF",
				"color": "#778899"
			});
			$(curId).css({
				"background-color": "#5599FF",
				"color": "#FFFFFF"
			});

			displayTabInfo(infoType);

			var buttonIdArray = ["#modifyBasicInfoButton", "#modifyThirdPartyAccountButton"];
			$(buttonIdArray[infoType]).attr("disabled", true);

			$(formerId + "Info").slideUp(1000, showSelectedTabInfo(curId + "Info"));

			formerId = curId;
		}
	}
});

$(function() {
	checkInfoFormat = function(value, infoNumber) {
		// 如果没有字符或者输入的是空格
		if(value.trim() == "") {
			return false;
		}

		if(infoNumber == 0) {
			return true;
		}

		//		年龄(2)、邮箱(3)、QQ号(4)和微信号(5)
		//		其中,年龄范围是1-120
		var reg = [
			"^(?:[1-9][0-9]?|1[01][0-9]|120)$", "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$",
			"^[1-9]{1}[0-9]{4,9}$", "^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}$"
		];
		return RegExp(reg[infoNumber - 2]).test(value);
	}
});

$(function() {
	showMaskLayer = function(tipContent) {
		//		修改div内容需要使用text方法
		$("#tipContent").text(tipContent);
		$("#maskLayer").show();
	}
});

$(function() {
	hideMaskLayer = function() {
		$("#maskLayer").hide();
	}
});

/*
 * A JavaScript implementation of the RSA Data Security, Inc. MD5 Message
 * Digest Algorithm, as defined in RFC 1321.
 * Version 2.1 Copyright (C) Paul Johnston 1999 - 2002.
 * Other contributors: Greg Holt, Andrew Kepert, Ydnar, Lostinet
 * Distributed under the BSD License
 * See http://pajhome.org.uk/crypt/md5 for more info.
 */

/*
 * Configurable variables. You may need to tweak these to be compatible with
 * the server-side, but the defaults work in most cases.
 */
var hexcase = 0; /* hex output format. 0 - lowercase; 1 - uppercase        */
var b64pad = ""; /* base-64 pad character. "=" for strict RFC compliance   */
var chrsz = 8; /* bits per input character. 8 - ASCII; 16 - Unicode      */

/*
 * These are the functions you'll usually want to call
 * They take string arguments and return either hex or base-64 encoded strings
 */
function hex_md5(s) {
	return binl2hex(core_md5(str2binl(s), s.length * chrsz));
}

function b64_md5(s) {
	return binl2b64(core_md5(str2binl(s), s.length * chrsz));
}

function str_md5(s) {
	return binl2str(core_md5(str2binl(s), s.length * chrsz));
}

function hex_hmac_md5(key, data) {
	return binl2hex(core_hmac_md5(key, data));
}

function b64_hmac_md5(key, data) {
	return binl2b64(core_hmac_md5(key, data));
}

function str_hmac_md5(key, data) {
	return binl2str(core_hmac_md5(key, data));
}

/*
 * Perform a simple self-test to see if the VM is working
 */
function md5_vm_test() {
	return hex_md5("abc") == "900150983cd24fb0d6963f7d28e17f72";
}

/*
 * Calculate the MD5 of an array of little-endian words, and a bit length
 */
function core_md5(x, len) {
	/* append padding */
	x[len >> 5] |= 0x80 << ((len) % 32);
	x[(((len + 64) >>> 9) << 4) + 14] = len;

	var a = 1732584193;
	var b = -271733879;
	var c = -1732584194;
	var d = 271733878;

	for(var i = 0; i < x.length; i += 16) {
		var olda = a;
		var oldb = b;
		var oldc = c;
		var oldd = d;

		a = md5_ff(a, b, c, d, x[i + 0], 7, -680876936);
		d = md5_ff(d, a, b, c, x[i + 1], 12, -389564586);
		c = md5_ff(c, d, a, b, x[i + 2], 17, 606105819);
		b = md5_ff(b, c, d, a, x[i + 3], 22, -1044525330);
		a = md5_ff(a, b, c, d, x[i + 4], 7, -176418897);
		d = md5_ff(d, a, b, c, x[i + 5], 12, 1200080426);
		c = md5_ff(c, d, a, b, x[i + 6], 17, -1473231341);
		b = md5_ff(b, c, d, a, x[i + 7], 22, -45705983);
		a = md5_ff(a, b, c, d, x[i + 8], 7, 1770035416);
		d = md5_ff(d, a, b, c, x[i + 9], 12, -1958414417);
		c = md5_ff(c, d, a, b, x[i + 10], 17, -42063);
		b = md5_ff(b, c, d, a, x[i + 11], 22, -1990404162);
		a = md5_ff(a, b, c, d, x[i + 12], 7, 1804603682);
		d = md5_ff(d, a, b, c, x[i + 13], 12, -40341101);
		c = md5_ff(c, d, a, b, x[i + 14], 17, -1502002290);
		b = md5_ff(b, c, d, a, x[i + 15], 22, 1236535329);

		a = md5_gg(a, b, c, d, x[i + 1], 5, -165796510);
		d = md5_gg(d, a, b, c, x[i + 6], 9, -1069501632);
		c = md5_gg(c, d, a, b, x[i + 11], 14, 643717713);
		b = md5_gg(b, c, d, a, x[i + 0], 20, -373897302);
		a = md5_gg(a, b, c, d, x[i + 5], 5, -701558691);
		d = md5_gg(d, a, b, c, x[i + 10], 9, 38016083);
		c = md5_gg(c, d, a, b, x[i + 15], 14, -660478335);
		b = md5_gg(b, c, d, a, x[i + 4], 20, -405537848);
		a = md5_gg(a, b, c, d, x[i + 9], 5, 568446438);
		d = md5_gg(d, a, b, c, x[i + 14], 9, -1019803690);
		c = md5_gg(c, d, a, b, x[i + 3], 14, -187363961);
		b = md5_gg(b, c, d, a, x[i + 8], 20, 1163531501);
		a = md5_gg(a, b, c, d, x[i + 13], 5, -1444681467);
		d = md5_gg(d, a, b, c, x[i + 2], 9, -51403784);
		c = md5_gg(c, d, a, b, x[i + 7], 14, 1735328473);
		b = md5_gg(b, c, d, a, x[i + 12], 20, -1926607734);

		a = md5_hh(a, b, c, d, x[i + 5], 4, -378558);
		d = md5_hh(d, a, b, c, x[i + 8], 11, -2022574463);
		c = md5_hh(c, d, a, b, x[i + 11], 16, 1839030562);
		b = md5_hh(b, c, d, a, x[i + 14], 23, -35309556);
		a = md5_hh(a, b, c, d, x[i + 1], 4, -1530992060);
		d = md5_hh(d, a, b, c, x[i + 4], 11, 1272893353);
		c = md5_hh(c, d, a, b, x[i + 7], 16, -155497632);
		b = md5_hh(b, c, d, a, x[i + 10], 23, -1094730640);
		a = md5_hh(a, b, c, d, x[i + 13], 4, 681279174);
		d = md5_hh(d, a, b, c, x[i + 0], 11, -358537222);
		c = md5_hh(c, d, a, b, x[i + 3], 16, -722521979);
		b = md5_hh(b, c, d, a, x[i + 6], 23, 76029189);
		a = md5_hh(a, b, c, d, x[i + 9], 4, -640364487);
		d = md5_hh(d, a, b, c, x[i + 12], 11, -421815835);
		c = md5_hh(c, d, a, b, x[i + 15], 16, 530742520);
		b = md5_hh(b, c, d, a, x[i + 2], 23, -995338651);

		a = md5_ii(a, b, c, d, x[i + 0], 6, -198630844);
		d = md5_ii(d, a, b, c, x[i + 7], 10, 1126891415);
		c = md5_ii(c, d, a, b, x[i + 14], 15, -1416354905);
		b = md5_ii(b, c, d, a, x[i + 5], 21, -57434055);
		a = md5_ii(a, b, c, d, x[i + 12], 6, 1700485571);
		d = md5_ii(d, a, b, c, x[i + 3], 10, -1894986606);
		c = md5_ii(c, d, a, b, x[i + 10], 15, -1051523);
		b = md5_ii(b, c, d, a, x[i + 1], 21, -2054922799);
		a = md5_ii(a, b, c, d, x[i + 8], 6, 1873313359);
		d = md5_ii(d, a, b, c, x[i + 15], 10, -30611744);
		c = md5_ii(c, d, a, b, x[i + 6], 15, -1560198380);
		b = md5_ii(b, c, d, a, x[i + 13], 21, 1309151649);
		a = md5_ii(a, b, c, d, x[i + 4], 6, -145523070);
		d = md5_ii(d, a, b, c, x[i + 11], 10, -1120210379);
		c = md5_ii(c, d, a, b, x[i + 2], 15, 718787259);
		b = md5_ii(b, c, d, a, x[i + 9], 21, -343485551);

		a = safe_add(a, olda);
		b = safe_add(b, oldb);
		c = safe_add(c, oldc);
		d = safe_add(d, oldd);
	}
	return Array(a, b, c, d);

}

/*
 * These functions implement the four basic operations the algorithm uses.
 */
function md5_cmn(q, a, b, x, s, t) {
	return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s), b);
}

function md5_ff(a, b, c, d, x, s, t) {
	return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
}

function md5_gg(a, b, c, d, x, s, t) {
	return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
}

function md5_hh(a, b, c, d, x, s, t) {
	return md5_cmn(b ^ c ^ d, a, b, x, s, t);
}

function md5_ii(a, b, c, d, x, s, t) {
	return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
}

/*
 * Calculate the HMAC-MD5, of a key and some data
 */
function core_hmac_md5(key, data) {
	var bkey = str2binl(key);
	if(bkey.length > 16) bkey = core_md5(bkey, key.length * chrsz);

	var ipad = Array(16),
		opad = Array(16);
	for(var i = 0; i < 16; i++) {
		ipad[i] = bkey[i] ^ 0x36363636;
		opad[i] = bkey[i] ^ 0x5C5C5C5C;
	}

	var hash = core_md5(ipad.concat(str2binl(data)), 512 + data.length * chrsz);
	return core_md5(opad.concat(hash), 512 + 128);
}

/*
 * Add integers, wrapping at 2^32. This uses 16-bit operations internally
 * to work around bugs in some JS interpreters.
 */
function safe_add(x, y) {
	var lsw = (x & 0xFFFF) + (y & 0xFFFF);
	var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
	return(msw << 16) | (lsw & 0xFFFF);
}

/*
 * Bitwise rotate a 32-bit number to the left.
 */
function bit_rol(num, cnt) {
	return(num << cnt) | (num >>> (32 - cnt));
}

/*
 * Convert a string to an array of little-endian words
 * If chrsz is ASCII, characters >255 have their hi-byte silently ignored.
 */
function str2binl(str) {
	var bin = Array();
	var mask = (1 << chrsz) - 1;
	for(var i = 0; i < str.length * chrsz; i += chrsz)
		bin[i >> 5] |= (str.charCodeAt(i / chrsz) & mask) << (i % 32);
	return bin;
}

/*
 * Convert an array of little-endian words to a string
 */
function binl2str(bin) {
	var str = "";
	var mask = (1 << chrsz) - 1;
	for(var i = 0; i < bin.length * 32; i += chrsz)
		str += String.fromCharCode((bin[i >> 5] >>> (i % 32)) & mask);
	return str;
}

/*
 * Convert an array of little-endian words to a hex string.
 */
function binl2hex(binarray) {
	var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
	var str = "";
	for(var i = 0; i < binarray.length * 4; i++) {
		str += hex_tab.charAt((binarray[i >> 2] >> ((i % 4) * 8 + 4)) & 0xF) +
			hex_tab.charAt((binarray[i >> 2] >> ((i % 4) * 8)) & 0xF);
	}
	return str;
}

/*
 * Convert an array of little-endian words to a base-64 string
 */
function binl2b64(binarray) {
	var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	var str = "";
	for(var i = 0; i < binarray.length * 4; i += 3) {
		var triplet = (((binarray[i >> 2] >> 8 * (i % 4)) & 0xFF) << 16) |
			(((binarray[i + 1 >> 2] >> 8 * ((i + 1) % 4)) & 0xFF) << 8) |
			((binarray[i + 2 >> 2] >> 8 * ((i + 2) % 4)) & 0xFF);
		for(var j = 0; j < 4; j++) {
			if(i * 8 + j * 6 > binarray.length * 32) str += b64pad;
			else str += tab.charAt((triplet >> 6 * (3 - j)) & 0x3F);
		}
	}
	return str;
}