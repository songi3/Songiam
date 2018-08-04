<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/included/included_head.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/included/included_header.jsp"%>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->

		<section id="main" class="main">
			<script
				src="${pageContext.request.contextPath}/resources/editor/ckeditor/ckeditor.js"></script>
			<script
				src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
			
			<form class="comment-edit-form" action="saveComment" method="post">
				<div class="one-layers">
					제목 <br> <input type="text" name="title" placeholder="Title..."
						value="${comment.title}" />
				</div>
				<div class="one-layers">
					소제목 <br> <input type="text" name="subTitle"
						placeholder="SubTitle" value="${comment.subTitle}" />
				</div>
				<div class="two-layers">
					<input type="text" name="index" placeholder="Index..."
						value="${comment.index}" style="margin-right: 1em;"> <input
						type="text" name="thumbnail" placeholder="Thumbnail..."
						value="${comment.thumbnail}">
				</div>
				<div class="one-layers">
					<textarea name="editor1" id="editor1" cols="30" rows="10"
						placeholder="Comment...">${comment.comment}</textarea>
				</div>
				<div class="one-layers">
					<textarea class="contentarea" style="display: none;"
						name="contentarea"></textarea>
				</div>
				<input type="submit" class="button big" id="commentSaveBtn"
					value="SAVE"> <input type="button" class="button big"
					id="cancelBtn" value="CANCEL" onclick="homeButtonClickEvent()">
			</form>
		</section>

		<script>
			$('#commentSaveBtn').click(function() {
				var contentData = CKEDITOR.instances["editor1"].getData();
				
				var conArea = $('.contentarea');
				conArea.html(contentData);
			});

			CKEDITOR
					.replace(
							'editor1',
							{
								filebrowserImageUploadUrl : '${pageContext.request.contextPath}/imageUpload' //파일 경로 전달
							});

			CKEDITOR.on('dialogDefinition', function(ev) {
				var dialogName = ev.data.name;
				var dialogDefinition = ev.data.definition;

				switch (dialogName) {
				case 'image': //Image Properties dialog
					//dialogDefinition.removeContents('info');
					//dialogDefinition.removeContents('Link');
					dialogDefinition.removeContents('advanced');
					break;
				}
			});
		</script>

		<%@ include file="/WEB-INF/views/included/included_fotter.jsp"%>
	</div>
</body>
</html>