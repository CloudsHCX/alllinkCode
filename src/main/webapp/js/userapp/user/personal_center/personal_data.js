var infoArray = [
	["我的昵称", 2, 45, "111@qq.com"],
	["1111111111", "aaaa11"]
];

// 第1个表示基本信息被修改的地方个数， 第2个表示第三方账号被修改的地方个数
var personalDataModifiedPlaceCount = [0, 0];
// 按钮id数组
var buttonIdArray = ["#modifyBasicInfoButton", "#modifyThirdPartyAccountButton"];

$(function() {
	// 对应昵称(0)、性别(1)、年龄(2)、邮箱(3)、QQ号(4)和微信号(5)
	var modifiedState = [false, false, false, false, false, false];

	setButtonState = function(isAdd, buttonNumber) {
		if(isAdd) {
			if(++personalDataModifiedPlaceCount[buttonNumber] == 1) {
				$(buttonIdArray[buttonNumber]).attr("disabled", false);
			}
		} else {
			if(--personalDataModifiedPlaceCount[buttonNumber] == 0) {
				$(buttonIdArray[buttonNumber]).attr("disabled", true);
			}
		}
	}

	contrastAndAdjustModifiedState = function(infoNumber, curModifiedState, buttonNumber) {
		if(curModifiedState != modifiedState[infoNumber]) {
			if(curModifiedState) {
				setButtonState(true, buttonNumber);
			} else {
				setButtonState(false, buttonNumber);
			}

			modifiedState[infoNumber] = curModifiedState;
		}
	}

	$("input:radio[name=sex]").change(function() {
		if(this.value == infoArray[0][1]) {
			contrastAndAdjustModifiedState(1, false, 0);
		} else {
			contrastAndAdjustModifiedState(1, true, 0);
		}
	});

	monitorContentChanges = function(obj, row, column, infoNumber, buttonNumber) {
		$(obj).on("input propertychange", function() {
			var originalValue = infoArray[row][column];
			var curValue = $(this).val();

			if(!checkInfoFormat(curValue, infoNumber) || (originalValue == curValue)) {
				contrastAndAdjustModifiedState(infoNumber, false, buttonNumber);
			} else {
				contrastAndAdjustModifiedState(infoNumber, true, buttonNumber);
			}
		});
	}
});

$(function() {
	displayTabInfo = function(infoType) {
		if(infoType == 0) {
			$("#nickname").val(infoArray[0][0]);
			$("input:radio[name=sex]")[infoArray[0][1]].checked = true;
			$("#age").val(infoArray[0][2]);
			$("#mailbox").val(infoArray[0][3]);
		} else {
			$("#QQNumber").val(infoArray[1][0]);
			$("#weChatNumber").val(infoArray[1][1]);
		}
	}
});

$(function() {
	//	infoType: 0表示基本信息 1表示第三方账号
	getUserPersonalData = function(infoType) {
		var urlArray = ["/alllink/user/getUserBasicInfo", "/alllink/user/getUserThirdPartyAccount"];
		var jsonData = {
			"phone_number": $(idArray[0]).val(),
		};
		var jsonString = JSON.stringify(jsonData);

		$.ajax({
			type: "post",
			url: urlArray[infoType],
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			data: jsonString,
			async: false,
			success: function(info) {
				if(infoType == 0) {
					//	设置图片路径
					$("#header-avatar").attr("src", info.photo);
					$("#tab-content-avatar").attr("src", info.photo);

					// 存储基本信息
					infoArray[0][0] = info.nickname;
					infoArray[0][1] = info.sex;
					infoArray[0][2] = info.age;
					infoArray[0][3] = info.email;
				} else {
					//	存储第三方账号
					infoArray[1][0] = info.QQNumber;
					infoArray[1][1] = info.weChatNumber;
				}
				
				displayTabInfo(infoType);
			},
			error: function() {
				alert("获取基本信息失败");
			}
		});
	}
});

$(function() {
	//	infoType: 0表示基本信息,1表示第三方账号
	modifyPersonalData = function(infoType) {
		var idArray = ["#phoneNumber", "#nickname", "#age", "#mailbox", "#QQNumber", "#weChatNumber"];
		var jsonData;
		if(infoType == 0) {
			jsonData = {
				"phoneNumber": $(idArray[0]).text(),
				"nickname": $(idArray[1]).val(),
				"sex": $("input:radio[name=sex]:checked").val(),
				"age": $(idArray[2]).val(),
				"email": $(idArray[3]).val()
			};
		} else {
			jsonData = {
				"phoneNumber": $(idArray[0]).text(),
				"qqNumber": $(idArray[4]).val(),
				"wechatNumber": $(idArray[5]).val()
			};
		}

		var jsonString = JSON.stringify(jsonData);

		// var urlArray = ["/user/updateUserInfo", "/user/updateUserInfo"];

		$.ajax({
			type: "post",
			url: "/user/updateUserInfo",
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			data: jsonString,
			async: false,
			success: function(json) {
				if(json.result == "success") {
					alert("注册成功，即将跳转主页面！");
					$(buttonIdArray[infoType]).attr("disabled", true);
				} else {
					alert(json.message);
				}
			},
			error: function() {
				alert("注册失败");
			}
		});
	}
});