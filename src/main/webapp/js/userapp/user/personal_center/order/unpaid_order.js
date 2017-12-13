var curDomObject;
$(function() {
	$.ajax({
		type: "post",
		url: "../order/getNonPayList",
		contentType: "application/json;charset=utf-8",
		data:JSON.stringify({"userName":"das"}),
		async: false,
        dataType: "json",
		success: function(json) {
			var dataarray = json.data;
			for(var order in dataarray) {
				$("#nonPayList").append(
					"<div class='width-100-percent height-70px margin-top-15px font-size-15px line-height-70px border-1px-solid-DDDDDD hover-border-1px-solid-9F88FF'>" +
					"<label class='display-none'>" + dataarray[order].orderId + "</label>"+
					"<div class='float-left width-15-percent'>" + dataarray[order].orderId + "</div>" +
					"<div class='position-relative float-left width-40-percent height-100-percent padding-left-100px text-align-left'>" +
					"<img class='vertically-center-by-absolute left-30px width-60px height-60px' src='../../pic/" + dataarray[order].imageUrl + "' / > " +
                    dataarray[order].activityName +
					"</div>" +
					"<div class='float-left width-15-percent'>" + dataarray[order].cost + "</div>" +
					"<div class='float-left width-15-percent'>" + dataarray[order].createTime + "</div>" +
					"<div class='float-left width-15-percent'>" +
					"<input type='button' class='width-60px height-32px font-size-15px green-button' value='支付' onclick='' />" + "&nbsp;" +
					"<input type='button' class='width-60px height-32px font-size-15px red-button' value='取消' onclick='a();setCurDomObject(this);' />" +
					"</div>" +
					"</div>"
				);
			}
		},
		error: function() {
			//				alert(json.message);
		}
	});
});

$(function(){
	a=function () {

        showMaskLayer('确定删除?');
    }
});
$(function() {
	deleteOrder = function() {
		hideMaskLayer();





		var jsonData = {
			"orderId": $($(curDomObject).children("label").get(0)).text()

    };
		var jsonString = JSON.stringify(jsonData);

		//$("#tipContent").text("正在提交");

		$.ajax({
			type: "post",
			url: "../order/deleteOrder",
			contentType: "application/json;charset=utf-8",
			async: false,
			data:jsonString,
			dataType:"json",
			success: function(json) {
				// $("#tipContent").text("取消成功");
				// confirmDelete = true;
                $(curDomObject).hide();

			},
			error: function() {
				//				alert(json.message);
			}
		});
	}
});

$(function() {
    setCurDomObject = function(obj) {

        curDomObject = $(obj).parent().parent();
    }
});