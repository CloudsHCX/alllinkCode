//验证密码强度
$(function () {
    $('#pwd1').keyup(function (e) {
        //密码为八位及以上并且字母数字特殊字符三项都包括，强度最强
        var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
        //密码为七位及以上并且字母、数字、特殊字符三项中有两项，强度是中等
        var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
        //强度为弱
        var enoughRegex = new RegExp("(?=.{6,}).*", "g");
        if ($(this).val() == "") {
            $("#pwd1-p").html("密码为空，请输入密码！");
            $("#pwd1-p").css("color", "#ff0000");
        } else if (false == enoughRegex.test($(this).val())) {
            $("#pwd1-p").html("密码过短！至少6个字符");
            $("#pwd1-p").css("color", "#ff0000");
        } else if (strongRegex.test($(this).val())) {
            $('#strong').className = 'ok';
            $("#pwd1-p").html("");
            $('#weak').css("background-color", "#DCDCDC");
            $('#medium').css("background-color", "#DCDCDC");
            $('#strong').css("background-color", "#009900");
        } else if (mediumRegex.test($(this).val())) {
            $('#medium').className = 'alert';
            $("#pwd1-p").html("");
            $('#weak').css("background-color", "#DCDCDC");
            $('#medium').css("background-color", "#ff9900");
            $('#strong').css("background-color", "#DCDCDC");
        } else {
            $('#weak').className = 'error';
            $("#pwd1-p").html("");
            $('#weak').css("background-color", "#ff0000");
            $('#medium').css("background-color", "#DCDCDC");
            $('#strong').css("background-color", "#DCDCDC");
        }
        return true;
    });
});

//校验密码一致性
function check() {
    var pwd1 = $("#pwd1").val();
    var pwd2 = $("#pwd2").val();
    if (pwd1 != "") {
        if (pwd1 == pwd2) {
            $("#pwd2-p").html("密码通过！");
            $("#pwd2-p").css("color", "#009900");
        }
        else {
            $("#pwd2-p").html("两次输入密码不一致，请重新输入！");
            $("#pwd2-p").css("color", "#ff0000");
        }
    }
    else {
        $("#pwd1-p").html("密码为空，请输入密码！");
        $("#pwd1-p").css("color", "#ff0000");
        return false;
    }
    return true;
}

function RegistrationValidate() {
    if (check()) {
        var phoneNumber = $("#phone").val();
        var password = $("#pwd1").val();
        var verificationCode = $("#verificatonCode").val();
        var jsondata = {"phoneNumber": phoneNumber, "password": password, "verificationCode": verificationCode};
        $.ajax({
            type: "POST",
            url: "/alllink/seller/add",
            processData: false,
            contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
            dataType: "json",
            data: JSON.stringify(jsondata),//这里必须将对象转成string类型，否则将掉入无线的大坑中。
            success: function (result) {
                if (result.code == 1) {
                    window.location.href = "/alllink/views/sellerapp/login.html";
                } else {
                    $("#sms-p").html(result.msg + "!");
                    $("#sms-p").css("color", "#ff0000");
                }
            }
        });
        return true;
    } else {
        return false;
    }
}

//发送短信
$("#btn").click(function () {
    var phoneNumber = $("#phone").val();
    var jsondata = {"phoneNumber": phoneNumber};
    $.ajax({
        type: "POST",
        url: "/alllink/seller/sendMessage",
        processData: false,
        contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
        dataType: "json",
        data: JSON.stringify(jsondata),//这里必须将对象转成string类型，否则将掉入无线的大坑中。
        success: function (success) {
            if (success.code == 1) {
                // alert("发送验证码成功");
                var countdown = 60;

                function sendemail() {
                    var obj = $("#btn");
                    settime(obj);
                }

                sendemail();

                function settime(obj) { //发送验证码倒计时
                    if (countdown == 0) {
                        obj.attr('disabled', false);
                        //obj.removeattr("disabled");
                        obj.val("免费获取验证码");
                        countdown = 60;
                        return;
                    } else {
                        obj.attr('disabled', true);
                        obj.val("重新发送(" + countdown + ")");
                        countdown--;
                    }
                    setTimeout(function () {
                            settime(obj)
                        }
                        , 1000)
                }
            } else {
                alert(success.msg);
            }
        }
    });
});

