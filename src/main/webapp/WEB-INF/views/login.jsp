<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<meta charset="utf-8" />
		<title>xKing</title>
		<%@ include file="head.jsp" %>
	</head>
	<body class="index">
		<div class="lucency">
			<div class="row lucency-div">
				<div class="col-md-5">
					<P class="lucency-logo">xKing <img src="img/crown.png" class="crown"></P>
					
					<c:if test="${userNotActivateError != null && message == null && error == null}">
						<div class="messge-tip messge-tip-error"><i class="fa fa-info-circle" aria-hidden="true"></i> ${userNotActivateError}
							<i class="fa fa-times" id="changeMessage" style="float: right;" aria-hidden="true" onclick="javascript:removeFather('changeMessage');"></i>
						</div>
					</c:if>
					
					<c:if test="${message != null || error != null || param.error != null}">
						<div class="messge-tip 
						<c:if test="${message != null}">messge-tip-info</c:if>
						<c:if test="${error != null || param.error != null}">messge-tip-error</c:if>
						">
							<i class="fa fa-info-circle" aria-hidden="true"></i> ${message} ${error} <c:if test="${param.error != null}">登录失败，请重新登录！</c:if>
							<i class="fa fa-times" id="changeMessage" style="float: right;" aria-hidden="true" onclick="javascript:removeFather('changeMessage');"></i>
						</div>
					</c:if>
					
					<div class="messge"><i id="messageIcons" class="fa fa-commenting-o fa-2x" aria-hidden="true"></i>&nbsp;<font></font></div><br />
					<sf:form method="post" action="${loginUrl}" onsubmit="return checkedAll()" id="login">
						<sf:errors></sf:errors>
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