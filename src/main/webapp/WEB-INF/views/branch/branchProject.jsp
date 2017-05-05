<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Branch Project</title>
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
						<label>Project</label>
						<hr />
						<div class="row center-myBranches-up">
							<sf:form action="" method="post">
								<div class="col-xs-4">
									<input type="text"  class="form-control" placeholder="Project Name" name="projectName"/>
								</div>
								<div class="col-xs-2">
									<input type="submit" class="btn btn-default" value="Add"/>
								</div>
							</sf:form>
						</div>
						<label>Branch Project</label>
						<c:forEach items="${ page.content }" var="project">
						<div class="row project-li">
							<div class="col-xs-3">
								<p class="hide-p project-name"><a>${ project.projectName }</a></p>
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
								<button class="btn btn-info">Into</button>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
