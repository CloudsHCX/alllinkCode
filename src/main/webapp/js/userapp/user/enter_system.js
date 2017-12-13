$(function() {
    //	curStep: 0表示注册,1表示登录,2表示重置密码
    submitInfoToEnterSystem = function(curStep) {
        if(checkPhoneNumberFormat() == false) {
            return false;
        }

        var isLogin = (curStep == 1) ? true : false;
        if(!isLogin) {
            if(checkVerificationCodeFormat() == false) {
                return false;
            }
        }

        if(checkPasswordFormat(isLogin) == false) {
            return false;
        }

        var idArray = ["#phoneNumber", "#password", "#verificationCode"];
        var jsonData;
        if(isLogin) {
            jsonData = {
                "phoneNumber": $(idArray[0]).val(),
                "password": $(idArray[1]).val()
            };
        } else {
            jsonData = {
                "phoneNumber": $(idArray[0]).val(),
                "password": $(idArray[1]).val(),
                "checkcode": $(idArray[2]).val()
            };
        }

        var jsonString = JSON.stringify(jsonData);

        var urlArray = ["../Register/toregistered", "../Login/tologin", "../user/resetPassword"];
        $.ajax({
            type: "post",
            url: urlArray[curStep],
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: jsonString,
            async: false,
            beforeSend: function() {
                showTip('正在检验，请稍等');
            },
            success: function(json) {
                if(json.result == "success") {
                    if(curStep == 0){
                        alert("注册成功，即将跳转到登录页面");
                        location.href = '../userapp/login';
                    }else if(curStep == 1){
                         alert("登录成功，即将跳转到用户中心");
                        location.href = '../userapp/                                                                                                            /personal_center';
                    }else if(curStep == 2){
                        alert("重置密码成功，即将跳转到登录页面");
                        location.href = '../userapp/logins';
                    }

                } else {
                    alert(json.message);
                }
            },
            error: function() {
                if(curStep == 0){
                    alert("注册失败！");
                }else if(curStep == 1){

                    alert("登录失败！");
                }else if(curStep == 2){

                    alert("重置密码失败！");
                }
            }
        });
    }
});