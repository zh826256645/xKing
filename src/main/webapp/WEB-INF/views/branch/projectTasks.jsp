<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Profile</title>
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
							<div class="col-xs-5">
								<ul class="nav nav-pills">
									<li class="active"><a>All <span class="badge">20</span></a></li>
									<li ><a href="#">TO DO <span class="badge">3</span></a></li>
									<li><a href="#">Urgent <span class="badge">10</span></a></li>
									<li><a href="#">Finished <span class="badge">3</span></a></li>
								</ul>
							</div>
							<div class="col-xs-7" style="padding-top: 5px">
								<a href="<c:url value="/branch/${currentBranch.branchName }/project/${currentProject.projectName }/task/new"/>"><button class="btn btn-success">创建新任务</button></a>
							</div>
						</div>
						<label>所有任务</label>
						<c:forEach items="${ page.content }" var="task">
						<div class="row center-tasks-li">
							<div class="col-xs-8 hide-p">
								<div class="row">
									<div class="col-xs-6">
										<a href="#" style="font-size: 20px; "><i class="fa fa-link" aria-hidden="true"></i>&nbsp; ${ task.title }</a>
									</div>
								</div>
								<div class="row" style="padding-left: 20px; font-size: 14px; border-left: 2px soild #888888;'"'>
									<div class="col-xs-3">
									 	发布人: <a href="#">${ task.publishMember.memberName }</a>
									</div>
									<div class="col-xs-3">
										<font>发布时间:</font><mark>${ task.getFormatPublishTime() }</mark>
									</div>
									<div class="col-xs-3">
										<font>开始时间:</font><mark>${ task.getFormatStartTime() }</mark>
									</div>
									<div class="col-xs-3">
										<font>结束时间:</font><mark>${ task.getFormatEndTime() }</mark>
									</div>
								</div>
							</div>
							<div class="col-xs-2 col-xs-offset-2">
								<button class="btn btn-success">Finish</button>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
