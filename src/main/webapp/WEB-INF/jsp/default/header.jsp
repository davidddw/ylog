<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="mm" uri="/mytaglib" %>
<div id="Header">
	<div id="HeaderTop"></div>
	<div id="HeadLeft">
		<h1><a href="">${options.content}</a></h1>
		<div id="MenuTop">
			<ul>
				<%-- <li><shiro:user><shiro:principal/><a href="${options.url}/admin">管理</a></shiro:user><shiro:guest><a href="${options.url}/login">登录</a></shiro:guest></li> --%>
				<li><a href="/contact/">联系我们</a></li>
				<li><a href="/geek/">什么是极客</a></li>
			</ul>
			<div class="myurl"><a href="http://www.21blog.com/">21blog</a><a href="">geekg</a></div>
		</div>
		<div id="navigation">${options.description}</div>
	</div>
	<div id="HeadRight">
		<div id="HeadRightTop"></div>
		<div id="topnavbox">
			<ul>
				<li><a href=""><img src="static/img/menu1.gif" alt=""><br />
						网站首页</a></li>
				<li><a href=""><img src="static/img/menu3.gif" alt=""><br />
						最新更新</a></li>
				<li><a href=""><img src="static/img/menu4.gif" alt=""><br />
						极客论坛</a></li>
				<li><a href=""><img src="static/img/menu2.gif" alt=""><br />
						邮箱订阅</a></li>
				<li><a href=""><img src="static/img/menu5.gif" alt=""><br />
						联系我们</a></li>
				<li><a href=""><img src="static/img/menu6.gif" alt=""><br />
						内容订阅</a></li>
				<li><a href=""><img src="static/img/menu6.gif" alt=""><br />
						内容订阅</a></li>
				<li><a href=""><img src="static/img/menu6.gif" alt=""><br />
                                                                内容订阅</a></li>
			</ul>
		</div>
		<div id="Submissions">
			<p>我们期待你的参与与创作，把你看到的最新潮、最有趣、最好玩的东西和事情和大家一起分享。 </p>
		</div>
	</div>
</div>