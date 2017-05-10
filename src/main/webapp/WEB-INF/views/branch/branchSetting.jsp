<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${ currentBranch.branchName }-设置</title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<div class="container">
		<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="branchleft.jsp" %>
				<!-- center -->
				<div class="col-xs-7 col-xs-offset-2" >
					<div class="center-branche-panel">
					<%@ include file="../message.jsp" %>
						<label>设置</label>
						<hr />
						<div class="row  center-profile-setting">
							<sf:form method="post" commandName="branch" action="?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
								<div class="col-xs-8 center-profile-setting-left">
								<label>基本信息</label>
									<p>组织名</p>
									<sf:input path="branchName" class="form-control" value="${currentBranch.branchName}" />
									<p>组织类型</p>
									<sf:input path="type" class="form-control" value="${ currentBranch.type }" />
									<p>国家</p>
									<div class="row">
										<div class="col-sm-7">
											<select class="form-control">
												<option>China</option>
												<option>English</option>
												<option>Japan</option>
											</select>
										</div>
									</div>
									<p>邮箱</p>
									<div class="row">
										<div class="col-sm-7">
											<select class="form-control">
												<option>${ currentBranch.email }</option>
											</select>
										</div>
									</div>
									<p>主页</p>
									<sf:input path="homePage" class="form-control" value="${ currentBranch.homePage }" />
									<p>介绍</p>
			    						<textarea name="intro" class="form-control" rows="3">${ currentBranch.intro }</textarea><br />
									<input type="submit" class="btn btn-success" value="更新信息"/>
								</div>
								<div class="col-xs-4">
									<label>组织头像</label><br />
									<img  id="preview" src="<c:url value="/picture/branch/${currentBranch.branchName}?pid=${currentBranch.picture}"/>" class="branch-img" style="width: 200px;height: 250px;" />
									<br/>
									<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="btn btn-info" id="addPicture">修改图片</label>
									<input style="display: none;" type="file" id="file" name="branchPicture" onchange="imgPreview(this)" />
								</div>
							</sf:form>
						</div>
						<div class="row center-panel-bottom">
							<label>组织权限设置</label>
							<hr />
							<div class="row role-authority-li" style="padding-left: 17px;">
								<label>组织权限</label><br />
								<div class="row role-authority">
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowChangeInformation">
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">组织设置修改权限:</div>
												<select class="form-control" name="roleName">
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowChangeInformation != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowChangeInformation.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowInto">
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">组织访问权限:</div>
											<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowInto == null }">
													<option selected="selected" value="" >所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowInto != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowInto != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowInto.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowInto == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
								</div>
							</div>
							<br />
							<div class="row role-authority-li" style="padding-left: 17px;">
								<label>公告权限</label>
								<div class="row role-authority">
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowSeeMessage">
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">查看公告权限:</div>
												<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowSeeMessage == null }">
													<option selected="selected" value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowSeeMessage != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowSeeMessage != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowSeeMessage.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowSeeMessage == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowCreateMessage">
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">创建公告权限:</div>
											<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowCreateMessage == null }">
													<option selected="selected" value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowCreateMessage != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowCreateMessage != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowCreateMessage.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowCreateMessage == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
								</div>
								<div class="row role-authority">
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowChangeMessage">
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">修改公告权限:</div>
												<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowChangeMessage == null }">
													<option selected="selected" value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowChangeMessage != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowChangeMessage != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowChangeMessage.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowChangeMessage == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowDeleteMessage">
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">删除公告权限:</div>
											<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowDeleteMessage == null }">
													<option selected="selected" value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowDeleteMessage != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowDeleteMessage != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowDeleteMessage.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowDeleteMessage == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
								</div>
							</div>
							<br />
							<div class="row role-authority-li" style="padding-left: 17px;">
								<label>项目权限</label>
								<div class="row role-authority">
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowTakeTask">
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">加入项目权限:</div>
												<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowTakeTask == null }">
													<option selected="selected" value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowTakeTask != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowTakeTask != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowTakeTask.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowTakeTask == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowCreateTask">
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">创建项目权限:</div>
											<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowCreateTask == null }">
													<option selected="selected" value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowCreateTask != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowCreateTask != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowCreateTask.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowCreateTask == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
								</div>
								<div class="row role-authority">
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowChangeTask">
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">修改项目权限:</div>
												<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowChangeTask == null }">
													<option selected="selected" value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowChangeTask != null }">
													<option value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowChangeTask != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowChangeTask != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowChangeTask.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowChangeTask == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowDeleteTask">
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">删除项目权限:</div>
											<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowDeleteTask == null }">
													<option selected="selected" value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowDeleteTask != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowDeleteTask != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowDeleteTask.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowDeleteTask == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
								</div>
							</div>
							<br />
							<div class="row role-authority-li" style="padding-left: 17px;">
								<label>评论权限</label>
								<div class="row role-authority">
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowPublishComment">
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">发表评论权限:</div>
												<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowPublishComment == null }">
													<option selected="selected" value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowPublishComment != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowPublishComment != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowPublishComment.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowPublishComment == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowDeleteComment">
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">删除评论权限:</div>
											<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowDeleteComment == null }">
													<option selected="selected" value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowDeleteComment != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowDeleteComment != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowDeleteComment.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowDeleteComment == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
								</div>
							</div>
							<br />
							<div class="row role-authority-li" style="padding-left: 17px;">
								<label>成员权限</label>
								<div class="row role-authority">
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowSeeMember">
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">查看成员权限:</div>
												<select class="form-control" name="roleName">
													<c:if test="${ currentBranch.branchAuthority.allowSeeMember == null }">
														<option selected="selected" value="">所有人</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowSeeMember != null }">
														<option value="">所有人</option>
													</c:if>
													<c:forEach items="${ currentBranchRoleList }" var="branchRole">
														<c:if test="${ currentBranch.branchAuthority.allowSeeMember != null }">
															<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowSeeMember.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
														</c:if>
														<c:if test="${ currentBranch.branchAuthority.allowSeeMember == null }">
															<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowAddMember">
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">添加成员权限:</div>
											<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowAddMember == null }">
													<option selected="selected" value="" >所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowAddMember != null }">
													<option value="" >所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowAddMember != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowAddMember.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowAddMember == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
								</div>
								<div class="row role-authority">
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowChangeMember">
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">修改成员权限:</div>
												<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowChangeMember == null }">
													<option selected="selected" value="">所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowChangeMember != null }">
													<option value="">所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowChangeMember != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowChangeMember.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowChangeMember == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowDeleteMember">
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">删除成员权限:</div>
											<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowDeleteMember == null }">
													<option selected="selected" value="" >所有人</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowDeleteMember != null }">
													<option value="" >所有人</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowDeleteMember != null }">
														<option value="${ branchRole.roleName }" <c:if test="${ currentBranch.branchAuthority.allowDeleteMember.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowDeleteMember == null }">
														<option value="${ branchRole.roleName }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</sf:form>
								</div>
							</div>
						</div>
						<br/>
					</div>
				</div>
				<!-- Right -->
				<div class="col-xs-2 center-branche-panel center-profile-setting-right" >
					<label>组织角色</label>
					<hr/>
					<div class="row add-role">
						<sf:form method="post" action="/branch/${ currentBranch.branchName }/role/new">
							<div class="col-xs-6" style="padding-left: 22px;">
								<input type="text"  class="form-control" name="roleName" placeholder="角色名"/>
							</div>
							<div class="col-xs-3">
								<input type="text"  class="form-control" name="roleLevle" placeholder="角色权限"/>
							</div>
							<div class="col-xs-2">
								<input type="submit" class="btn btn-success" value="添加"/>
							</div>
						</sf:form>
					</div>
					<br/>
					<c:forEach items="${ currentBranchRoleList }" var="branchRole">
					<div class="row role-li">
						<div class="col-xs-5" style="padding-left: 0px;"><p class="role-li-name">${ branchRole.roleName }</p></div>
						<div class="col-xs-3"><p class="role-li-level">${ branchRole.roleLevel }</p></div>
						<div class="col-xs-2"><i class="fa fa-edit role-font" style="color: #67B168;cursor: pointer;" aria-hidden="true" onclick="changeRole(this)"></i></div>
						<div class="col-xs-2"><i class="fa fa-times role-font" style="color: #D9534F;" aria-hidden="true"></i></div>
					</div>
					<div class="row add-role" style="display: none;">
						<sf:form method="post" action="/branch/${ currentBranch.branchName }/role/setting">
							<input type="hidden" value="${ branchRole.roleName }" name="oldRoleName">
							<div class="col-xs-4" style="padding-left: 22px;">
								<input type="text"  class="form-control" placeholder="Rolename" name="newRoleName" value="${ branchRole.roleName }"/>
							</div>
							<div class="col-xs-3">
								<input type="text"  class="form-control" placeholder="level" name="newRoleLevel" value="${ branchRole.roleLevel }"/>
							</div>
							<div class="col-xs-2">
								<input type="submit" class="btn btn-success" value="YES"/>
							</div>
						</sf:form>
							<div class="col-xs-2" style="margin-left: 4px;">
								<button class="btn btn-warning" onclick="noChangeRole(this)">No</button>
							</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</body>
</html>