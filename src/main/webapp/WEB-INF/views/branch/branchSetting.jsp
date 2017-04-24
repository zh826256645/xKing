<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Branch Setting</title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<div class="container">
		<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="branchleft.jsp" %>
				<!-- center -->
				<div class="col-xs-7" >
					<div class="center-branche-panel">
					<%@ include file="../message.jsp" %>
						<label>Setting</label>
						<hr />
						<div class="row  center-profile-setting">
							<sf:form method="post" commandName="branch" action="?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
								<div class="col-xs-8 center-profile-setting-left">
								<label>basic information</label>
									<p>Branch Name</p>
									<sf:input path="branchName" class="form-control" value="${currentBranch.branchName}" />
									<p>Type</p>
									<sf:input path="type" class="form-control" value="${ currentBranch.type }" />
									<p>Country</p>
									<div class="row">
										<div class="col-sm-7">
											<select class="form-control">
												<option>China</option>
												<option>English</option>
												<option>Japan</option>
											</select>
										</div>
									</div>
									<p>Email</p>
									<div class="row">
										<div class="col-sm-7">
											<select class="form-control">
												<option>${ currentBranch.email }</option>
											</select>
										</div>
									</div>
									<p>Home Page</p>
									<sf:input path="homePage" class="form-control" value="${ currentBranch.homePage }" />
									<p>introduction</p>
			    						<textarea name="intro" class="form-control" rows="3">${ currentBranch.intro }</textarea><br />
									<input type="submit" class="btn btn-success" value="Update profile"/>
								</div>
								<div class="col-xs-4">
									<label>Branch Picturle</label><br />
									<img  id="preview" src="<c:url value="/picture/branch/${currentBranch.branchName}?pid=${currentBranch.picture}"/>" class="branch-img" style="width: 200px;height: 250px;" />
									<br/>
									<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="btn btn-info" id="addPicture">Add picture</label>
									<input style="display: none;" type="file" id="file" name="branchPicture" onchange="imgPreview(this)" />
								</div>
							</sf:form>
						</div>
						<div class="row center-panel-bottom">
							<label>Branch Role Authority</label>
							<hr />
							<div class="row role-authority-li" style="padding-left: 17px;">
								<label>Branch Authority</label><br />
								<div class="row role-authority">
									<sf:form method="post" action="/branch/${ currentBranch.branchName }/authority">
										<input type="hidden" name="authorityName"  value="allowChangeInformation">
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">Change Branch Information:</div>
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
											<div class="input-group-addon">Allow Into:</div>
											<select class="form-control" name="roleName">
												<c:if test="${ currentBranch.branchAuthority.allowInto == null }">
													<option selected="selected" value="" >every one</option>
												</c:if>
												<c:if test="${ currentBranch.branchAuthority.allowInto != null }">
													<option value="">every one</option>
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
								<label>Message Authority</label>
								<div class="row role-authority">
									<form>
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">See Message:</div>
												<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowSeeMessage == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowSeeMessage != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowSeeMessage.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowSeeMessage == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
									<form>
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">Create Message:</div>
											<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowCreateMessage == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowCreateMessage != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowCreateMessage.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowCreateMessage == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
								</div>
								<div class="row role-authority">
									<form>
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">Change Message:</div>
												<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowChangeMessage == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowChangeMessage != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowChangeMessage.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowChangeMessage == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
									<form>
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">Delete Message:</div>
											<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowDeleteMessage == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowDeleteMessage != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowDeleteMessage.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowDeleteMessage == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
								</div>
							</div>
							<br />
							<div class="row role-authority-li" style="padding-left: 17px;">
								<label>Task Authority</label>
								<div class="row role-authority">
									<form>
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">Take Task:</div>
												<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowTakeTask == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowTakeTask != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowTakeTask.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowTakeTask == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
									<form>
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">Create Task:</div>
											<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowCreateTask == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowCreateTask != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowCreateTask.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowCreateTask == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
								</div>
								<div class="row role-authority">
									<form>
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">Change Task:</div>
												<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowChangeTask == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowChangeTask != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowChangeTask.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowChangeTask == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
									<form>
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">Delete Task:</div>
											<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowDeleteTask == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowDeleteTask != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowDeleteTask.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowDeleteTask == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
								</div>
							</div>
							<br />
							<div class="row role-authority-li" style="padding-left: 17px;">
								<label>Comment Authority</label>
								<div class="row role-authority">
									<form>
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">Publish Comment:</div>
												<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowPublishComment == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowPublishComment != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowPublishComment.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowPublishComment == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
									<form>
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">Delete Comment:</div>
											<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowDeleteComment == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowDeleteComment != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowDeleteComment.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowDeleteComment == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
								</div>
							</div>
							<br />
							<div class="row role-authority-li" style="padding-left: 17px;">
								<label>Member Authority</label>
								<div class="row role-authority">
									<form>
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">See Members:</div>
												<select class="form-control">
													<c:if test="${ currentBranch.branchAuthority.allowSeeMember == null }">
														<option selected="selected" >every one</option>
													</c:if>
													<c:forEach items="${ currentBranchRoleList }" var="branchRole">
														<c:if test="${ currentBranch.branchAuthority.allowSeeMember != null }">
															<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowSeeMember.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
														</c:if>
														<c:if test="${ currentBranch.branchAuthority.allowSeeMember == null }">
															<option value="${ branchRole.id }">${ branchRole.roleName }</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
									<form>
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">Add Member:</div>
											<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowAddMember == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowAddMember != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowAddMember.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowAddMember == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
								</div>
								<div class="row role-authority">
									<form>
										<div class="col-xs-4">
											<div class="input-group">
												<div class="input-group-addon">Change Member:</div>
												<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowChangeMember == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowChangeMember != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowChangeMember.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowChangeMember == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
									<form>
										<div class="col-xs-4">
										<div class="input-group">
											<div class="input-group-addon">Delete Member:</div>
											<select class="form-control">
												<c:if test="${ currentBranch.branchAuthority.allowDeleteMember == null }">
													<option selected="selected" >every one</option>
												</c:if>
												<c:forEach items="${ currentBranchRoleList }" var="branchRole">
													<c:if test="${ currentBranch.branchAuthority.allowDeleteMember != null }">
														<option value="${ branchRole.id }" <c:if test="${ currentBranch.branchAuthority.allowDeleteMember.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
													</c:if>
													<c:if test="${ currentBranch.branchAuthority.allowDeleteMember == null }">
														<option value="${ branchRole.id }">${ branchRole.roleName }</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										</div>
										<div class="col-xs-2 role-submit">
											<input type="submit" value="Change" class="btn btn-info" />
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Right -->
				<div class="col-xs-2 center-branche-panel center-profile-setting-right" >
					<label>Branch Role</label>
					<hr/>
					<div class="row add-role">
						<sf:form method="post" action="/branch/${ currentBranch.branchName }/role/new">
							<div class="col-xs-6" style="padding-left: 22px;">
								<input type="text"  class="form-control" name="roleName" placeholder="Role name"/>
							</div>
							<div class="col-xs-3">
								<input type="text"  class="form-control" name="roleLevle" placeholder="level"/>
							</div>
							<div class="col-xs-2">
								<input type="submit" class="btn btn-success" value="Add"/>
							</div>
						</sf:form>
					</div>
					<c:forEach items="${ currentBranchRoleList }" var="branchRole">
					<div class="row role-li">
						<div class="col-xs-5" style="padding-left: 0px;"><p class="role-li-name">${ branchRole.roleName }</p></div>
						<div class="col-xs-3"><p class="role-li-level">${ branchRole.roleLevel }</p></div>
						<div class="col-xs-2"><i class="fa fa-edit role-font" style="color: #67B168;" aria-hidden="true"></i></div>
						<div class="col-xs-2"><i class="fa fa-times role-font" style="color: #D9534F;" aria-hidden="true"></i></div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</body>
</html>