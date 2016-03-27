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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Admin Login</title>
	<link rel="stylesheet" href="<%=basePath%>static/resources/css/style.css" type="text/css" media="screen" />
	<link rel="icon" href="<%=basePath%>static/favicon.ico" type="image/x-icon" />
	<script type="text/javascript" src="<%=basePath%>static/resources/script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/resources/script/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/resources/script/hashlib.js"></script>
	<base href="<%=basePath%>" />
</head>
<body id="login">
<div id="login-wrapper" class="png_bg">
  	<div id="login-top">
    	<h1>Simpla Admin</h1>
    	<!-- Logo (221px width) -->
    	<a href=""><img id="logo" src="static/resources/images/logo.png" alt="Simpla Admin logo" /></a> 
    </div>
  	<!-- End #logn-top -->
  	<div id="login-content">
	    <form id="loginForm" action="<%=basePath%>login" method="post">
	      	<div class="notification information png_bg">
	        	<div>请输入用户名和密码</div>
	      	</div>
	      	<p><label>用户名：</label><input id="name" class="text-input" type="text" name="name"/></p>
	      	<div class="clear"></div>
	      	<p>
	        	<label>密&nbsp;&nbsp;&nbsp;码：</label>
	        	<input id="password" class="text-input" type="password" name="password"/>
	      	</p>
	      	<div class="clear"></div>
	      	<p id="remember-password"><input type="checkbox" />记住密码  </p>
	      	<div class="clear"></div>
	      	<p><input id="btnLogin1" class="button" type="submit" value="登&nbsp;&nbsp;&nbsp;录" /></p>
	    </form>
	</div>
  	<!-- End #login-content -->
</div>
<!-- End #login-wrapper -->
</body>
</html>