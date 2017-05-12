<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>个人中心</title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="userleft.jsp" %>
				<!-- center -->
				<div class="col-xs-7 center col-xs-offset-2">
					<%@ include file="../message.jsp" %>
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
											<a href="<c:url value="/user/me?tab=branches&page=0&size=3" />"><s:message code="profile.yourBranches"/></a> <a href="<c:url value="/user/me?tab=branches&page=0&size=3" />" class="more">更多的组织</a>
											</h3>
										</div>
										<div class="panel-body">
										<c:if test="${branches.size() > 0}">
											<c:forEach begin="0" end="${branches.size()}" items="${branches}" var="branch">
											<div class="center-branches col-xs-6">
												<div>
													<img class="branch-picture branch-img-small" src="<c:url value="/picture/branch/${branch.branchName}?pid=${branch.picture}"/>" />
													<a href="<c:url value="/branch/${branch.branchName}"/>"><p class="hide-p">${branch.branchName}</p></a>
													<small>${branch.intro}</small>
													<br/>
													<p class="center-branches-information hide-p" style="padding-top: 5px">
														<i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp; ${branch.getFormatTime() }&nbsp;
														<i class="fa fa-user" aria-hidden="true"></i>&nbsp;${branch.memberNum }&nbsp;
														<i class="fa fa-level-up" aria-hidden="true"></i>&nbsp;13
													</p>
												</div>
											</div>
											</c:forEach>
										</c:if>
										<c:if test="${branches != null && branches.size() == 0}">
											<div style="font-size: 28px;font-weight: 600; color: #888888;padding-top: 54px;padding-left: 422px;padding-bottom: 63px">
											未加入任何组织
											</div>
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
											<a href="#"><s:message code="profile.yourTasks"/></a> <a href="<c:url value="/user/me?tab=branches&page=0&size=10" />" class="more">更多的任务</a>
											</h3>
										</div>
										<div class="panel-body">
											<div style="height: 108px;">
											<c:forEach items="${ tasks.content }" var="task">
											<div class="row center-task">
												<div class="col-xs-4 center-task-li">
													<a href="<c:url value="/branch/${ task.project.branch.branchName }/project/${ task.project.projectName }/task/${  task.id }"/>"><i class="fa fa-link" aria-hidden="true"></i>&nbsp;${ task.title }</a>
												</div>
												<div class="col-xs-5 center-task-li" style="font-size: 14px">
													<small style="color: #888888">开始&nbsp;&nbsp;</small>
													<span style="font-size: 16px">${ task.getFormatStartTime() }</span>&nbsp;&nbsp;
													<small style="color: #888888">最后期限&nbsp;&nbsp;</small>
													<span style="font-size: 16px">${ task.getFormatEndTime() }</span>
													&nbsp;&nbsp;&nbsp;
													<small style="color: #BFBFBF;"><c:choose>
													<c:when test="${ task.state != 'Finish' }"><c:if test="${ task.getStartState() == 0 }"><span style="color:#89C4F4;">任务还未开始，请耐心等待</span></c:if><c:if test="${ task.getStartState() != 0 && task.getLastDay() != 0 }">你还有${ task.getLastDay() } 天可以完成</c:if><c:if test="${  task.getStartState() != 0 && task.getLastDay() == 0 }"><span style="color:red">任务已到期！</span></c:if></c:when>
													<c:when test="${ task.state == 'Finish' }"><span style="color:green">你已经成了该任务</span></c:when>
													</c:choose></small>
												</div>
												<div class="col-xs-2 center-task-li">
													组织 <a href="<c:url value="/branch/${ task.project.branch.branchName }"/>">${ task.project.branch.branchName }</a>
												</div>
												<div class="col-xs-1">
													<a href="<c:url value="/branch/${ task.project.branch.branchName }/project/${ task.project.projectName }/task/${  task.id }"/>"><button class="btn btn-success center-task-btn">查看</button></a>
												</div>
											</div>
											</c:forEach>
											<c:if test="${tasks != null && tasks.content.size() == 0}">
												<div style="font-size: 28px;font-weight: 600; color: #888888;padding-top: 44px;padding-left: 422px;padding-bottom: 63px">
												还有没收到任务
												</div>
											</c:if>
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
											<a href="#"><s:message code="profile.yourActivity"/></a> <a href="#" class="more">更多的活动</a>
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
									<p><i class="fa fa-users fa-3x" aria-hidden="true"></i><a href="<c:url value="/user/me?tab=friends&page=0&size=8"/>">${ firendNum }</a></p>
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