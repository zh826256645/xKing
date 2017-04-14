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
		<%@ include file="../header.jsp" %>
		<div class="row">
			<!-- left -->
			<%@ include file="branchleft.jsp" %>
			<!-- center -->

			<!-- Right -->

		</div>
		<!-- Center -->
		<div class="row">
			<div class="col-sm-10 col-sm-offset-2">
				<div class="center-branche-panel">
					<label>Create Branch's Message</label>
					<hr />
					<label>Title</label>
					<div class="row center-myBranches-up" style="border-bottom: 0px !important;">
						<form action="#">
							<div class="col-sm-6">
								<input type="text"  class="form-control" placeholder="Message Title"/>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
								<div class="input-group-addon">Type:</div>
								<select class="form-control">
									<option>All</option>
									<option>School</option>
									<option>Association</option>
									<option>Company</option>
								</select>
								</div>
							</div>
						</form>
					</div>
					<label>Message</label>
					<div class="row">
						<div class="col-md-8">
								<div class="message-panel">
								<ul id="myTab" class="nav nav-tabs" style="padding-left: 20px;">
									
								   <li class="active">
								      <a href="#home" data-toggle="tab">
								         Write
								      </a>
								   </li>
								   <li><a href="#ios" data-toggle="tab">View</a></li>
								</ul>
								</div>
								<div id="myTabContent" class="tab-content tab-control">
								   <div class="tab-pane fade in active" id="home">
									  <br />
								      <textarea id="text-input" rows="13" class="form-control messge-textarea"  oninput="this.editor.update()">#I'm very happy today!</textarea>
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
				</div>
			</div>
		</div>
		<script>
	      function Editor(input, preview) {
	        this.update = function () {
	          preview.innerHTML = markdown.toHTML(input.value);
	        };
	        input.editor = this;
	        this.update();
	      }
	      var $ = function (id) { return document.getElementById(id); };
	      new Editor($("text-input"), $("preview"));
	    </script>
	</body>
</html>