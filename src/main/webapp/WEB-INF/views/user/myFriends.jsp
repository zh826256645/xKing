<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Profile</title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="userleft.jsp" %>
				<div class="col-xs-10">
					<div class="row">
						<div class="col-xs-12">
							<div class="center-myBranches center-myTasks">
								<label>Friends</label>
								<hr />
								<div class="row center-myBranches-up">
									<form action="#">
										<div class="col-xs-4">
											<input type="text"  class="form-control" id="friendUsername" placeholder="friend's name"/>
										</div>
										<div class="col-xs-3">
											<div class="input-group">
											<div class="input-group-addon">Type:</div>
											<select class="form-control">
												<option>All</option>
												<option>School</option>
												<option>Association</option>
												<option>Company</option>
											</select>
											</div>
										</div>
										<div class="col-xs-1" style="padding-left: 20px">
											<input type="submit" class="btn btn-default" value="Search"/>
										</div>
									</form>
										<div class="col-xs-1" style="padding-left: 0px">
											<button class="btn btn-success" style="height: 32px;margin-top: -15px" id="addFriend">Add Friend</button>
										</div>
								</div>
								<div class="row">
									<div class="col-xs-8">
										<label>All Friends</label>
										<c:forEach items="${page.content}" var="userFriend">
										<c:if test="${userFriend.user.username != currentUser.username }">
											<c:set value="${userFriend.user }" var="friend"/>
										</c:if>
										<c:if test="${userFriend.friend.username != currentUser.username }">
											<c:set value="${userFriend.friend }" var="friend"/>
										</c:if>
										<div class="row center-friends-li">
											<div class="col-xs-1">
												<img src="<c:url value='/picture/user/${friend.username}'/>?pid=${friend.picture}" class="img-circle heard-profile-picture">
											</div>
											<div class="col-xs-5 center-friends-li-name">
												<a href="#"><c:if test="${friend.name != null || friend.name != '' }">${ friend.name }</c:if><c:if test="${ friend.name == null }">${ friend.username }</c:if></a> <span class="badge friend-message">0</span>
												<p class="p-hide"><i class="fa fa-comment" aria-hidden="true"></i>${ friend.introduction }</p>
											</div>
											<div class="col-xs-3 col-xs-offset-2">
												<button class="btn btn-success">Message</button>
												<button class="btn btn-danger">Delete</button>
											</div>
										</div>
										</c:forEach>
									</div>
									<div class="col-xs-3 col-xs-offset-1 friend-request">
										<label>Friend Request</label>
										<div class="row center-friends-li friend-request-li">
											<div class="col-xs-3">
												<img class="img-circle" src="img/profile.jpg">
											</div>
											<div class="col-xs-9">
												<button class="btn">Agree</button>
												<button class="btn btn-danger">Deny</button>
											</div>
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
