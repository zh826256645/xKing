<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<nav class="navbar navbar-default navbar-fixed-top heard" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<!--
             			xKing
                 -->
					<a class="navbar-brand" href="#" style="font-size: 25px;">xKing</a>
				</div>
				<!--
              		左导航栏
                -->
                <div id="navbar" class="navbar-collapse collapse">
                	<ul class="nav navbar-nav">
                		<li class="active">
                			<a href="#">Home</a>
                		</li>
                	</ul>
                  	<form class="navbar-form navbar-left" role="search">
        		   		<div class="form-group">
                			<input type="text" class="form-control" placeholder="Search">
            			</div>
        			</form>
        			<ul class="nav navbar-nav navbar-right">
        				<li> 
							<img src="<c:url value='/img/profile.jpg'/>" class="img-circle heard-profile-picture">
        				</li>
        				<li class="dropdown">
        					<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
        						zhonghao <span class="caret"></span>		
        					</a>
							<ul class="dropdown-menu" role="menu">
				                <li><a href="<c:url value="/user/me"/>">Profile</a></li>
				                <li><a href="#">Message</a></li>
				                <li><a href="<c:url value="/setting"/>">Setting</a></li>
				                <li class="divider"></li>
				                <li class="dropdown-header">System</li>
				                <li><a href="#">Help</a></li>
				                <li><a id="logout" style="cursor: pointer;">Logout</a></li>
              				</ul>
        				</li>
        			</ul>
                </div>
               <sf:form id="logoutForm" style="display: none;" servletRelativeAction="/logout" method="post">
               </sf:form>
			</div>
		</nav>