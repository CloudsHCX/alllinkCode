$(function(){
    $("#addactivity-btn").click(function(e) {

        var activityName=$("#activity_name").val().trim();
        var totalNumber=$("#total_number").val().trim();
        var cost=$("#cost").val().trim();
        var activityType=$("#activity_type").val().trim();
        var beginTime=$("#begin_time").val();
        var t1 = $("#t1").val().trim();
        var t2 = $("#t2").val().trim();
        var t3 = $("#t3").val().trim();
        var t4 = $("#t4").val().trim();
        beginTime = beginTime + " " + t1 + t2 + ":" + t3 + t4 + ":00";
        var endTime=$("#end_time").val();
        var e1 = $("#e1").val().trim();
        var e2 = $("#e2").val().trim();
        var e3 = $("#e3").val().trim();
        var e4 = $("#e4").val().trim();
        endTime = endTime + " " + e1 + e2 + ":" + e3 + e4 + ":00";
        // beginTime=beginTime.replace('T',' ');
        // beginTime=beginTime+":00";
        // var endTime=$("#end_time").val();
        // endTime=endTime.replace('T',' ');
        // endTime=endTime+":00";
        var address=$("#address").val().trim();
        var activityInfo=$("#activity_info").val().trim();
        var latitude=$("#latitude").html();
        var longitude=$("#longitude").html();

        var sellerId = $("#sellerId-none").html();
        var activityPhoto= $("#activityPhoto").attr("src");
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

        function check(beginTime, endTime) {
            if (beginTime.length > 0 && endTime.length > 0) {
                var startTmp = beginTime.toString().substr(0, 10).split("-");
                var endTmp = endTime.toString().substr(0, 10).split("-");
                var startTmp1 = beginTime.toString().substr(12, 19).split(":");
                var endTmp1 = endTime.toString().substr(12, 19).split(":");
                var sd = new Date(startTmp[0], startTmp[1], startTmp[2], startTmp1[0], startTmp1[1], startTmp1[2]);
                var ed = new Date(endTmp[0], endTmp[1], endTmp[2], endTmp1[0], endTmp1[1], endTmp1[2]);
                if (sd.getTime() > ed.getTime()) {
                    return true;
                }
            }
            return false;
        }

        if (activityName == '' || totalNumber == '' || cost == '' || activityType == '' || beginTime == '' || endTime == '' || address == '' || activityInfo == '' || latitude == '' || longitude == '' || t1 == '' || t2 == '' || t3 == '' || t4 == '' || e1 == '' || e2 == '' || e3 == '' || e4 == '') {
            alert("请完善所有信息！")
        } else if (check(beginTime, endTime)) {
            alert("开始日期不能大于结束日期");
        }else {
            var jsondata = {
                "sellerId": sellerId,
                "activityName": activityName,
                "totalNumber":totalNumber,
                "cost":cost,
                "activityType":activityType,
                "beginTime":beginTime,
                "endTime":endTime,
                "address":address,
                "activityInfo":activityInfo,
                "activityPhoto":activityPhoto,
                "latitude":latitude,
                "longitude":longitude
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
                    alert("每个时间输入框只能输入一位数字！请重新输入");
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

    $("#addimage").change(function () {
        $.ajaxFileUpload({
            url: "/alllink/file/photo", //后台方法的路径
            type: "post",   //当要提交自定义参数时，这个参数要设置成post
            fileElementId:"addimage",    //需要上传的文件域的ID，即<input type="file">的ID。
            dataType: "json",   //服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。
            //secureuri: false,   //是否启用安全提交，默认为false。
            contentType: "application/json;charset=utf-8",
            //async:false,   //是否是异步
            success: function(result) {//提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                // $("#activityPhoto").attr("src",result.url);
                document.getElementById("activityPhoto").setAttribute('src', result.url);
            },
            error: function(data, status, e) {  //提交失败自动执行的处理函数。
                alert("添加图片失败");
            }
        });
    });
});