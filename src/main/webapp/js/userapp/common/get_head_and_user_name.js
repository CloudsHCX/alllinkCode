var phoneNumber;

//获取头像和用户名
$(function () {
    $.ajax({
        type: "post",
        url: "/alllink/user/getUserInfoFromSession",
        contentType: "application/json;charset=utf-8",
        async: true,
        success: function (json) {
            phoneNumber = json.phoneNumber;

            $("#header-user-name").text(phoneNumber.substring(0, 4) + "...");
            $("#header-avatar").attr("src", "/pic/" + json.photoUrl);
            $("#main-avatar").attr("src", "/pic/" + json.photoUrl);
        },
        error: function () {

        }
    });
});