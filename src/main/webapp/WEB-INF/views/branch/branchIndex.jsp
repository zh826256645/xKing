<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${ currentBranch.branchName }</title>
	<%@ include file="../head.jsp" %>
</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<!-- left -->
				<%@ include file="branchleft.jsp" %>
				<!-- center -->
				<div class="col-xs-7 col-xs-offset-2">
					<div class="row">
						<div class="center-branche-panel">
							<div class="panel panel-default">
							    <div class="panel-body panel-nopadding" style="padding-right: 0px;">
						        	<div class="col-xs-4 text-center branch-left">
						        		<br/>
						        		<img src="<c:url value="/picture/branch/${currentBranch.branchName}?pid=${currentBranch.picture}"/>"  class="branch-img-small"/><br />
						        		<p class="h3 hide-p" style="padding-top: 10px"><a href="<c:url value="/branch/${currentBranch.branchName}/setting"/>">${currentBranch.branchName}</a></p>
						        	</div>
						        	<div class="col-xs-8 branch-left">
						        		<label class="label label-color">公告</label>
						        		<c:if test="${ branchMessages.content != null && branchMessages.content.size() != 0 }">
						        		<c:forEach items="${ branchMessages.content }" var="branchMessage">
						        		<p class="hide-p branch-message-p">
											<i class="fa fa-tags" aria-hidden="true"></i> <c:if test="${ branchMessage.messageTag != null }">[${ branchMessage.messageTag.tagName }]</c:if><c:if test="${ branchMessage.messageTag == null }">[所有]</c:if>
											<a href="<c:url value="/branch/${ currentBranch.branchName }/message/${ branchMessage.id }" />">${ branchMessage.title }</a>
										</p>
										</c:forEach>
										</c:if>
										<c:if test="${ branchMessages.content == null || branchMessages.content.size() == 0 }">
										<div >
										<p style="font-size: 28px;padding-left: 168px;padding-top: 50px;color: #888888">暂无公告信息</p>
										</div>
										</c:if>
						        	</div>
						        </div>
						    </div>
						</div>
					</div>
					<div class="row">
						<div class="center-branche-panel" style="margin-top: 0px;">
							<div class="panel panel-default">
							    <div class="panel-body panel-nopadding" style="height: 250px">
							    	<label class="label label-color">最新项目</label>
							    	<c:forEach items="${ projects.content }" var="project">
							    	<div class="row" style="padding-top: 5px">
										<div class="col-xs-3">
											 <p class="hide-p" style="font-size: 18px;padding-top: 8px">&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-pie-chart" aria-hidden="true" style="color:#555555;">&nbsp;&nbsp;&nbsp;</i><a  href="<c:url value="/branch/${ currentBranch.branchName }/project/${ project.projectName }"/>">${ project.projectName }</a></p>
										</div>
										<div class="col-xs-5">
											<div class="row">
													<div class="col-xs-3" style="padding-left: 30px;padding-top: 13px;">
													<span style="color:#555555;font-weight: 400;">项目成员</span>
													</div>
													<div class="col-xs-9">
													<c:forEach items="${ project.projectMember }" var="member" end="6">
														<img src="<c:url value='/picture/user/${member.user.username}'/>?pid=${member.user.picture}" class="img-circle project-picture">
													</c:forEach>
													</div>
											</div>
										</div>
										<div class="col-xs-2" style="padding-top: 15px">
											<i class="fa fa-calendar" aria-hidden="true"></i><font> ${ project.getFormatTime() }</font>
										</div>
										<div class="col-xs-1" style="padding-top: 6px">
											<a  href="<c:url value="/branch/${ currentBranch.branchName }/project/${ project.projectName }"/>"><button class="btn btn-info">进入</button></a>
										</div>
							    	</div>	
							    	</c:forEach>
							    	<c:if test="${ projects.content == null || projects.content.size() == 0 }">
							    		<div style="font-size: 28px;padding-left: 416px;padding-top: 50px;color: #888888">暂无项目信息</div>
							    	</c:if>
						        </div>
						    </div>
						</div>
					</div>
					<div class="row" style="padding-left: 10px">
						<div class="panel panel-default col-xs-12 left-panel">
							<div class="panel-heading left-panel-label">
								<h3 class="panel-title">
									<s:message code="profile.history"/>
								</h3>
							</div>
							<div class="panel-body left-panel-history">
								<c:forEach items="${ histories.content }" var="history">
										<c:if test="${ history.type == 'CreateProject' }">
										<p class="left-panel-history-li">
											<i class="fa fa-star" aria-hidden="true"></i>
											<a href="#" class="history-source">${ history.initiateMember.memberName }</a>
											<font class="history-event">${ history.action }</font>
											<a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ history.project.projectName }"/>"><i class="fa fa-link" aria-hidden="true"></i>${ history.project.projectName }</a>
										</p>
										</c:if>
										<c:if test="${ history.type == 'Message' }">
										<p class="left-panel-history-li">
											<i class="fa fa-star" aria-hidden="true"></i>
											<a href="#" class="history-source">${ history.initiateMember.memberName }</a>
											<font class="history-event">${ history.action }</font>
											<a href="<c:url value="/branch/${ currentBranch.branchName }/message/${  history.branchMessage.id }"/>"><i class="fa fa-link" aria-hidden="true"></i>${ history.branchMessage.title }</a>
										</p>
										</c:if>
									</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<!-- Right -->
				<div class="col-xs-2">
					<div class="row">
						<div class="center-branche-panel">
							<div class="panel-body panel-nopadding">
								<div class="panel panel-default">
								    <div class="panel-body panel-nopadding" style="padding-left: 0px;padding-right: 0px;">
										<div class="col-xs-12 branch-user-id" style="padding-bottom: 5px;">
							        		<c:if test="${currentBranchMember != null}">
							        		<label class="label label-color">你的信息</label>
							        		<img class="img-circle center-person-picture center-block" src="<c:url value='/picture/user/${username}'/>?pid=${userPicture}" style="margin-bottom: 5px"/>
						        			<div class="row">
						        				<div class="col-xs-offset-1 col-xs-10 user-id hide-p">
						        					<p><i class="fa fa-user" aria-hidden="true"></i> &nbsp;&nbsp;&nbsp;<a>${currentBranchMember.memberName}</a></p>
						        					<p><i class="fa fa-envelope" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<font>${currentBranchMember.email}</font></p>
						        					<p><i class="fa fa-shield" aria-hidden="true"></i> &nbsp;&nbsp;&nbsp;<font>${currentBranchMember.branchRole.roleName}</font></p>
						        					<p class="hide-p"><i class="fa fa-comment" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<font>${currentUser.introduction}</font></p></p>
						        				</div>
						        			</div>
						        			</c:if>
										</div>
									</div>
								</div>
							</div>
						</div>					
					</div>
					<div class="row" style="padding-left: 9px">
						<div class="center-branche-panel" style="margin-top: 0px;padding-left: 0px;">
							<div class="panel-body panel-nopadding">
								<div class="panel panel-default">
								    <div class="panel-body panel-nopadding" style="padding-left: 0px;padding-right: 0px;">
										<div class="col-xs-12" style="padding-bottom: 5px; margin-right: 20px">
							        		<label class="label label-color">组织简介</label>
						        			<div class="row">
						        				<div class="col-xs-offset-1 col-xs-10 user-id hide-p" style="font-size: 36px;">
						        					<div class="row">
						        					<div class="col-xs-10" style="padding-top: 10px">
							        					<div class="container"><p ><i class="fa fa-users" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a>${ currentBranch.memberNum }</a></p></div>
							        					<div class="container" style="padding-left: 18px;"><p ><i class="fa fa-volume-up" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a>${ messageNum }</a></p></div>
							        					<div class="container"><p ><i class="fa fa-tasks" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a>${ projectNum }</a></p></div>
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
		</div>
	</body>
</html>
