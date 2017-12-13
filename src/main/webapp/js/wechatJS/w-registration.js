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
             }
             else{
             	 message = "该手机号码可以注册！";
                 flag = true;
             }
             if(!flag){
            //提示错误效果
                $.alert(message);
//              jQuery("#phone").focus();
             }else{
            //提示正确效果
            document.getElementById("btn").innerHTML="验证码发送中...";
           		return flag;
             }
             }
			
			function checkPhoneIsExist(){
                 var phone = jQuery("#phone").val();
                 var flag = false;
                 var status=1;
                 jQuery.ajax(
                    { url: "checkPhone?t=" + (new Date()).getTime(),
                        data:{phone:phone},
                        dataType:"json",
                             type:"POST",
                             async:false,
                             success:function(data) {
                             status = data.code;
                             if(status == "0"){
                                 flag = true;
                             }
                         }
                });
                return flag;
             }
			
			function checkPassword(){
				var flag = false;
				var pwd1 = jQuery("#pwd1").val();
				var enoughRegex = new RegExp("(?=.{6,}).*", "g");
				if(pwd1 == ''){
                 $.alert("密码不能为空！");
             }else if(false ==enoughRegex.test(phone)){
                 $.alert("密码过短！至少6个字符！");
             }else{
             	var pwd2 = $("#pwd2").val();
             	if(pwd1 == pwd2)
				     {
				        flag = true;
				     }
				else {
					    $.alert("两次输入密码不一致，请重新输入！");
				      }
				
             }return flag;
			}