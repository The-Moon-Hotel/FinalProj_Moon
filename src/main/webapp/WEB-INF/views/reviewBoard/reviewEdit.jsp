<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/inc/top"></c:import>
<link rel="stylesheet" href="/css/top.css">
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
var oEditors=[];
	$(function() {
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder : "txtCon",
			sSkinURI : "../js/smartEdit/SmartEditor2Skin.html",
		      htParams : {
		          bUseToolbar : true,             
		          bUseVerticalResizer : true,     
		          bUseModeChanger : true,         
		          fOnBeforeUnload : function(){
		          }
		      }, 
		      fCreator: "createSEditor2"
		      });
	  	  $("#sub1").click(function(){
	        //id가 smarteditor인 textarea에 에디터에서 대입
	          oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	        $('#frm').submit();
	    });
			$('#btn1').click(function() {
			location.href = "reviewBoardList.jsp";
		});
	});
</script>

<body>
<br><br><br>
<div id="div1" class="container text-left">
	<form id="frm" method="post" enctype="multipart/form-data" action="reviewEdit_ok.jsp">
		<input type ="hidden" name="reviewNo" value="${vo.reviewNo}">
		<input type ="hidden" name="guestNo" value="${vo.guestNo}">
		<fieldset>
			<legend>게시글 수정</legend>
			<div class="form-group">
				<label for="exampleSelect1" class="form-label mt-4">게시판 목록</label>
				<select class="form-select" id="selBoard">
					<option>공지사항</option>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1" class="form-label mt-4"></label>
				<input type="text" name="title" class="form-control" id="title1" value="${vo.r_title}">
				
			</div>
			<div class="form-group">
				<label for="exampleTextarea" class="form-label mt-4"></label>
				<textarea id="content" name="content"class="form-control" rows="6">${vo.r_content}</textarea>
			</div>
			<div class="form-group">
				<label for="formFile" class="form-label mt-4">파일 첨부</label>
				<input class="form-control" type="file" id="formFile" name="fileName">
			</div>
			<button id="sub1" type="submit" class="btn btn-dark">작성</button>
			<button id="btn1" type="button" class="btn btn-dark">목록</button>
		</fieldset>
	</form>
	</div>
</body>
<br><br><br>
<jsp:include page="../inc/footer.jsp"></jsp:include>
<link rel="stylesheet" href="css/footer.css">
</html>