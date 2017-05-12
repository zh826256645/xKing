<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${ currentBranch.branchName }-项目</title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<!-- left -->
				<%@ include file="branchleft.jsp" %>
				<div class="col-xs-7 col-xs-offset-2">
					<div class="center-branche-panel">
						<%@ include file="../message.jsp" %>
						<label>项目</label>
						<hr />
						<div class="row center-myBranches-up">
							<sf:form action="" method="post">
								<div class="col-xs-4">
									<input type="text"  class="form-control" placeholder="项目名" name="projectName"/>
								</div>
								<div class="col-xs-2">
									<input type="submit" class="btn btn-default" value="添加"/>
								</div>
							</sf:form>
						</div>
						<label>所有项目</label>
						<div style="height: 600px">
						<c:forEach items="${ page.content }" var="project">
						<div class="row project-li">
							<div class="col-xs-3">
								<p class="hide-p project-name"><a href="<c:url value="/branch/${ currentBranch.branchName }/project/${ project.projectName }"/>">${ project.projectName }</a></p>
							</div>
							<div class="col-xs-5">
								<div class="row">
									<c:forEach items="${ project.projectMember }" var="member" end="6">
										<img src="<c:url value='/picture/user/${member.user.username}'/>?pid=${member.user.picture}" class="img-circle project-picture">
									</c:forEach>
								</div>
							</div>
							<div class="col-xs-2" style="padding-top: 15px">
								<i class="fa fa-calendar" aria-hidden="true"></i><font> ${ project.getFormatTime() }</font>
							</div>
							<div class="col-xs-2" style="padding-top: 6px">
								<a  href="<c:url value="/branch/${ currentBranch.branchName }/project/${ project.projectName }/member"/>"><button class="btn btn-info">Into</button></a>
							</div>
						</div>
						</c:forEach>
						<c:if test="${ page.content == null || page.content.size() == 0 }">
						<div class="empty">暂无项目信息</div>
						</c:if>
						</div>
					</div>
					<c:if test="${page.totalPages >= 1}">
						<div class="row">
							<div class="col-xs-12">
								<div style="float: right;">
									<ul class="pagination" >
										<li><a href="<c:url value="/branch/${currentBranch.branchName}/project?page=0&size=10"/>">&laquo;</a></li>
										<c:if test="${page.totalPages <= 5}">
											<c:forEach begin="1" end="${page.totalPages}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/project?page=${p-1}&size=12"/>">${p}</a></li>
											</c:forEach>
										</c:if>
										<c:if test="${page.totalPages > 5}">
											<c:if test="${page.number - 2 <= 0}">
												<c:forEach begin="1" end="5" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/project?page=${p-1}&size=12"/>">${p}</a></li>
												</c:forEach>
											</c:if>c:if test="${ page.content == null || page.content.size() == 0 }">
											<c:if test="${page.number -2 > 0 && page.totalPages - page.number > 3}">
												<c:forEach begin="${page.number - 1}" end="${page.number + 3}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/project?page=${p-1}&size=12"/>">${p}</a></li>
												</c:forEach>
											</c:if>
											<c:if test="${page.totalPages - page.number <= 3}">
												<c:forEach begin="${page.number - 3}" end="${page.totalPages}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/project?page=${p-1}&size=12"/>">${p}</a></li>
												</c:forEach>
											</c:if>
										</c:if>
										<li><a href="<c:url value="/branch/${currentBranch.branchName}/project?page=${page.totalPages-1}&size=12"/>">&raquo;</a></li>
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
