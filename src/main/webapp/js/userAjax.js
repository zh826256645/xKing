$(document).ready(function() {
    $('#startTIme').datepicker({
        format: 'yyyy-mm-dd'
    });
    $('#endTIme').datepicker({
        format: 'yyyy-mm-dd'
    });
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
	$("#sendMessage").click(function(){
		var username = $("#messageUsername").val();
		var messageContent = $("#messageContent").val();
		if(!messageContent || typeof(messageContent) == "undefined"){
			bootbox.alert({
				message: "请输入你要说的话！",
				size: 'small'
			});
			return;
		}
		var host = window.location.host;
		var header = $("meta[name='_csrf_header']").attr("content");  
		var token = $("meta[name='_csrf']").attr("content");  
		$.ajax({
			type:"post",
			url: "http://" + host + "/user/friend/message",
			data:{"username": username,"content": messageContent},
			async:true,		    
			beforeSend: function(xhr){  
		        xhr.setRequestHeader(header, token);  
		    },
			success:function(data) {
				$("#messageContent").val("");
				if(data != null) {
					bootbox.alert({
	    				message: data["msg"],
	    				size: 'small'
					});
				}
				
				getFriendMessages(username);
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

function friendMessage(env) {
	var username = $(env).prev().html();

	$("#gridSystemModalLabel").html(username);
	$("#messageUsername").attr("value", username);
	$("#friendMessage").modal('show');
	getFriendMessages(username);
	var friend_message = $(env).parent().parent().parent().prev().children("#friend-message");
	if(friend_message.length > 0) {
		friend_message.remove();
	}
};

function getFriendMessages(username){
	var host = window.location.host; 
	$.ajax({
	type:"get",
	url: "http://" + host + "/user/friend/message?username=" + username ,
	async:true,
	success:function(data) {
		if(data != null ) {
			$("#messagWindow").empty();
			for(var index in data) {
				if(data[index].user.username == username){
					$("#messagWindow").append('<div class="row forum-li" >' +
									    		'<div class="col-xs-1 text-center" style="padding-left: 0px">' +
									    			'<img class="img-circle heard-profile-picture" src="//'+ host +'/picture/user/'+ data[index].user.username + '?pid=' + data[index].user.picture + '" /><br />' +
									    		'</div>' +
									    		'<div class="col-xs-11">' +
									    			'<span><a href="#">' + data[index].user.username + '</a> &nbsp;<small>' + getFormatTime(data[index].dateline) + '</small></span><br/>' +
									    			'<p class="h4">'+ data[index].content + '</p>' +
									    		'</div>' +
									    	'</div>')
				} else {
					$("#messagWindow").append('<div class="row forum-li" >' +
				    		'<div class="col-xs-11">' +
			    			'<span style="float: right;"><small>' + getFormatTime(data[index].dateline) +  '</small> &nbsp;<a href="#">ZhongHao</a></span><br/>' +
			    			'<p class="h4" style="float: right;text-align:right;">' + data[index].content + '</p>' +
			    		'</div>' +
			    		'<div class="col-xs-1 text-center" style="padding-left: 0px">' +
			    			'<img class="img-circle heard-profile-picture" src="//'+ host +'/picture/user/'+ data[index].user.username + '?pid=' + data[index].user.picture + '" /><br />' +
			    		'</div>' +
			    	'</div>')
				}
			}
		}
	}
});
}

function getFormatTime(time) {
	var date = new Date(time);
	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + ' ' + date.getHours() + ':' + date.getMinutes() + ':' +date.getSeconds();
}
