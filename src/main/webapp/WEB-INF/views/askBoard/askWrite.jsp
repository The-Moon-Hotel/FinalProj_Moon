<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/inc/top"></c:import>
<%-- <%@ include file = "../login/checkLogin.jsp" %> --%>

<style type="text/css">
#div1 {
	width: 900px;
}

#formFile {
	width: 400px;
}

#btn1 {
	clear: none;
}

textarea{
	font-size: 1em; 
	width:600px; 
	height: 200px;
	border: none;
	overflow: hidden;
}
</style>
<script type="text/javascript"
	src="/js/smartEdit/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">

	$(function() {
		$('#btn1').click(function() {
			location.href = "/askBoard/askBoardList";
		});
	});
	
	$(function(){
		$('form[name=askWrite]').submit(function(){
			$('.form-control').each(function(index, item){
				if($(this).val()=""){
					alert('작성이 완료되지 않았습니다.');
					$(this).focus();
					event.preventDefault();
					return false;
				}	
			});
		});
	});
</script>

<br>
<br>
<br>
<div id="div1" class="container text-left">
	<form name="askWrite" enctype="multipart/form-data" method="post" action="/askBoard/askWrite">
		<fieldset>
			<legend>문의하기</legend>
			<div class="form-group">
				<label for="exampleInputEmail1" class="form-label mt-4"></label> 
				<input type="text" class="form-control" id="title1" name="a_title"
					placeholder="제목을 입력하세요">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1" class="form-label mt-4">파일첨부</label> 
				<input type="file" class="form-control" id="fileName" name="fileName">
			</div>
			<div class="form-group">
				<label for="exampleTextarea" class="form-label mt-4"></label>
				<textarea id="txtCon" class="form-control" rows="6" name="a_content"
					 ></textarea>
			</div>
			<br><br>
			<input type="hidden" class="form-control" id="userid" name="userid" value="${userid}">
			<button id="sub1" type="submit" class="btn btn-dark">작성</button>
			<button id="btn1" type="button" class="btn btn-dark">목록</button>
		</fieldset>
	</form>
</div>

<br>
<br>
<br>
<jsp:include page="../inc/footer.jsp"></jsp:include>