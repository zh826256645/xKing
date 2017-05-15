<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
				<div class="col-xs-10 col-xs-offset-2">
					<div class="row">
						<div class="col-xs-12">
							<div class="center-myBranches center-myTasks">
								<%@ include file="../message.jsp" %>
								<label>我的好友</label>
								<hr />
								<div class="row center-myBranches-up">
									<form action="#">
										<div class="col-xs-2">
											<input type="text"  class="form-control" id="friendUsername" placeholder="用户名"/>
										</div>
									</form>
										<div class="col-xs-1" style="padding-left: 0px">
											<button class="btn btn-success" style="height: 32px;margin-top: -15px" id="addFriend">添加好友</button>
										</div>
								</div>
								<div class="row">
									<div class="col-xs-8">
										<label>所有好友</label>
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
												<div class="row">
													<div class="col-xs-3">
														<span style="display: none;">${ friend.username }</span>
														<button class="btn btn-success" onclick="friendMessage(this)">信息</button>
													</div>
													<div class="col-xs-6">
														<sf:form action="/user/friend/remove" method="post">
														<input type="hidden" name="username" value="${ friend.username }">
														<button class="btn btn-danger">删除</button>
														</sf:form>
													</div>
												</div>
											</div>
										</div>
										</c:forEach>
										<c:if test="${ page.content == null || page.content.size() == 0 }">
											<div class="empty">
												没有添加任何好友
											</div>
										</c:if>
									</div>
									<div class="col-xs-3 col-xs-offset-1 friend-request">
										<label>好友请求</label>
										<c:forEach  items="${reuqestPage.content}" var="userFriend">
											<c:set value="${userFriend.user }" var="friend"/>
											<div class="row center-friends-li friend-request-li">
											<div class="col-xs-3">
												<a class="popover-show" data-toggle="popover" data-placement="bottom" onmouseover="showUserInfo(this, '${friend.username}')"><img src="<c:url value='/picture/user/${friend.username}'/>?pid=${friend.picture}" class="img-circle heard-profile-picture"></a>
												<div style="display: none;" id="friend_username"><a>${friend.username}</a></div>
											</div>
											<div class="col-xs-9">
												<button class="btn" onclick="friendRequest(this, 1, '${friend.username}')">同意</button>
												<button class="btn btn-danger" onclick="friendRequest(this, 2, '${friend.username}')">拒绝</button>
											</div>
										</div>
										</c:forEach>
										<c:if test="${ reuqestPage.content == null || reuqestPage.content.size() == 0 }">
										<div class="right-empty">没有好友请求</div>
										</c:if>
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
				<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel" id="friendMessage">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title" id="gridSystemModalLabel"></h4>
				      </div>
				      <div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								
								<div class="panel panel-default">
								    <div class="panel-body panel-nopadding">
								    	<br />
								    	<div class="row">
								    		<div class="col-sm-9 col-md-9">
								    			<input class="hidden" id="messageUsername" name="username"/>
								    			<input class="form-control" id="messageContent" name="content"/>
								    		</div>
								    		<div class="col-sm-2 col-md-2">
								    			<input type="submit" class="submit" style="height: 34px;" value="发送" id="sendMessage"/>
								    		</div>
								    	</div>
								    	<br/>
								    	<div style="position:relative; height:200px; overflow-x:auto"  id="messagWindow">
								    	</div>`
							        </div>
						   		 </div>

							</div>
						</div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				      </div>
				    </div>
				  </div>
				</div>
				</div>
		 	</div>
		</div>
	</body>
</html>
