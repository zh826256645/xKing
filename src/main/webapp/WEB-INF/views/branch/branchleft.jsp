<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-2 left-sidebar" id="branchLeft">
	<div class="left-branch-message" align="center">
		<img src="<c:url value="/picture/branch/${currentBranch.branchName}?pid=${currentBranch.picture}"/>" class="branch-img" /><br />
		<p class="h1 hide-p">${currentBranch.branchName}</p>
		<p class="hide-p">${currentBranch.intro}</p>
		<p><i class="fa fa-user" aria-hidden="true"></i>&nbsp;100&nbsp;&nbsp;&nbsp;&nbsp;
			<i class="fa fa-level-up" aria-hidden="true"></i>&nbsp;13</p>
	</div>
	<ul class="nav nav-sidebar left-branch-ul">
		<li id="overview" 
			<c:if test="${tab == 'index'}">
			class="active"
			</c:if>
		>
			<a id="overview" href="<c:url value="/branch/${currentBranch.branchName}" />">Index</a>
		</li>
		<li id="branches"
			<c:if test="${tab == 'message'}">
			class="active"
			</c:if>
		 >
			<a id="branches" href="<c:url value="/branch/${currentBranch.branchName}/message" />">Message</a>
		</li>
		<li id="tasks"  <c:if test="${tab == 'member'}">
			class="active"
			</c:if>>
			<a id="tasks" href="<c:url value="/branch/${currentBranch.branchName}/member?page=0&size=10" />">Members</a>
		</li>
		<li id="tasks">
			<a id="tasks" href="#">Tasks</a>
		</li>
	</ul>
</div>