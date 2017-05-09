<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="userleft.jsp" %>
				<div class="col-xs-10 col-xs-offset-2">
					<div class="row">
						<div class="col-xs-12">
							<div class="center-myBranches">
							<%@ include file="../message.jsp" %>
								<label><s:message code="myBranches.branches"/></label>
								<hr />
								<div class="row center-myBranches-up">
								<sf:form method="post" action="/user/branch/request">
										<div class="col-xs-4">
											<input type="text"  class="form-control" placeholder="组织名" name="branchName"/>
										</div>
										<div class="col-xs-3">
											<input type="text"  class="form-control" placeholder="请求加入的理由" name="message"/>
										</div>
										<div class="col-xs-2">
											<input type="submit" class="btn btn-default" value="请求加入"/>
										</div>
									</sf:form>
									<div class="col-xs-2">
										<a href="<c:url value="/branch/new"/>"><button class="btn btn-success">Create New Branche</button></a>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-9">
										<label><s:message code="myBranches.allBranches"/></label>
										<c:forEach items="${page.content}" var="branchMember" >
										<div class="row center-branches-li">
											<div class="col-xs-2">
												<img src="<c:url value="/picture/branch/${branchMember.branch.branchName}?pid=${branchMember.branch.picture}"/>"  class="branch-img-small"/><br />
											</div>
											<div class="col-xs-9 center-branches-li-message">
												<div style="margin-bottom: 10px"><a href="<c:url value="/branch/${branchMember.branch.branchName}"/>">${branchMember.branch.branchName}</a><br/></div>
												<span class="label label-info"><c:if test="${ branchMember.branch.type != null }">${ branchMember.branch.type }</c:if><c:if test="${ branchMember.branch.type == null }">暂无</c:if></span>
												<p class="p-label"><s:message code="myBranches.information"/></p>
												<div class="row center-branches-li-message-body">
													<div class="col-xs-5" style="background-color: #f9fafc;">
														<p><s:message code="myBranches.users"/>:<font>${ branchMember.branch.memberNum }</font> &nbsp;&nbsp;&nbsp;<s:message code="myBranches.active"/>:<font>79</font></p>
														<p><s:message code="myBranches.publicEmail"/>:<font>${branchMember.branch.email}</font></p>
														<p class="hide-p"><s:message code="myBranches.afficheInfo"/>:<font>${branchMember.branch.intro}</font></p>
													</div>
													<div class="col-xs-5" style="background-color: #f9fafc;">
														<p><s:message code="myBranches.yourName"/>:<font>${branchMember.memberName}</font></p>
														<p><s:message code="myBranches.yourRole"/>:<font>${branchMember.branchRole.roleName}</font>&nbsp;&nbsp;&nbsp;<s:message code="myBranches.yourTask"/>:<font>4</font></p>
														<p><s:message code="myBranches.email"/>:<font>${branchMember.email}</font></p>
													</div>
												</div>
											</div>
											<div class="col-xs-1">
												<a href="<c:url value="/branch/${branchMember.branch.branchName}"/>"><button class="btn btn-info"><s:message code="myBranches.enterInto"/></button></a>
											</div>
										</div>
										</c:forEach>
									</div>
									<div class="col-xs-3 friend-request">
										<div class="row">
										<label><s:message code="myBranches.inviteRequest"/></label><br/><br/>
										<c:forEach items="${ invitePage.content }" var="memberRequest">
										<div class="row invite-request-li">
											<div class="col-xs-4">
												<img src="<c:url value="/picture/branch/${memberRequest.branch.branchName}?pid=${memberRequest.branch.picture}"/>"  class="branch-img-small-small"/>
											</div>
											<div class="col-xs-7">
												<a style="font-size: 22px !important; ">${memberRequest.branch.branchName}</a>
												<p class="hide-p">${ memberRequest.message }</p>
												<div class="btn-line">
													<div class="row">
														<div class="col-xs-5">
															<sf:form method="post" action="/user/member/request">
																<input type="hidden" value="1" name="state">
																<input type="hidden" value="${memberRequest.branch.branchName}" name="branchName">
																<button class="btn btn-info">Join</button>
															</sf:form>
														</div>
														<div class="col-xs-5">
															<sf:form method="post" action="/user/member/request">
																<input type="hidden" value="2" name="state">
																<input type="hidden" value="${memberRequest.branch.branchName}" name="branchName">
																<button class="btn btn-warning">No</button>
															</sf:form>
														</div>
													</div>
												</div>
											</div>
										</div>
										</c:forEach>
									</div>
										<div class="row">
										<label><s:message code="myBranches.requestJoin"/></label><br/><br/>
										<c:forEach items="${ requestJoinPage.content }" var="memberRequest">
										<div class="row invite-request-li">
											<div class="col-xs-4">
												<img src="<c:url value="/picture/branch/${memberRequest.branch.branchName}?pid=${memberRequest.branch.picture}"/>"  class="branch-img-small-small"/>
											</div>
											<div class="col-xs-7">
												<a style="font-size: 22px !important; ">${memberRequest.branch.branchName}</a>
												<p class="hide-p">${ memberRequest.message }</p>
												<div class="btn-line">
												</div>
											</div>
										</div>
										</c:forEach>
									</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<c:if test="${page.totalPages >= 1}">
					<div class="row">
						<div class="col-xs-8">
							<div style="float: right;">
								<ul class="pagination" >
									<li><a href="<c:url value="/user/me?tab=branches&page=0&size=3"/>">&laquo;</a></li>
									<c:if test="${page.totalPages <= 5}">
										<c:forEach begin="1" end="${page.totalPages}" var="p">
											<li 
											<c:if test="${page.number == p-1}">class="active"</c:if>
											><a href="<c:url value="/user/me?tab=branches&page=${p-1}&size=3"/>">${p}</a></li>
										</c:forEach>
									</c:if>
									<c:if test="${page.totalPages > 5}">
										<c:if test="${page.number - 2 <= 0}">
											<c:forEach begin="1" end="5" var="p">
											<li 
											<c:if test="${page.number == p-1}">class="active"</c:if>
											><a href="<c:url value="/user/me?tab=branches&page=${p-1}&size=3"/>">${p}</a></li>
											</c:forEach>
										</c:if>
										<c:if test="${page.number -2 > 0 && page.totalPages - page.number > 3}">
											<c:forEach begin="${page.number - 1}" end="${page.number + 3}" var="p">
											<li 
											<c:if test="${page.number == p-1}">class="active"</c:if>
											><a href="<c:url value="/user/me?tab=branches&page=${p-1}&size=3"/>">${p}</a></li>
											</c:forEach>
										</c:if>
										<c:if test="${page.totalPages - page.number <= 3}">
											<c:forEach begin="${page.number - 3}" end="${page.totalPages}" var="p">
											<li 
											<c:if test="${page.number == p-1}">class="active"</c:if>
											><a href="<c:url value="/user/me?tab=branches&page=${p-1}&size=3"/>">${p}</a></li>
											</c:forEach>
										</c:if>
									</c:if>
									<li><a href="<c:url value="/user/me?tab=branches&page=${page.totalPages-1}&size=3"/>">&raquo;</a></li>
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