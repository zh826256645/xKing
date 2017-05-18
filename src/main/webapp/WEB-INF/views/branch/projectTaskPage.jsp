<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${ currentProject.projectName }-任务页面</title>
		<%@ include file="../head.jsp" %>
		<style>
		    .markdown-body {
		        box-sizing: border-box;
		        min-width: 200px;
		        max-width: 980px;
		        margin: 0 auto;
		        padding: 45px;
		    }
		</style>
	</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<!-- left -->
				<%@ include file="branchleft.jsp" %>
				<!-- center -->
	
				<!-- Right -->

			<!-- Center -->
				<div class="col-xs-7 col-xs-offset-2">
					<div class="center-branche-panel">
						<%@ include file="../message.jsp" %>
						<label>任务页面</label>
						<hr />
						<div class="row">
							<div class="col-xs-12">
								<label>任务信息</label>
								<h2 class="hide-p" style="font-weight: 600;padding-left: 10px;border-left: 4px solid #969896;margin-left: 10px;">${ currentTask.title }</h2>
							</div>
						</div>
						<br />
						<div class="row task-message">
							<div class="col-xs-2">
								<span >任务等级：</span><b><c:choose>
										<c:when test="${ currentTask.taskLevel == 'Common' }">普通</c:when>
										<c:when test="${ currentTask.taskLevel == 'Preference' }">优先</c:when>
										<c:when test="${ currentTask.taskLevel == 'Emergency' }">紧急</c:when>
										</c:choose></b>
							</div>
							<div class="col-xs-3">
								<span >发布时间：</span><b>${ currentTask.getFormatPublishTime() }</b>
							</div>
						</div>
						<div class="row task-message">
							<div class="col-xs-2">
								<span >任务类型：</span><b><c:choose>
										<c:when test="${ currentTask.type == 'Task' }">任务</c:when>
										<c:when test="${ currentTask.type == 'Test' }">测试</c:when>
										<c:when test="${ currentTask.type == 'Bug' }">漏洞</c:when>
										<c:when test="${ currentTask.type == 'Debugging' }">调试</c:when>
										<c:when test="${ currentTask.type == 'Features' }">功能</c:when>
										</c:choose></b>
							</div>
							<div class="col-xs-3">
								<span >开始时间：</span><b>${ currentTask.getFormatStartTime() }</b>
							</div>
							<div class="col-xs-3">
								<span >最后时间：</span><b>${ currentTask.getFormatEndTime() }</b>
							</div>
						</div>
						<div class="row task-message">
							<div class="col-xs-6">
								<span style="display: block;">任务发布者：<img src="<c:url value='/picture/user/${ currentTask.publishMember.user.username}'/>?pid=${currentTask.publishMember.user.picture}" class="img-circle heard-profile-picture"></span>
							</div>
						</div>
						<div class="row task-message">
							<div class="col-xs-12">
								<span style="display: block;">任务接受者：<c:forEach items="${ currentTask.takeMembers }" var="member"><img src="<c:url value='/picture/user/${ member.user.username}'/>?pid=${ member.user.picture}" class="img-circle heard-profile-picture"></c:forEach></span>
							</div>
						</div>
						<hr />
						<div class="row">
							<div class="col-xs-10" style="padding-left: 25px;">
							<div class="row">
								<div class="col-xs-12">
									<p style="font-size: 20px;font-weight: 600;">主任务</p>
									<br/>
										<div class="row">
										<sf:form action="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task/${ currentTask.id }/state" method="post">
											<div class="col-xs-3">
												<div class="input-group">
													<div class="input-group-addon">任务状态</div>
													<select class="form-control" name="state">
														<option <c:if test="${ currentTask.state == 'New' }">selected="selected"</c:if> value="New">新建</option>
														<option <c:if test="${ currentTask.state == 'Take' }">selected="selected"</c:if> value="Take">接受</option>
														<option <c:if test="${ currentTask.state == 'Doding' }">selected="selected"</c:if> value="Doding">进行中</option>
														<option <c:if test="${ currentTask.state == 'FrontendFinish' }">selected="selected"</c:if> value="FrontendFinish">前端完成</option>
														<option <c:if test="${ currentTask.state == 'RearendFinish' }">selected="selected"</c:if> value="RearendFinish">后端完成</option>
														<option <c:if test="${ currentTask.state == 'Finish' }">selected="selected"</c:if> value="Finish">完成</option>
														<option <c:if test="${ currentTask.state == 'Refuse' }">selected="selected"</c:if> value="Refuse">拒绝</option>
													</select>
													
												</div>
											</div>
											<div class="col-xs-2">
												<input type="submit" class="btn btn-info" value="修改" />
											</div>
											</sf:form>
										</div>
									<br/>
									<textarea style="display: none;" id="branch-message">${ currentTask.content }</textarea>
									<div id="preview" style="border: 2px solid #F2F2F2;background-color: #E1EDF7;padding-left: 10px;padding-top:10px; "></div>
								<br/>
								</div>
								<c:forEach items="${ currentTask.subtasks }" var="task" varStatus="num">
								<div class="col-xs-12" style="margin-left: 30px!important;border-left: 5px solid #CCCCCC;">
									<p style="font-size: 20px;font-weight: 600;">子任务</p>
									<br/>
										<div class="row">
										<sf:form action="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task/${ currentTask.id }/state" method="post">
											<input type="hidden" value="${ task.id }" name="subTaskId">
											<div class="col-xs-3">
												<div class="input-group">
													<div class="input-group-addon">任务状态</div>
													<select class="form-control" name="state">
														<option <c:if test="${ task.state == 'New' }">selected="selected"</c:if> value="New">新建</option>
														<option <c:if test="${ task.state == 'Take' }">selected="selected"</c:if> value="Take">接受</option>
														<option <c:if test="${ task.state == 'Doding' }">selected="selected"</c:if> value="Doding">进行中</option>
														<option <c:if test="${ task.state == 'FrontendFinish' }">selected="selected"</c:if> value="FrontendFinish">前端完成</option>
														<option <c:if test="${ task.state == 'RearendFinish' }">selected="selected"</c:if> value="RearendFinish">后端完成</option>
														<option <c:if test="${ task.state == 'Finish' }">selected="selected"</c:if> value="Finish">完成</option>
														<option <c:if test="${ task.state == 'Refuse' }">selected="selected"</c:if> value="Refuse">拒绝</option>
													</select>
												</div>
											</div>
											<div class="col-xs-2">
												<input type="submit" class="btn btn-info" value="修改" />
											</div>
											</sf:form>
										</div>
									<br/>
									<textarea style="display: none;" id="branch-message${ num.index }">${ task.content }</textarea>
									<div id="preview${ num.index }" style="border: 2px solid #F2F2F2;background-color: #FAF2CC;padding-left: 10px;"></div>
									<br />
								</div>	
								</c:forEach>
							</div>
							
							<a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task/${ currentTask.id }/subTask" />"><button class="btn btn-success" style="float: right;">添加子任务</button></a>
							</div>
						</div>
						<br/>
						<br/>
						<div class="row" >
								<div class="col-xs-9" style="border-top: 1px solid #EBEBEB;margin-left: 25px;">
									<br />
									<label>提问</label>
									<sf:form action="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/task/${ currentTask.id }/problem" method="Post">
									<div class="row">
										<div class="col-xs-1">
											<img src="<c:url value='/picture/user/${username}'/>?pid=${userPicture}" class="img-circle heard-profile-picture" style="margin: 0px;">
										</div>
										<div class="col-xs-11">
											<textarea class="form-control" rows="3" name="content"></textarea>
										</div>
									</div>
									<br />
									<div class="row">
										<div class="col-xs-2 col-xs-offset-10">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-info" value="提问" />
										</div>
									</div>
									</sf:form>
									<br />
									<br />
									<p style="font-size: 18px;font-weight: 600;">${ problemNum } 个问题</p>
								</div>
							</div>
							<div class="row" >
								<div class="col-xs-9" style="border-top: 1px solid #EBEBEB;margin-left: 25px;">
									
									<c:forEach items="${ problems }" var="problem" varStatus="idx">
									<div class="row">
										<br />
										<div class="col-xs-1">
											<img src="<c:url value='/picture/user/${ problem.member.user.username }'/>?pid=${ problem.member.user.picture }" class="img-circle heard-profile-picture" style="margin: 0px;">
										</div>
										<div class="col-xs-6">
											${ problem.member.memberName } 
											<br />
											<small style="font-size: 14px;color: #888888;"> ${ idx.index + 1 } 楼 &nbsp;&nbsp;${ problem.getFormatTime() } &nbsp;&nbsp;&nbsp;&nbsp;状态: <c:choose>
												<c:when test="${ problem.problemState == 'New' }"><span style="color: red;">未解决</span></c:when>
												<c:when test="${ problem.problemState == 'Solve' }"><span style="color: red;">已解决</span></c:when>
											</c:choose></small>
										</div>
									</div>
									<br />
									<div class="row" style="border-bottom: 1px solid #EBEBEB;">
										<div class="col-xs-11">
											<p style="font-size: 16px;font-weight: 500;">${ problem.content }</p>
										</div>
										<br />
									</div>
									</c:forEach>
									<br/>
									<br/>
								</div>
							</div>
						<br/>
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
									<a href=""><i class="fa fa-link" aria-hidden="true"></i>${ history.task.title }</a>
								</p>
								</c:if>
								<c:if test="${ history.type == 'SubTask' }">
								<p class="left-panel-history-li">
									<i class="fa fa-star" aria-hidden="true"></i>
									<a href="#" class="history-source">${ history.initiateMember.memberName }</a>
									<font class="history-event">${ history.action }</font>
								</p>
								</c:if>
								<c:if test="${ history.type == 'Problem' }">
								<p class="left-panel-history-li">
									<i class="fa fa-star" aria-hidden="true"></i>
									<a href="#" class="history-source">${ history.initiateMember.memberName }</a>
									<font class="history-event">${ history.action }</font>
								</p>
								</c:if>
								<c:if test="${ history.type == 'TaskState' }">
								<p class="left-panel-history-li">
									<i class="fa fa-star" aria-hidden="true"></i>
									<a href="#" class="history-source">${ history.initiateMember.memberName }</a>
									<font class="history-event">${ history.action }</font>
									<font class="history-event"><c:choose>
										<c:when test="${ history.actionMessage == 'New' }"><span style="color: red;">新建</span></c:when>
										<c:when test="${ history.actionMessage == 'Take' }"><span style="color: blue;">已接受</span></c:when>
										<c:when test="${ history.actionMessage == 'Doding' }"><span style="color: green;">进行中</span></c:when>
										<c:when test="${ history.actionMessage == 'FrontendFinish' }"><span style="color: purple;"> 前端完成</span></c:when>
										<c:when test="${ history.actionMessage == 'RearendFinish' }"><span style="color: purple;">后端完成</span></c:when>
										<c:when test="${ history.actionMessage == 'Finish' }"><span style="color: orange; ;">完成</span></c:when>
										<c:when test="${ history.actionMessage == 'Refuse' }"><span style="color: red;">拒绝</span></c:when>
										</c:choose></font>
								</p>
								</c:if>
							</c:forEach>
							</div>
					</div>
				</div>
			</div>
		</div>
		<script>
		      	document.getElementById('preview').innerHTML = "";
				var text = $("#branch-message").val();
				document.getElementById('preview').innerHTML = (marked(text));
				<c:forEach items="${ currentTask.subtasks }" var="task" varStatus="num">
				document.getElementById('preview${ num.index }').innerHTML = "";
				var text = $("#branch-message${ num.index }").val();
				document.getElementById('preview${ num.index }').innerHTML = (marked(text));
				</c:forEach>
	    </script>
	</body>
</html>
