<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>${ currentProject.projectName }-任务</title>
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
						<label>项目任务</label>
						<hr />
						<div class="row center-myTasks-up">
							<div class="col-xs-8">
								<ul class="nav nav-pills">
									<li <c:if test="${ state == null }">class="active"</c:if>><a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task?page=0&size=10"/>">所有</a></li>
									<li <c:if test="${ state == 'New' }">class="active"</c:if>><a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task?page=0&size=10&state=New"/>">新建</a></li>
									<li <c:if test="${ state == 'Take' }">class="active"</c:if>><a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task?page=0&size=10&state=Take"/>">已接受</a></li>
									<li <c:if test="${ state == 'Doding' }">class="active"</c:if>><a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task?page=0&size=10&state=Doding"/>">进行中</a></li>
									<li <c:if test="${ state == 'FrontendFinish' }">class="active"</c:if>><a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task?page=0&size=10&state=FrontendFinish"/>">前端完成</a></li>
									<li <c:if test="${ state == 'RearendFinish' }">class="active"</c:if>><a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task?page=0&size=10&state=RearendFinish"/>">后端完成</a></li>
									<li <c:if test="${ state == 'Finish' }">class="active"</c:if>><a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task?page=0&size=10&state=Finish"/>">已完成</a></li>
									<li <c:if test="${ state == 'Refuse' }">class="active"</c:if>><a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task?page=0&size=10&state=Refuse"/>">已拒绝</a></li>
								</ul>
							</div>
							<div class="col-xs-4" style="padding-top: 5px">
								<a href="<c:url value="/branch/${currentBranch.branchName }/project/${currentProject.projectName }/task/new"/>"><button class="btn btn-success">创建新任务</button></a>
							</div>
						</div>
						<label>所有任务</label>
						<c:forEach items="${ page.content }" var="task">
						<div class="row center-tasks-li">
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
								<div class="row" style="margin-left: 20px;margin-top:4px;margin-bottom: 4	px; font-size: 14px; border-left: 3px solid #888888;">
									<div class="col-xs-3">
									 	发布人: <a href="#">${ task.publishMember.memberName }</a>
									</div>
									<div class="col-xs-3">
										<font>发布时间: </font>${ task.getFormatPublishTime() }
									</div>
									<div class="col-xs-5">
										<font>时限: </font>${ task.getFormatStartTime() } - ${ task.getFormatEndTime() }
									</div>
								</div>
							</div>
							<div class="col-xs-2 col-xs-offset-2">
								<a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task/${ task.id }"/>"><button class="btn btn-success">查看</button></a>
							</div>
						</div>
						</c:forEach>
						<c:if test="${ page.content == null || page.content.size() == 0 }">
							<div class="empty">
								还没有发布任务！
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
