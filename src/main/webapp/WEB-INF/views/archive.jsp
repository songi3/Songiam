
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/views/included/included_head.jsp"%>
</head>
<body>

	<%@ include file="/WEB-INF/views/included/included_header.jsp"%>

	<!-- Wrapper -->
	<div id="wrapper">

		<div class="loading-image"></div>
		<div class="mask"></div>
		
		<!-- Main -->

		<section id="main" class="main">
			<header>
				<h1>Archive</h1>
				<div class="header-detail">
					<div class="comment-index" style="display: none;">${comment.index}</div>
				</div>
			</header>
			<br> <br>
			<div class="header-detail"></div>
			<c:set var="path" value="/songihome/resources/uploadfiles/" />
			<p>
			<table style="text-align: center;">
				<tr>
					<td>목록</td>
					<td></td>
				</tr>
				
				<c:forEach var="file" items="${list}">
					<c:set var="filepath" value="${path}${file.name}" />
					<tr>
						<td>${file.name}</td>
						<td><a href="${filepath}">다운</a></td>
					</tr>
				</c:forEach>
				
			</table>
			</p>
		</section>

		<div class="footer-nav">
			<input type="button" class="button" value="HOME"
				onclick="homeButtonClickEvent()" style="margin-right: 1em;">
			<input type="button" class="button" value="TOP"
				style="margin-right: 1em;" onclick="topButtonClickEvent()">
		</div>
		<%@ include file="/WEB-INF/views/included/included_fotter.jsp"%>
	</div>
</body>
</html>
