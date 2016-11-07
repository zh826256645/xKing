<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<meta charset="utf-8" />
		<title>xKing</title>
		<link rel="stylesheet" type="text/css" href="css/application.css"/>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
		<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/checkout.js"/>" ></script>
	</head>
	<body class="index">
		<div class="lucency">
			<div class="row lucency-div">
				<div class="col-md-5">
					<P class="lucency-logo">xKing <img src="img/crown.png" class="crown"></P>
					<div class="messge"><i id="messageIcons" class="fa fa-commenting-o fa-2x" aria-hidden="true"></i>&nbsp;<font></font></div><br />
					<sf:form method="post" action="${loginUrl}" onsubmit="return checkedAll()" id="login">
						<input type="text" class="input" id="username" name="username" placeholder="用户名"/><br /><br />
						<input type="password" class="input" id="password" name="password" placeholder="密码"/><br /><br />
						<input type="submit" class="submit" value="登录" id="login"/><br /><br /><br />
						<p class="register">还没有账户？您可以在此 <a class="register" href='<c:url value='/user/new'/>'>注册</a> <a href="" class="forget">忘记密码</a></p>
					</sf:form>
				</div>
			</div>
		</div>
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
	</body>
</html>