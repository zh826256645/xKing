$(document).ready(function() {
	$("input.submit").mouseover(function() {
		$("input.submit").css("background-color","rgba(18,176,255,1)");	
	});
	$("input.submit").mouseout(function() {
		$("input.submit").css("background-color","rgba(18,176,255,0.8)");	
	});
	$("form#login").submit(function() {
		return checkedAll(0);
	});
	$("form#register").submit(function() {
		return checkedAll(1);
	});
	$("#username").blur(function(){
		checkedUsername();
	});
	$("#password").blur(function(){
		checkedPassword();
	});
	$("#email").blur(function() {
		checkedEmail();
	});
});

function checkedUsername() {
	var username = $("#username").val();
	if(username.length == 0) {
		verifyChange(0,"username")
		$("#username").attr("placeholder","用户名不能为空!");
		return false;
	} else if(username.length < 6) {
		verifyChange(0,"username");
		$("div.messge font").html("用户名长度要大于6!");
		$("div.messge").fadeIn();
		return false;
	} else {
		verifyChange(1,"username");
		$("div.messge").fadeOut();
		return true;
	}
}

function checkedPassword() {
	var password = $("#password").val();
	if(password.length == 0) {
		verifyChange(0,"password")
		$("#password").attr("placeholder","密码不能为空！");
		return false;
	} else if(password.length < 6) {
		verifyChange(0,"password");
		$("div.messge font").html("密码长度要大于6!");
		$("div.messge").fadeIn();
		return false
	} else {
		verifyChange(1,"password");
		$("div.messge").fadeOut();
		return true;
	}
}

function checkedEmail() {
	var email = $("#email").val();
	var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.length == 0) {
		verifyChange(0,"email");
		$("#email").attr("placeholder","邮箱不能为空！");
		return false;
	} else if(!filter.test(email)) {
		verifyChange(0,"email");
		$("div.messge font").html("邮箱格式不正确!");
		$("div.messge").fadeIn();
		return false;
	} else {
		verifyChange(1,"email");
		$("div.messge").fadeOut();
		return true;		
	}
}

function verifyChange(i,id) {
	var name = "#" + id;
	if(i == 0) {
		$(name).css("border","1px solid #EF4836");
		$(name).css("background-color","#E4F1FE");
		$(name).focus();
	} else if(i == 1) {
		$(name).css("border","1px solid #2ECC71");
		$(name).css("background-color","#FFFFFF");	
	}
}

function checkedAll(i) {
	if(i == 0){
		if(checkedUsername() && checkedPassword()) {
			return true;
		} else {
			return false;
		}
	} else if(i == 1) {
		if(checkedUsername() && checkedPassword() && checkedEmail()) {
			return true;
		} else {
			return false;
		}	
	}
}

function ajaxUsername() {
	$.ajax({
		url:'http://localhost:8888/xKing/user/ajax/username?username=' + $("#username").val(),
		type:'GET',
		async:true,
		dataType: 'html',
		success : function(data)  {
			if(data == "yes") {
				verifyChange(1,"username");
				$("div.messge").fadeOut();
			} else {
				$(name).css("border","1px solid #EF4836");
				$(name).css("background-color","#E4F1FE");
				$("div.messge font").html("该用户名不可用!");
				$("div.messge").fadeIn();
				return false;
			}
		}
	})
}

