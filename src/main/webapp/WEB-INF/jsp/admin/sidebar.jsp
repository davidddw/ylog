<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar-fixed-top">
	<div class="navbar" >
		<div class="navbar-inner">
			<ul class="nav pull-right">
				<li>
					<a href="" class="hidden-phone visible-tablet visible-desktop"><i class="icon-home icon-white"></i> View Site</a>  
				</li>
				<li>
					<a href="${options.url}/admin/listSetting" class="hidden-phone visible-tablet visible-desktop" ><i class="icon-cogs icon-white"></i> Settings</a>
				</li>
				<li id="fat-menu" class="dropdown">
					<a href="#"  class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user icon-white"></i> Account<i class="icon-caret-down"></i> </a>
					<ul class="dropdown-menu">
						<li>
							<a tabindex="-1" href="#">My Account</a>
						</li>
						<li class="divider visible-phone"></li>
						<li><a tabindex="-1" href="sign-in.html">Logout</a></li>
					</ul>
				</li>
			</ul>
			<a class="brand" href="index.html"><span class="first">Your</span> <span class="second">Company</span></a>
		</div>
	</div>
</div>
<div id="sidebar">
	<div id="sidebar-wrapper">
		<!-- Sidebar with logo and menu -->
		<!-- Logo (221px wide) -->
		<a href=""><img id="logo" src="static/admin/images/logo.png" alt="Simpla Admin logo" /></a>
		<!-- Sidebar Profile links -->

		<ul id="main-nav">
	        <!-- Accordion Menu -->
	        <li> <a href="${options.url}/admin" 
	        <c:choose>
	        	<c:when test="${action=='admin'}">
					class="nav-top-item no-submenu current"		
   				</c:when>
   				<c:otherwise>class="nav-top-item no-submenu"</c:otherwise>
			</c:choose>>
	        Dashboard </a> </li>
	        <li> <a href="#" class="nav-top-item"> 	Articles </a>
	          	<ul>
	            	<li><a href="${options.url}/admin/addArticle" <c:if test="${action=='addarticle'}">class="current"</c:if>>Write a new Article</a></li>
	            	<li><a href="${options.url}/admin/listArticle" <c:if test="${action=='listarticle'}">class="current"</c:if>>Manage Articles</a></li>
	            	<li><a href="${options.url}/admin/listCategory" <c:if test="${action=='listcategorie'}">class="current"</c:if>>Manage Categories</a></li>
	            	<li><a href="${options.url}/admin/listComment" <c:if test="${action=='listcomment'}">class="current"</c:if>>Manage Comments</a></li>
	          	</ul>
	        </li>
	        <li> <a href="#" class="nav-top-item"> Settings </a>
	          	<ul>
	            	<li><a href="${options.url}/admin/listSetting" <c:if test="${action=='listsetting'}">class="current"</c:if>>General</a></li>
	            	<li><a href="${options.url}/admin/listUser" <c:if test="${action=='listuser'}">class="current"</c:if>>Users and Permissions</a></li>
	          	</ul>
	        </li>  
	     </ul>
	</div>
</div>