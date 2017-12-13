var idArray = ["#phoneNumber", "#password"];

$(function() {
	for(var i = 0; i < 2; i++) {
		var curId = idArray[i];
		if($(curId).val() == "") {
			$(curId + "Label").show();
		}
	}

	$("#phoneNumber").focus();
});

//$(function() {
//	login = function() {
//		if(checkPhoneNumberFormat() == false) {
//			return false;
//		}
//		if(checkPasswordFormat(true) == false) {
//			return false;
//		}
//
//		var jsonData = {
//			"phone_number": $(idArray[0]).val(),
//			"password": $(idArray[1]).val()
//		};
//		var jsonString = JSON.stringify(jsonData);
//
//		$.ajax({
//			type: "post",
//			url: "/alllink/user/login",
//			contentType: "application/json;charset=utf-8",
//			dataType: "json",
//			data: jsonString,
//			async: false,
//			beforeSend: function() {
//				showTip('正在检验，请稍等');
//			},
//			success: function(json) {
//				if(json.result == "success") {
//					alert("登录成功，即将跳转主页面！");
//				} else {
//					alert(json.message);
//				}
//			},
//			error: function() {
//				alert("登录失败");
//			}
//		});
//	}
//});