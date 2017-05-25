<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>任务发布</title>
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
				<%@ include file="branchleft.jsp" %>
				<div class="col-xs-7 col-xs-offset-2">
				<sf:form action="" method="post" commandName="task">
					<div class="center-branche-panel">
						<%@ include file="../message.jsp" %>
						<label>新建任务</label>
						<hr />
						<div class="row">
							<div class="col-xs-4">
								<div class="row">
									<div class="col-xs-12">
										<label>任务类型</label>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-10">
										<div class="input-group">
										<div class="input-group-addon">类型:</div>
										<select class="form-control" name="type">
											<option value="Task" <c:if test="${ task.type != null && task.type == 'Task'}">selected="selected"</c:if>>任务</option>
											<option value="Test" <c:if test="${ task.type != null && task.type == 'Test'}">selected="selected"</c:if>>测试</option>
											<option value="Debugging" <c:if test="${ task.type != null && task.type == 'Debugging'}">selected="selected"</c:if>>调试</option>
											<option value="Features" <c:if test="${ task.type != null && task.type == 'Features'}">selected="selected"</c:if>>功能</option>
											<option value="Bug" <c:if test="${ task.type != null && task.type == 'Bug'}">selected="selected"</c:if>>漏洞</option>
											<option value="BiSai" <c:if test="${ task.type != null && task.type == 'BiSai'}">selected="selected"</c:if>>比赛</option>
										</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="row">
									<div class="col-xs-12">
										<label>任务优先级</label>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-10">
										<div class="input-group">
										<div class="input-group-addon">优先级:</div>
										<select class="form-control" name="taskLevel">
											<option value="Common" <c:if test="${ task.taskLevel != null && task.taskLevel == 'Common'}">selected="selected"</c:if>>普通</option>
											<option value="Preference"  <c:if test="${ task.taskLevel != null && task.taskLevel == 'Preference'}">selected="selected"</c:if>>优先</option>
											<option value="Emergency" <c:if test="${ task.taskLevel != null && task.taskLevel == 'Emergency'}">selected="selected"</c:if>>紧急</option>
										</select>
										</div>
									</div>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div  class="col-xs-12">
								<div class="row">
									<div class="col-xs-12">
										<label>任务时间</label>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">开始时间</div>
													<input class="form-control" id="startTIme" name="startTimeStr" value="${ startTimeStr }"/>
											</div>
										</div>
									<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">结束时间</div>
													<input class="form-control" id="endTIme" name="endTimeStr" value="${ endTimeStr }"/>
											</div>
										</div>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-xs-6">
								<div class="row">
									<div class="col-xs-12">
										<label>任务标题</label>
									</div>
								</div>
								<div class="row center-myBranches-up" style="border-bottom: 0px !important;">
									<div class="col-xs-12">
										<input type="text"  class="form-control" placeholder="标题" name="title" value="${ task.title }"/>
									</div>
								</div>
							</div>
								<div class=col-xs-4>
									<div class="row">
										<div class="col-xs-12">
											<label>指派成员</label>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12">
											<div class="input-group">
												<div class="input-group-addon">指派给:</div>
												<select class="form-control" name="memberId">
													<c:forEach items="${ currentProject.projectMember }" var="member">
														<option value="${ member.id }" <c:if test="${ memberId != null && member.id == memberId }">selected="selected"</c:if>><c:if test="${ member.user.id == currentUser.id }">我自己</c:if ><c:if test="${ member.user.id != currentUser.id }">${ member.memberName }</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="row">
										<div class="col-xs-12">
											<label>任务内容</label>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-11">
											<div class="message-panel">
											<ul id="myTab" class="nav nav-tabs" style="padding-left: 20px;">
												
											   <li class="active">
											      <a href="#home" data-toggle="tab">
											         编辑
											      </a>
											   </li>
											   <li>
											   		<a href="#ios" data-toggle="tab" onclick="editor()">预览</a>
											   </li>
											</ul>
											</div>
											<div id="myTabContent" class="tab-content tab-control">
											   <div class="tab-pane fade in active" id="home">
												  <br />
											      <textarea id="text-input" rows="13" class="form-control messge-textarea" name="content">${ task.content }</textarea>
											   </div>
											   <div  class="tab-pane fade" id="ios">
											     <div >
											     	<article class="markdown-body" id="preview">
													</article>
											     </div>
											   </div>  
											</div>
										</div>
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-xs-1 col-xs-offset-10">
									<input type="submit" value="sumbit" class="btn btn-info">
								</div>
							</div>
						</div>
						</sf:form>
					</div>

			</div>
		</div>
		<script>
	      function editor() {
		      	document.getElementById('preview').innerHTML = "";
				var text = $("#text-input").val();
				document.getElementById('preview').innerHTML = (marked(text));
		      }
	    </script>
	</body>
</html>