<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message.jsp</title>
</head>
<body>
	<%
		String msg=(String)request.getAttribute("msg");
		String url=(String)request.getAttribute("url");
	%>
	
	<script type="text/javascript">
	<c:if test="${!empty msg}">
		alert("<%=msg%>");
	</c:if>
		location.href="<%=url%>";
	</script>
</body>
</html>