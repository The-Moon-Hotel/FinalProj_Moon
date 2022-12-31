<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../inc/top.jsp"></jsp:include>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">

<style>
	p{
		font-size: 200%; 
		text-align: center; 
		margin-top:20px;
		font-weight: bold;	
	}
	th{
		font-size: 1.2em;
	}
	
/* 	#title{
		font-size: 250%;
		font-weight: bold;	
	} */
</style>

<div style="height: 800px;">
	<p id="title">예약 내역</p>
	
	<table class="table" style="width: 800px; margin:auto; text-align: center">
		<thead>
		<tr>
			<th>온라인 예약번호</th>
			<th>예약자 성명</th>
		</tr>
		</thead>
		<tr>
			<td>10336951</td>
			<td>홍길동</td>
		</tr>
		<tr>
			<th>연락처</th>
			<th>이메일</th>
		</tr>
		</thead>
		<tr>
			<td>010-1234-1234</td>
			<td>hong@naver.com</td>
		</tr>
	</table><br><br>
	<div style="width: 950px; margin:auto">
		<div id="reservHotelImg" style="float:left;">
			<img src="../images/sm_hotel1.jpg" style="width: 500px; float:left;">
		</div>
		<div id="reservInfo" style="float:left; margin-top:65px">
			<div id="reservInfo1" style="float:left; font-weight: bold;">
				<ui class="list-group list-group-flush">
					<li class="list-group-item">호텔 및 객실</li>
					<li class="list-group-item">투숙 날짜</li>
					<li class="list-group-item">객실 및 인원</li>
				</ui>
			</div>
			<div id="reservInfo2" style="float:left;">
				<ui class="list-group list-group-flush">
					<li class="list-group-item">더문호텔 1호점 디럭스</li>
					<li class="list-group-item">2022년 10월 01일 - 2022년 10월 02일</li>
					<li class="list-group-item">객실1, 성인2</li>
				</ui>
			</div>
		</div>
	</div><br>

</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>