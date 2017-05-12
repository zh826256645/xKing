<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>我的任务</title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="userleft.jsp" %>
				<div class="col-xs-10 col-xs-offset-2">
					<div class="center-myBranches center-myTasks">
						<label>任务</label>
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
							<div class="col-xs-7">
								<div class="row">
									<form>
										<div class="col-xs-4">
											<input type="text"  class="form-control" placeholder="Task's keywords"/>
										</div>
										<div class="col-xs-5">
											<div class="input-group">
											<div class="input-group-addon">Branches</div>
											<select class="form-control">
												<option>All</option>
												<option>LongMaoShe</option>
												<option>Computer</option>
											</select>
											</div>
										</div>
										<div class="col-xs-2">
											<input type="submit" class="btn btn-default" value="Search"/>
										</div>
									</form>
								</div>
							</div>
						</div>
						<label>所有任务</label>
						<c:forEach items="${ page.content }" var="task">
						<div class="row center-tasks-li">
							<div class="col-xs-8 hide-p">
								<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">${ task.title }</a>&nbsp;&nbsp;&nbsp;等级: <small><c:choose>
										<c:when test="${ task.taskLevel == 'Common' }"><span style="color: blue;">普通</span></c:when>
										<c:when test="${ task.taskLevel == 'Preference' }"><span style="color: green;">优先</span></c:when>
										<c:when test="${ task.taskLevel == 'Emergency' }"><span style="color: red;">紧急</span></c:when>
										</c:choose></small> &nbsp;&nbsp;&nbsp;状态: <small><c:choose>
										<c:when test="${ task.state == 'New' }"><span style="color: red;">新建</span></c:when>
										<c:when test="${ task.state == 'Take' }"><span style="color: blue;">已接受</span></c:when>
										<c:when test="${ task.state == 'Doding' }"><span style="color: green;">进行中</span></c:when>
										<c:when test="${ task.state == 'FrontendFinish' }"><span style="color: purple;"> 前端完成</span></c:when>
										<c:when test="${ task.state == 'RearendFinish' }"><span style="color: purple;">后端完成</span></c:when>
										<c:when test="${ task.state == 'Finish' }"><span style="color: orange; ;">完成</span></c:when>
										<c:when test="${ task.state == 'Refuse' }"><span style="color: red;">拒绝</span></c:when>
										</c:choose></small>
								<p class="hide-p">${ task.content }</p>
								<div class="center-tasks-li-message">
									<p><font>所属组织:</font><a href="#">${ task.project.branch.branchName }</a> <font>发布人:</font><a href="#">${ task.publishMember.memberName }</a><font>开始时间:</font><mark>${ task.getFormatStartTime() }</mark><font>结束时间:</font><mark>${ task.getFormatEndTime() }</mark></p>
								</div>
							</div>
							<div class="col-xs-2 col-xs-offset-2">
								<button class="btn btn-success">Finish</button>
								<button class="btn more-message" >More</button>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
