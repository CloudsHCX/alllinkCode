//验证手机号
function vailPhone(){
    var phone = jQuery("#phone").val();
    var flag = false;
    var message = "";
    var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if(phone == ''){
        message = "手机号码不能为空！";
    }else if(phone.length !=11){
        message = "手机号码必须为11位！";
    }
    else if(checkPhoneIsExist()){
        message = "该手机号码已经被绑定！";
    } else{
        message = "该手机号码可以注册！";
        flag = true;
    }
    if(!flag){
        //提示错误效果
        $.alert(message);
    }else{
        //提示正确效果
        //
        return true;
    }
    return flag;
}
//验证手机号是否存在
function checkPhoneIsExist(){
    var phone = jQuery("#phone").val();
    var flag = false;
    var status = 1;
    jQuery.ajax({
        url: "/alllink/seller/checkPhone?t=" + (new Date()).getTime(),
        data:{phoneNumber:phone},
        dataType:"json",
        type:"POST",
        async:false,
        success:function(data) {
            status = data.code;
            if(status == "0"){//手机号重复
                flag = true;
            }
        }
    });
    return flag;
}
//验证密码强度
$(function(){
    $('#pwd1').blur(function(e) {

        //强度为弱
        var enoughRegex = new RegExp("(?=.{6,}).*", "g");
        if($(this).val()==""){
            $.alert("密码不能为空！");
        }
        else if(false == enoughRegex.test($(this).val() ) )
        {
            $.alert("密码过短！至少6个字符");
        }
        return true;
    });
});
//校验密码一致性
function check(){
    var pwd1 = $("#pwd1").val();
    var pwd2 = $("#pwd2").val();
    if(pwd1!=""){
        if(pwd1 == pwd2)
        {
            return true;
        }
        else {

            $.alert("两次输入密码不一致，请重新输入！");
        }
    }
    else{

        $.alert("密码为空，请输入密码！");
        return false;
    }
    return true;
}
function RegistrationValidate(){
    if(vailPhone()&&check()){
        var phoneNumber = $("#phone").val();
        var password = $("#pwd1").val();
        var verificationCode = $("#verificatonCode").val();
        var jsondata={"phoneNumber":phoneNumber,"password":password, "verificationCode":verificationCode};
        $.ajax({
            type:"POST",
            url:"/alllink/seller/add",
            processData:false,
            contentType:"application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
            dataType: "json",
            data:JSON.stringify(jsondata),//这里必须将对象转成string类型，否则将掉入无线的大坑中。
            success:function(result){
                if(result.code == 1){
                    window.location.href="/alllink/views/wechat/w-login.html";
                } else {

                    $.alert(result.msg);
                }
            }
        });
        return true;
    }else{
        return false;
    }
}
//发送短信
$("#btn").click(function () {
    var phoneNumber = $("#phone").val();
    var jsondata={"phoneNumber":phoneNumber};
    $.ajax({
        type:"POST",
        url:"/alllink/seller/sendMessage",
        processData: false,
        contentType:"application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
        dataType: "json",
        data:JSON.stringify(jsondata),//这里必须将对象转成string类型，否则将掉入无线的大坑中。
        success:function(success){
            if(success.code == 1)
            {
                // alert("发送验证码成功");
                var countdown=60;
                function sendemail(){
                    var obj = $("#btn");
                    settime(obj);
                }
                sendemail();
                function settime(obj) { //发送验证码倒计时
                    if (countdown == 0) {
                        obj.attr('disabled',false);
                        //obj.removeattr("disabled");
                        obj.val("获取验证码");
                        countdown = 60;
                        return;
                    } else {
                        obj.attr('disabled',true);
                        obj.val("倒计时(" + countdown + ")");
                        // $.alert("重新发送(" + countdown + ")");
                        countdown--;
                    }
                    setTimeout(function() {
                            settime(obj) }
                        ,1000)
                }
            }else {
                alert(success.msg);
            }
        }
    });
});

