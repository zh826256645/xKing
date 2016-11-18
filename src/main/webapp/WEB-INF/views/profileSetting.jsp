<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Profile-setting</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>"/>
		<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/profile.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/utils.js"/>" ></script>
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<div class="row">
			<!-- left -->
			<%@ include file="userleft.jsp" %>
			<!-- center -->
			<div class="col-sm-7 col-sm-offset-2 center-myBranches">
				<c:if test="${message != null || error != null}">
				<p class="
				<c:if test="${message != null }">bg-info </c:if>
				<c:if test="${error != null }">bg-danger </c:if>
				change-message">
					<i class="fa fa-comment" aria-hidden="true"></i> ${message}${error}
					<i class="fa fa-times" id="changeMessage" style="float: right;" aria-hidden="true" onclick="javascript:removeFather('changeMessage');"></i>
				</p>
				</c:if>
				<label>Setting</label>
				<hr />
				<label>Public information</label><br />
				<div class="row  center-profile-setting">
					<sf:form action="setting/profile" method="post">
					<div class="col-sm-5 center-profile-setting-left">
						<p>Name</p>
						<input class="form-control" value="${currentUser.name}" name="name"/>
						<p>Country</p>
						<div class="row">
							<div class="col-sm-7">
								<select class="form-control">
									<option>China</option>
									<option>English</option>
									<option>Japan</option>
								</select>
							</div>
						</div>
						<p>Email</p>
						<div class="row">
							<div class="col-sm-7">
								<select class="form-control">
									<option>${currentUser.email}</option>
									<option>Don't show my email!</option>
								</select>
							</div>
						</div>
						<p>Blog</p>
						<input class="form-control" name="blog" value="${currentUser.blog}" />
						<p>introduction</p>
    					<textarea class="form-control" rows="3" name="introduction">${currentUser.introduction}</textarea><br />
						<input type="submit" class="btn btn-success" value="Update profile"/>
					</div>
					</sf:form>
					<div class="col-sm-4 col-sm-offset-1">
						<img class="img-circle" src="<c:url value="/img/profile.jpg"/>"/>
						<button class="btn btn-info">Update new picture</button><br />
						<hr />
						<label>Security</label>
						<div class="row center-profile-setting-left">
							<p>Account state:<font> Normal</font></p>
							<p>The last login IP:<font>192.168.114.1 MeiZhou</font></p>
							<p>Password security levels</p>
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="60"
									 aria-valuemin="0" aria-valuemax="100" style="width: 80%;">
								</div>
							</div>
							<label class="btn btn-info" id="changePassword" onclick="changePassword();">Change your password</label>
							<div class="row">
							<div id="password" style="display: none;" class="col-sm-9">
								<sf:form action="setting/password" method="post">
								<input class='form-control' type="password" placeholder='old password' name="oldPassword"/><br />
								<input class='form-control' type="password" placeholder='new password' name="newPassword"/><br />
								<input type='submit' class='btn btn-success' value='Change'/>
								</sf:form>
							</div>
							</div>
						</div>
					</div>
				</div>
				<label>Private information</label><br />
				<div class="col-sm-8 center-profile-setting">
					<div class="center-profile-setting-li">
						<label>Message</label>
						<form>
							<p><input type="checkbox" value=""/>  Don't accept the message of strangers</p>
							<font>If you don't want the stranger sent message to you, you can check this setting!</font><br /><br />
							<input type="submit" class="btn btn-success" value="Update"/>
						</form>
					</div>
					<div class="center-profile-setting-li">
						<label>Friends</label>
						<form>
							<p><input type="checkbox" value=""/>  Don't accept  friend request</p>
							<font>If you don't want to agree the friend request from stranger, you can check this setting!</font><br /><br />
							<input type="submit" class="btn btn-success" value="Update"/>
						</form>
					</div>
				</div>
			</div>
			<!-- Right -->
			<div class="col-sm-3 right">

			</div>
		</div>
	</body>
</html>

