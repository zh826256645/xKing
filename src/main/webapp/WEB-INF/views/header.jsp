<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%--获取用户名 --%>
		<security:authentication property="principal.username" var="username" scope="session"/>
		
		<nav class="navbar navbar-default navbar-fixed-top heard" role="navigation">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2">
					<div class="navbar-header">
						<a class="navbar-brand" href="<c:url value="/"/>" style="font-size: 25px;">xKing</a>
					</div>
					<!--
	              		左导航栏
	                -->
	                <div id="navbar" class="navbar-collapse collapse">
	                	<ul class="nav navbar-nav">
	                		<li class="active">
	                			<a href="<c:url value="/user/me"/>"><s:message code="header.index"/></a>
	                		</li>
	                	</ul>
	                  	<form class="navbar-form navbar-left" role="search">
	        		   		<div class="form-group">
	                			<input type="text" class="form-control" placeholder="<s:message code="header.search"/>">
	            			</div>
	        			</form>
	        			<ul class="nav navbar-nav navbar-right">
	        				<li> 
								<img src="<c:url value='/picture/user/${username}'/>?pid=${userPicture}" class="img-circle heard-profile-picture">
	        				</li>
	        				<li class="dropdown">
	        					<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
	        						${username} <span class="caret"></span>		
	        					</a>
								<ul class="dropdown-menu" role="menu">
					                <li><a href="<c:url value="/user/me"/>"><s:message code="header.menu.profile"/></a></li>
					                <li><a href="#"><s:message code="header.menu.message"/></a></li>
					                <li><a href="<c:url value="/setting"/>"><s:message code="header.menu.setting"/></a></li>
					                <li class="divider"></li>
					                <li class="dropdown-header"><s:message code="header.menu.system"/></li>
					                <li><a href="#"><s:message code="header.menu.help"/></a></li>
					                <li><a id="logout" style="cursor: pointer;"><s:message code="header.menu.logout"/></a></li>
	              				</ul>
	        				</li>
	        			</ul>
	                </div>
	               <sf:form id="logoutForm" style="display: none;" servletRelativeAction="/logout" method="post">
	               </sf:form>
				</div>
			</div>
		</nav>