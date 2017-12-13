 $(function(){
	  $("#btn").click(function () {
		  var sellerName = $("#sellerName").val();
		  var weChatNumber = $("#weChatNumber").val();
		  var email = $("#email").val();
		  var address = $("#address").val();
		  var jsondata = {"sellerName": sellerName,
		                  "wechatNumber":weChatNumber,
		                  "email":email,
		                  "address":address};
		  $.ajax({
              async: false,
			  type: "POST",
			  url: "/alllink/seller/update",
			  processData: false,
			  contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
			  dataType: "json",
			  data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
			  success: function (result) {
				  if(result.code == 1){
				  	// window.location.href="/alllink/sellerapp/homepage.html?p1="+result.seller.phoneNumber+"&p2="+result.seller.sellerName+'&p3='+result.seller.wechatNumber+'&p4='+result.seller.email+"&p5="+result.seller.address;

                      // $("#navName").val("");
                      // $("#navName").html(result.seller.sellerName);
                      // window.location.reload();
                      var d1=document.getElementById('right0');
                      var d2=document.getElementById('right1');
                      d2.style.display="block";
                      $("#d1").replaceWith($("#d2").html());
                      $("#phoneNumber1").html(result.seller.phoneNumber);
                      $("#sellerName1").html(result.seller.sellerName);
                      $("#weChatNumber1").html(result.seller.wechatNumber);
                      $("#email1").html(result.seller.email);
                      $("#address1").html(result.seller.address);
				  }
				  else{
				  	alert("出现错误！请重试！");
				  }
			  },
			  error:function(XMLHttpRequest, textStatus){
                  alert(XMLHttpRequest.status);
                  alert(XMLHttpRequest.readyState);
                  alert(textStatus);
			  }
		  });
	  });

     $("#name-btn").click(function () {
         var sellerName = $("#sellerName2").val();
         var jsondata = {"sellerName": sellerName};
         $.ajax({
             type: "POST",
             url: "/alllink/seller/update",
             processData: false,
             contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
             dataType: "json",
             data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
             success: function (result) {
                 if(result.code == 1){
                     var d1=document.getElementById('right1.2');
                     var d2=document.getElementById('right1');
                     d1.style.display="none";
                     d2.style.display="block";

                     $("#d1").replaceWith($("#d2").html());
                     $("#sellerName1").val("");
                     $("#sellerName1").html(result.seller.sellerName);
                     $("#navName").val("");
                     $("#navName").html(result.seller.sellerName);
                 }
                 else{
                     alert("出现错误！请重试！");
                 }
             }
         });
     });

     $("#wechat-btn").click(function () {
         var weChatNumber = $("#weChatNumber2").val();
         var jsondata = {"wechatNumber":weChatNumber};
         $.ajax({
             type: "POST",
             url: "/alllink/seller/update",
             processData: false,
             contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
             dataType: "json",
             data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
             success: function (result) {
                 if(result.code == 1){
                     var d1=document.getElementById('right1.3');
                     var d2=document.getElementById('right1');
                     d1.style.display="none";
                     d2.style.display="block";

                     $("#d1").replaceWith($("#d2").html());
                     $("#weChatNumber1").val("");
                     $("#weChatNumber1").html(result.seller.wechatNumber);
                 }
                 else{
                     alert("出现错误！请重试！");
                 }
             }
         });
     });

     $("#email-btn").click(function () {
         var email = $("#email2").val();
         var jsondata = {"email":email};
         $.ajax({
             type: "POST",
             url: "/alllink/seller/update",
             processData: false,
             contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
             dataType: "json",
             data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
             success: function (result) {
                 if(result.code == 1){alert("aa");
                     var d1=document.getElementById('right1.4');
                     var d2=document.getElementById('right1');
                     d1.style.display="none";
                     d2.style.display="block";
                     alert(result.seller.email);
                     $("#d1").replaceWith($("#d2").html());
                     $("#email1").val("");
                     $("#email1").html(result.seller.email);
                 }
                 else{
                     alert("出现错误！请重试！");
                 }
             }
         });
     });

     $("#address-btn").click(function () {
         var address = $("#address2").val();
         var jsondata = {"address":address};
         $.ajax({
             type: "POST",
             url: "/alllink/seller/update",
             processData: false,
             contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
             dataType: "json",
             data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
             success: function (result) {
                 if(result.code == 1){
                     var d1=document.getElementById('right1.5');
                     var d2=document.getElementById('right1');
                     d1.style.display="none";
                     d2.style.display="block";

                     $("#d1").replaceWith($("#d2").html());
                     $("#address1").val("");
                     $("#address1").html(result.seller.address);
                 }
                 else{
                     alert("出现错误！请重试！");
                 }
             }
         });
     });

  });
