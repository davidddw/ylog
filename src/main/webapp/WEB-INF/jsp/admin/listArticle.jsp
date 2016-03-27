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
    <script type="text/javascript" src="<%=basePath%>static/js/custom/article.operation.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/js/custom/comment.operation.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/js/custom/kindeditor.configuration.js"></script>
</head>
<body>
<div id="body-wrapper">
	<%@ include file="sidebar.jsp" %>
  	<!-- End #sidebar -->
  	<div id="content" >
  		<div id="header">
			<h1 class="page-title">文章管理</h1>
			<div class="stats">
				<p class="stat"><span class="number">53</span>tickets</p>
				<p class="stat"><span class="number">27</span>tasks</p>
				<p class="stat"><span class="number">15</span>waiting</p>
			</div>
		</div>
		<ul class="breadcrumb">
			<li><a href="admin">Home</a>
			<span class="divider">/</span></li>
			<li class="active">文章管理</li>
		</ul>
		<div id="main-content">

		    	<!-- End .content-box-header -->
			    <div>
			        <div class="tab-content default-tab" id="listTab">
			        	<div class="tabletop">
			    			<button id="addnew" class="btn btn-primary" type="button"><i class="icon-plus-sign"></i>&nbsp;&nbsp;Delete</button>
			    			<div class="input-append searchright">
    							<input id="appendedInputButtons" type="text" placeholder="Text input" />
    							<button class="btn" type="button">Search</button>
    						</div>
			    		</div>
		          		<input id="totalarticle" type="hidden" name="tatal" value="${tatal}" />
		          		<input id="pagesize" type="hidden" name="size" value="${pageSize}" />
			          	<table id="loadingArticle" class="loading"></table>
			        </div>
			        <!-- End #tab1 -->
			        <div class="tab-content" id="editTab" style="display:none">	
				        <form id="articleForm">
				            <fieldset>
				              	<input type="hidden" name="id" id="id" value="" />
				            <p>
				              	<label>Input the title</label>
				              	<input class="text-input medium-input" type="text" id="title" name="title" value=""/>
				            </p>
				            <p> 
				              	<label>Select the Author</label>
				              	<select name="user" class="small-input" id="user" >
				              		<option value=""></option>
				              		<c:forEach items="${users}" var="user" >
										<option value="${user.id}">${user.name}</option>
									</c:forEach>	                		
				              	</select>
				            </p>
				            <p>
				              	<label>Select the category</label>
				              	<select name="category" class="small-input" id="category">
				              		<option value=""></option>
				              		<c:forEach items="${categories}" var="category" >
										<option value="${category.id}">${category.intro}</option>
									</c:forEach>
				              	</select>
				            </p>
				            <p>
			              		<label>Input the tag</label>	
			              		<input class="text-input medium-input" type="text" id="tags" name="tags" value=""/>
			              		<span class="input-notification attention png_bg">separated by ;</span> </p>
				            <p>
				              	<label>content</label>
				              	<textarea class="text-input textarea" id="kindContent" name="content" ></textarea>
				            </p>
				            <p>
				             	<button class="btn " type="button" id="returnListForm"><i class="icon-reply icon-white"></i> 返回 </button>
				              	<button class="btn btn-primary" type="button" id="submitEditForm"><i class="icon-ok icon-white"></i> 提交 </button>
				            </p>
				            </fieldset>

				            <!-- End .clear -->
				       	</form>
					</div><!-- End #tab2 -->      
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