<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Profile</title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="userleft.jsp" %>
				<div class="col-xs-10">
					<div class="center-myBranches center-myTasks">
						<label>Friends</label>
						<hr />
						<div class="row center-myBranches-up">
							<form action="#">
								<div class="col-xs-4">
									<input type="text"  class="form-control" placeholder="friend's name"/>
								</div>
								<div class="col-xs-3">
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
								<div class="col-xs-2">
									<input type="submit" class="btn btn-default" value="Search"/>
								</div>
							</form>
						</div>
						<label>All Friends</label>
						<div class="row center-friends-li">
							<div class="col-xs-1">
								<img class="img-circle" src="<c:url value="/img/profile.jpg"/>">
							</div>
							<div class="col-xs-5 center-friends-li-name">
								<a href="#">ZhongHao</a> <span class="badge friend-message">3</span>
								<p class="p-hide"><i class="fa fa-comment" aria-hidden="true"></i>I'm very happy today!!</p>
							</div>
							<div class="col-xs-3 col-xs-offset-2">
								<button class="btn btn-success">Message</button>
								<button class="btn btn-danger">Delete</button>
							</div>
						</div>
						<div class="row center-friends-li">
							<div class="col-xs-1">
								<img class="img-circle" src="<c:url value="/img/profile.jpg"/>">
							</div>
							<div class="col-xs-5 center-friends-li-name">
								<a href="#">ZhongHao</a> 
								<p class="p-hide"><i class="fa fa-comment" aria-hidden="true"></i>I'm very happy today!!</p>
							</div>
							<div class="col-xs-3 col-xs-offset-2">
								<button class="btn btn-success">Message</button>
								<button class="btn btn-danger">Delete</button>
							</div>
						</div>
						<div class="row center-friends-li">
							<div class="col-xs-1">
								<img class="img-circle" src="<c:url value="/img/profile.jpg"/>">
							</div>
							<div class="col-xs-5 center-friends-li-name">
								<a href="#">ZhongHao</a> 
								<p class="p-hide"><i class="fa fa-comment" aria-hidden="true"></i>I'm very happy today!!</p>
							</div>
							<div class="col-xs-3 col-xs-offset-2">
								<button class="btn btn-success">Message</button>
								<button class="btn btn-danger">Delete</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
