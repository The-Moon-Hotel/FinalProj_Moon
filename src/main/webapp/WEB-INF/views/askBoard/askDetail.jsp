<%@page import="com.moon.askBoard.model.AskboardService"%>
<%@page import="com.moon.guest.model.GuestVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.moon.askBoard.model.AskBoardVO"%>
<%@page import="com.moon.askBoard.model.AskBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:include page="../inc/top.jsp"></jsp:include>
<%-- <%@ include file = "../login/checkLogin.jsp" %> --%>

<jsp:useBean id="guestSerivce" class="com.moon.guest.model.GuestSerivce" scope="session"></jsp:useBean>
<jsp:useBean id="guestVO" class="com.moon.guest.model.GuestVO" scope="page"></jsp:useBean>



<%
	String userid=(String)session.getAttribute("userid");
	GuestVO g_vo = guestSerivce.selectByUserid(userid);


	String askno = request.getParameter("askno");

	if (askno == null || askno.isEmpty()) {%>
	<script type="text/javascript">
		alert('잘못된 url입니다');
		location.href = "askBoardList.jsp";
	</script>
	<%
		return;
	}
	//2.
	AskboardService dao = new AskboardService();
	AskBoardVO vo = null;

	try {
		vo = dao.selectByAskNo(Integer.parseInt(askno));

	} catch (SQLException e) {
		e.printStackTrace();
	}
	//3.
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
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
<script type="text/javascript"src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#btnUpdate').click(function(){
			location.href="<%=request.getContextPath() %>/askBoard/askEdit.jsp?askno=<%=askno %>";
		});
		$('#btnDelete').click(function(){
			if(!confirm('해당 문의글을 삭제하시겠습니까?')){
				event.preventDefault();
			}else{
				location.href="<%=request.getContextPath() %>/askBoard/askDelete_ok.jsp?askno=<%=askno %>";
			}
		});
		
		$('#btnlist').click(function(){
			location.href="<%=request.getContextPath() %>/askBoard/askBoardList.jsp";
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
				<%=vo.getA_title() %>
				<!-- 클릭한 게시물 제목 보이기 -->
				<span class="board_date"><%=sdf.format(vo.getA_regdate()) %> </span><!-- 게시글 날짜 -->
				</div> 
				<br><br>
				<div class="board_info_box">
					<textarea class="board_author" readonly><%=vo.getA_content() %></textarea><!-- 게시글 내용 -->
				</div>
				<br>
				<div class="board_content"></div>
				<div class="board_tag">
				</div>
			</div>
			<div style="margin-top: 20px">
				<br>
				<br>
				
				<button type="button" class="btn btn-dark" id="btnUpdate">수정</button>
				<button type="button" class="btn btn-dark" id="btnDelete">삭제</button>
				<button type="button" class="btn btn-dark" id="btnlist">목록</button>
				<br><br><br>
				
			</div>
		</div>
	</article>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
		
<jsp:include page="commentsList.jsp?askno=<%=askno %>"></jsp:include>
<%if(g_vo.getSys() != 1){ %>
	<jsp:include page="commentsWrite.jsp?askno=<%=askno %>"></jsp:include>
<%} %>
<br><br><br>
</body>

<jsp:include page="../inc/footer.jsp"></jsp:include>
</html>