<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Branch</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>"/>
		<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/profile.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/utils.js"/>" ></script>
	</head>
	<body>
		<%@ include file="../header.jsp" %>
		<div class="row">
			<%@ include file="userleft.jsp" %>
			<!-- center -->
			<div class="col-sm-7 col-sm-offset-2 center-myBranches">
				<%@ include file="../message.jsp" %>
				<label>Create Branch</label>
				<hr />
				<div class="row  center-profile-setting">
					<sf:form  method="post" commandName="branch" enctype="multipart/form-data">
					<div class="col-sm-6 center-profile-setting-left">
						<label>Basic information</label><br />
						<p>Branch's Name</p>
						<sf:input path="branchName" class="form-control"/>
						<p>Public email</p>
						<div class="row">
							<div class="col-sm-7">
								<sf:select path="email" class="form-control">
									<sf:option value="${user.email}">${user.email}</sf:option>
								</sf:select>
							</div>
						</div>
						<p>Your Role name</p>
						<div class="row">
							<div class="col-sm-7">
								<input name="yourRoleName" class="form-control"/>
								<small> you have the best power in this branch!</small>
								
							</div>
						</div>
						<p>Newcomer's Role name</p>
						<div class="row">
							<div class="col-sm-7">
								<input name="newComerRoleName" class="form-control"/>
							</div>
						</div>
						<p>Intro</p>
    					<sf:textarea path="intro" class="form-control" rows="3"></sf:textarea><br />
    					<label>Basic Setting</label><br />
						<div class="center-profile-setting-li">
							<p><input type="checkbox" value=""/>  Visitors could not enter</p>
							<font>If you don't want the visitors see your branch, you can check this setting!</font><br /><br />
						</div>
						<div class="center-profile-setting-li">
							<p><input type="checkbox" value=""/>  Don't accept  friend request</p>
							<font>If you don't want to agree the friend request from stranger, you can check this setting!</font><br /><br />
						</div>
						<br />
						<div class="row">
							<div class="col-sm-2 col-sm-offset-10">
								<input type="submit" class="btn btn-success" value="Create"/>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<label>Branch's picture</label><br /><br />
						<img id="preview" style="width: 200px;height: 250px;"/>
   						<br /> <br />
   						<label class="btn btn-info" id="addPicture">Add picture</label>
    					<input style="display: none;" type="file" id="file" name="branchPicture"  onchange="imgPreview(this)" />
    					<br />
					</div>
					</sf:form>
				</div>
			</div>
			
			<!-- Right -->
			<div class="col-sm-3 right">

			</div>
		</div>
	</body>
</html>
