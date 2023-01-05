
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/inc/top"></c:import>
<style>
	p{
		font-size: 120%; 
		text-align: center; 
		margin-top:100px;
	}	
	#reservList, #reservDelete{
		margin-top: 20px;
		display: inline-block;
	}
</style>
<script type="text/javascript">
	$(function(){
		$('#reservDelete').click(function(){
			if(!confirm("예약을 취소하시겠습니까?")){
				event.preventDefault();
			}
		});
	});
</script>
<div style="height:700px; width: 800px; margin: auto;">
	<p>예약 세부 내역</p>
	<div style="width: 800px; margin: auto;">
		<ul class="list-group" style="float:left; width: 35%; text-align: right; font-weight: bold;">
		  <li class="list-group-item">예약번호</li>
		  <li class="list-group-item">이름</li>
		  <li class="list-group-item">회원번호</li>
		  <li class="list-group-item">아이디</li>
		  <li class="list-group-item">이메일</li>
		  <li class="list-group-item">전화번호</li>
		  <li class="list-group-item">예약날짜</li>
		  <li class="list-group-item">지점정보</li>
		  <li class="list-group-item">객실타입</li>
		  <li class="list-group-item">이용객 수</li>
		  <li class="list-group-item">가격</li>
		</ul>
		<ul class="list-group" style="float:left; width: 65%">
		  <li class="list-group-item">${reservNo }</li>
		  <li class="list-group-item">${guestVo.name }</li>
		  <li class="list-group-item">${reservVo.guestNo }</li>
		  <li class="list-group-item">${guestVo.userid }</li>
		  <li class="list-group-item">${guestVo.email }</li>
		  <li class="list-group-item">${guestVo.tel }</li>
		  <li class="list-group-item">${reservVo.ci_date } - ${reservVo.co_date }</li>
		  <li class="list-group-item">${roomVo.locName }</li>
		  <li class="list-group-item">${roomVo.roomType }</li>
		  <li class="list-group-item">성인: ${reservVo.adult } / 아동: ${reservVo.kids }</li>
		  <li class="list-group-item"><fmt:formatNumber value="${reservVo.totalPrice }" pattern="#,###"/></li>
		</ul>
	</div>
	<div align="center">
		<a class="btn btn-secondary" role="button" id="reservList"
			 href="/admin/reservList">예약 목록</a>
		<a class="btn btn-secondary" role="button" id="reservDelete"
			 href="/admin/reservDelete?reservNo=${reservNo}">예약 취소</a>
	</div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>