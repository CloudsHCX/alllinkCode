$(function () {
    $("#addactivity-btn").click(function (e) {
        var activityName = $("#activity_name").val().trim();
        var totalNumber = $("#total_number").val().trim();
        var cost = $("#cost").val().trim();
        var activityType = $("#activity_type").val().trim();
        var beginTime = $("#begin_time").val();
        beginTime = beginTime.replace('T', ' ');
        beginTime = beginTime + ":00";
        var endTime = $("#end_time").val();
        endTime = endTime.replace('T', ' ');
        endTime = endTime + ":00";
        var address = $("#address").val().trim();
        var activityInfo = $("#activity_info").val().trim();
        var activityPhoto = $("#photo").html();
        var sellerId = $("#seller_id").val();
        var latitude = $("#latitude").html();
        var longitude = $("#longitude").html();

        // alert("经度"+longitude+"\n纬度"+latitude);
        function check(beginTime, endTime) {
            if (beginTime.length > 0 && endTime.length > 0) {
                var startTmp = beginTime.toString().substr(0, 10).split("-");
                var endTmp = endTime.toString().substr(0, 10).split("-");
                var startTmp1 = beginTime.toString().substr(12, 19).split(":");
                var endTmp1 = endTime.toString().substr(12, 19).split(":");
                var sd = new Date(startTmp[0], startTmp[1], startTmp[2], startTmp1[0], startTmp1[1], startTmp1[2]);
                var ed = new Date(endTmp[0], endTmp[1], endTmp[2], endTmp1[0], endTmp1[1], endTmp1[2]);
                if (sd.getTime() > ed.getTime()) {
                    return true;
                }
            }
            return false;
        }

        if (activityName == '' || totalNumber == '' || cost == '' || activityType == '' || beginTime == '' || endTime == '' || address == '' || activityInfo == '') {
            $.alert("请完善所有信息！")
        } else if (check(beginTime, endTime)) {
            $.alert("开始日期不能大于结束日期");
        } else {
            var jsondata = {
                "sellerId": sellerId,
                "activityName": activityName,
                "totalNumber": totalNumber,
                "cost": cost,
                "activityType": activityType,
                "beginTime": beginTime,
                "endTime": endTime,
                "address": address,
                "activityInfo": activityInfo,
                "activityPhoto": activityPhoto,
                "latitude": latitude,
                "longitude": longitude
            };
            $.ajax({
                async: false,
                type: "POST",
                url: "/alllink/sellerActivity/save",
                processData: false,
                contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
                dataType: "json",
                data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
                success: function (result) {
                    if (result.code == 1) {
                        //这里还有动态添加li标签及内容到活动列表页面
                        window.location.href = "/alllink/views/wechat/w-EventList.html";
                    }
                    else {
                        alert("出现错误！请重试！");
                    }
                },
                error: function (XMLHttpRequest, textStatus) {
                    alert(textStatus);
                }
            });
        }
    });
});