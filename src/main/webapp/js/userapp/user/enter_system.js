$(function() {
    //	curStep: 0表示注册,1表示登录,2表示重置密码
    submitInfoToEnterSystem = function(curStep) {
        // alert(1);
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
                        // alert("注册成功，即将跳转到登录页面");
                        location.href = '../userapp/login';
                    }else if(curStep == 1){
                         // alert("登录成功，即将跳转到用户中心");
                        //location.href = '../userapp/personal_center/personal_center';

                        //若不为空，则跳转到之前的页面
                        var ll = getQueryString("url");
                        if(ll!=null){
                            location.href = ll;
                        }else{
                            // alert("登录成功，跳转到主页面");
                            location.href = '../userapp/personal_center/personal_center';
                        }

                    }else if(curStep == 2){
                        // alert("重置密码成功，即将跳转到登录页面");
                        location.href = '../userapp/login';
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

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}