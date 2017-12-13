$(function() {
	myPlaceHolder = function(obj) {
		$(obj).on("input propertychange", function() {
			var label = $(this).next();
			var val = $(this).val();
			alert(val);

			if(val == "") {
				label.show();
			} else {
				label.hide();
			}
		});
	}
});

$(function() {
    myPlaceHolder = function(obj) {
        $(obj).on("input propertychange", function() {
            var label = $(this).next();
            var val = $(this).val();
            if(val == "") {
                label.show();
            } else {
                label.hide();
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

$(function() {
    checkPhoneNumberFormat = function() {
        var id = "#phoneNumber";
        var phoneNumber = $(id).val();
        var msg = "";
        var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
        debugger
        if(phoneNumber == "") {
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
        //var length = password.length;
        var msg = "";

        var reg = /[0-9]+[a-zA-Z]+[0-9a-zA-Z]*|[a-zA-Z]+[0-9]+[0-9a-zA-Z]/;
        if(password == "") {
            msg = "请输入密码";
        } else if( password.length< 6 || !reg.test(password)) {
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
        if(verificationCode == "") {
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
	var formerId = "#tab0";
	$(formerId).css({
		"color": "#FFFFFF",
		"background-color": "#5599FF"
	});

	showSelectedTabInfo = function(id) {
		$(id).slideDown(1000);
	}

	selectTab = function(curId, infoType) {
		if(curId != formerId) {
			$(formerId).css({
				"background-color": "#FFF5EE",
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
	/*触发file的点击事件*/
	triggerFileClickEvent = function(obj) {
		$(obj).prev().trigger("click");
	}

	/*上传文件*/
	uploadFile = function(obj) {
		var filePath = $(obj).val();
		var msg = "";

		if(filePath != "") {
			var size = $(obj)[0].files[0].size;
			if(filePath.indexOf("jpg") == -1 && filePath.indexOf("png") == -1 && filePath.indexOf("gif") == -1) {
				msg = "上传图片格式不正确";
			} else if(size > 2097152) {
				msg = "上传图片大小不正确";
			}

			if(msg != "") {
				showMaskLayer(msg);
			} else {
				var jsonData = {
					"phoneNumber": 13968574525
				};
				var jsonString = JSON.stringify(jsonData);

				$.ajaxFileUpload({
					url: "/alllink/user/uploadPic?phoneNumber=13968574525",
					type: "post",
					fileElementId: "avatarFile", // 对应html中上传file的id
					contentType: "application/json;charset=utf-8",
					async: false,
					/*返回的类型默认是html类型*/
					success: function(data) {
                        var str = $(data).find("body").text();

						/*图片路径加随机数是为了避免浏览器对于相同的路径自动去读缓存*/
						$("#header-avatar").attr("src", str + "?" + Math.random());
						$("#tab-content-avatar").attr("src", str + "?" + Math.random());
					},
					error: function() {
						/*alert("请连接网络");*/
					}
				})
			}

			/*清除file的内容，下一次上传图片时可以触发onchange事件*/
			/*如果是IE*/
			if(window.ActiveXObject || "ActiveXObject" in window) {
				$(obj).replaceWith($(obj).clone(true));
			} else {
				$(obj).val("");
			}
		}
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
