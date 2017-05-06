<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../head.jsp" %>
<title>Message Page</title>
</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<!-- left -->
				<%@ include file="branchleft.jsp" %>
				<!-- center -->
	
				<!-- Right -->
			<!-- Center -->
				<div class="col-xs-7 col-xs-offset-2">
					<div class="center-branche-panel">
						<%@ include file="../message.jsp" %>
						<label>Message</label>
						<hr />
						<div class="row">
							<div class="col-xs-10">
								<div class="row">
									<div class="col-xs-9 col-xs-offset-1">
										<h2 style="font-weight: 800; font-size: 35px;">${ branchMessage.title }</h2>
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-xs-9 col-xs-offset-1">
										<div class="row">
											<div class="col-xs-1">
												<img src="<c:url value='/picture/user/${ branchMessage.branchMember.user.username}'/>?pid=${branchMessage.branchMember.user.picture}" class="img-circle heard-profile-picture">
											</div>
											<div class="col-xs-5" style="padding-top: 5px;">
												by: <a>${ branchMessage.branchMember.memberName }</a>
												<br />
												<small style="font-size: 14px;color: #888888;">${ branchMessage.getFormatTime2() } &nbsp;&nbsp;评论: ${ commentNum }</small>
											</div>
										</div>
									</div>
								</div>
								<br />
								<br />
								<div class="row">
									<div class="col-xs-9 col-xs-offset-1">
										<textarea style="display: none;" id="branch-message">${ branchMessage.messageContent }</textarea>
										<div id="preview"></div>
									</div>
								</div>
								<br />
								<br />
								<div class="row" >
									<div class="col-xs-9 col-xs-offset-1" style="border-top: 1px solid #EBEBEB;">
										<br />
										<label>评论</label>
										<sf:form action="/branch/${ currentBranch.branchName }/message/${ branchMessage.id }/comment" method="Post">
										<div class="row">
											<div class="col-xs-1">
												<img src="<c:url value='/picture/user/${username}'/>?pid=${userPicture}" class="img-circle heard-profile-picture" style="margin: 0px;">
											</div>
											<div class="col-xs-11">
												<textarea class="form-control" rows="3" name="comment"></textarea>
											</div>
										</div>
										<br />
										<div class="row">
											<div class="col-xs-2 col-xs-offset-10">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-info" value="评论" />
											</div>
										</div>
										</sf:form>
										<br />
										<br />
										<p style="font-size: 18px;font-weight: 600;">${ commentNum } 条评论</p>
									</div>
								</div>
								<div class="row" >
									<div class="col-xs-9 col-xs-offset-1" style="border-top: 1px solid #EBEBEB;">
										
										<c:forEach items="${ comments }" var="comment" varStatus="idx">
										<div class="row">
											<br />
											<div class="col-xs-1">
												<img src="<c:url value='/picture/user/${ comment.user.username }'/>?pid=${ comment.user.picture }" class="img-circle heard-profile-picture" style="margin: 0px;">
											</div>
											<div class="col-xs-4">
												${ comment.user.username }
												<br />
												<small style="font-size: 14px;color: #888888;"> ${ idx.index + 1 } 楼 &nbsp;&nbsp;${ comment.getFormatTime() } </small>
											</div>
										</div>
										<br />
										<div class="row" style="border-bottom: 1px solid #EBEBEB;">
											<div class="col-xs-11">
												<p style="font-size: 16px;font-weight: 500;">${ comment.comment }</p>
											</div>
											<br />
										</div>
										</c:forEach>
										<br/>
										<br/>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
		      	document.getElementById('preview').innerHTML = "";
				var text = $("#branch-message").val();
				document.getElementById('preview').innerHTML = (marked(text));
	    </script>
	</body>
	</html>