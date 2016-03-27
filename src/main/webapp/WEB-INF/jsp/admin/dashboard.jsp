<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="header.jsp" />
</head>
<body>
<div id="body-wrapper">
	<%@ include file="sidebar.jsp" %>
  	<!-- End #sidebar -->
  	<div id="content" >
  		<div id="header">
			<h1 class="page-title">Dashboard</h1>
			<div class="stats">
				<p class="stat"><span class="number">53</span>tickets</p>
				<p class="stat"><span class="number">27</span>tasks</p>
				<p class="stat"><span class="number">15</span>waiting</p>
			</div>
		</div>
		<ul class="breadcrumb">
			<li>
				<a href="index.html">Home</a><span class="divider">/</span>
			</li>
			<li class="active">
				Dashboard
			</li>
		</ul>
  		<div id="main-content">
		    <ul class="shortcut-buttons-set">
		      	<li><a class="shortcut-button" href="${options.url}/admin/addArticle"><span> <img src="static/admin/images/icons/pencil_48.png" alt="icon" /><br />
		        	新建文章 </span></a></li>
		      	<li><a class="shortcut-button" href="${options.url}/admin/listArticle"><span> <img src="static/admin/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
		        	文章管理 </span></a></li>
		      	<li><a class="shortcut-button" href="#"><span> <img src="static/admin/images/icons/image_add_48.png" alt="icon" /><br />
		        	Upload an Image </span></a></li>
		      	<li><a class="shortcut-button" href="#"><span> <img src="static/admin/images/icons/clock_48.png" alt="icon" /><br />
		        	Add an Event </span></a></li>
		      	<li><a class="shortcut-button" href="#messages" rel="modal"><span> <img src="static/admin/images/icons/comment_48.png" alt="icon" /><br />
		        	Open Modal </span></a></li>
		    </ul>
		    <!-- End .shortcut-buttons-set -->
		    <div class="clear"></div>
		    <div class="content-box ">
				<div class="content-box-header column-fix">
					<h3>Latest Stats</h3>
				</div>
				<!-- End .content-box-header -->
				<div class="content-box-content">
					<div class="tab-content default-tab stat-widget-container">
						<div class="stat-widget">
							<div class="stat-button">
								<p class="title">2,500</p>
								<p class="detail">Accounts</p>
							</div>
						</div>
						<div class="stat-widget">
							<div class="stat-button">
								<p class="title">3,299</p>
								<p class="detail">Subscribers</p>
							</div>
						</div>
						<div class="stat-widget">
							<div class="stat-button">
								<p class="title">$1,500</p>
								<p class="detail">Pending</p>
							</div>
						</div>
						<div class="stat-widget">
							<div class="stat-button">
								<p class="title">$12,675</p>
								<p class="detail">Completed</p>
							</div>
						</div>
					</div>
					<!-- End #tab3 -->
				</div>
				<!-- End .content-box-content -->
			</div>
		    <!-- End .clear -->
		    <div class="content-box column-fix">
		      	<!-- Start Content Box -->
		      	<div class="content-box-header">
		        	<h3>Content box</h3>
		        	<ul class="content-box-tabs">
		          		<li><a href="#tab1" class="default-tab">Table</a></li>
		          		<!-- href must be unique and match the id of target div -->
		          		<li><a href="#tab2">Forms</a></li>
		        	</ul>
		        	<div class="clear"></div>
		      	</div>
		      	<!-- End .content-box-header -->
		      	<div class="content-box-content">
		        	<div class="tab-content default-tab" id="tab1">
		          		<!-- This is the target div. id must match the href of this div's tab -->
						<h4>Maecenas dignissim</h4>
		          		<p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in porta lectus. Maecenas dignissim enim quis ipsum mattis aliquet. Maecenas id velit et elit gravida bibendum. Duis nec rutrum lorem. Donec egestas metus a risus euismod ultricies. Maecenas lacinia orci at neque commodo commodo. </p>
		        	</div>
		        	<!-- End #tab1 -->
		        	<div class="tab-content" id="tab2">
		          		<h4>Maecenas dignissim</h4>
		          		<p> Lorem ipsum dolor sit amet,aecenas id velit et elit gravida bibendum. Duis nec rutrum lorem. Donec egestas metus a risus euismod ultricies. Maecenas lacinia orci at neque commodo commodo. </p>
		        	</div>
		        	<!-- End #tab2 -->
		      	</div>
		      	<!-- End .content-box-content -->
		    	<!-- End .content-box -->
		    </div>
		    <div class="clear"></div>
		    <!-- Start Notifications -->
		</div>
	    <jsp:include page="footer.jsp" />
	</div>
  	<!-- End #main-content -->
</div>
</body>
</html>