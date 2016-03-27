<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="mm" uri="/mytaglib" %>
<div class="alt-wrapper"> 
	<b class="rtop"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b></b>
	<div class="inner-wrapper">
		<h3>最新文章</h3>
		<div class="item-list">
			<ul>
				<c:forEach items="${latestArticles}" var="latestArticle" >
 					<li><span class="text1"><a title="${latestArticle.title}" href="${options.url}/node/article/${latestArticle.id}.html">${latestArticle.title}</a></span>
 					<span><mm:dateFormat>${latestArticle.createdDate}</mm:dateFormat></span></li>
 				</c:forEach>
			</ul>
		</div>
	</div>
	<b class="rbottom"><b class="r4"></b><b class="r3"></b><b class="r2"></b><b class="r1"></b></b> 
</div>
<div class="diggwrapper"> 
	<b class="rtop"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b></b>
		<div class="inner-wrapper">
			<h3>点击排行</h3>
			<div class="item-list">
			<ul>
				<c:forEach items="${mostArticles}" var="mostArticle" >
					<li><span class="text1"><a title="${mostArticle.title}" href="${options.url}/node/article/${mostArticle.id}.html">${mostArticle.title}</a></span>
					<span>${mostArticle.pv}阅</span></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<b class="rbottom"><b class="r4"></b><b class="r3"></b><b class="r2"></b><b class="r1"></b></b> 
</div>
	<div class="wrapper"> <b class="rtop"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b></b>
		<div class="inner-wrapper">
			<div class="block block-comment" id="block-comment-0">
				<h3 class="title">最近评论</h3>
				<div class="content">
					<div class="item-list">
						<ul>
						<c:forEach items="${mostComments}" var="mostComment" >
							<li><a href="${options.url}/node/article/${mostComment.article.id}.html#comment-${mostComment.id}">${mostComment.author}</a>:
							${mostComment.content}</li>
						</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	<b class="rbottom"><b class="r4"></b><b class="r3"></b><b class="r2"></b><b class="r1"></b></b> 
</div>	