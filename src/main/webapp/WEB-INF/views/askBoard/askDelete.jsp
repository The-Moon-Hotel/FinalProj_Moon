<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }
	.divForm{
		width:650px;
		border:1px solid gray;		
	}
	
	.divForm div	{
		border:none;
		padding: 7px 0;
		margin: 0;
		overflow: auto;
	}	
	
	.sp{
		font-size:0.9em;		
	}
	
	.divForm fieldset	{
		border:0;
	}
</style>
<script type="text/javascript" src = "../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">

	$(function(){
		$('form[name=frmDelete]').submit(function(){
			if($('#pwd').val().length<1){
				alert('비밀번호를 입력하세요');
				$('#pwd').focus();
				return false;
			}else if($('#pwd').val()!=${vo.pwd} ){
				alert('비밀번호를 확인하세요');
				$('#pwd').focus();
				return false;
			}else if(!confirm('삭제하시겠습니까?')){
				event.preventDefault();
			}
		});
		
	});
</script>
</head>
<body>

<div class="divForm">
<form name="frmDelete" method="post"	action="/askBoard/delete" >
		<!-- 삭제시 no가 필요하므로 hidden 필드에 넣는다 -->
		<input type="hidden" name="askno" value="${askNo}">
		<fieldset>
		<legend>글 삭제</legend>
	        <div>           
	        	<span class="sp">해당 문의글을 삭제하시겠습니까?</span>                        
	        </div>
	        <div>           
	            <label for="pwd">비밀번호</label>
	            <input type="password" id="pwd" name="pwd"/>   
	        </div>
	        <div class="center">
	            <input type ="submit"  value="삭제" />
	            <a href="askBoardList.jsp">
	            	<input type = "button" value="글목록"" />
	            </a>
	        </div>
	    </fieldset>
    </form>
</div>

</body>
</html>
</head>