<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title>xKing</title>
		<%@ include file="head.jsp" %>
	</head>
	<body class="index">
		<div class="row">
			<div class="col-md-6">
				<div class="flag">
					<img class="flag-red" src="img/flag1.png" />
					<img class="flag-red-lion" src="img/lion.png"  />
					<img class="flag-white" src="img/flag2.png"/>
					<img class="flag-white-knight" src="img/knight.png" />
				</div>	
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6 col-lg-offset-1">
				<div class="welcome-message" >
					<p class="welcomefont">
						<span class="welcome-message-label">欢迎来到信息发布系统~</span>
						<p class="welcome-message-normal">你可以做任何你想做的事</p>
						<p class="welcome-message-normal">
							<span class="welcome-message-normal">如果你是系统用户,请进行</span>
							<span style="color: #66CC99;font-size: 45px;" class="welcome-message-label">登录</span>
						</p>
						<p class="welcome-message-normal">开始你的旅程吧~</p>
					</p>
					<hr/>
			<a href="<c:url value="/user/me"/>"><button type="button" style="width: 165px; height: 50px; font-size: 25px;" class="btn btn-primary btn-lg">Start</button></a>	
				</div>
			</div>
		</div>
		</div>
	</body>
</html>
