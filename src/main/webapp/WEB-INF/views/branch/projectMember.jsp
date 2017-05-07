<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Project Member</title>
	<%@ include file="../head.jsp" %>
</head>
<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<!-- left -->
				<%@ include file="branchleft.jsp" %>
				<!-- center -->
				<div class="col-xs-10 col-xs-offset-2 ">
					<div class="center-branche-panel">
					<%@ include file="../message.jsp" %>
						<label>Project Member</label>
						<hr />
						<div class="row center-myBranches-up">
							<sf:form method="post" action="">
								<div class="col-xs-4">
									<input type="text"  class="form-control" placeholder="ID Name" name="username"/>
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
								<c:forEach items="${ currentProject.projectMember }" var="member">
									<div class="col-xs-6 branchMember-li">
										<div class="row">
											<div class="col-xs-3">
												<img class="img-circle center-person-picture" src="<c:url value='/picture/user/${member.user.username}'/>?pid=${member.user.picture}" />
											</div>
											<sf:form method="post" action="/branch/${ currentBranch.branchName }/member/role">
											<div class="col-xs-6 branchMember-li-message">
												<div class="row">
													<div class="col-xs-5" style="padding-right: 0px">
														<p class="hide-p username-p"><a href="#">${ member.memberName }</a></p>
													</div>
												</div>
												<div class="row" style="margin-top: 5px">
													&nbsp;&nbsp;&nbsp;&nbsp;<small class="hide-p">${ member.user.introduction }</small>
												</div>
											</div>
											<div class="col-xs-3">
												<br /><br />
												<button class="btn btn-success">Delete</button>
											</div>
											</sf:form>
										</div>
									</div>
								</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>