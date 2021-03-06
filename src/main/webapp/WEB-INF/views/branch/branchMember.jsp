<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${ currentBranch.branchName }-成员</title>
	<%@ include file="../head.jsp" %>
</head>
<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<!-- left -->
				<%@ include file="branchleft.jsp" %>
				<!-- center -->
				<div class="col-xs-10 col-xs-offset-2 ">
					<div class="center-branche-panel">
					<%@ include file="../message.jsp" %>
						<label>成员</label>
						<hr />
						<div class="row center-myBranches-up">
							<sf:form method="post" action="/branch/${ currentBranch.branchName }/member/invite">
								<div class="col-xs-4">
									<input type="text"  class="form-control" placeholder="用户名" name="username"/>
								</div>
								<div class="col-xs-4">
									<input type="text"  class="form-control" placeholder="邀请信息" name="message"/>
								</div>
								<div class="col-xs-2">
									<input type="submit" class="btn btn-default" value="邀请"/>
								</div>
							</sf:form>
						</div>
						
						<div class="row">
						<div class="col-xs-8">
							<label>所有成员</label>
							<div class="row" style="height: 600px">
								<c:forEach items="${ page.content }" var="member">
									<div class="col-xs-6 branchMember-li">
										<div class="row" style="padding-bottom: 5px;">
											<div class="col-xs-3">
												<img class="img-circle center-person-picture" style="height: 80px;width: 80px;" src="<c:url value='/picture/user/${member.user.username}'/>?pid=${member.user.picture}" />
											</div>
											<sf:form method="post" action="/branch/${ currentBranch.branchName }/member/role">
											<div class="col-xs-6 branchMember-li-message">
												<div class="row">
													<div class="col-xs-5" style="padding-right: 0px">
														<p class="hide-p username-p"><a href="#">${ member.memberName }</a></p>
													</div>
													
													<div class="col-xs-6" style="padding-left: 8px; height: 45px">
															<input type="hidden" name="username" value="${ member.user.username }">											
															<select class="form-control" name="roleName">	
															<c:forEach items="${ currentBranchRoleList }" var="branchRole">
																	<option value="${ branchRole.roleName }" <c:if test="${ member.branchRole.id == branchRole.id }">selected="selected"</c:if>>${ branchRole.roleName }</option>
															</c:forEach>
															</select>
															<br/>
													</div>
												</div>
												<div class="row">
													&nbsp;&nbsp;&nbsp;&nbsp;<small class="hide-p">${ member.user.introduction }</small>
												</div>
											</div>
											<div class="col-xs-3">
												<br />
												<button class="btn btn-success">修改</button>
											</div>
											</sf:form>
										</div>
									</div>
								</c:forEach>
								</div>
							</div>
							<div class="col-xs-3 col-xs-offset-1 friend-request">
								<div class="row">
								<label>发出的邀请</label>
								<c:forEach  items="${invitePage.content}" var="memberRequest">
											<div class="row center-friends-li friend-request-li branchMember-li">
												<div class="col-xs-3">
													<img src="<c:url value='/picture/user/${memberRequest.user.username}'/>?pid=${memberRequest.user.picture}" class="img-circle heard-profile-picture">
												</div>
												<div class="col-xs-5">
													<a href="#">${ memberRequest.user.username }</a>
													<p class="hide-p">${ memberRequest.message }</p>
												</div>
										</div>
								</c:forEach>
									<c:if test="${ joinRequestPage.content == null || joinRequestPage.content.size() == 0 }">
									<div class="right-empty">没有发出邀请</div>
									</c:if>
								</div>
								<div class="row">
								<label>加入请求</label>
								<c:forEach  items="${joinRequestPage.content}" var="memberRequest">
											<div class="row center-friends-li branchMember-li">
												<div class="col-xs-3">
													<img src="<c:url value='/picture/user/${memberRequest.user.username}'/>?pid=${memberRequest.user.picture}" class="img-circle heard-profile-picture">
												</div>
												<div class="col-xs-4">
													<a href="#">${ memberRequest.user.username }</a>
													<p class="hide-p">${ memberRequest.message }</p>
												</div>
												<div class="col-xs-2">
														<sf:form method="post" action="/branch/${ currentBranch.branchName }/member/request">
															<input type="hidden" name="state" value="1">
															<input type="hidden" name="username" value="${memberRequest.user.username}">
															<button class="btn btn-info">Agree</button>
														</sf:form>
												</div>
												<div class="col-xs-2">
														<sf:form method="post" action="/branch/${ currentBranch.branchName }/member/request">
															<input type="hidden" name="state" value="2">
															<input type="hidden" name="username" value="${memberRequest.user.username}">
															<button class="btn btn-warning">No</button>
														</sf:form>
												</div>
										</div>
								</c:forEach>
									<c:if test="${ joinRequestPage.content == null || joinRequestPage.content.size() == 0 }">
									<div class="right-empty">没有新的加入请求</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<c:if test="${page.totalPages >= 1}">
						<div class="row">
							<div class="col-xs-8">
								<div style="float: right;">
									<ul class="pagination" >
										<li><a href="<c:url value="/branch/${currentBranch.branchName}/member?page=0&size=12"/>">&laquo;</a></li>
										<c:if test="${page.totalPages <= 5}">
											<c:forEach begin="1" end="${page.totalPages}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/member?page=${p-1}&size=12"/>">${p}</a></li>
											</c:forEach>
										</c:if>
										<c:if test="${page.totalPages > 5}">
											<c:if test="${page.number - 2 <= 0}">
												<c:forEach begin="1" end="5" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/member?page=${p-1}&size=12"/>">${p}</a></li>
												</c:forEach>
											</c:if>c:if test="${ page.content == null || page.content.size() == 0 }">
											<c:if test="${page.number -2 > 0 && page.totalPages - page.number > 3}">
												<c:forEach begin="${page.number - 1}" end="${page.number + 3}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/member?page=${p-1}&size=12"/>">${p}</a></li>
												</c:forEach>
											</c:if>
											<c:if test="${page.totalPages - page.number <= 3}">
												<c:forEach begin="${page.number - 3}" end="${page.totalPages}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/member?page=${p-1}&size=12"/>">${p}</a></li>
												</c:forEach>
											</c:if>
										</c:if>
										<li><a href="<c:url value="/branch/${currentBranch.branchName}/member?page=${page.totalPages-1}&size=12"/>">&raquo;</a></li>
									</ul>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</body>
</html>