<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title>xKing</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>" />
		<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/checkout.js"/>" ></script>
	</head>
	<body class="index">
		<div class="lucency">
			<div class="row lucency-div">
				<div class="col-md-5">
					<P class="lucency-logo">xKing <img src="<c:url value="/img/crown.png"/>" class="crown"></P>
					<sf:form method="post" id="register" commandName="user" onsubmit="return checkedAll()">
					<c:if test="${errors == true}">
						<div class="row">
							<div class="col-sm-8 alert alert-danger error-message"> <sf:errors path="*"/> </div>
						</div>
					</c:if>
					<div class="messge"><i id="messageIcons" class="fa fa-commenting-o fa-2x" aria-hidden="true"></i>&nbsp;<font></font></div><br />

						<sf:input class="input" path="username" id="username" placeholder="用户名"/><br /><br />
						<sf:password class="input" path="password" id="password" placeholder="密码"/><br /><br />
						<sf:input class="input" path="email" id="email" placeholder="邮箱" /><br /><br />
						<input type="submit" class="submit" value="注册" /><br /><br /><br />
						<p class="register">已有账号？ <a class="register" href="<c:url value="/user"/>">登录</a> </p>
					</sf:form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="flag">
					<img class="flag-red" src="<c:url value="/img/flag1.png"/>" />
					<img class="flag-red-lion" src="<c:url value="/img/lion.png"/>"  />
					<img class="flag-white" src="<c:url value="/img/flag2.png"/>"/>
					<img class="flag-white-knight" src="<c:url value="/img/knight.png"/>" />
				</div>	
			</div>
		</div>
	</body>
</html>