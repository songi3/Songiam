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

		<!-- Main -->

		<section id="main" class="main">
			<header>
				<h1>${comment.title}</h1>
				<div class="header-detail">
					<div>
						<i class="fa fa-user"></i> ${comment.id}
					</div>
					&nbsp;&nbsp;
					<div>
						<i class="fa fa-table"></i> ${comment.regDate}
					</div>
					&nbsp;&nbsp;
					<div>
						<i class="fa fa-bar-chart-o"></i> ${comment.count}
					</div>
					&nbsp;&nbsp;
					<div class ="comment-index" style="display:none;">${comment.index}</div>
				</div>
			</header>
			<br>
			<hr>
			<br>
			<div class="header-detail"></div>
			<p>${comment.comment}</p>
		</section>

		<div class="footer-nav">
			<input type="button" class="button" value="HOME"
				onclick="homeButtonClickEvent()" style="margin-right: 1em;">
			<input type="button" class="button" value="TOP" style="margin-right: 1em;"
				onclick="topButtonClickEvent()"> 
			<input type="button"
				class="button" value="EDIT" id="comment-edit-btn" style="margin-right: 1em;" onclick="commentEditButtonClickEvent()">
			<input type="button"
				class="button" value="DELETE" id="comment-remove-btn" onclick="commentRemoveButtonClickEvent()">
		</div>
		<%@ include file="/WEB-INF/views/included/included_fotter.jsp"%>
	</div>
</body>
</html>
