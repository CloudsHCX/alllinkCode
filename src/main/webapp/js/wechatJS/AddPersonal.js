$(function () {
    $("#submit").click(function () {
        var sellerId = $("#sellerId-none").html();
        var phoneNumber = $("#phoneNumber").html();
        var cr_real_name = $("#cr_real_name").val();
        var cr_card_id = $("#cr_card_id").val();
        var organziation_code_certificate = $("#organziation_code_certificate").val();
        var photo = $("#businessLicence").html();

        var jsondata = {
            "sellerId": sellerId,
            "crRealName": cr_real_name,
            "crCardId": cr_card_id,
            "organziationCodeCertificate": organziation_code_certificate,
            "businessLicence": photo
        };
        $.ajax({
            async: false,
            type: "POST",
            url: "/alllink/sellerauthinfo/save",
            processData: false,
            contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
            dataType: "json",
            data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
            success: function (result) {
                if (result.code == 1) {
                    //window.location.href="/alllink/views/wechat/w-personal.html?p1="+result.sellerAuthinfo.sauthinfoId+"&p2="+phoneNumber;
                    window.location.href = "/alllink/views/wechat/w-personal.html";
                }
                else {
                    $.alert("出现错误！请重试！");
                }
            },
            error: function (XMLHttpRequest, textStatus) {
                alert(textStatus);
            }
        });
    });
});