

$(function() {
	/*var jsonData = {
		"activityId": $(idArray[0]).val()
	};
	var jsonString = JSON.stringify(jsonData);*/

	/*$.ajax({
		type: "post",
		url: "/alllink/activity/getActivityInfo",
		contentType: "application/json;charset=utf-8",
		dataType: "json",
		data: jsonString,
		async: false,
		success: function(json) {
			$("#activityName").text(json.data.activityName);

			var imgA
			rray = json.data.imageUrl.split(',');
			for(var i = 0; i < imgArray.length; i++) {
				$("#imgDiv").append(
					"<img class='vertically-center-by-absolute left-" + (30 + i * 70) + "px width-60px height-60px' src='/pic/" +
					imgArray[i] + "'" >

				);
			}

			$("#activityInfo").text(json.data.activityInfo);
			$("#activityType").text(json.data.activityType);
			$("#beginTime").text(json.data.beginTime);
			$("#endTime").text(json.data.endTime);
			$("#cost").text(json.data.cost);
			$("#number").text(json.data.enrollNumber + "/" + json.data.totalNumber);
		},
		error: function(json) {
			//				alert(json.message);
		}
	});*/
});

$(function() {
	// 报名活动
	apply = function() {
		var jsonData = {
			"activityId": $("#activityId").val(),
            "sellerId":$("#sellerId").val()
		};
		var jsonString = JSON.stringify(jsonData);

		$.ajax({
			type: "post",
			url: "../order/apply",
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			data: jsonString,
            async: true,
			success: function(json) {
				if(json==0){
                    var local = location.href;
                    location.href = "../userapp/login?url="+local;
				}
				if(json.result=="success"){
                    location.href = "../userapp/personal_center/order/unpaid_order";
				}else{

				}
			},
			error: function(json) {
								alert(json.message);
			}
		});
	}
});