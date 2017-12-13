var idArray = ["#phoneNumber", "#verificationCode", "#password", "#confirmPassword"];

$(function() {
	for(var i = 0; i < 4; i++) {
		var curId = idArray[i];
		if($(curId).val() == "") {
			$(curId + "Label").show();
		}
	}

	$("#phoneNumber").focus();
});

//$(function() {
//	register = function() {
//		if(checkPhoneNumberFormat() == false) {
//			return false;
//		}
//		if(checkVerificationCodeFormat() == false) {
//			return false;
//		}
//		if(checkPasswordFormat(false) == false) {
//			return false;
//		}
//
//		var jsonData = {
//			"phone_number": $(idArray[0]).val(),
//			"password": $(idArray[2]).val(),
//			"checkcode": $(idArray[1]).val()
//		};
//
//		var jsonString = JSON.stringify(jsonData);
//
//		$.ajax({
//			type: "post",
//			url: "/alllink/user/registered",
//			contentType: "application/json;charset=utf-8",
//			dataType: "json",
//			data: jsonString,
//			async: false,
//			beforeSend: function() {
//				showTip('正在检验，请稍等');
//			},
//			success: function(json) {
//				if(json.result == "success") {
//					alert("注册成功，即将跳转主页面！");
//				} else {
//					alert(json.message);
//				}
//			},
//			error: function() {
//				alert("注册失败");
//			}
//		});
//	}
//});