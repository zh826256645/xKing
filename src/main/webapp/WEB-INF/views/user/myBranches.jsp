<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Profile</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>"/>
		<script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/profile.js"/>" ></script>
	</head>
	<body>
		<%@ include file="../header.jsp" %>
		<div class="row">
			<%@ include file="userleft.jsp" %>
		</div>
		<!-- Center -->
		<div class="row">
			<div class="col-lg-7 col-md-10 col-sm-10 col-sm-offset-2">
				<div class="center-myBranches">
				<%@ include file="../message.jsp" %>1
					<label>Branches</label>
					<hr />
					<div class="row center-myBranches-up">
						<form action="#">
							<div class="col-sm-4">
								<input type="text"  class="form-control" placeholder="branch's name"/>
							</div>
							<div class="col-sm-3">
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
							<div class="col-sm-2">
								<input type="submit" class="btn btn-default" value="Search"/>
							</div>
						</form>
						<div class="col-sm-2">
							<a href="<c:url value="/branch/new"/>"><button class="btn btn-success">Create New Branche</button></a>
						</div>
					</div>
					<label>All Branches</label>
					<c:forEach items="${page.content}" var="branchMember" >
					<div class="row center-branches-li">
						<div class="col-sm-2">
							<img src="<c:url value="/picture/branch/${branchMember.branch.branchName}?pid=${branchMember.branch.picture}"/>"  class="branch-img-small"/><br />
						</div>
						<div class="col-sm-8 center-branches-li-message">
							<div style="margin-bottom: 10px"><a href="<c:url value="/branch/${branchMember.branch.branchName}"/>">${branchMember.branch.branchName}</a><br/></div>
							<span class="label label-info">Association</span>
							<p class="p-label">Information</p>
							<div class="row center-branches-li-message-body">
								<div class="col-sm-5" style="background-color: #f9fafc;">
									<p>Users:<font>100</font> &nbsp;&nbsp;&nbsp;Active:<font>79</font></p>
									<p>Public Email:<font>${branchMember.branch.email}</font></p>
									<p class="hide-p">Affiche Info:<font>${branchMember.branch.intro}</font></p>
								</div>
								<div class="col-sm-5" style="background-color: #f9fafc;">
									<p>Your name:<font>${branchMember.memberName}</font></p>
									<p>Role:<font>${branchMember.branchRole.roleName}</font>&nbsp;&nbsp;&nbsp;Your Task:<font>4</font></p>
									<p>Your Email:<font>${branchMember.email}</font></p>
								</div>
							</div>
						</div>
						<div class="col-sm-1">
							<a href="<c:url value="/branch/${branchMember.branch.branchName}"/>"><button class="btn btn-info">enter Into</button></a>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<c:if test="${page.totalPages >= 1}">
		<div class="row">
			<div class="col-lg-7 col-md-10 col-sm-10 col-sm-offset-2">
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
	</body>
</html>