$(function () {
    $.ajax({
        type: "post",
        url: "../../user/getUserBasicInfo",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            "phoneNumber": phoneNumber
        }),
        async: true,
        dataType: "json",
        success: function (json) {
            $("#integral").text(json.data.totalPoints);
        },
        error: function () {

        }
    });
});