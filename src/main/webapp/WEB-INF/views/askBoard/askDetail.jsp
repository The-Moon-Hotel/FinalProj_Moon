<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

textarea{
	font-size: 1em; 
	width:600px; 
	height: 400px;
	border: none;
	overflow: hidden;
}
</style>
<script type="text/javascript"src="/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#btnUpdate').click(function(){
			location.href="/askBoard/askEdit?askNo="+${vo.askNo};
		});
		$('#btnDelete').click(function(){
			if(!confirm('해당 문의글을 삭제하시겠습니까?')){
				event.preventDefault();
			}else{
				location.href="/askBoard/askDelete?askNo="+${vo.askNo};
			}
		});
		
		$('#btnlist').click(function(){
			location.href="/askBoard/askBoardList";
		});
		
		
	});
	
</script>
<body>
	<article>
		<div class="container" role="main">
		<br><br><br>
			<h2>문의내역</h2>
			<br><br>
			<div class="bg-white rounded shadow-sm">
				<div class="board_title">
				${vo.a_title }
				<!-- 클릭한 게시물 제목 보이기 -->
				<span class="board_date"><fmt:formatDate value="${vo.a_regdate }" pattern ="yyyy-MM-dd HH:mm:ss"/> </span><!-- 게시글 날짜 -->
				</div> 
				<br><br>
				<div class="board_info_box">
					<textarea class="board_author" readonly>${vo.a_content }</textarea><!-- 게시글 내용 -->
				</div>
				<br>
				<div class="board_content"></div>
				<div class="board_tag">
				</div>
			</div>
			<div style="margin-top: 20px">
				<br>
				<br>
				<c:if test="${guestNo==vo.guestNo }">
					<button type="button" class="btn btn-dark" id="btnUpdate">수정</button>
					<button type="button" class="btn btn-dark" id="btnDelete">삭제</button>
				</c:if>
				<button type="button" class="btn btn-dark" id="btnlist">목록</button>
				<br><br><br>
				
			</div>
		</div>
	</article>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>

<c:import url="/askBoard/commentsList?askNo=${vo.askNo }"></c:import>		
<c:if test="${g_vo.sys ne 1 }">
	<c:import url="/askBoard/commentsWrite?askNo=${vo.askNo }"></c:import>		
</c:if>	
<br><br><br>
</body>

<jsp:include page="../inc/footer.jsp"></jsp:include>
</html>