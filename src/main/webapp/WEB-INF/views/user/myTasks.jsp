<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Profile</title>
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
			<%@ include file="userleft.jsp" %>
		</div>
		<!-- Center -->
		<div class="row">
			<div class="col-md-10 col-sm-10 col-sm-offset-2">
				<div class="center-myBranches center-myTasks">
					<label>Tasks</label>
					<hr />
					<div class="row center-myTasks-up">
						<div class="col-sm-5">
							<ul class="nav nav-pills">
								<li class="active"><a>All <span class="badge">20</span></a></li>
								<li ><a href="#">TO DO <span class="badge">3</span></a></li>
								<li><a href="#">Urgent <span class="badge">10</span></a></li>
								<li><a href="#">Finished <span class="badge">3</span></a></li>
							</ul>
						</div>
						<div class="col-sm-7">
							<div class="row">
								<form>
									<div class="col-sm-4">
										<input type="text"  class="form-control" placeholder="Task's keywords"/>
									</div>
									<div class="col-sm-5">
										<div class="input-group">
										<div class="input-group-addon">Branches</div>
										<select class="form-control">
											<option>All</option>
											<option>LongMaoShe</option>
											<option>Computer</option>
										</select>
										</div>
									</div>
									<div class="col-sm-2">
										<input type="submit" class="btn btn-default" value="Search"/>
									</div>
								</form>
							</div>
						</div>
					</div>
					<label>All Tasks</label>
					<div class="row center-tasks-li">
						<div class="col-sm-8 hide-p">
							<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
							<p class="hide-p">You have to go to the shopping mall and buy someting use to decorate party!</p>
							<div class="center-tasks-li-message">
								<p><font>Branche:</font><a href="#">LongMaoShe</a> <font>Publisher:</font><a href="#">ZhongHao</a><font>Start:</font><mark>2016-10-1</mark><font>End:</font><mark>2016-10-30</mark></p>
							</div>
						</div>
						<div class="col-sm-2 col-sm-offset-2">
							<button class="btn btn-success">Finish</button>
							<button class="btn more-message" >More</button>
						</div>
					</div>
					<div class="row center-tasks-li">
						<div class="col-sm-8 hide-p">
							<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
							<p class="hide-p">You have to go to the shopping mall and buy someting use to decorate party!</p>
							<div class="center-tasks-li-message">
								<p><font>Branche:</font><a href="#">LongMaoShe</a> <font>Publisher:</font><a href="#">ZhongHao</a><font>Start:</font><mark>2016-10-1</mark><font>End:</font><mark>2016-10-30</mark></p>
							</div>
						</div>
						<div class="col-sm-2 col-sm-offset-2">
							<button class="btn btn-success">Finish</button>
							<button class="btn more-message">More</button>
						</div>
					</div>
					<div class="row center-tasks-li">
						<div class="col-sm-8 hide-p">
							<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
							<p class="hide-p">You have to go to the shopping mall and buy someting use to decorate party!</p>
							<div class="center-tasks-li-message">
								<p><font>Branche:</font><a href="#">LongMaoShe</a> <font>Publisher:</font><a href="#">ZhongHao</a><font>Start:</font><mark>2016-10-1</mark><font>End:</font><mark>2016-10-30</mark></p>
							</div>
						</div>
						<div class="col-sm-2 col-sm-offset-2">
							<button class="btn btn-success">Finish</button>
							<button class="btn more-message">More</button>
						</div>
					</div>
					<div class="row center-tasks-li">
						<div class="col-sm-8 hide-p">
							<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
							<p class="hide-p">You have to go to the shopping mall and buy someting use to decorate party!</p>
							<div class="center-tasks-li-message">
								<p><font>Branche:</font><a href="#">LongMaoShe</a> <font>Publisher:</font><a href="#">ZhongHao</a><font>Start:</font><mark>2016-10-1</mark><font>End:</font><mark>2016-10-30</mark></p>
							</div>
						</div>
						<div class="col-sm-2 col-sm-offset-2">
							<button class="btn btn-success">Finish</button>
							<button class="btn more-message">More</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
