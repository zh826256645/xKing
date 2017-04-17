<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Profile</title>
		<%@ include file="../head.jsp" %>
	</head>
	<body>
		<div class="container">
			<%@ include file="../header.jsp" %>
			<div class="row">
				<%@ include file="userleft.jsp" %>
				<div class="col-md-10">
					<div class="center-myBranches center-myTasks">
						<label>Tasks</label>
						<hr />
						<div class="row center-myTasks-up">
							<div class="col-xs-5">
								<ul class="nav nav-pills">
									<li class="active"><a>All <span class="badge">20</span></a></li>
									<li ><a href="#">TO DO <span class="badge">3</span></a></li>
									<li><a href="#">Urgent <span class="badge">10</span></a></li>
									<li><a href="#">Finished <span class="badge">3</span></a></li>
								</ul>
							</div>
							<div class="col-xs-7">
								<div class="row">
									<form>
										<div class="col-xs-4">
											<input type="text"  class="form-control" placeholder="Task's keywords"/>
										</div>
										<div class="col-xs-5">
											<div class="input-group">
											<div class="input-group-addon">Branches</div>
											<select class="form-control">
												<option>All</option>
												<option>LongMaoShe</option>
												<option>Computer</option>
											</select>
											</div>
										</div>
										<div class="col-xs-2">
											<input type="submit" class="btn btn-default" value="Search"/>
										</div>
									</form>
								</div>
							</div>
						</div>
						<label>All Tasks</label>
						<div class="row center-tasks-li">
							<div class="col-xs-8 hide-p">
								<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
								<p class="hide-p">You have to go to the shopping mall and buy someting use to decorate party!</p>
								<div class="center-tasks-li-message">
									<p><font>Branche:</font><a href="#">LongMaoShe</a> <font>Publisher:</font><a href="#">ZhongHao</a><font>Start:</font><mark>2016-10-1</mark><font>End:</font><mark>2016-10-30</mark></p>
								</div>
							</div>
							<div class="col-xs-2 col-xs-offset-2">
								<button class="btn btn-success">Finish</button>
								<button class="btn more-message" >More</button>
							</div>
						</div>
						<div class="row center-tasks-li">
							<div class="col-xs-8 hide-p">
								<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
								<p class="hide-p">You have to go to the shopping mall and buy someting use to decorate party!</p>
								<div class="center-tasks-li-message">
									<p><font>Branche:</font><a href="#">LongMaoShe</a> <font>Publisher:</font><a href="#">ZhongHao</a><font>Start:</font><mark>2016-10-1</mark><font>End:</font><mark>2016-10-30</mark></p>
								</div>
							</div>
							<div class="col-xs-2 col-xs-offset-2">
								<button class="btn btn-success">Finish</button>
								<button class="btn more-message">More</button>
							</div>
						</div>
						<div class="row center-tasks-li">
							<div class="col-xs-8 hide-p">
								<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
								<p class="hide-p">You have to go to the shopping mall and buy someting use to decorate party!</p>
								<div class="center-tasks-li-message">
									<p><font>Branche:</font><a href="#">LongMaoShe</a> <font>Publisher:</font><a href="#">ZhongHao</a><font>Start:</font><mark>2016-10-1</mark><font>End:</font><mark>2016-10-30</mark></p>
								</div>
							</div>
							<div class="col-xs-2 col-xs-offset-2">
								<button class="btn btn-success">Finish</button>
								<button class="btn more-message">More</button>
							</div>
						</div>
						<div class="row center-tasks-li">
							<div class="col-xs-8 hide-p">
								<a><i class="fa fa-link" aria-hidden="true"></i></a>&nbsp;<a href="#">Go to buy something use to decorate party!</a>
								<p class="hide-p">You have to go to the shopping mall and buy someting use to decorate party!</p>
								<div class="center-tasks-li-message">
									<p><font>Branche:</font><a href="#">LongMaoShe</a> <font>Publisher:</font><a href="#">ZhongHao</a><font>Start:</font><mark>2016-10-1</mark><font>End:</font><mark>2016-10-30</mark></p>
								</div>
							</div>
							<div class="col-xs-2 col-xs-offset-2">
								<button class="btn btn-success">Finish</button>
								<button class="btn more-message">More</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
