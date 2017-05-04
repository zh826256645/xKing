<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>发布信息</title>
	<%@ include file="../head.jsp" %>
	<style>
	    .markdown-body {
	        box-sizing: border-box;
	        min-width: 200px;
	        max-width: 980px;
	        margin: 0 auto;
	        padding: 45px;
	    }
	</style>
</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="branchleft.jsp" %>
				<div class="col-xs-7">
					<div class="center-branche-panel">
						<%@ include file="../message.jsp" %>
						<label>Create Branch's Message</label>
						<hr />
						<label>Title</label>
						<sf:form action="" method="post" commandName="branchMessage">
						<div class="row center-myBranches-up" style="border-bottom: 0px !important;">
							<div class="col-xs-6">
								<input type="text"  class="form-control" placeholder="Message Title" name="title"/>
							</div>
							<div class="col-xs-3">
								<div class="input-group">
								<div class="input-group-addon">Type:</div>
								<select class="form-control" name="messageTag">
									<option value="" selected="selected">All</option>
								<c:forEach items="${ messageTags }" var="tag">
									<option value="${ tag.tagName }">${ tag.tagName }</option>
								</c:forEach>
								</select>
								</div>
							</div>		
						</div>
						<label>Message</label>
						<div class="row">
							<div class="col-xs-11">
								<div class="message-panel">
								<ul id="myTab" class="nav nav-tabs" style="padding-left: 20px;">
									
								   <li class="active">
								      <a href="#home" data-toggle="tab">
								         Write
								      </a>
								   </li>
								   <li>
								   		<a href="#ios" data-toggle="tab" onclick="editor()">View</a>
								   </li>
								</ul>
								</div>
								<div id="myTabContent" class="tab-content tab-control">
								   <div class="tab-pane fade in active" id="home">
									  <br />
								      <textarea id="text-input" rows="13" class="form-control messge-textarea" name="messageContent"></textarea>
								   </div>
								   <div  class="tab-pane fade" id="ios">
								     <div >
								     	<article class="markdown-body" id="preview">
										</article>
								     </div>
								   </div>  
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-1 col-xs-offset-10">
								<input type="submit" value="sumbit" class="btn btn-info">
							</div>
						</div>
						</sf:form>
					</div>
				</div>
				<div class="col-xs-2 center-branche-panel center-profile-setting-right" >
					<label>Message Tag</label>
					<hr/>
					<div class="row add-role">
						<sf:form action="/branch/${ currentBranch.branchName }/message/tag/new" method="post">
							<div class="col-xs-9" style="padding-left: 22px;">
								<input type="text"  class="form-control" placeholder="Tag name" name="tagName"/>
							</div>
							<div class="col-xs-2">
								<input type="submit" class="btn btn-success" value="Add"/>
							</div>
						</sf:form>
					</div>
					<br />
					<c:forEach items="${ messageTags }" var="tag">
					<div class="row role-li" >
							<div class="col-xs-5" style="padding-left: 0px;"><p class="hide-p" style="font-size: 16px;"><i class="fa fa-tag" aria-hidden="true" style="font-size: 14px;margin-right: 5px;"></i>${ tag.tagName }</p></div>
							<div class="col-xs-2"><i class="fa fa-edit role-font" style="color: #67B168;cursor: pointer;" aria-hidden="true" onclick="changeRole(this)"></i></div>
							<div class="col-xs-2"><i class="fa fa-times role-font" style="color: #D9534F;" aria-hidden="true"></i></div>
					</div>
					<div class="row add-role" style="display: none;">
						<form>
							<div class="col-xs-4" style="padding-left: 22px;">
								<input type="text"  class="form-control" placeholder="Role name"/>
							</div>
							<div class="col-xs-3">
								<input type="text"  class="form-control" placeholder="level"/>
							</div>
							<div class="col-xs-2">
								<input type="submit" class="btn btn-success" value="YES"/>
							</div>
						</form>
							<div class="col-xs-2" style="margin-left: 4px;">
								<button class="btn btn-warning" onclick="noChangeRole(this)">No</button>
							</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<script>
	      function editor() {
		      	document.getElementById('preview').innerHTML = "";
				var text = $("#text-input").val();
				document.getElementById('preview').innerHTML = (marked(text));
		      }
	    </script>
	</body>
</html>