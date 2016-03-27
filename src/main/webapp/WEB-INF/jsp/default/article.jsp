<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="mm" uri="/mytaglib" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${article.title}</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/style.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/js/fancybox/jquery.fancybox.css" media="screen" />
	<link rel="icon" href="<%=basePath%>static/favicon.ico" type="image/x-icon" />
	<script type="text/javascript" src="<%=basePath%>static/js/lib/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/lib/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/fancybox/jquery.fancybox.pack.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/custom/article.view.js"></script>
	<base href="<%=basePath%>" />
</head>
<body id="section-homepage">
	<div id="Container">
		<div id="ContainerTop"></div>
		<!-- start header -->
		<%@ include file="header.jsp" %>
		<%@ include file="mainmenu.jsp" %>
		<!-- start content items -->
		<div id="MainBody">
			<div id="MainBodyTop"></div>
			<div id="content">
				<div id="breadcrumb">
					<a href="${options.url}">首页</a><a href="${options.url}/node/category/${article.category.name}">${article.category.intro}</a>${article.title}
				</div>
				<div class="Post">
					<div class="PostHead">
						<div class="PostHeadTop"></div>
						<div class="date-wrap">
							<span class="date-day"><fmt:formatDate type="date" value="${article.createdDate}" pattern="d"/></span>
							<span class="date-month"><fmt:formatDate type="date" value="${article.createdDate}" pattern="M"/></span>
						</div>
						<h1><a href="${options.url}/node/article/${article.id}.html">${article.title}</a></h1>
						<span class="submitted">作者: ${article.user.name} | 发表时间: <fmt:formatDate type="both" value="${article.createdDate}" pattern="MMM dd, yyyy" /> </span>
					</div>
					<div class="PostContent">
						<p>${article.content}</p>		
					</div>
					<div class="clear"></div>
					<div class="tools">
						<span class="cat">分类: <c:choose>
   							<c:when test="${!empty article.category}">
							<a href="${options.url}/node/category/${article.category.name}">${article.category.intro}</a>
   							</c:when>
   							<c:otherwise>未分类</c:otherwise>
						</c:choose>
						</span>  
						<span class="tags">标签: 
							<c:forEach items="${article.tagStrings}" var="tag" >
								<a href="${options.url}/node/tag/${tag}">${tag}</a>
							</c:forEach>
						</span> 
						<span class="node_read_more">
							点击率：${article.pv}
						</span>
						<span class="first comment_comments">
							<a href="${options.url}/node/article/${article.id}.html#comments" class="comment_comments">${article.commentCount}条评论</a>
						</span>
					</div>
				</div>
				<%-- --%>
				<div class="entrynavigation"> &lt;&lt; 
					<c:choose>
   						<c:when test="${!empty relate_article.prev_article}">
						<a href="${options.url}/node/article/${relate_article.prev_article.id}.html" title="${relate_article.prev_article.title}">${relate_article.prev_article.title}</a>
   						</c:when>
   						<c:otherwise>没有了
   						</c:otherwise>
					</c:choose>	| 
					<c:choose>
   						<c:when test="${!empty relate_article.next_article}">
						<a href="${options.url}/node/article/${relate_article.next_article.id}.html" title="${relate_article.next_article.title}">${relate_article.next_article.title}</a> 
   						</c:when>
   						<c:otherwise>没有了
   						</c:otherwise>
					</c:choose>	&gt;&gt;
				</div>
				<div id="similar">
					<h3>你也许对下面的内容感兴趣</h3>
					<ul>
					<c:forEach items="${relate_tags}" var="relate_tag" >
						<li><a href="${options.url}/node/article/${relate_tag.id}.html">${relate_tag.title}</a></li>
					</c:forEach>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<div id="Comment">
					<h3 id="comments">现有${article.commentCount}条评论</h3>
					<c:forEach items="${comments}" var="comment" varStatus="status">
						<div class="ComListLi">
						 	<div class="ComListLiTop"></div>
							<div class="ListUser">${comment.author}</div>
							<p class="ListDate"><fmt:formatDate type="both" value="${comment.posttime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
							<span class="ListNr">${pagination.pageSize*pagination.currentPage+status.index+1}</span>
							<div class="ListContent" id="comment-${comment.id}">
								<p>${comment.content}</p>			
							</div>
						</div>
					</c:forEach>					
				</div> 
				<div class="navigation">
					<div class="alignleft"></div>
					<div class="alignright"></div>
				</div>
				<mm:Pagination pagination="${pagination}" url="${contextPath}" suffix="${'#Comment'}" /> 
				<div id="respond">
					<h3 id="leave">发表评论</h3>
					<form id="commentform">
					 	<div id="author_info">
							<p><input class="input" type="text" name="submitter" id="submitter" value="" size="22" tabindex="1" />
							<label for="author">昵称 (*)</label></p>
							<p><input class="input" type="text" name="email" id="email" value="" size="22" tabindex="2" />
							<label for="email">E-mail (打死不公开) (*)</label></p>
							<p><input class="input" type="text" name="homepage" id="homepage" value="" size="22" tabindex="3" />
							<label for="url">网址 (可选)</label></p>
							<p><input class="input" type="text" name="validateCode" id="validateCode" value="" size="22" tabindex="3" />
							<img id="imgCheckCode" src="validateCode?t=0.31415"/><a href="" id="change_image">  看不清，换一张</a></p>
						</div>
						<p><textarea class="input" name="content" id="commentText" cols="90%" rows="8" tabindex="4"></textarea></p>
						<div class="error"></div>
						<p><input name="commentsubmit" type="submit" id="commentsubmit" tabindex="5" value="提交 Ctrl+Enter" />
							<input type='hidden' name='postId' value='${article.id}' id='postId' />
						</p>
						<div class="clear"></div>
					</form>
				</div>
				<div class="clear"></div> 
			</div>
			<div id="SideBar">
				<jsp:include page="seach.jsp" />
				<jsp:include page="sidebar.jsp" />
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>