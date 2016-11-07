<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
	<!-- left -->
	<div class="col-sm-2 left-sidebar">
		<ul class="nav nav-sidebar">
			<li id="overview" <c:if test="${tab == 'profile'}"> class="active" </c:if>>
				<a id="overview" href="<c:url value="/user/me"/>"><i class="fa fa-cloud fa-3x" aria-hidden="true"></i><br/>Overview</a>
			</li>
			<li id="branches" <c:if test="${tab == 'branches'}"> class="active" </c:if>>
				<a id="branches" href="<c:url value="/user/me?tab=branches"/>"><i class="fa fa-university fa-3x" aria-hidden="true"></i><br/>Branches</a>
			</li>
			<li id="tasks" <c:if test="${tab == 'tasks'}"> class="active" </c:if>>
				<a id="tasks" href="<c:url value="/user/me?tab=tasks"/>"><i class="fa fa-tasks fa-3x" aria-hidden="true"></i><br/>Tasks</a>
			</li>
			<li id="friendes" <c:if test="${tab == 'friends'}"> class="active" </c:if>>
				<a id="friendes" href="<c:url value="/user/me?tab=friends"/>"><i class="fa fa-users fa-3x" aria-hidden="true"></i><br/>Friendes</a>
			</li>
		</ul>
	</div>