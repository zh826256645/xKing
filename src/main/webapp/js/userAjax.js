$(document).ready(function() {
	$("#addFriend").click(function(){
		var username = $("#friendUsername").val();
		var host = window.location.host;
		var header = $("meta[name='_csrf_header']").attr("content");  
		var token = $("meta[name='_csrf']").attr("content");  
		$.ajax({
			type:"post",
			url: "http://" + host + "/user/friends/new",
			contentType:"text/html;charset=UTF-8",
			data:username,
			async:true,		    
			beforeSend: function(xhr){  
		        xhr.setRequestHeader(header, token);  
		    },
			success:function(data) {
				if(data != null) {
					bootbox.alert({
	    				message: data["msg"],
	    				size: 'small'
					});
				}
			}
		});
	});
});
