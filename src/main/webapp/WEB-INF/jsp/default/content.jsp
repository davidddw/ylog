<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="mm" uri="/mytaglib" %>
<c:forEach items="${articles}" var="article" varStatus="status" >
	<div class="Post">
		<div class="PostHead">
			<div class="PostHeadTop"></div>
			<div class="date-wrap">
				<span class="date-day"><fmt:formatDate type="date" value="${article.createdDate}" pattern="d"/></span>
				<span class="date-month"><fmt:formatDate type="date" value="${article.createdDate}" pattern="M"/></span></div>
			<h1><a href="${options.url}/node/article/${article.id}.html">${article.title}</a></h1>
			<span class="submitted">作者: ${article.user.name} | 发表时间: <fmt:formatDate type="both" value="${article.createdDate}" /> </span>
		</div>
		<div class="PostContent">
			<p><mm:cutHtml length="250" ellipsis="...">${article.content}</mm:cutHtml></p>
			<p class="more"><a href="${options.url}/node/article/${article.id}.html" title="${article.title}">阅读剩余部分...</a></p>		
		</div>
		<div class="clear"></div>
		<div class="tools">
			<span class="cat">分类: 
				<a href="${options.url}/node/category/${article.category.name}">${article.category.intro}</a>
			</span>  
			<span class="tags">标签: 
			<c:forEach items="${article.tagStrings}" var="tag" >
				<a href="${options.url}/node/tag/${tag}">${tag}</a>
			</c:forEach>
			</span>
			<span class="node_read_more">
			    浏览次数: ${article.pv} 次
			</span> 
			<span class="first comment_comments">
				<a href="${options.url}/node/article/${article.id}.html#comments" class="comment_comments">${article.commentCount}条评论</a>
			</span>
		</div>
	</div>	
</c:forEach>