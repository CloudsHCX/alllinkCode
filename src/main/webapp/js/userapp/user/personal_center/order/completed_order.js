$(function () {
    $.ajax({
        type: "post",
        url: "../../../order/getOrderList",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            "orderState": 6
        }),
        async: false,
        dataType: "json",
        success: function (json) {
            var dataarray = json.data;
            for (var order in dataarray) {
                $("#order-list").append(
                    "<div class='width-100-percent height-70px margin-top-15px font-size-15px line-height-70px border-1px-solid-DDDDDD hover-border-1px-solid-9F88FF'>" +
                    "<div class='float-left width-15-percent'>" + dataarray[order].orderIdStr + "</div>" +
                    "<div class='position-relative float-left height-100-percent padding-left-100px text-align-left' style='width: 30%;'>" +
                    "<img class='vertically-center-by-absolute left-30px width-60px height-60px' src='" + dataarray[order].imageUrl + "' / > " +
                    dataarray[order].activityName +
                    "</div>" +
                    "<div class='float-left width-15-percent'>" + dataarray[order].cost + "</div>" +
                    "<div class='float-left width-20-percent'>" + dataarray[order].createTime + "</div>" +
                    "<div class='float-left width-20-percent'>" +
                    "<input type='button' class='width-60px height-32px font-size-15px red-button' value='删除' onclick='a(this);'/>" +
                    "</div>" +
                    "</div>"
                );
            }
        },
        error: function () {
            //				alert(json.message);
        }
    });
});