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
				<div class="col-xs-8 col-xs-offset-2">
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
									<form>
										<div class="row">
											<div class="col-xs-3">
												<div class="input-group">
													<div class="input-group-addon">任务状态</div>
													<select class="form-control">
														<option <c:if test="${ currentTask.state == 'New' }">selected="selected"</c:if>>新建</option>
														<option <c:if test="${ currentTask.state == 'Take' }">selected="selected"</c:if>>接受</optiton>
														<option <c:if test="${ currentTask.state == 'Doding' }">selected="selected"</c:if>>进行中</option>
														<option <c:if test="${ currentTask.state == 'FrontendFinish' }">selected="selected"</c:if>>前段完成</option>
														<option <c:if test="${ currentTask.state == 'RearendFinish' }">selected="selected"</c:if>>后端完成</option>
														<option <c:if test="${ currentTask.state == 'Finish' }">selected="selected"</c:if>>完成</option>
														<option <c:if test="${ currentTask.state == 'Refuse' }">selected="selected"</c:if>>拒绝</option>
													</select>
												</div>
											</div>
											<div class="col-xs-2">
												<input type="submit" class="btn btn-info" value="修改" />
											</div>
										</div>
									</form>
									<br/>
									<textarea style="display: none;" id="branch-message">${ currentTask.content }</textarea>
									<div id="preview" style="border: 2px solid #F2F2F2;background-color: #E1EDF7;padding-left: 10px;padding-top:10px; "></div>
								<br/>
								</div>
								<c:forEach items="${ currentTask.subtasks }" var="task" varStatus="num">
								<div class="col-xs-12" style="margin-left: 30px!important;border-left: 5px solid #CCCCCC;">
									<p style="font-size: 20px;font-weight: 600;">子任务</p>
									<br/>
									<form>
										<div class="row">
											<div class="col-xs-3">
												<div class="input-group">
													<div class="input-group-addon">任务状态</div>
													<select class="form-control">
														<option <c:if test="${ task.state == 'New' }">selected="selected"</c:if>>新建</option>
														<option <c:if test="${ task.state == 'Take' }">selected="selected"</c:if>>接受</optiton>
														<option <c:if test="${ task.state == 'Doding' }">selected="selected"</c:if>>进行中</option>
														<option <c:if test="${ task.state == 'FrontendFinish' }">selected="selected"</c:if>>前段完成</option>
														<option <c:if test="${ task.state == 'RearendFinish' }">selected="selected"</c:if>>后端完成</option>
														<option <c:if test="${ task.state == 'Finish' }">selected="selected"</c:if>>完成</option>
														<option <c:if test="${ task.state == 'Refuse' }">selected="selected"</c:if>>拒绝</option>
													</select>
												</div>
											</div>
											<div class="col-xs-2">
												<input type="submit" class="btn btn-info" value="修改" />
											</div>
										</div>
									</form>
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
