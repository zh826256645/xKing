<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Profile</title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="userleft.jsp" %>
				<!-- center -->
				<div class="col-xs-7 center">
					<div class="row">
						<div class="col-xs-12">
							<div class="panel panel-default center-person">
								<div class="row">
									<div class="col-xs-3 center-person-message-left">
										<img class="img-circle center-person-picture center-block " src="<c:url value='/picture/user/${username}'/>?pid=${userPicture}">
										<p class="h4 center-person-message-username"><font>Hi</font> <a href="<c:url value="/setting"/>" class="center-person-message-username">${currentUser.name}</a></p>
									</div>
									<div class="col-xs-4 center-person-message-center">
										<p class="center-person-message-label"><s:message code="profile.publicInformation"/>:</p>
										<p><i class="fa fa-envelope" aria-hidden="true"></i> &nbsp;&nbsp;<font>${currentUser.email}</font></p>
										<p><i class="fa fa-rss" aria-hidden="true"></i> &nbsp;&nbsp;<a href="#">${currentUser.blog}</a></p>
										<p class="hide-p"><i class="fa fa-comment" aria-hidden="true"></i> &nbsp;
											<a id="changeIntroduction" style="color: #22313F;cursor: pointer;" title="修改">${currentUser.introduction}</a>
													<input id="input-introduction" class="input input-introduction"/>
											 <i style="color: #398439;cursor: pointer;display: none;" id="introduction-submit" title="提交" class="fa fa-pencil" aria-hidden="true"></i> &nbsp;
											 <i style="display: none" class="fa fa-times" id="getBack" aria-hidden="true"></i>		
										</p>
									</div>
									<div class="col-xs-4 center-person-message-right">
										<p class="center-person-message-label"><s:message code="profile.tasks"/>:<font><i class="fa fa-calendar" aria-hidden="true"></i>&nbsp&nbsp<font id="dateNow"></font></font></p>
										<p class="center-person-message-right-task"><a href="#">5</a><font>(TO DO)</font></p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<!-- Branches -->
							<div class="row">
								<div class="col-xs-12">
									<div class="panel panel-default">
										<div class="panel-heading" style="border-bottom: 0px; background-color: #f9fafc;">
											<h3 class="panel-title" style="font-weight: bold;color: black;">
											<a href="<c:url value="/user/me?tab=branches&page=0&size=3" />"><s:message code="profile.yourBranches"/></a> <a href="<c:url value="/user/me?tab=branches&page=0&size=3" />" class="more">more branches</a>
											</h3>
										</div>
										<div class="panel-body">
										<c:if test="${branches.size() > 0}">
											<c:forEach begin="0" end="${branches.size()}" items="${branches}" var="branch">
											<div class="center-branches col-xs-6">
												<div>
													<img class="branch-picture branch-img-small" src="<c:url value="/picture/branch/${branch.branchName}?pid=${branch.picture}"/>" />
													<a href="<c:url value="/branch/${branch.branchName}"/>">${branch.branchName}</a><br />
													<small>${branch.intro}</small>
													<p class="center-branches-time"><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;&nbsp;创建于 ${branch.createTime}</p>
													<p class="center-branches-information">
														<i class="fa fa-user" aria-hidden="true"></i>&nbsp;100&nbsp;&nbsp;&nbsp;&nbsp;
														<i class="fa fa-level-up" aria-hidden="true"></i>&nbsp;13
													</p>
												</div>
											</div>
											</c:forEach>
										</c:if>
										</div>
									</div>
								</div>
							</div>
							<!-- Tasks -->
							<div class="row">
								<div class="col-xs-12">
									<div class="panel panel-default">
										<div class="panel-heading" style="border-bottom: 0px; background-color: #f9fafc;">
											<h3 class="panel-title" style="font-weight: bold;color: black;">
											<a href="#"><s:message code="profile.yourTasks"/></a> <a href="#" class="more">more tasks</a>
											</h3>
										</div>
										<div class="panel-body">
											<div class="row center-task">
												<div class="col-xs-4 center-task-li">
													<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
												</div>
												<div class="col-xs-5 center-task-li">
													<small>start&nbsp;</small>
													<mark>2016-11-01</mark>
													<small>end&nbsp;</small>
													<mark>206-12-01</mark>
													&nbsp;&nbsp;&nbsp;
													<small style="color: #BFBFBF;">you have 30 day to do</small>
												</div>
												<div class="col-xs-2 center-task-li">
													in <a href="#">LongMaoShe</a>
												</div>
												<div class="col-xs-1">
													<button class="btn btn-success center-task-btn">finish</button>
												</div>
											</div>
											<div class="row center-task">
												<div class="col-xs-4 center-task-li">
													<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
												</div>
												<div class="col-xs-5 center-task-li">
													<small>start&nbsp;</small>
													<mark>2016-11-01</mark>
													<small>end&nbsp;</small>
													<mark>206-12-01</mark>
													&nbsp;&nbsp;&nbsp;
													<small style="color: #BFBFBF;">you have 30 day to do</small>
												</div>
												<div class="col-xs-2 center-task-li">
													in <a href="#">LongMaoShe</a>
												</div>
												<div class="col-xs-1">
													<button class="btn btn-success center-task-btn">finish</button>
												</div>
											</div>
											<div class="row center-task">
												<div class="col-xs-4 center-task-li">
													<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
												</div>
												<div class="col-xs-5 center-task-li">
													<small>start&nbsp;</small>
													<mark>2016-11-01</mark>
													<small>end&nbsp;</small>
													<mark>206-12-01</mark>
													&nbsp;&nbsp;&nbsp;
													<small style="color: #BFBFBF;">you have 30 day to do</small>
												</div>
												<div class="col-xs-2 center-task-li">
													in <a href="#">LongMaoShe</a>
												</div>
												<div class="col-xs-1">
													<button class="btn btn-success center-task-btn">finish</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- Activity -->
							<div class="row">
								<div class="col-xs-12">
									<div class="panel panel-default">
										<div class="panel-heading" style="border-bottom: 0px; background-color: #f9fafc;">
											<h3 class="panel-title" style="font-weight: bold;color: black;">
											<a href="#"><s:message code="profile.yourActivity"/></a> <a href="#" class="more">more activity</a>
											</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-12">
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
												<div class="col-xs-12">
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
					</div>
				</div>
				<!-- Right -->
				<div class="col-xs-3 right">
					<div class="row">
						<div class="panel panel-default col-xs-10 right-messge">
							<div class="row">
								<div class="col-xs-6 right-messge-friends">
									<p><i class="fa fa-users fa-3x" aria-hidden="true"></i><a href="#">28</a></p>
								</div>
								<div class="col-xs-6 right-messge-message">
									<p><i class="fa fa-commenting fa-3x" aria-hidden="true" style="color: red"></i><a href="#" >2</a></p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="panel panel-default col-xs-10 left-panel">
							<div class="panel-heading left-panel-label">
								<h3 class="panel-title">
									<s:message code="profile.history"/>
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
		</div>
	</body>
</html>