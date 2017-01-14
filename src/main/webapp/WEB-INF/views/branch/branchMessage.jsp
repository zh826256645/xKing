<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BranchMessage</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>"/>
		<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/profile.js"/>" ></script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<div class="row">
		<!-- left -->
		<%@ include file="branchleft.jsp" %>
		<!-- center -->

		<!-- Right -->

	</div>
	<!-- Center -->
	<div class="row">
		<div class="col-md-10 col-sm-10 col-sm-offset-2">
			<div class="center-branche-panel">
				<label>Message Center</label>
				<hr />
				<div class="row center-myBranches-up">
					<form action="#">
						<div class="col-sm-4">
							<input type="text"  class="form-control" placeholder="Message's Title"/>
						</div>
						<div class="col-sm-2">
							<input type="submit" class="btn btn-default" value="Search"/>
						</div>
					</form>
				</div>
				<label>Official Message</label>
				<div class="row center-branch-message-li">
					<div class="col-sm-8">
						<p class="hide-p">
						[Active] <a href="#">We well have a party in the 2016-11-23，welcome to join us!</a>
						</p>
					</div>
					<div class="col-sm-3">
						<i class="fa fa-calendar" aria-hidden="true"></i><font> 2016-10-11</font>
					</div>
					<div class="col-sm-1">
						<a href="#"><button class="btn btn-info">Read</button></a>
					</div>
				</div>
				<div class="row center-branch-message-li">
					<div class="col-sm-8">
						<p class="hide-p">
						[Active] <a href="#">We well have a party in the 2016-11-23，welcome to join us!</a>
						</p>
					</div>
					<div class="col-sm-3">
						<i class="fa fa-calendar" aria-hidden="true"></i><font> 2016-10-11</font>
					</div>
					<div class="col-sm-1">
						<a href="#"><button class="btn btn-info">Read</button></a>
					</div>
				</div>
				<div class="row center-branch-message-li">
					<div class="col-sm-8">
						<p class="hide-p">
						[Active] <a href="#">We well have a party in the 2016-11-23，welcome to join us!</a>
						</p>
					</div>
					<div class="col-sm-3">
						<i class="fa fa-calendar" aria-hidden="true"></i><font> 2016-10-11</font>
					</div>
					<div class="col-sm-1">
						<a href="#"><button class="btn btn-info">Read</button></a>
					</div>
				</div>
				<div class="row center-branch-message-li">
					<div class="col-sm-8">
						<p class="hide-p">
						[Active] <a href="#">We well have a party in the 2016-11-23，welcome to join us!</a>
						</p>
					</div>
					<div class="col-sm-3">
						<i class="fa fa-calendar" aria-hidden="true"></i><font> 2016-10-11</font>
					</div>
					<div class="col-sm-1">
						<a href="#"><button class="btn btn-info">Read</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
