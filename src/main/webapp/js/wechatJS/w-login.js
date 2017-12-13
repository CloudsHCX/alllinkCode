

$(function(){
    $("#login-btn").click(function () {
        var phoneNumber = $("#phoneNumber").val();
        var password = $("#password").val();
        var jsondata={"phoneNumber":phoneNumber,"password":password};
        if(phoneNumber!=""){
            //document.getElementById("form2").submit();
            $.ajax({
                type:"POST",
                url:"/alllink/seller/login",
                processData: false,
                contentType:"application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
                dataType: "json",
                data:JSON.stringify(jsondata),//这里必须将对象转成string类型
                success:function(result){
                    if(result.code == 1){
                        window.location.href="/alllink/success.jsp";
                    }else {
                        alert(result.msg);
                    }
                },
                error:function(e){
                    alert("登录错误！！");
                    //这里能不能分别把用户名和密码的正确与否传过来，我在页面设置用户名正确，密码错误和用户名错误两种不同的效果
                }
            });
        }
        else{
            $.alert("用户名不能为空！");
            return false;
        }


    });
});