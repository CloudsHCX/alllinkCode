$(function () {
    $("#btn").click(function () {
        var sellerName = $("#sellerName").val();
        var weChatNumber = $("#weChatNumber").val();
        var alipayNumber = $("#alipayNumber").val();
        var email = $("#email").val();
        var address = $("#address").val();
        var photo = $("#sellerPhoto").attr("src");
        var sellerId = $("#sellerId-none").html();
        var jsondata = {
            "sellerId": sellerId,
            "sellerName": sellerName,
            "wechatNumber": weChatNumber,
            "alipayNumber": alipayNumber,
            "email": email,
            "address": address,
            "photo": photo
        };
        $.ajax({
            async: false,
            type: "POST",
            url: "/alllink/seller/update",
            processData: false,
            contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
            dataType: "json",
            data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
            success: function (result) {
                if (result.code == 1) {
                    var d1 = document.getElementById('right0');
                    var d2 = document.getElementById('right1');
                    d2.style.display = "block";
                    $("#d1").replaceWith($("#d2").html());
                    $("#seller_photo").attr("src", result.seller.photo);
                    $("#phoneNumber1").html(result.seller.phoneNumber);
                    $("#alipayNumber1").html(result.seller.alipayNumber);
                    $("#sellerName1").html(result.seller.sellerName);
                    $("#weChatNumber1").html(result.seller.wechatNumber);
                    $("#email1").html(result.seller.email);
                    $("#address1").html(result.seller.address);
                }
                else {
                    alert("出现错误！请重试！");
                }
            },
            error: function (XMLHttpRequest, textStatus) {
                alert(textStatus);
            }
        });
    });


    $("#name-btn").click(function () {
        var sellerName = $("#sellerName2").val();
        var sellerId = $("#sellerId-none").html();
        var jsondata = {"sellerName": sellerName, "sellerId": sellerId};
        $.ajax({
            type: "POST",
            url: "/alllink/seller/update",
            processData: false,
            contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
            dataType: "json",
            data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
            success: function (result) {
                if (result.code == 1) {
                    var d1 = document.getElementById('right1.2');
                    var d2 = document.getElementById('right1');
                    d1.style.display = "none";
                    d2.style.display = "block";

                    $("#d1").replaceWith($("#d2").html());
                    $("#sellerName1").val("");
                    $("#sellerName1").html(result.seller.sellerName);
                    $("#navName").val("");
                    $("#navName").html(result.seller.sellerName);
                }
                else {
                    alert("出现错误！请重试！");
                }
            }
        });
    });

    $("#wechat-btn").click(function () {
        var weChatNumber = $("#weChatNumber2").val();
        var sellerId = $("#sellerId-none").html();
        var jsondata = {"wechatNumber": weChatNumber, "sellerId": sellerId};
        $.ajax({
            type: "POST",
            url: "/alllink/seller/update",
            processData: false,
            contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
            dataType: "json",
            data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
            success: function (result) {
                if (result.code == 1) {
                    var d1 = document.getElementById('right1.3');
                    var d2 = document.getElementById('right1');
                    d1.style.display = "none";
                    d2.style.display = "block";

                    $("#d1").replaceWith($("#d2").html());
                    $("#weChatNumber1").val("");
                    $("#weChatNumber1").html(result.seller.wechatNumber);
                }
                else {
                    alert("出现错误！请重试！");
                }
            }
        });
    });

    $("#email-btn").click(function () {
        var email = $("#email2").val();
        var sellerId = $("#sellerId-none").html();
        var jsondata = {"email": email, "sellerId": sellerId};
        $.ajax({
            type: "POST",
            url: "/alllink/seller/update",
            processData: false,
            contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
            dataType: "json",
            data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
            success: function (result) {
                if (result.code == 1) {
                    var d1 = document.getElementById('right1.4');
                    var d2 = document.getElementById('right1');
                    d1.style.display = "none";
                    d2.style.display = "block";
                    $("#d1").replaceWith($("#d2").html());
                    $("#email1").val("");
                    $("#email1").html(result.seller.email);
                }
                else {
                    alert("出现错误！请重试！");
                }
            }
        });
    });

    $("#address-btn").click(function () {
        var address = $("#address2").val();
        var sellerId = $("#sellerId-none").html();
        var jsondata = {"address": address, "sellerId": sellerId};
        $.ajax({
            type: "POST",
            url: "/alllink/seller/update",
            processData: false,
            contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
            dataType: "json",
            data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
            success: function (result) {
                if (result.code == 1) {
                    var d1 = document.getElementById('right1.5');
                    var d2 = document.getElementById('right1');
                    d1.style.display = "none";
                    d2.style.display = "block";
                    $("#d1").replaceWith($("#d2").html());
                    $("#address1").val("");
                    $("#address1").html(result.seller.address);
                }
                else {
                    alert("出现错误！请重试！");
                }
            }
        });
    });
});
