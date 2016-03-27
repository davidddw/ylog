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
	<script type="text/javascript" src="<%=basePath%>static/js/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/custom/comment.operation.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/custom/kindeditor.configuration.js"></script>
</head>
<body>
<div id="body-wrapper">
	<%@ include file="sidebar.jsp" %>
  	<!-- End #sidebar -->
  	<div id="content" >
  		<div id="header">
			<h1 class="page-title">评论管理</h1>
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
				评论管理
			</li>
		</ul>
		<div id="main-content">
		    <div class="clear"></div>
		    	<!-- End .content-box-header -->
			    <div>
			        <div class="tab-content default-tab" id="listTab">
		          		<input id="total" type="hidden" name="tatal" value="${total}" />
		          		<input id="pagesize" type="hidden" name="size" value="${pageSize}" />
			          	<table id="loadingComment" class="loading"></table>
			        </div>
			        <!-- End #tab1 -->      
			    </div>
		    	<!-- End .content-box-content -->

		    <!-- End .content-box -->
		    <div class="clear"></div>
		    <!-- Start Notifications -->
			<jsp:include page="footer.jsp" />
		</div>
	</div>
  	<!-- End #main-content -->
</div>
</body>
</html>