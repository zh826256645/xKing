<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${ currentBranch.branchName }-公告</title>
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
						<label>公告中心</label>
						<hr />
						<div class="row center-myBranches-up">
							<form action="#">
								<div class="col-xs-4">
									<input type="text"  class="form-control" placeholder="请输入公告的标题"/>
								</div>
								<div class="col-xs-1">
									<input type="submit" class="btn btn-default" value="搜索"/>
								</div>
							</form>
							<div class="col-xs-1">
								<a href="<c:url value="/branch/${currentBranch.branchName}/message/new"/>"><button class="btn btn-success">创建公告</button></a>
							</div>
						</div>
						<label>官方公告</label>
						<div style="height: 600px">
						<c:forEach items="${ page.content }" var="branchMessage">
						<div class="row center-branch-message-li">
							<div class="col-xs-8" style="padding-top:6px;">
								<p class="hide-p">
								 <c:if test="${ branchMessage.messageTag != null }">[${ branchMessage.messageTag.tagName }]</c:if><c:if test="${ branchMessage.messageTag == null }">[所有]</c:if><a href="<c:url value="/branch/${ currentBranch.branchName }/message/${ branchMessage.id }"/>">${ branchMessage.title }</a>
								</p>
							</div>
							<div class="col-xs-2" style="padding-top:6px;">
								<i class="fa fa-calendar" aria-hidden="true"></i><font> ${ branchMessage.getFormatTime() }</font>
							</div>
							<div class="col-xs-2">
								<a href="<c:url value="/branch/${ currentBranch.branchName }/message/${ branchMessage.id }"/>"><button class="btn btn-default">查看</button></a>
								<a href="<c:url value="/branch/${ currentBranch.branchName }/message/${ branchMessage.id }/change"/>"><button class="btn btn-info">修改</button></a>
							</div>
						</div>
						</c:forEach>
						<c:if test="${ page.content == null || page.content.size() == 0 }">
						<div class="empty">暂无公告信息</div>
						</c:if>
						</div>
					</div>
					<c:if test="${page.totalPages >= 1}">
						<div class="row">
							<div class="col-xs-12">
								<div style="float: right;">
									<ul class="pagination" >
										<li><a href="<c:url value="/branch/${currentBranch.branchName}/message?page=0&size=16"/>">&laquo;</a></li>
										<c:if test="${page.totalPages <= 5}">
											<c:forEach begin="1" end="${page.totalPages}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/message?page=${p-1}&size=12"/>">${p}</a></li>
											</c:forEach>
										</c:if>
										<c:if test="${page.totalPages > 5}">
											<c:if test="${page.number - 2 <= 0}">
												<c:forEach begin="1" end="5" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/message?page=${p-1}&size=12"/>">${p}</a></li>
												</c:forEach>
											</c:if>c:if test="${ page.content == null || page.content.size() == 0 }">
											<c:if test="${page.number -2 > 0 && page.totalPages - page.number > 3}">
												<c:forEach begin="${page.number - 1}" end="${page.number + 3}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/message?page=${p-1}&size=12"/>">${p}</a></li>
												</c:forEach>
											</c:if>
											<c:if test="${page.totalPages - page.number <= 3}">
												<c:forEach begin="${page.number - 3}" end="${page.totalPages}" var="p">
												<li 
												<c:if test="${page.number == p-1}">class="active"</c:if>
												><a href="<c:url value="/branch/${currentBranch.branchName}/message?page=${p-1}&size=12"/>">${p}</a></li>
												</c:forEach>
											</c:if>
										</c:if>
										<li><a href="<c:url value="/branch/${currentBranch.branchName}/message?page=${page.totalPages-1}&size=12"/>">&raquo;</a></li>
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
