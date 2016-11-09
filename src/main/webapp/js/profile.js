$(document).ready(function() {
	$("#overview").click(function() {
		$(".left-sidebar li").attr("class","li");
		$("#overview").attr("class","active");
	});
	$("#branches").click(function() {
		$(".left-sidebar li").attr("class","li");
		$("#branches").attr("class","active");
	});
	$("#tasks").click(function() {
		$(".left-sidebar li").attr("class","li");
		$("#tasks").attr("class","active");
	});
	$("#friendes").click(function() {
		$(".left-sidebar li").attr("class","li");
		$("#friendes").attr("class","active");
	});
	$("#dateNow").html(localDate(new Date()));
	$(".more-message").each(function() {
		$(this).click(function(){
			var i = $(this).parent().parent().find(".center-tasks-li-message").eq(0);
			if(i.css("display") == "none") {
				i.slideDown();
				$(this).html("Hide");
			} else {
				$(this).html("More");
				i.slideUp();
			}
		});
	});
	$("#logout").click(function() {
		$("#logoutForm").submit();
	});
});

function localDate(date) {
	var local = date.getFullYear() + "-";
	local = local + (date.getMonth() + 1) + "-";
	local = local + date.getDate();
	return local;
}

