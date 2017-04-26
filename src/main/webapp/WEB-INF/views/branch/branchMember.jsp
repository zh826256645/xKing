<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BranchMessage</title>
	<%@ include file="../head.jsp" %>
</head>
<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<!-- left -->
				<%@ include file="branchleft.jsp" %>
				<!-- center -->
				<div class="col-xs-10 ">
					<div class="center-branche-panel">
					<%@ include file="../message.jsp" %>
						<label>Member</label>
						<hr />
						<div class="row center-myBranches-up">
							<sf:form method="post" action="/branch/${ currentBranch.branchName }/member/invite">
								<div class="col-xs-4">
									<input type="text"  class="form-control" placeholder="ID Name" name="username"/>
								</div>
								<div class="col-xs-4">
									<input type="text"  class="form-control" placeholder="Invite Message" name="message"/>
								</div>
								<div class="col-xs-2">
									<input type="submit" class="btn btn-default" value="Invite"/>
								</div>
							</sf:form>
						</div>
						
						<div class="row">
						<div class="col-xs-8">
							<label>All Member</label>
							<div class="row">
								<c:forEach items="${ page.content }" var="member">
									<div class="col-xs-6 branchMember-li">
										<div class="row">
											<div class="col-xs-3">
												<img class="img-circle center-person-picture" src="<c:url value='/picture/user/${member.user.username}'/>?pid=${member.user.picture}" />
											</div>
											<div class="col-xs-6 branchMember-li-message">
												<a href="#">${ member.memberName }</a> <small>${ member.branchRole.roleName }</small><br/>
												<p class="hide-p">${ member.user.introduction }</p>
											</div>
											<div class="col-xs-3">
												<br /><br />
												<button class="btn btn-success">Add Friend</button>
											</div>
										</div>
									</div>
								</c:forEach>
								</div>
							</div>
							<div class="col-xs-3 col-xs-offset-1 friend-request">
								<label>Invite Request</label>
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
							</div>
						</div>
					</div>
					<c:if test="${page.totalPages >= 1}">
						<div class="row">
							<div class="col-xs-8">
								<div style="float: right;">
									<ul class="pagination" >
										<li><a href="<c:url value="/user/me?tab=friends&page=0&size=3"/>">&laquo;</a></li>
										<c:if test="${page.totalPages <= 5}">
											<c:forEach begin="1" end="${page.totalPages}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/user/me?tab=friends&page=${p-1}&size=3"/>">${p}</a></li>
											</c:forEach>
										</c:if>
										<c:if test="${page.totalPages > 5}">
											<c:if test="${page.number - 2 <= 0}">
												<c:forEach begin="1" end="5" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/user/me?tab=friends&page=${p-1}&size=3"/>">${p}</a></li>
												</c:forEach>
											</c:if>
											<c:if test="${page.number -2 > 0 && page.totalPages - page.number > 3}">
												<c:forEach begin="${page.number - 1}" end="${page.number + 3}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/user/me?tab=friends&page=${p-1}&size=3"/>">${p}</a></li>
												</c:forEach>
											</c:if>
											<c:if test="${page.totalPages - page.number <= 3}">
												<c:forEach begin="${page.number - 3}" end="${page.totalPages}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/user/me?tab=friends&page=${p-1}&size=3"/>">${p}</a></li>
												</c:forEach>
											</c:if>
										</c:if>
										<li><a href="<c:url value="/user/me?tab=friends&page=${page.totalPages-1}&size=3"/>">&raquo;</a></li>
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