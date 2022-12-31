
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>checkUserid.jsp</title>
    
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script type="text/javascript" src="/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#btUse').click(function(){
			<%-- 
			opener.document.frm1.userid.value = "<%=userid%>"; 
			--%>
			$(opener.document).find('#userid').val("${userid}");
			$(opener.document).find('#chkId').val("Y");
			self.close();
		});
	});
</script>
<style type="text/css">
h2{
	font-weight: bold;
}
#userid {
display: inline-block;
width: 200px;	
}
#div1{
	width: 410px;
	margin: 0 auto;
	padding-top: 50px; 

}
#btUse{
	width: 100%;
}
#div2{
	margin: 5px;
	text-align: center;
}
#userid{
	width: 215px
}
#p1{
margin: 5px 0;
color: red;
}
#p2{
margin: 5px 0;
color: #0d6efd;
}
</style>

</head>
<body>
<div id="div1">
    <h2>아이디 중복 검사</h2>
 	<form name="frmUserid" method="post" action="/guest/checkUserid">
 		<label for="userid">아이디 : </label>
        <input type="text" name="userid" id="userid" class="form-control" value="${userid}">
        <input type="submit" value="아이디 확인" class="btn btn-secondary">
        <c:if test="${result==EXIST_ID}">
	        <p id="p1">이미 등록된 아이디입니다. 다른 아이디를 입력하세요</p>
        </c:if>
        <c:if test="${result==NOT_EXIST_ID}">
        	<p id="p2">사용가능한 아이디입니다. [사용하기] 버튼을 클릭 하세요</p>
        	<div id="div2">
		        <input type="button" value="사용하기"  id="btUse" class="btn btn-primary">
        	</div>
        </c:if>
	</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>