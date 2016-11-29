<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Profile</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>"/>
		<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/profile.js"/>" ></script>
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<div class="row">
			<%@ include file="userleft.jsp" %>
		</div>
		<!-- Center -->
		<div class="row">
			<div class="col-lg-7 col-md-10 col-sm-10 col-sm-offset-2">
				<div class="center-myBranches center-myTasks">
					<label>Friends</label>
					<hr />
					<div class="row center-myBranches-up">
						<form action="#">
							<div class="col-sm-4">
								<input type="text"  class="form-control" placeholder="friend's name"/>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
								<div class="input-group-addon">Type:</div>
								<select class="form-control">
									<option>All</option>
									<option>School</option>
									<option>Association</option>
									<option>Company</option>
								</select>
								</div>
							</div>
							<div class="col-sm-2">
								<input type="submit" class="btn btn-default" value="Search"/>
							</div>
						</form>
					</div>
					<label>All Friends</label>
					<div class="row center-friends-li">
						<div class="col-sm-1">
							<img class="img-circle" src="<c:url value="/img/profile.jpg"/>">
						</div>
						<div class="col-sm-5 center-friends-li-name">
							<a href="#">ZhongHao</a> <span class="badge friend-message">3</span>
							<p class="p-hide"><i class="fa fa-comment" aria-hidden="true"></i>I'm very happy today!!</p>
						</div>
						<div class="col-sm-3 col-sm-offset-2">
							<button class="btn btn-success">Message</button>
							<button class="btn btn-danger">Delete</button>
						</div>
					</div>
					<div class="row center-friends-li">
						<div class="col-sm-1">
							<img class="img-circle" src="<c:url value="/img/profile.jpg"/>">
						</div>
						<div class="col-sm-5 center-friends-li-name">
							<a href="#">ZhongHao</a> 
							<p class="p-hide"><i class="fa fa-comment" aria-hidden="true"></i>I'm very happy today!!</p>
						</div>
						<div class="col-sm-3 col-sm-offset-2">
							<button class="btn btn-success">Message</button>
							<button class="btn btn-danger">Delete</button>
						</div>
					</div>
					<div class="row center-friends-li">
						<div class="col-sm-1">
							<img class="img-circle" src="<c:url value="/img/profile.jpg"/>">
						</div>
						<div class="col-sm-5 center-friends-li-name">
							<a href="#">ZhongHao</a> 
							<p class="p-hide"><i class="fa fa-comment" aria-hidden="true"></i>I'm very happy today!!</p>
						</div>
						<div class="col-sm-3 col-sm-offset-2">
							<button class="btn btn-success">Message</button>
							<button class="btn btn-danger">Delete</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
