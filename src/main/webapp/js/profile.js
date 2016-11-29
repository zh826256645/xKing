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
	 	$("#getBack").css("display", "inline");
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
					$("#getBack").css("display", "none");
				 	$("#changeIntroduction").html(data);
				}
			}
		});
	});
	$("#getBack").click(function() {
	 	$("#changeIntroduction").css("display", "inline");
	 	$("#input-introduction").css("display", "none");
	 	$("#introduction-submit").css("display", "none");
		$("#getBack").css("display", "none");
	});
	$("#addPicture").click(function() { 
		$("#file").click();
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

function imgPreview(fileDom){
    //判断是否支持FileReader
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }

    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;
    //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //读取完成
    reader.onload = function(e) {
        //获取图片dom
        var img = document.getElementById("preview");
        //图片路径设置为读取的图片
        img.src = e.target.result;
    };
    reader.readAsDataURL(file);
}
