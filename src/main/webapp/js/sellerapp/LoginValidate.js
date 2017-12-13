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
                success:function(success){
                    if(success.code == 1){
                        // window.location.href="/alllink/success.jsp";
                        window.location.href="/alllink/views/sellerapp/homepage.html"
                    }else {
                        alert(success.msg);
                    }

                },
                error:function(e){
                    alert("错误！！");
                    //这里能不能分别把用户名和密码的正确与否传过来，我在页面设置用户名正确，密码错误和用户名错误两种不同的效果
                }
            });
        }
        else{
            $("#phone-p").html("用户名不能为空！");
            $("#phone-p").css("color","#ff0000");
            return false;
        }

//记住密码        
        var oForm = document.getElementById('form');
        var ophoneNumber = document.getElementById('phoneNumber');
        var opassword = document.getElementById('password');
        var oRemember = document.getElementById('remember');
        //页面初始化时，如果帐号密码cookie存在则填充
        if(getCookie('phoneNumber') && getCookie('password')){
            ophoneNumber.value = getCookie('phoneNumber');
            opassword.value = getCookie('password');
            oRemember.checked = true;
        }
        //复选框勾选状态发生改变时，如果未勾选则清除cookie
        oRemember.onchange = function(){
            if(!this.checked){
                delCookie('phoneNumber');
                delCookie('password');
            }
        };
        //表单提交事件触发时，如果复选框是勾选状态则保存cookie
        oForm.onsubmit = function(){
            if(remember.checked){
                setCookie('phoneNumber',ophoneNumber.value,7); //保存帐号到cookie，有效期7天
                setCookie('password',opassword.value,7); //保存密码到cookie，有效期7天
            }
        };

        //设置cookie
        function setCookie(name,value,day){
            var date = new Date();
            date.setDate(date.getDate() + day);
            document.cookie = name + '=' + value + ';expires='+ date;
        };
        //获取cookie
        function getCookie(name){
            var reg = RegExp(name+'=([^;]+)');
            var arr = document.cookie.match(reg);
            if(arr){
                return arr[1];
            }else{
                return '';
            }
        };
        //删除cookie
        function delCookie(name){
            setCookie(name,null,-1);
        };

    });
});