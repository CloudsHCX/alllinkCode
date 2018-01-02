$(function() {
    function countDownForActivity(endTime, index) {
        var curTime = new Date().getTime();

        var cha = endTime - curTime;
        if (cha > 0) {
            // 剩余分钟数
            var minute = Math.floor(cha / 60000);
            if (minute < 10) {
                minute = "0" + minute;
            }

            // 剩余秒数
            var second = Math.floor(cha % 60000 / 1000);
            if (second < 10) {
                second = "0" + second;
            }

            $(".class-for-index0:nth-child(" + index + ")").children(".class-for-index1").text(minute + ":" + second);

            setTimeout(function () {
                countDownForActivity(endTime, index);
            }, 1000);
        } else {
            // find递归查询，与children查询最外面一层不同
            $(".class-for-index0:nth-child(" + index + ")").find(".class-for-index2").trigger("click");
            // $(".class-for-index0:nth-child(" + index + ")").children(".class-for-index1").text("倒计时结束");
        }
    }

    $.ajax({
        type: "post",
        url: "../../../order/getOrderList",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            "orderState": 0
        }),
        dataType: "json",
        success: function (json) {
            var dataarray = json.data;
            for (var order in dataarray) {
                $("#order-list").append(
                    "<div class='width-100-percent height-70px margin-top-15px font-size-15px line-height-70px border-1px-solid-DDDDDD hover-border-1px-solid-9F88FF class-for-index0'>" +
                    "<div class='float-left width-15-percent'>" + dataarray[order].orderIdStr + "</div>" +
                    "<div class='position-relative float-left height-100-percent padding-left-100px text-align-left' style='width: 25%;'>" +
                    "<img class='vertically-center-by-absolute left-30px width-60px height-60px' src='../../pic/" + dataarray[order].imageUrl + "' / > " +
                    dataarray[order].activityName +
                    "</div>" +
                    "<div class='float-left width-10-percent'>" + dataarray[order].cost + "</div>" +
                    "<div class='float-left width-15-percent'>" + dataarray[order].createTime + "</div>" +
                    "<div class='float-left width-15-percent class-for-index1'>" + "" + "</div>" +
                    "<div class='float-left' style='width: 20%;'>" +
                    "<input type='button' class='width-60px height-32px font-size-15px green-button' value='付款' onclick='payOrder(this);'/>&nbsp;&nbsp;" +
                    "<input type='button' class='width-60px height-32px font-size-15px blue-button class-for-index2' value='取消' onclick='cancelOrder(this);'/>&nbsp;&nbsp;" +
                    "<input type='button' class='width-60px height-32px font-size-15px red-button' value='删除' onclick='a(this);' />" +
                    "</div>" +
                    "</div>"
                );
            }
            for (var i = 0; i < dataarray.length; i++) {
                countDownForActivity(new Date(dataarray[i].createTime).getTime() + 1800000, i + 1);
            }
        },
        error: function () {
            //				alert(json.message);
        }
    });
});

$(function() {
	payOrder = function(obj) {
		var curDomObject = $(obj).parent().parent();
        // ,
        //     "subject": $($(curDomObject).children("div").get(1)).text(),
        //         "totalAmount": $($(curDomObject).children("div").get(2)).text()
		var jsonData = {
            "orderIdStr": $($(curDomObject).children("div").get(0)).text()
		};
		var jsonString = JSON.stringify(jsonData);
        // alert(jsonString);

		//$("#tipContent").text("正在提交");

		$.ajax({
			type: "post",
            url: "../../../alipay/payWeb",
			contentType: "application/json;charset=utf-8",
            async: true,
			data: jsonString,
			dataType: "json",
            timeout: 50000,
			success: function(json) {
				// $("#tipContent").text("取消成功");
				// confirmDelete = true;
				// $(curDomObject).hide();
                // debugger;
                $("#order-list").html(json.data);
			},
			error: function() {
				//				alert(json.message);
			}
		});
	}
});

$(function() {
    cancelOrder = function (obj) {
        var curDomObject = $(obj).parent().parent();
        hideMaskLayer();

        var jsonData = {
            "orderIdStr": $($(curDomObject).children("div").get(0)).text(),
            "orderState": "-1"
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
            success: function (json) {
                // $("#tipContent").text("取消成功");
                // confirmDelete = true;
                $(curDomObject).hide();

            },
            error: function () {
                //				alert(json.message);
            }
        });
    }
});
