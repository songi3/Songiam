<!DOCTYPE html>
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

		<!-- Banner -->
		<section id="intro" class="main">
			<span class="icon fa-diamond major"></span>
			<h2>Learn About Me</h2>
			<p>학력 및 자격사항</p>
			<ul class="actions">
				<li><a href="#" class="button big"
					onclick="historyButtonClickEvent()">Learn More</a></li>
			</ul>
		</section>

		<!-- Items -->
		<section class="main items">

			<!-- 반복 -->
			<c:forEach var="comment" items="${commentsList}">
				<!-- 썸네일 존재하는지 확인 -->
				<c:choose>
					<c:when test="${empty comment.thumbnail}">

						<c:set var="imgSRC"
							value="${pageContext.request.contextPath}/resources/img/sample_default.jpg" />
					</c:when>
					<c:otherwise>
						<c:set var="imgSRCPath"
							value="${pageContext.request.contextPath}/" />
						<c:set var="imgSRCThumbnailURL" value="${comment.thumbnail}" />
						<c:set var="imgSRC" value="${imgSRCPath}${imgSRCThumbnailURL}" />
					</c:otherwise>
				</c:choose>

				<!-- Tag 삽입 -->
				<article class="item">
					<header>
						<a href="#"><img src="${imgSRC}" alt="${comment.index}" /></a>
						<h3>${comment.title}</h3>
					</header>
					<p>${comment.subTitle}</p>
					<ul class="actions">
						<li><a href="#" class="button"
							onclick="commentDetailEvent(this)" id="${comment.index}">More</a></li>
					</ul>
				</article>
			</c:forEach>
		</section>

		<!-- CTA -->
		<section id="cta" class="main special">
			<h2>CONTACT</h2>
			<p>문의사항은 메세지를 보내주세요.</p>
			<form class="contact-form">
				<div class="two-layers">
					<input type="text" id="name" placeholder="Your Name..."> <input
						type="email" id="email" placeholder="Your Email...">
				</div>
				<div class="one-layers">
					<input type="text" id="subject" placeholder="Subject...">
				</div>
				<div class="one-layers">
					<textarea name="content" id="content" cols="30" rows="6"
						placeholder="Leave your message..."></textarea>
				</div>
				<input type="button" class="button big" id="messageDefaultBtn"
					value="Send Message" onclick="sendMessageButtonClickEvent()">

			</form>

		</section>

		<%@ include file="/WEB-INF/views/included/included_fotter.jsp"%>
	</div>
</body>
</html>