askDelete_ok.jsp<%@page import="com.moon.askBoard.model.AskBoardVO"%>
<%@page import="com.moon.guest.model.GuestVO"%>
<%@page import="com.moon.askBoard.model.CommentDAO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.moon.askBoard.model.AskBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String no = request.getParameter("no");
	String askno = request.getParameter("askno");
	
	CommentDAO dao = new CommentDAO();
	try {
		int cnt = dao.deleteComment(Integer.parseInt(no));
		
		if (cnt > 0) {%>
			<script type="text/javascript">
				alert('삭제되었습니다.');
				location.href = "<%=request.getContextPath() %>/askBoard/askBoardList.jsp";
				<%-- askDetail.jsp?askno=<%=askno%>" --%>
			</script>
		<%}else {%>
			<script type="text/javascript">
				alert('삭제오류');
				history.back();
			</script>
		<%}

	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NumberFormatException e) {
		e.printStackTrace();
	}
	%>
</body>
</html>