<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>${ currentProject.projectName }-首页</title>
		<%@ include file="../head.jsp" %>
		<script ref="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
	</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="branchleft.jsp" %>
				<div class="col-xs-7 col-xs-offset-2">
					<div class="center-myBranches center-myTasks">
						<%@ include file="../message.jsp" %>
						<label>项目首页</label>
						<hr />
						<div class="row">
							<div class="col-xs-6">
								<div class="panel panel-default" style="padding-top: 10px;background-color: #F2F2F2;">
									<div class="row">
										<div class="col-xs-12" style="margin-bottom: 10px;">
											<div style="font-size: 16px;color: #C0C0C0;font-weight:600 ;padding-left: 10px;">项目名</div>
											<br />
											<div class="hide-p" style="font-size: 40px;margin-bottom: 30px;font-weight: 600;">
												<p style="text-align: center;"><a>${ currentProject.projectName }</a></p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-xs-5">
								<div class="panel panel-default" style="padding-top: 20px; height: 158px;background-color: #E1EDF7;">
									<div class="row">
										<div class="col-xs-3" style="font-size: 16px;color: #D58512;padding-left: 30px;padding-right: 0px;padding-top: 18px;">管理员:</div>
										<div class="col-xs-9" style="font-size: 16px;color: #C0C0C0;padding-left: 0px;"><img src="<c:url value='/picture/user/${ currentProject.branchMember.user.username}'/>?pid=${ currentProject.branchMember.user.picture}" class="img-circle heard-profile-picture"></div>
									</div>
									<div class="row" style="margin-top: 10px;">
										<div class="col-xs-3" style="font-size: 16px;color: #D58512;padding-left: 30px;padding-right: 0px;padding-top: 18px;">项目成员 </div>
										<div class="col-xs-9" style="font-size: 16px;color: #C0C0C0;padding-left: 0px;"><c:forEach items="${ currentProject.projectMember }" var="member"><img src="<c:url value='/picture/user/${ member.user.username}'/>?pid=${ member.user.picture}" class="img-circle heard-profile-picture">&nbsp;</c:forEach></div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-11">
								<div class="panel panel-default">
									<div class="panel-heading" style="border-bottom: 0px; background-color: #f9fafc;">
										<h3 class="panel-title" style="font-weight: bold;color: black;">
										<a href="">任务</a> <a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task?page=0&size=10"/>" class="more">所有任务</a>
										</h3>
									</div>
									<div class="panel-body" style="height: 250px">
										<c:forEach items="${ tasks.content }" var="task">
										<div class="row" style="padding-top: 7px">
											<div class="col-xs-8 hide-p">
												<div class="row">
													<div class="col-xs-7">
														<a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task/${ task.id }"/>" style="font-size: 20px; "><i class="fa fa-link" aria-hidden="true"></i>&nbsp; [<c:choose>
														<c:when test="${ task.type == 'Task' }">任务</c:when>
														<c:when test="${ task.type == 'Test' }">测试</c:when>
														<c:when test="${ task.type == 'Bug' }">漏洞</c:when>
														<c:when test="${ task.type == 'Debugging' }">调试</c:when>
														<c:when test="${ task.type == 'Features' }">功能</c:when>
														</c:choose>] ${ task.title }</a>
													</div>
													<div class="col-xs-3" style="padding-top: 5px">
														<small>状态：<c:choose>
														<c:when test="${ task.state == 'New' }"><span style="color: red;">新建</span></c:when>
														<c:when test="${ task.state == 'Take' }"><span style="color: blue;">已接受</span></c:when>
														<c:when test="${ task.state == 'Doding' }"><span style="color: green;">进行中</span></c:when>
														<c:when test="${ task.state == 'FrontendFinish' }"><span style="color: purple;"> 前端完成</span></c:when>
														<c:when test="${ task.state == 'RearendFinish' }"><span style="color: purple;">后端完成</span></c:when>
														<c:when test="${ task.state == 'Finish' }"><span style="color: orange; ;">完成</span></c:when>
														<c:when test="${ task.state == 'Refuse' }"><span style="color: red;">拒绝</span></c:when>
														</c:choose></small>
													</div>
													<div class="col-xs-2" style="padding-top: 5px">
														<small>等级：<c:choose>
														<c:when test="${ task.taskLevel == 'Common' }"><span style="color: blue;">普通</span></c:when>
														<c:when test="${ task.taskLevel == 'Preference' }"><span style="color: green;">优先</span></c:when>
														<c:when test="${ task.taskLevel == 'Emergency' }"><span style="color: red;">紧急</span></c:when>
														</c:choose></small>
													</div>
												</div>
											</div>
											<div class="col-xs-2 col-xs-offset-2">
												<a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task/${ task.id }"/>"><button class="btn btn-success">查看</button></a>
											</div>
										</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-3">
					<div class="center-branche-panel">
					<label>历史记录</label>
							<div class="panel-body left-panel-history">
							<c:forEach items="${ histories }" var="history">
								<c:if test="${ history.type == 'Task' }">
								<p class="left-panel-history-li">
									<i class="fa fa-star" aria-hidden="true"></i>
									<a href="#" class="history-source">${ history.initiateMember.memberName }</a>
									<font class="history-event">${ history.action }</font>
									<a href="<c:url value="/branch/${ history.project.branch.branchName }/project/${ history.project.projectName }/task/${ history.task.id }"/>"><i class="fa fa-link" aria-hidden="true"></i>${ history.task.title }</a>
								</p>
								</c:if>
								<c:if test="${ history.type == 'AddProjectMember' }">
								<p class="left-panel-history-li">
									<i class="fa fa-star" aria-hidden="true"></i>
									<a href="#" class="history-source">${ history.initiateMember.memberName }</a>
									<font class="history-event">${ history.action }</font>
									<a href="#" class="history-source">${ history.acceptedMember.memberName }</a>
								</p>
								</c:if>
							</c:forEach>
							</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
