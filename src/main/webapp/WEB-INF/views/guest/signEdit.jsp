<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/inc/top"></c:import>
<link rel="stylesheet" type="text/css" href="/css/login.css">
<script type="text/javascript" src="/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#pwd').focus();
	
	$('#tel1_val').change(function(){
		var str=$('#tel1_val > option:selected').val();
		$('#tel1').val(str); 
	});
	
	$('#wr_submit').click(function(){
		if($('#pwd').val().length<1){
			alert('비밀번호를 입력하세요');
			$('#pwd').focus();
			event.preventDefault();
		}else if($('#pwd').val()!=$('#pwd2').val()){
			alert('비밀번호가 일치하지 않습니다.');
			$('#pwd2').focus();
			event.preventDefault();
		}else if(!validate_phone($('#hp2').val())
				|| !validate_phone($('#hp3').val())){
			alert('전화번호는 숫자만 가능합니다.');
			$('#hp2').focus();
			event.preventDefault();
		}
	});
   
    
    
 });
 
function vaildate_phone(tel){
	var pattern new EegExp(/^[0-9]*$/g);
	return pattern.test(tel);
}
</script>
<section class="loginBanner">
	<article>
		<div>
			<img alt="" src="../images/signEditBanner.png" >
		</div>
	</article>
</section>
<div class="container text-center loginTitle">
		<h1>회원정보수정</h1>
</div>
<section class="content" >
	<article>
			<div class="input-form container input-form-backgroud row" style=" margin: 0 auto; width:800px;">
			<br><br><br>
				<form class="validation-form container" novalidate method="post" action="/guest/signEdit">
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">이름</label>
							<input type="text" class="form-control" id="name" name="name" value="${guestVO.name}" disabled>
						</div>
						<div class="col-md-6 mb-3">
							<label for="userid">ID</label>
							<input type="text" class="form-control" id="userid" name="userid" value="${userid}" disabled>
						</div>
						<div class="col-md-6 mb-3">
							<label for="pwd">비밀번호</label>
							<input type="password" class="form-control" id="pwd" name="pwd" value="" >
						</div>
						<div class="col-md-6 mb-3">
							<label for="pwd2">비밀번호 확인</label>
							<input type="password" class="form-control" id="pwd2" name="pwd2" value="" >
						</div>
						<p style="margin-bottom: 1px">이메일</p>
						<div class="input-group mb-3">
						  <input type="text" class="form-control" id="email1" name="email1" aria-label="TheMoonHotel" value="${email1}">
						  <span class="input-group-text">@</span>
						  <input type="text" class="form-control" id="email2" name="email2" aria-label="naver.com"  value="${email2}">
						</div>
					</div>
					     	<label for="tel1">전화번호</label> <br>
					<div class="container " style="height: 30px">
					  <div class="row">
					    <div class="col" style="padding:0">
					    	<input type="hidden" id="tel1" name="te1">
					      	<select class="form-select"
								aria-label="Default select example" id="tel1_val" >
								<option value="010"
									<c:if test="${tel1 eq '010' }">
										selected="selected"
									</c:if>
									>010</option>
								<option value="011"
									<c:if test="${tel1 eq '011' }">
										selected="selected"
									</c:if>
									>011</option>
								<option value="016"
									<c:if test="${tel1 eq '016' }">
										selected="selected"
									</c:if>
									>016</option>
								<option value="017"
									<c:if test="${tel1 eq '017' }">
										selected="selected"
									</c:if>
									>017</option>
								<option value="018"
									<c:if test="${tel1 eq '018' }">
										selected="selected"
									</c:if>
									>018</option>
								<option value="019"
									<c:if test="${tel1 eq '019' }">
										selected="selected"
									</c:if>
									>019</option>
							</select>
					    </div>
					    <div class="" style="padding:5px;text-align: center;width: 20px;">
					      -
					    </div>
					    <div class="col" style="padding:0">
					      <input type="text" class="form-control" style="width: auto%" id="tel2" name="tel2" maxlength="4" title="휴대폰 가운데자리"value="${tel2}">
					    </div>
					    <div class="" style="padding:5px;text-align: center;width: 20px;">
					      -
					    </div>
					    <div class="col" style="padding:0">
					      <input type="text" class="form-control" id="tel3" name="tel3" maxlength="4" title="휴대폰 뒷자리" value="${tel3}">
					    </div>
					  </div>
					</div>
					<br>
					<button id="sub1" class="btn btn-dark btn-block editChk"  type="submit">회원정보수정</button>
				</form>
			</div>
	</article>
</section>
<jsp:include page="../inc/footer.jsp"></jsp:include>