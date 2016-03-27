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
	<script type="text/javascript" src="<%=basePath%>static/js/custom/global.operation.js"></script>
</head>
<body>
<div id="body-wrapper">
	<%@ include file="sidebar.jsp" %>
  	<!-- End #sidebar -->
  	<div id="content" >
  		<div id="header">
			<h1 class="page-title">系统设置</h1>
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
				系统设置
			</li>
		</ul>
		<div id="main-content">
		    
		    	<!-- End .content-box-header -->
			    <div id="inner-content">
			    <div class="clear"></div>
			    	<div class="clear"></div>
			        <table id="loadingSettings"  class="loading">
			        </table>
			        <!-- End #tab1 --> 
			        <div class="clear"></div> 
			    </div>
		    	<!-- End .content-box-content -->

		    <!-- End .content-box -->
		    
		    <!-- Start Notifications -->
	    	<jsp:include page="footer.jsp" />
		</div>
	</div>
  	<!-- End #main-content -->
</div>
</body>
</html>