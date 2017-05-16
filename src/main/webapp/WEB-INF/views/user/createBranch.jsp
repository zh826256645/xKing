<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ss" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>创建组织</title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<div class="container">
		<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="userleft.jsp" %>
				<!-- center -->
				<div class="col-xs-8 center-myBranches col-xs-offset-2">
					<%@ include file="../message.jsp" %>
					<label>创建组织</label>
					<hr />
					<div class="row  center-profile-setting">
						<sf:form  method="post" commandName="branch" action="?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
						<div class="col-xs-6 center-profile-setting-left">
							<label>基本信息</label><br />
							<p>组织名</p>
							<sf:input path="branchName" class="form-control"/>
							<p>组织邮箱</p>
							<div class="row">
								<div class="col-xs-7">
									<sf:select path="email" class="form-control">
										<sf:option value="${user.email}">${user.email}</sf:option>
									</sf:select>
								</div>
							</div>
							<p>你的角色名</p>
							<div class="row">
								<div class="col-xs-7">
									<input name="yourRoleName" class="form-control"/>
									<small>你将拥有最高的权限在组织中！</small>
									
								</div>
							</div>
							<p>新成员的角色名</p>
							<div class="row">
								<div class="col-xs-7">
									<input name="newComerRoleName" class="form-control"/>
								</div>
							</div>
							<p>简介</p>
	    					<sf:textarea path="intro"  class="form-control" rows="3"></sf:textarea><br />
	    					<label>基本设置</label><br />
							<div class="center-profile-setting-li">
								<p><input type="checkbox" value=""/>是否允许非成员访问</p>
								<font>如果你不希望其他人员访问组织，你可以选择该项设置</font><br /><br />
							</div>
							<div class="center-profile-setting-li">
								<p><input type="checkbox" value=""/>不接受成员请求</p>
								<font>如果你不希望别人请求加入组织，你可以选择该项设置</font><br /><br />
							</div>
							<br />
							<div class="row">
								<div class="col-xs-2 col-xs-offset-10">
									<input type="submit" class="btn btn-success" value="Create"/>
								</div>
							</div>
						</div>
						<div class="col-xs-6">
							<label>组织图片</label><br /><br />
							<img id="preview" style="width: 200px;height: 250px;"/>
	   						<br /> <br />
	   						<label class="btn btn-info" id="addPicture">添加图片</label>
	    					<input style="display: none;" type="file" id="file" name="branchPicture"  onchange="imgPreview(this)" />
	    					<br />
						</div>
						</sf:form>
					</div>
				</div>
				
				<!-- Right -->
				<div class="col-xs-2 right">
	
				</div>
			</div>
		</div>
	</body>
</html>
