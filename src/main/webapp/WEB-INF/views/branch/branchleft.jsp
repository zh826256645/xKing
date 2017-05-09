<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="left-sidebar" id="branchLeft" style="width: 310px;">
	<div class="left-branch-message" align="center">
		<img src="<c:url value="/picture/branch/${currentBranch.branchName}?pid=${currentBranch.picture}"/>" class="branch-img" /><br />
		<p class="h1 hide-p">${currentBranch.branchName}</p>
		<p class="hide-p">${currentBranch.intro}</p>
		<p><i class="fa fa-user" aria-hidden="true"></i>&nbsp;${currentBranch.memberNum }&nbsp;&nbsp;&nbsp;&nbsp;
			<i class="fa fa-level-up" aria-hidden="true"></i>&nbsp;13</p>
	</div>
	<ul class="nav nav-sidebar left-branch-ul">
		<li id="overview" 
			<c:if test="${tab == 'index'}">
			class="active"
			</c:if>
		>
			<a id="overview" href="<c:url value="/branch/${currentBranch.branchName}" />"><s:message code="branchLeft.index"/></a>
		</li>
		<li id="branches"
			<c:if test="${tab == 'message'}">
			class="active"
			</c:if>
		 >
			<a id="branches" href="<c:url value="/branch/${currentBranch.branchName}/message?page=0&size=10" />"><s:message code="branchLeft.message"/></a>
		</li>
		<li id="tasks"  <c:if test="${tab == 'member'}">
			class="active"
			</c:if>>
			<a id="tasks" href="<c:url value="/branch/${currentBranch.branchName}/member?page=0&size=10" />"><s:message code="branchLeft.member"/></a>
		</li>
		<li id="tasks" <c:if test="${tab == 'project'}">
					class="active"
			</c:if>>
			<a id="tasks" href="<c:url value="/branch/${currentBranch.branchName}/project?page=0&size=10" />"><s:message code="branchLeft.project"/></a>
		</li>
	</ul>
	<c:if test="${ currentProject != null }">
	<div style="background-color: #428bca;margin-top: 7px; padding-top: 1px; padding-bottom: 10px;">
		<div class="left-branch-message" align="center">
			<p class="h3 hide-p">${ currentProject.projectName }</p>
		</div>
		<ul class="nav nav-sidebar">
			<li id="overview" <c:if test="${ tag == 'projectMember' }">class="project-active"</c:if>>
				<a id="overview" href="<c:url value="/branch/${ currentBranch.branchName }/project/${ currentProject.projectName }/member"/>" class="left-branch-ul-li"><s:message code="branchLeft.projectMember"/></a>
			</li>
			<li id="branches" >
				<a id="branches" href="BranchMessage.html" class="left-branch-ul-li"><s:message code="branchLeft.task"/></a>
			</li>
		</ul>
	</div>
	</c:if>
</div>