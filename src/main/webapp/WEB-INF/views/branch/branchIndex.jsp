<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Branch</title>
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
			<!-- left -->
			<%@ include file="branchleft.jsp" %>
			<!-- center -->
			<!-- Right -->
		</div>
		<!-- Center -->
		<div class="row">
			<div class="col-lg-7 col-md-10 col-sm-10 col-sm-offset-2">
				<div class="row">
					<div class="center-branche-panel">
						<div class="panel panel-default">
						    <div class="panel-body panel-nopadding" style="padding-right: 0px;">
					        	<div class="col-sm-3 text-center branch-left">
					        		<br/>
					        		<img src="<c:url value="/picture/branch/${currentBranch.branchName}?pid=${currentBranch.picture}"/>"  class="branch-img-small"/><br />
					        		<p class="h3">Welcome to<br/><a>${currentBranch.branchName}</a></p>
					        	</div>
					        	<div class="col-sm-6 branch-left">
					        		<label class="label label-color">Message</label><br />
					        		<p class="hide-p branch-message-p">
										<i class="fa fa-tags" aria-hidden="true"></i> 
										<a href="#">We well have a party in the 2016-11-23，welcome to join us!</a>
									</p>
									<p class="hide-p branch-message-p">
										<i class="fa fa-tags" aria-hidden="true"></i> 
										<a href="#">We well have a party in the 2016-11-23，welcome to join us!</a>
									</p>
									<p class="hide-p branch-message-p">
										<i class="fa fa-tags" aria-hidden="true"></i> 
										<a href="#">We well have a party in the 2016-11-23，welcome to join us!</a>
									</p>
									<p class="hide-p branch-message-p">
										<i class="fa fa-tags" aria-hidden="true"></i> 
										<a href="#">We well have a party in the 2016-11-23，welcome to join us!</a>
									</p>
									<p class="hide-p branch-message-p">
										<i class="fa fa-tags" aria-hidden="true"></i> 
										<a href="#">We well have a party in the 2016-11-23，welcome to join us!</a>
									</p>
					        	</div>
					        	<div class="col-sm-3 branch-user-id">
					        		<c:if test="${currentBranchMember != null}">
					        		<label class="label label-color">Your Id</label>
					        		<img class="img-circle center-person-picture center-block" src="<c:url value='/picture/user/${username}'/>?pid=${userPicture}"/>
					        		<br />
				        			<div class="row">
				        				<div class="col-sm-offset-1 col-sm-10 user-id hide-p">
				        					<p><i class="fa fa-user" aria-hidden="true"></i> &nbsp;&nbsp;&nbsp;<a>${currentBranchMember.memberName}</a></p>
				        					<p><i class="fa fa-envelope" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<font>${currentBranchMember.email}</font></p>
				        					<p><i class="fa fa-shield" aria-hidden="true"></i> &nbsp;&nbsp;&nbsp;<font>${currentBranchMember.branchRole.roleName}</font></p>
				        					<p class="hide-p"><i class="fa fa-comment" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<font>${currentUser.introduction}</font></p></p>
				        				</div>
				        			</div>
				        			</c:if>
					        	</div>
					        </div>
					    </div>
					</div>
				</div>
				<div class="row">
					<div class="center-branche-panel" style="margin-top: 0px;">
						<div class="panel panel-default">
						    <div class="panel-body panel-nopadding">
						    	<label class="label label-color">Forum</label>
						    	<form>
						    	<div class="row">
						    		<div class="col-sm-9 col-md-9">
						    			<input class="form-control" placeholder="Say something today!" />
						    		</div>
						    		<div class="col-sm-2 col-md-2">
						    			<input type="submit" class="submit" style="height: 34px;" value="publish"/>
						    		</div>
						    	</div>
						    	</form>
						    	<br/>
						    	<div style="position:relative; height:200px; overflow-x:auto">
							    	<div class="row forum-li" >
							    		<div class="col-sm-1 text-center" >
							    			<img class="img-circle heard-profile-picture" src="img/profile.jpg" /><br />
							    		</div>
							    		<div class="col-sm-9">
							    			<a href="#">ZhongHao</a> &nbsp;<small>2016-11-26</small>
							    			<div style="float: right; color: #888888;">
							    				<i class="fa fa-comment" aria-hidden="true"></i><i class="fa fa-eye" aria-hidden="true"></i><i class="fa fa-thumbs-up" aria-hidden="true"></i> 20
							    			</div>
							    			<p class="h4 hide-p">I'm very happy today!!!Plase give some power!!</p>
							    		</div>
							    	</div>
							    	<div class="row forum-li" >
							    		<div class="col-sm-1 text-center" >
							    			<img class="img-circle heard-profile-picture" src="img/profile.jpg" /><br />
							    		</div>
							    		<div class="col-sm-9">
							    			<a href="#">ZhongHao</a> &nbsp;<small>2016-11-26</small>
							    			<div style="float: right; color: #888888;">
							    				<i class="fa fa-comment" aria-hidden="true"></i><i class="fa fa-eye" aria-hidden="true"></i><i class="fa fa-thumbs-up" aria-hidden="true"></i> 20
							    			</div>
							    			<p class="h4 hide-p">I'm very happy today!!!Plase give some power!!</p>
							    		</div>
							    	</div>
							    	<div class="row forum-li" >
							    		<div class="col-sm-1 text-center" >
							    			<img class="img-circle heard-profile-picture" src="img/profile.jpg" /><br />
							    		</div>
							    		<div class="col-sm-9">
							    			<a href="#">ZhongHao</a> &nbsp;<small>2016-11-26</small>
							    			<div style="float: right; color: #888888;">
							    				<i class="fa fa-comment" aria-hidden="true"></i><i class="fa fa-eye" aria-hidden="true"></i><i class="fa fa-thumbs-up" aria-hidden="true"></i> 20
							    			</div>
							    			<p class="h4 hide-p">I'm very happy today!!!Plase give some power!!</p>
							    		</div>
							    	</div>
							    	<div class="row forum-li" >
							    		<div class="col-sm-1 text-center" >
							    			<img class="img-circle heard-profile-picture" src="img/profile.jpg" /><br />
							    		</div>
							    		<div class="col-sm-9">
							    			<a href="#">ZhongHao</a> &nbsp;<small>2016-11-26</small>
							    			<div style="float: right; color: #888888;">
							    				<i class="fa fa-comment" aria-hidden="true"></i><i class="fa fa-eye" aria-hidden="true"></i><i class="fa fa-thumbs-up" aria-hidden="true"></i> 20
							    			</div>
							    			<p class="h4 hide-p">I'm very happy today!!!Plase give some power!!</p>
							    		</div>
							    	</div>
							    	<div class="row forum-li" >
							    		<div class="col-sm-1 text-center" >
							    			<img class="img-circle heard-profile-picture" src="img/profile.jpg" /><br />
							    		</div>
							    		<div class="col-sm-9">
							    			<a href="#">ZhongHao</a> &nbsp;<small>2016-11-26</small>
							    			<div style="float: right; color: #888888;">
							    				<i class="fa fa-comment" aria-hidden="true"></i><i class="fa fa-eye" aria-hidden="true"></i><i class="fa fa-thumbs-up" aria-hidden="true"></i> 20
							    			</div>
							    			<p class="h4 hide-p">I'm very happy today!!!Plase give some power!!</p>
							    		</div>
							    	</div>
						    	</div>
					        </div>
					    </div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
