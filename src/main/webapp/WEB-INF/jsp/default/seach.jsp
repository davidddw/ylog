<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mm" uri="/mytaglib" %>
<div id="searchblock">
	<h3>搜索内容</h3>
	<div id="right_search_form">
		<form action="${options.url}/node/search" method="post" id="search-theme-form">
			<div>
				<input name="svalue" id="edit-search-theme-form-1" size="15" value="请输入关键词" onblur="if(this.value==&#39;&#39;) this.value=&#39;请输入关键词&#39;;" onfocus="if(this.value==&#39;请输入关键词&#39;) this.value=&#39;&#39;;" class="form-text" type="text">
				<input name="op" id="edit-submit" class="btn_search_small" value="" type="submit">
			</div>
		</form>
	</div>
	<div class="text">如果要进行更为详细的信息搜索.</div>
</div>