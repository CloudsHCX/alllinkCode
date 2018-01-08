$(function () {
    getQueryString = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    };

    commitEvaluation = function () {
        var score = $("input:radio[name=evaluation]:checked").val();
        if (score == null) {
            showTip("请打分");
            return;
        }

        var evaluationContent = $("#evaluation-content").val();
        if (evaluationContent == "") {
            showTip("请填写评价内容");
            return;
        }

        var url = window.location.search;
        var activityId = getQueryString("activityId");
        var orderId = getQueryString("orderId");

        var jsonData = {
            "activityId": activityId,
            "orderId": orderId,
            "evaluateLevel": score,
            "evaluateContent": evaluationContent
        };

        var jsonString = JSON.stringify(jsonData);

        $.ajax({
            type: "post",
            url: "../../../evaluate/saveWeb",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: jsonString,
            async: false,
            success: function (json) {
                if (json.result == "success") {
                    location.href = "completed_order";
                }
            },
            error: function () {

            }
        });
    }
});