
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/inc/top"></c:import>
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
#div1{
	width: 900px;
}
#formFile{
	width: 400px;
}
#btn1{
	clear: none;
}
</style>


<script type="text/javascript"
	src="../js/smartEdit/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#btn1').click(function() {
			location.href = "/askBoard/askBoardList";
		});
		
	});
</script>
<body>
<br><br><br>
<div id="div1" class="container text-left">
	<form name="askEdit" method="post" action="/askBoard/askEdit">
		<input type = "hidden" name="askNo" value ="${vo.askNo }"/>
		<fieldset>
			<legend>게시글 수정</legend>
			<div class="form-group">
				<label for="exampleSelect1" class="form-label mt-4">게시판 목록</label>
				<select class="form-select" id="selBoard">
					<option>문의게시판</option>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1" class="form-label mt-4"></label>
				<input type="text" class="form-control" id="title1" name="a_title"
					value="${vo.a_title}" > 
			</div>
			<div class="form-group">
				<label for="exampleTextarea" class="form-label mt-4"></label>
				<textarea id="txtCon" class="form-control" rows="6" name="a_content"
					 style="height: 200px;">${vo.a_content }</textarea>
			</div>
			<br><br>
			<button id="sub1" type="submit" class="btn btn-dark" >수정</button>
			<button id="btn1" type="button" class="btn btn-dark" >목록</button>
		</fieldset>
	</form>
	</div>
</body>
<br><br><br>
<jsp:include page="../inc/footer.jsp"></jsp:include>
</html>