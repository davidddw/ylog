<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mm" uri="/mytaglib" %>
<div id="MainMenu">
	<div id="MainMenuTop"></div>
	<ul>
	<c:forEach items="${categories}" var="category" varStatus="status" >
		<c:set value="${category.name}" var="name" />
		<c:set value="${category.intro}" var="title" />
		<li <c:if test="${status.last}">class="noborder"</c:if>>
		<a href="${options.url}/node/category/${name}" <c:if test="${status.first}">class="first"</c:if>>
		<strong>${title}</strong><mm:capital>${name}</mm:capital></a></li>
    </c:forEach>
	</ul>
</div>