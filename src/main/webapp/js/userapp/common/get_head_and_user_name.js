//获取头像和用户名
$(function () {
    $.ajax({
        type: "post",
        url: "/alllink/user/getUserInfoFromSession",
        contentType: "application/json;charset=utf-8",
        async: true,
        success: function (json) {
            var phoneNumber = json.phoneNumber;
            phoneNumber = phoneNumber.substring(0, 4) + "...";

            $("#header-user-name").text(phoneNumber);
            $("#header-avatar").attr("src", json.photoUrl);
        },
        error: function () {

        }
    });
});