<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-2 left-sidebar" id="branchLeft">
	<div class="left-branch-message" align="center">
		<img src="<c:url value="/picture/branch/${currentBranch.branchName}?pid=${currentBranch.picture}"/>" class="branch-img visible-lg" /><br />
		<p class="h1 hide-p">${currentBranch.branchName}</p>
		<p class="hide-p">${currentBranch.intro}</p>
		<p><i class="fa fa-user" aria-hidden="true"></i>&nbsp;100&nbsp;&nbsp;&nbsp;&nbsp;
			<i class="fa fa-level-up" aria-hidden="true"></i>&nbsp;13</p>
	</div>
	<ul class="nav nav-sidebar left-branch-ul">
		<li id="overview" class="active">
			<a id="overview" href="<c:url value="/branch/${currentBranch.branchName}" />">Index</a>
		</li>
		<li id="branches" >
			<a id="branches" href="BranchMessage.html">Message</a>
		</li>
		<li id="tasks" >
			<a id="tasks" href="BranchMember.html">Members</a>
		</li>
		<li id="tasks">
			<a id="tasks" href="#">Tasks</a>
		</li>
	</ul>
</div>