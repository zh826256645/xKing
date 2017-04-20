$(document).ready(function() {
	$("#addFriend").click(function(){
		var username = $("#friendUsername").val();
		if(!username || typeof(username) == "undefined"){
			bootbox.alert({
				message: "请输入要添加的用户！",
				size: 'small'
			});
			return;
		}
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

function friendRequest(env, state, username) {
	var host = window.location.host;
	var header = $("meta[name='_csrf_header']").attr("content");  
	var token = $("meta[name='_csrf']").attr("content");  
	$.ajax({
	type:"post",
	url: "http://" + host + "/user/friends/state",
	data:{"username":username, "state": state},
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
			if(data["code"] == "200"){
				setTimeout("location.reload(true)", 1000);;
			}
		}
	}
});
}
