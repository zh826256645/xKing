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
	
	$("#changeIntroduction").click(function() {
	 	$("#changeIntroduction").css("display", "none");
	 	$("#input-introduction").css("display", "inline");
	 	$("#introduction-submit").css("display", "inline");
	});
	
	$("#introduction-submit").click(function() {
		var introduction = $("#input-introduction").val();
		var header = $("meta[name='_csrf_header']").attr("content");  
		var token = $("meta[name='_csrf']").attr("content");  
		$.ajax({
			type:"post",
			url:"http://localhost:8888/xKing/setting/introduction",
			data:introduction,
			contentType:"text/html;charset=UTF-8",
			async:true,
		    beforeSend: function(xhr){  
		        xhr.setRequestHeader(header, token);  
		    },
			success:function(data) {
				if(data != null) {
				 	$("#changeIntroduction").css("display", "inline");
				 	$("#input-introduction").css("display", "none");
				 	$("#introduction-submit").css("display", "none");
				 	$("#changeIntroduction").html(data);
				}
			}
		});
	});
});

function localDate(date) {
	var local = date.getFullYear() + "-";
	local = local + (date.getMonth() + 1) + "-";
	local = local + date.getDate();
	return local;
}

function changePassword() {
	$("#changePassword").remove();
	$("#password").css("display", "block");
}


