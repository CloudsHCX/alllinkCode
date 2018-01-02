var infoArray = [
    ["", 2, 45, ""],
    ["", ""]
];

// 第1个表示基本信息被修改的地方个数， 第2个表示第三方账号被修改的地方个数
// var personalDataModifiedPlaceCount = [0, 0];
// 按钮id数组
// var buttonIdArray = ["#modifyBasicInfoButton", "#modifyThirdPartyAccountButton"];

$(function () {
    showTip = function (index, msg) {
        var id = "#tipDiv" + index;
        $(id).css({
            "display": "inline-block"
        });
        $(id).text(msg);

        setTimeout(function () {
            $(id).css({
                "display": "none"
            });
        }, 3000);
    }
});

$(function() {
	// 对应昵称(0)、性别(1)、年龄(2)、邮箱(3)、QQ号(4)和微信号(5)
    // var modifiedState = [false, false, false, false, false, false];
    //
    // setButtonState = function(isAdd, buttonNumber) {
    // 	if(isAdd) {
    // 		if(++personalDataModifiedPlaceCount[buttonNumber] == 1) {
    // 			$(buttonIdArray[buttonNumber]).attr("disabled", false);
    // 		}
    // 	} else {
    // 		if(--personalDataModifiedPlaceCount[buttonNumber] == 0) {
    // 			$(buttonIdArray[buttonNumber]).attr("disabled", true);
    // 		}
    // 	}
    // }
    //
    // contrastAndAdjustModifiedState = function(infoNumber, curModifiedState, buttonNumber) {
    // 	if(curModifiedState != modifiedState[infoNumber]) {
    // 		if(curModifiedState) {
    // 			setButtonState(true, buttonNumber);
    // 		} else {
    // 			setButtonState(false, buttonNumber);
    // 		}
    //
    // 		modifiedState[infoNumber] = curModifiedState;
    // 	}
    // }
    //
    // $("input:radio[name=sex]").change(function() {
    // 	if(this.value == infoArray[0][1]) {
    // 		contrastAndAdjustModifiedState(1, false, 0);
    // 	} else {
    // 		contrastAndAdjustModifiedState(1, true, 0);
    // 	}
    // });
    //
    // monitorContentChanges = function(obj, row, column, infoNumber, buttonNumber) {
    // 	$(obj).on("input propertychange", function() {
    // 		var originalValue = infoArray[row][column];
    // 		var curValue = $(this).val();
    //
    // 		if(!checkInfoFormat(curValue, infoNumber) || (originalValue == curValue)) {
    // 			contrastAndAdjustModifiedState(infoNumber, false, buttonNumber);
    // 		} else {
    // 			contrastAndAdjustModifiedState(infoNumber, true, buttonNumber);
    // 		}
    // 	});
    // }
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

var phoneNumber;
$(function() {
	//	infoType: 0表示基本信息 1表示第三方账号
	getUserPersonalData = function(infoType) {
		var urlArray = ["/alllink/user/getUserBasicInfo", "/alllink/user/getUserThirdPartyAccount"];
        var jsonData = {
            "phoneNumber": "131111111"
        };

		$.ajax({
			type: "post",
			url: urlArray[infoType],
			contentType: "application/json;charset=utf-8",
            async: true,
            dataType: "json",
            data: JSON.stringify(jsonData),
			success: function(info) {
				if(infoType == 0) {
					//	设置图片路径
                    $("#header-avatar").attr("src", "/pic/" + info.data.photo);
                    $("#tab-content-avatar").attr("src", "/pic/" + info.data.photo);

					// 存储基本信息
                    infoArray[0][0] = info.data.nickname;
                    infoArray[0][1] = info.data.gender;
                    infoArray[0][2] = info.data.age;
                    infoArray[0][3] = info.data.email;

                    phoneNumber = info.data.phoneNumber;
                    $("#phoneNumber").text(info.data.phoneNumber);

                    var curTime = info.data.createTime;
                    $("#registerTime").text(curTime.substring(0, 19));
				} else {
					//	存储第三方账号
                    infoArray[1][0] = info.data.qqNumber;
                    infoArray[1][1] = info.data.wechatNumber;
				}
				
				displayTabInfo(infoType);
			},
			error: function() {

			}
		});
    };
    getUserPersonalData(0);
    getUserPersonalData(1);
});

$(function() {
    var idArray = ["#phoneNumber", "#nickname", "#age", "#mailbox", "#QQNumber", "#weChatNumber"];

    // 提交前校验基本资料格式是否合法
    checkBasicInfo = function () {
        if (!checkInfoFormat($(idArray[1]).val(), 0)) {
            showTip(0, "昵称非法");
            return false;
        }

        if (!checkInfoFormat($(idArray[2]).val(), 2)) {
            showTip(0, "年龄非法");
            return false;
        }

        if (!checkInfoFormat($(idArray[3]).val(), 3)) {
            showTip(0, "邮箱非法");
            return false;
        }

        return true;
    };

    // 提交前校验第三方账号是否合法
    checkThirdAccount = function () {
        if (!checkInfoFormat($(idArray[4]).val(), 4)) {
            showTip(1, "QQ号非法");
            return false;
        }

        if (!checkInfoFormat($(idArray[5]).val(), 5)) {
            showTip(1, "微信号非法");
            return false;
        }

        return true;
    };

	//	infoType: 0表示基本信息,1表示第三方账号
	modifyPersonalData = function(infoType) {
        if (infoType == 0) {
            if (!checkBasicInfo()) {
                return false;
            }
        } else {
            if (!checkThirdAccount()) {
                return false;
            }
        }

		var jsonData;
		if(infoType == 0) {
			jsonData = {
                "phoneNumber": phoneNumber,
				"nickname": $(idArray[1]).val(),
                "gender": $("input:radio[name=sex]:checked").val(),
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
            url: "/alllink/user/updateUserInfo",
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			data: jsonString,
            async: true,
			success: function(json) {
                // if(json.result == "success") {
                // 	$(buttonIdArray[infoType]).attr("disabled", true);
                // }

                if (infoType == 0) {
                    infoArray[0][0] = $(idArray[1]).val();
                    infoArray[0][1] = $("input:radio[name=sex]:checked").val();
                    infoArray[0][2] = $(idArray[2]).val();
                    infoArray[0][3] = $(idArray[3]).val();
                } else {
                    infoArray[1][0] = $(idArray[4]).val();
                    infoArray[1][1] = $(idArray[5]).val();
                }

                showTip(infoType, json.message);
			},
			error: function() {

            }
		});
	}
});