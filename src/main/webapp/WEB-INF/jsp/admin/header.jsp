<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Admin</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>static/js/bootstrap/css/bootstrap.min.css" media="screen">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>static/js/font-awesome/css/font-awesome.min.css" media="screen">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>static/js/bootstrap.dialog/css/bootbox.css" media="screen" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>static/admin/css/reset.css" media="screen" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>static/admin/css/invalid.css" media="screen" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>static/admin/css/style.css" media="screen" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>static/js/showLoading/css/showLoading.css" media="screen" />
	<link rel="icon" href="<%=basePath%>static/favicon.ico" type="image/x-icon" />
	<script type="text/javascript" src="<%=basePath%>static/js/lib/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/showLoading/js/jquery.showLoading.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/lib/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/bootstrap.dialog/js/bootbox.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/custom/admin.configuration.js"></script>
	<base href="<%=basePath%>" />