<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="_csrf" content="${_csrf.token}"/><meta name="_csrf_header" content="${_csrf.headerName}">
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
			<!-- center -->
			<div class="col-sm-7 col-sm-offset-2 center">
				<div class="panel panel-default center-person">
					<div class="row">
						<div class="col-lg-3 center-person-message-left">
							<img class="img-circle center-person-picture center-block " src="<c:url value="/img/profile.jpg"/>">
							<p class="h4 center-person-message-username"><font>Hi</font> <a href="<c:url value="/setting"/>" class="center-person-message-username">${currentUser.name}</a></p>
						</div>
						<div class="col-sm-4 center-person-message-center">
							<p class="center-person-message-label">Public information:</p>
							<p><i class="fa fa-envelope" aria-hidden="true"></i> &nbsp;&nbsp;<font>${currentUser.email}</font></p>
							<p><i class="fa fa-rss" aria-hidden="true"></i> &nbsp;&nbsp;<a href="#">${currentUser.blog}</a></p>
							<p class="hide-p"><i class="fa fa-comment" aria-hidden="true"></i> &nbsp;
								<a id="changeIntroduction" style="color: #000000;cursor: pointer;" title="修改">${currentUser.introduction}</a>
										<input id="input-introduction" class="input input-introduction"/>
								 <i style="color: #398439;cursor: pointer;display: none;" id="introduction-submit" title="提交" class="fa fa-pencil" aria-hidden="true"></i>		
							</p>
						</div>
						<div class="col-sm-4 center-person-message-right">
							<p class="center-person-message-label">Tasks:<font><i class="fa fa-calendar" aria-hidden="true"></i>&nbsp&nbsp<font id="dateNow"></font></font></p>
							<p class="center-person-message-right-task"><a href="#">5</a><font>(TO DO)</font></p>
						</div>
					</div>
				</div>
			</div>
			<!-- Right -->
			<div class="col-sm-3 right">
				<div class="row">
					<div class="panel panel-default col-sm-10 right-messge">
						<div class="row">
							<div class="col-sm-6 right-messge-friends">
								<p><i class="fa fa-users fa-3x" aria-hidden="true"></i><a href="#">28</a></p>
							</div>
							<div class="col-sm-6 right-messge-message">
								<p><i class="fa fa-commenting fa-3x" aria-hidden="true" style="color: red"></i><a href="#" >2</a></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Center -->
		<div class="row">
			<div class="col-sm-7 col-sm-offset-2">
				<!-- Branches -->
				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading" style="border-bottom: 0px; background-color: #f9fafc;">
								<h3 class="panel-title" style="font-weight: bold;color: black;">
								<a href="#">Your Branches</a> <a href="myBranches.html" class="more">more branches</a>
								</h3>
							</div>
							<div class="panel-body">
								<div class="center-branches col-sm-6">
									<div>
										<img class="img-thumbnail branch-picture" src="<c:url value="/img/branche.jpeg"/>" />
										<a href="#">LongMaoShe</a><br />
										<small>This a Branche and have some peopel who like LongMao.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</small>
										<p class="center-branches-time"><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;&nbsp;Founded in 2016-10-31</p>
										<p class="center-branches-information">
											<i class="fa fa-user" aria-hidden="true"></i>&nbsp;100&nbsp;&nbsp;&nbsp;&nbsp;
											<i class="fa fa-level-up" aria-hidden="true"></i>&nbsp;13
										</p>
									</div>
								</div>
								<div class="center-branches col-sm-6">
									<div>
										<img class="img-thumbnail branch-picture" src="<c:url value="/img/branche.jpeg"/>" />
										<a href="#">LongMaoShe</a><br />
										<small>This a Branche and have some peopel who like LongMao.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</small>
										<p class="center-branches-time"><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;&nbsp;Founded in 2016-10-31</p>
										<p class="center-branches-information">
											<i class="fa fa-user" aria-hidden="true"></i>&nbsp;100&nbsp;&nbsp;&nbsp;&nbsp;
											<i class="fa fa-level-up" aria-hidden="true"></i>&nbsp;13
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Tasks -->
				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading" style="border-bottom: 0px; background-color: #f9fafc;">
								<h3 class="panel-title" style="font-weight: bold;color: black;">
								<a href="#">Your Tasks</a> <a href="#" class="more">more tasks</a>
								</h3>
							</div>
							<div class="panel-body">
								<div class="row center-task">
									<div class="col-sm-4 center-task-li">
										<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
									</div>
									<div class="col-sm-5 center-task-li">
										<small>start&nbsp;</small>
										<mark>2016-11-01</mark>
										<small>end&nbsp;</small>
										<mark>206-12-01</mark>
										&nbsp;&nbsp;&nbsp;
										<small style="color: #BFBFBF;">you have 30 day to do</small>
									</div>
									<div class="col-sm-2 center-task-li">
										in <a href="#">LongMaoShe</a>
									</div>
									<div class="col-sm-1">
										<button class="btn btn-success center-task-btn">finish</button>
									</div>
								</div>
								<div class="row center-task">
									<div class="col-sm-4 center-task-li">
										<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
									</div>
									<div class="col-sm-5 center-task-li">
										<small>start&nbsp;</small>
										<mark>2016-11-01</mark>
										<small>end&nbsp;</small>
										<mark>206-12-01</mark>
										&nbsp;&nbsp;&nbsp;
										<small style="color: #BFBFBF;">you have 30 day to do</small>
									</div>
									<div class="col-sm-2 center-task-li">
										in <a href="#">LongMaoShe</a>
									</div>
									<div class="col-sm-1">
										<button class="btn btn-success center-task-btn">finish</button>
									</div>
								</div>
								<div class="row center-task">
									<div class="col-sm-4 center-task-li">
										<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
									</div>
									<div class="col-sm-5 center-task-li">
										<small>start&nbsp;</small>
										<mark>2016-11-01</mark>
										<small>end&nbsp;</small>
										<mark>206-12-01</mark>
										&nbsp;&nbsp;&nbsp;
										<small style="color: #BFBFBF;">you have 30 day to do</small>
									</div>
									<div class="col-sm-2 center-task-li">
										in <a href="#">LongMaoShe</a>
									</div>
									<div class="col-sm-1">
										<button class="btn btn-success center-task-btn">finish</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Activity -->
				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading" style="border-bottom: 0px; background-color: #f9fafc;">
								<h3 class="panel-title" style="font-weight: bold;color: black;">
								<a href="#">Your Activity</a> <a href="#" class="more">more activity</a>
								</h3>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-12">
										<div class="center-activity">
											<i class="fa fa-calendar-o" aria-hidden="true"></i>&nbsp;&nbsp;<font class="center-activity-date">October 2016</font>
											<div class="center-activity-information">
												 <div class="center-activity-information-li">
												 		<!-- activity -->
														<font>Join in a Branch</font>
														<!-- object -->
														<font><i class="fa fa-university" aria-hidden="true"></i>&nbsp;<a href="#">LongMaoShe</a></font>
														<!-- date -->
														<font>in <mark>2016-10-31</mark></font>
												 </div>
												 <div class="center-activity-information-li">
												 		<!-- activity -->
														<font>finsh a Task</font>				
														<!-- object -->
														<font><a href="#"><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a></font>
														<!-- date -->
														<font>in <mark>2016-10-31</mark></font>
												 </div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="center-activity">
											<i class="fa fa-calendar-o" aria-hidden="true"></i>&nbsp;&nbsp;<font class="center-activity-date">September 2016</font>
											<div class="center-activity-information">
												 <div class="center-activity-information-li">
												 		<!-- activity -->
														<font>Join in a Branch</font>
														<!-- object -->
														<font><i class="fa fa-university" aria-hidden="true"></i>&nbsp;<a href="#">LongMaoShe</a></font>
														<!-- date -->
														<font>in <mark>2016-10-31</mark></font>
												 </div>
												 <div class="center-activity-information-li">
												 		<!-- activity -->
														<font>finsh a Task</font>				
														<!-- object -->
														<font><a href="#"><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a></font>
														<!-- date -->
														<font>in <mark>2016-10-31</mark></font>
												 </div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="row">
					<div class="panel panel-default col-sm-10 left-panel">
						<div class="panel-heading left-panel-label">
							<h3 class="panel-title">
								History
							</h3>
						</div>
						<div class="panel-body left-panel-history">
							<p class="left-panel-history-li">
								<i class="fa fa-star" aria-hidden="true"></i>
								<a href="#" class="history-source">LongMaoShe</a>
								<font class="history-event">publish</font>
								<font class="history-object">Task</font>
								<a href="#"><i class="fa fa-link" aria-hidden="true"></i>Go to buy something use to decorate party!</a>
							</p>
							<p class="left-panel-history-li">
								<i class="fa fa-star" aria-hidden="true"></i>
								<a href="#" class="history-source">LongMaoShe</a>
								<font class="history-event">publish</font>
								<font class="history-object">Task</font>
								<a href="#"><i class="fa fa-link" aria-hidden="true"></i>Go to buy something use to decorate party!</a>
							</p>
							<p class="left-panel-history-li">
								<i class="fa fa-star" aria-hidden="true"></i>
								<a href="#" class="history-source">LongMaoShe</a>
								<font class="history-event">publish</font>
								<font class="history-object">Task</font>
								<a href="#"><i class="fa fa-link" aria-hidden="true"></i>Go to buy something use to decorate party!</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>