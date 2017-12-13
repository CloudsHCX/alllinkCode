$(function(){
    $(".nameSpan").click(function (e) {alert("h");
        var id = $(e.target).attr('id');
        var activityId=$("#"+id).parent().find("span:last-child").attr("id");
        var jsondata = {"activityId": activityId};
        $.ajax({
            async: false,
            type: "POST",
            url: "/alllink/sellerActivity/info",
            processData: false,
            contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
            dataType: "json",
            data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
            success: function (result) {
                if(result.code == 1){
                    //这里还有动态添加li标签及内容到活动列表页面
                    window.location.href="/alllink/views/sellerapp/EventDetails.html?p1="+result.activityName+"&p2="+result.totalNumber+"&p3="+result.cost+"&p4="+result.activityType+"&p5="+result.beginTime+"&p6="+result.endTime+"&p7="+result.address+"&p8="+result.activityInfo;
                }
                else{
                    alert("出现错误！请重试！");
                }
            }
        });
    });
});
    $(function(){
    $(".cancel").click(function (e) {alert("cancel");
        var id = $(e.target).attr('id');
        var delid=$("#"+id).parent().parent().attr("id");
        var jsondata = {"activityId": id};
        $.ajax({
            type: "POST",
            url: "/alllink/sellerActivity/delete",
            processData: false,
            contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
            dataType: "json",
            data: JSON.stringify(jsondata),//这里必须将对象转成string类型。
            success: function (result) {
                if(result.code == 1){
                    $("#"+delid).remove();
                }
                else{
                    alert("出现错误！请重试！");
                }
            }
        });
    });
});