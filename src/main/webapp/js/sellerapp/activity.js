$(function(){
    $("#addactivity-btn").click(function(e) {
        var activityName=$("#activity_name").val().trim();
        var totalNumber=$("#total_number").val().trim();
        var cost=$("#cost").val().trim();
        var activityType=$("#activity_type").val().trim();
        var beginTime=$("#begin_time").val().trim();
        var endTime=$("#end_time").val().trim();
        var address=$("#address").val().trim();
        var activityInfo=$("#activity_info").val().trim();
        // var latitude=position.coords.latitude;
        // var longitude=position.coords.longitude;
        if (activityName==''){
            $("#activity_name").focus();
        }else if (totalNumber==''){
            $("#total_number").focus();
        }else if (cost==''){
            $("#cost").focus();
        }else if (activityType==''){
            $("#activity_type").focus();
        }else if (beginTime==''){
            $("#begin_time").focus();
        }else if (endTime==''){
            $("#end_time").focus();
        }else if (address==''){
            $("#address").focus();
        }else if (activityInfo==''){
            $("#activity_info").focus();
        }
        if (activityName==''||totalNumber==''||cost==''||activityType==''||beginTime==''||endTime==''||address==''||activityInfo=='') {
            alert("请完善所有信息！")
        }else {
            var jsondata = {"activityName": activityName,
                "totalNumber":totalNumber,
                "cost":cost,
                "activityType":activityType,
                "beginTime":beginTime,
                "endTime":endTime,
                "address":address,
                "activityInfo":activityInfo
            };
            $.ajax({
                async: false,
                type: "POST",
                url: "/alllink/sellerActivity/save",
                processData: false,
                contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
                dataType: "json",
                data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
                success: function (result) {
                    if(result.code == 1){
                    	//这里还有动态添加li标签及内容到活动列表页面
                        // window.location.href="/alllink/views/sellerapp/EventDetails.html?p1="+result.activityName+"&p2="+result.totalNumber+"&p3="+result.cost+"&p4="+result.activityType+"&p5="+result.beginTime+"&p6="+result.endTime+"&p7="+result.address+"&p8="+result.activityInfo;
                        window.location.href="/alllink/views/sellerapp/EventsList.html";
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
        }
    });
    
    $("#addimage").change(function (e) {
        $("#addimage").hide();
        var img_addrs=$("#addimage").prop("files");
        var result=document.getElementById("images");
        for(i = 0; i< img_addrs.length; i ++) {
            var reader= new FileReader();
            reader.readAsDataURL(img_addrs[i]);
            reader.onload=function(e){
                //多图预览
                result.innerHTML = result.innerHTML + ' <img width="120px" height="120px" src="' + this.result +'" alt="" />  ';
            }
        }
    });

});