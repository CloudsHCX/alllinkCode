
$(function() {
	var searchContent = "#searchContent";
	if($(searchContent).val() == "") {
		$(searchContent + "Label").show();
	}

	$(searchContent).focus();
});

var searchContentArray = ["", "所有类型", "综合排序"];
$(function() {
	var searchButtonId = "#search";

	// 搜索处被修改的地方个数
	var searchModifiedPlaceCount = 0;
	// 对应活动名称(0)、类型(1)、排序方式(2)
	var modifiedState = [false, false, false];

	setButtonState = function(isAdd) {
		if(isAdd) {
			if(++searchModifiedPlaceCount == 1) {
				$(searchButtonId).attr("disabled", false);
			}
		} else {
			if(--searchModifiedPlaceCount == 0) {
				$(searchButtonId).attr("disabled", true);
			}
		}
	}

	contrastAndAdjustModifiedState = function(infoNumber, curModifiedState) {
		if(curModifiedState != modifiedState[infoNumber]) {
			if(curModifiedState) {
				setButtonState(true);
			} else {
				setButtonState(false);
			}

			modifiedState[infoNumber] = curModifiedState;
		}
	}
	
//	监听div内容变化
//	$(".select-header").on("DOMNodeInserted", function(){
//      alert($(this).text());
// });
	
	monitorTypeAndSortOrderChanges = function(obj, column) {
		var originalValue = searchContentArray[column];
		var curValue = $(obj).prev().text();

		if(originalValue == curValue) {
			contrastAndAdjustModifiedState(column, false);
		} else {
			contrastAndAdjustModifiedState(column, true);
		}
	}

	// 监听活动名称文本框输入内容变化
	$("#searchContent").on("input propertychange", function() {
		var originalValue = searchContentArray[0];
		var curValue = $(this).val();

		if(!checkInfoFormat(curValue, 0) || (originalValue == curValue)) {
			contrastAndAdjustModifiedState(0, false);
		} else {
			contrastAndAdjustModifiedState(0, true);
		}
	});
});