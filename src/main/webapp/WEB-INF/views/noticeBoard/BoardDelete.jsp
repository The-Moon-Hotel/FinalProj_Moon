<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/inc/top"></c:import>
<link rel="stylesheet" href="css/top.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
</head>
<style type="text/css">
body {
	padding-top: 70px;
	padding-bottom: 30px;
}

.board_title {
	font-weight: 700;
	font-size: 22pt;
	margin: 10pt;
}

.board_info_box {
	color: #6B6B6B;
	margin: 10pt;
}

.board_author {
	font-size: 10pt;
	margin-right: 10pt;
}

.board_date {
	font-size: 10pt;
}

.board_content {
	color: #444343;
	font-size: 12pt;
	margin: 10pt;
}

.board_tag {
	font-size: 11pt;
	margin: 10pt;
	padding-bottom: 10pt;
}
</style>
<script type="text/javascript"src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#btnUpdate').click(function(){
			location.href="/noticeBoard/noticeEdit";
		});
		$('#btn1').click(function(){
			location.href="/noticeBoard/noticeBoardList";
		});
	});
</script>
<body>
	<div class="divForm">
	<form name="frmDelete" method="post" action="/noticeBoard/BoardDelete?noticeNo=${vo.noticeNo}">
			<!--  삭제시 no가 필요하므로 hidden 필드에 넣어준다. -->
			<input type="hidden" name="no" value="${vo.noticeNo}">
			<input type="hidden" name="gno" value="${vo.guestNo}">
			<fieldset>
			<br><br><br><br><br>
				<div style="text-align: center;">
					<span class="sp">${vo.noticeNo} 번 글을 삭제하시겠습니까?</span>
				</div>
				<br>
				<div style="text-align: center;">
					<input type="submit" class="btn btn-dark" value="삭제" />
					<input id="btn1" type="Button" class="btn btn-dark" value="글목록"/>
				</div>
				<br><br><br><br><br>
			</fieldset>
		</form>
		</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
<jsp:include page="../inc/footer.jsp"></jsp:include>
<link rel="stylesheet" href="css/footer.css">
</html>