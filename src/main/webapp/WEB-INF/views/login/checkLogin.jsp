<%@page import="javax.script.ScriptContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkLogin.jsp</title>
</head>
<body>
<c:if test="${empty l_userid }">
	<script type="text/javascript">
		alert('먼저 로그인하세요');
		location.href
		="/login/login";
	</script>
</c:if>

</body>
</html>