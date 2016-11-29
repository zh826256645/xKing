<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${message != null || error != null}">
<p class="
<c:if test="${message != null }">bg-info </c:if>
<c:if test="${error != null }">bg-danger </c:if>
change-message">
	<i class="fa fa-comment" aria-hidden="true"></i> ${message}${error}
	<i class="fa fa-times" id="changeMessage" style="float: right;" aria-hidden="true" onclick="javascript:removeFather('changeMessage');"></i>
</p>
</c:if>