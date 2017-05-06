<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BranchMessage</title>
	<%@ include file="../head.jsp" %>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="branchleft.jsp" %>
				<div class="col-xs-7 col-xs-offset-2">
					<div class="center-branche-panel">
						<%@ include file="../message.jsp" %>
						<label>Message Center</label>
						<hr />
						<div class="row center-myBranches-up">
							<form action="#">
								<div class="col-xs-4">
									<input type="text"  class="form-control" placeholder="Message's Title"/>
								</div>
								<div class="col-xs-1">
									<input type="submit" class="btn btn-default" value="Search"/>
								</div>
							</form>
							<div class="col-xs-1">
								<a href="<c:url value="/branch/${currentBranch.branchName}/message/new"/>"><button class="btn btn-success">Create New Message</button></a>
							</div>
						</div>
						<label>Official Message</label>
						<c:forEach items="${ page.content }" var="branchMessage">
						<div class="row center-branch-message-li">
							<div class="col-xs-8">
								<p class="hide-p">
								 <c:if test="${ branchMessage.messageTag != null }">[${ branchMessage.messageTag.tagName }]</c:if><c:if test="${ branchMessage.messageTag == null }">[All]</c:if><a href="<c:url value="/branch/${ currentBranch.branchName }/message/${ branchMessage.id }"/>">${ branchMessage.title }</a>
								</p>
							</div>
							<div class="col-xs-3">
								<i class="fa fa-calendar" aria-hidden="true"></i><font> ${ branchMessage.getFormatTime() }</font>
							</div>
							<div class="col-xs-1">
								<a href="<c:url value="/branch/${ currentBranch.branchName }/message/${ branchMessage.id }"/>"><button class="btn btn-info">Read</button></a>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
