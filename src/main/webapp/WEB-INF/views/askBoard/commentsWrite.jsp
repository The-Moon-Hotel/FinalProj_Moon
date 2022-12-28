<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('.subm').click(function(){
			if($('#c_content').val()==""){
				alert('답변을 등록해주세요');
				$('#c_content').focus();
				event.preventDefault();
				return false;
			}
		});
	});
</script>
<style>
	.divForm{
		width: 800px;
		margin-top: 30px;
		margin-left: 300px;
	}
</style>
<div class="divForm">
<form name="c_Write" method="post" action="/askBoard/commentWrite" >
<fieldset style="width: 500px; margin: 10;">
	
	<input type="hidden" name="askNo" value="${askNo}">
	<input type="hidden" name="userid" value="${userid}">
	
        <div >  
        	<label for="c_content"><b>관리자 문의답변 작성</b></label>
        	<br> <br>     
 			<textarea id="c_content" name="c_content" style="height: 200px; width:450px;font-size: 15px;border:1px solid #d1d1d1;"></textarea>
        </div>
        <br>
        <div class="center">
            <input type = "submit" class="subm" value="등록"/>
            <input type = "reset" value="지우기"/>        
        </div>
    </fieldset>
</form>
</div>  