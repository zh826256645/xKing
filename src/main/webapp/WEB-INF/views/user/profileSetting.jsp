<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		 <security:csrfMetaTags/>
		<title><s:message code="profileSetting.Profile-setting"/> </title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<%@ include file="../header.jsp" %>
		<div class="row">
			<!-- left -->
			<%@ include file="userleft.jsp" %>
			<!-- center -->
			<div class="col-lg-7 col-md-10 col-sm-10 col-sm-offset-2 center-myBranches">
				<%@ include file="../message.jsp" %>
				<label><s:message code="profileSetting.setting"/></label>
				<hr />
				<label><s:message code="profileSetting.PublicInformation"/></label><br />
				<div class="row  center-profile-setting">
					<sf:form action="setting/profile" method="post">
					<div class="col-sm-5 center-profile-setting-left">
						<p><s:message code="profileSetting.name"/></p>
						<input class="form-control" value="${currentUser.name}" name="name"/>
						<p><s:message code="profileSetting.country"/></p>
						<div class="row">
							<div class="col-sm-7">
								<select class="form-control">
									<option>China</option>
									<option>English</option>
									<option>Japan</option>
								</select>
							</div>
						</div>
						<p><s:message code="profileSetting.email"/></p>
						<div class="row">
							<div class="col-sm-7">
								<select class="form-control">
									<option>${currentUser.email}</option>
									<option>Don't show my email!</option>
								</select>
							</div>
						</div>
						<p><s:message code="profileSetting.blog"/></p>
						<input class="form-control" name="blog" value="${currentUser.blog}" />
						<p><s:message code="profileSetting.introduction"/></p>
    					<textarea class="form-control" rows="3" name="introduction">${currentUser.introduction}</textarea><br />
						<input type="submit" class="btn btn-success" value="Update profile"/>
					</div>
					</sf:form>
					<div class="col-sm-4 col-sm-offset-1">
						<img class="img-circle" src="<c:url value='/picture/user/${username}'/>?pid=${userPicture}"/>
						<button class="btn btn-info" data-toggle="modal" data-target="#myModal"><s:message code="profileSetting.updatePicture"/></button><br />
						<!-- 模态框（Modal） -->
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
											&times;
										</button>
										<h4 class="modal-title" id="myModalLabel">
											上传头像
										</h4>
									</div>
									<div class="modal-body">
									<sf:form action="setting/picture?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">			
										<input type="file" name="profilePicture" accept="image/jpeg,image/png,image/gif"/><br/>
										<input type="submit" class="btn btn-primary" value="提交"/>	
									</sf:form>	
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">关闭
										</button>		
									</div>
									
								</div><!-- /.modal-content -->
							</div><!-- /.modal -->
						</div>
						<hr />
						<label><s:message code="profileSetting.security"/></label>
						<div class="row center-profile-setting-left">
							<p><s:message code="profileSetting.AccountState"/>:<font> Normal</font></p>
							<p><s:message code="profileSetting.ip"/>:<font>192.168.114.1 MeiZhou</font></p>
							<p><s:message code="profileSetting.level"/></p>
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="60"
									 aria-valuemin="0" aria-valuemax="100" style="width: 80%;">
								</div>
							</div>
							<label class="btn btn-info" id="changePassword" onclick="changePassword();"><s:message code="profileSetting.changePassword"/></label>
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
				<label><s:message code="profileSetting.privateInformation"/></label><br />
				<div class="col-sm-8 center-profile-setting">
					<div class="center-profile-setting-li">
						<label><s:message code="profileSetting.message"/></label>
						<form>
							<p><input type="checkbox" value=""/>  Don't accept the message of strangers</p>
							<font>If you don't want the stranger sent message to you, you can check this setting!</font><br /><br />
							<input type="submit" class="btn btn-success" value="Update"/>
						</form>
					</div>
					<div class="center-profile-setting-li">
						<label><s:message code="profileSetting.friends"/></label>
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

