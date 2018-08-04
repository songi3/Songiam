<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/views/included/included_head.jsp"%>
</head>
<body>
	<div id="wrapper">
		<section id="cta" class="main special"
			style="display: flex; justify-content: center; align-items: center;">
			<div class="login-form">
				<div class="text-info">LOGIN</div>
				<div class="input-form">
					<form class="input-form">
						<div class="one-layers">
							<input type="text" id="id" name="id" placeholder="ID...">
						</div>
						<div class="one-layers">
							<input type="password" id="password" name="password" placeholder="PASSWORD..." />
						</div>
						<input type="button" class="button big" value="LOGIN" onclick="loginCheckButtonClickEvent()"> 
						<input type="button" class="button big" value="CANCEL" onclick="homeButtonClickEvent()">
					</form>

				</div>

			</div>

		</section>
	</div>
	<%@ include file="/WEB-INF/views/included/included_foot.jsp"%>
</body>
</html>